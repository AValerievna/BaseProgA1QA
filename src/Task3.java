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
        String resStr = str.replaceAll("\\.([^\\.]+)$", "_res.$1");
        File resFile = new File(resStr);
        FileWriter fr1 = null;
        FileWriter fr2 = null;

        try {

            List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
            int linesCount = allLines.size();
            int numLimit;
            if (linesCount>num) {
                numLimit=num;
            } else {
                numLimit=linesCount-1;
            }
            Set<Integer> linesToRemove = new Random().ints(1, linesCount).distinct().limit(numLimit).boxed().collect(Collectors.toSet());

            fr1 = new FileWriter(sourceFile);
            fr2 = new FileWriter(resFile);

            fr1.write(allLines.get(0)+"\n");
            fr2.write(allLines.get(0)+"\n");
            for(int i=1;i<linesCount;i++) {

                if(linesToRemove.contains(i)) {
                    fr2.write(allLines.get(i)+"\n");
                } else {
                    fr1.write(allLines.get(i)+"\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr1 != null) {
                    fr1.close();
                }
                if (fr2 != null) {
                    fr2.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       return str;
    }

    private static String selectCases(String path) {
        return selectCases(path,defNum);
    }
}
