import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

class Task3 {
    private static final int DEF_NUM = 10;
    private static final String REGEXP_1 = "\\.([^.]+)$";
    private String regexp2;

    public Task3(String resEnd) {
        regexp2 = resEnd + ".$1";
    }

    public Task3() {
        this("_res");
    }

    String selectCases(String str, int num) {
        Path path = Paths.get(str);
        File sourceFile = new File(str);
        String resStr = str.replaceAll(REGEXP_1, regexp2);
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

    private void writeListToFile(File sourceFile, ArrayList<String> lines) {
        try (FileWriter fw = new FileWriter(sourceFile)) {
            for (String line : lines) {
                fw.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Set<Integer> generateRandomStringIndexes(int num, int linesCount) {
        int numLimit;
        if (linesCount > num) {
            numLimit = num;
        } else {
            numLimit = linesCount - 1;
        }
        return new Random().ints(1, linesCount).distinct().limit(numLimit).boxed().collect(Collectors.toSet());
    }

    String selectCases(String path) {
        return selectCases(path, DEF_NUM);
    }
}
