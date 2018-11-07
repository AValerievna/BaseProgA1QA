import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

class Task3 {
    private static final int DEF_NUM = 10;
    private static final String REGEXP_1 = "\\.([^.]+)$";
    private static final String REGEXP_2 = "_res.$1";

    static void cutRandomStringsFromOneFileToOther() {
        Scanner in = new Scanner(System.in);
        System.out.println("Input a file path:");
        String path = in.nextLine();
        System.out.println("Which version would you like to see? Choose the number");
        System.out.println("  1)Only path");
        System.out.println("  2)With number of string");
        String res = null;
        switch (in.nextInt()) {
            case 1:
                res = selectCases(path);
                break;
            case 2:
                System.out.println("Input a number of strings:");
                int num = in.nextInt();
                if (num < 0) {
                    System.out.println("Please, input natural number");
                    break;
                }
                res = selectCases(path, num);
                break;
            default:
                System.out.println("Please, choose the number from the list");
                break;
        }
        System.out.println(res);
    }

    private static String selectCases(String str, int num) {
        Path path = Paths.get(str);
        File sourceFile = new File(str);
        String resStr = str.replaceAll(REGEXP_1, REGEXP_2);
        File resFile = new File(resStr);
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int linesCount = 0;
        if (allLines != null) {
            linesCount = allLines.size();
        }
        Set<Integer> linesToRemove = generateRandomStringIndexes(num, linesCount);

        ArrayList<String> lines1 = new ArrayList<>();
        ArrayList<String> lines2 = new ArrayList<>();
        if (allLines != null) {

            lines1.add(allLines.get(0));
            lines2.add(allLines.get(0));
            for (int i = 1; i < linesCount; i++) {

                if (linesToRemove.contains(i)) {
                    lines2.add(allLines.get(i));
                } else {
                    lines1.add(allLines.get(i));
                }
            }
        }
        writeListToFile(sourceFile, lines1);
        writeListToFile(resFile, lines2);

        return resStr;
    }

    private static void writeListToFile(File sourceFile, ArrayList<String> lines) {
        try (FileWriter fw = new FileWriter(sourceFile)) {
            for (String line : lines) {
                fw.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Set<Integer> generateRandomStringIndexes(int num, int linesCount) {
        int numLimit;
        if (linesCount > num) {
            numLimit = num;
        } else {
            numLimit = linesCount - 1;
        }
        return new Random().ints(1, linesCount).distinct().limit(numLimit).boxed().collect(Collectors.toSet());
    }

    private static String selectCases(String path) {
        return selectCases(path, DEF_NUM);
    }
}
