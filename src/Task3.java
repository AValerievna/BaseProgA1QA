import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

class Task3 {
    private static final int defNum = 10;
    private static final String regexp1 = "\\.([^.]+)$";
    private static final String regexp2 = "_res.$1";

    static void task3() {
        Scanner in = new Scanner(System.in);
        System.out.println("Input a file path:");
        String path = in.nextLine();
        System.out.println("Which version would you like to see? Choose the number");
        System.out.println("  1)Only path");
        System.out.println("  2)With number of string");
        String res = null;
        switch(in.nextInt()) {
            case 1:
                res = selectCases(path);
                break;
            case 2:
                System.out.println("Input a number of strings:");
                int num = in.nextInt();
                res = selectCases(path,num);
                break;
            default: System.out.println("Please, choose the number from the list");
                break;
        }
        System.out.println(res);
    }

    private static String selectCases(String str, int num) {
        Path path = Paths.get(str);
        File sourceFile = new File(str);
        String resStr = str.replaceAll(regexp1, regexp2);
        File resFile = new File(resStr);
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter fr1 = new FileWriter(sourceFile); FileWriter fr2 = new FileWriter(resFile)) {
            int linesCount = 0;
            if (allLines != null) {
                linesCount = allLines.size();
            }
            int numLimit;
            if (linesCount>num) {
                numLimit=num;
            } else {
                numLimit=linesCount-1;
            }
            Set<Integer> linesToRemove = new Random().ints(1, linesCount).distinct().limit(numLimit).boxed().collect(Collectors.toSet());

            if (allLines != null) {
                fr1.write(allLines.get(0)+"\n");
                fr2.write(allLines.get(0)+"\n");
                for(int i=1;i<linesCount;i++) {

                    if(linesToRemove.contains(i)) {
                        fr2.write(allLines.get(i)+"\n");
                    } else {
                        fr1.write(allLines.get(i)+"\n");
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
       return resStr;
    }

    private static String selectCases(String path) {
        return selectCases(path,defNum);
    }
}
