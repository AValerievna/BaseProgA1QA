import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task1 {

    private static class ExtFilenameFilter implements FilenameFilter {

        private String ext;

        public ExtFilenameFilter(String ext){
            this.ext = ext;
        }
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(ext);
        }
    }

    static void task1() {


        Scanner in = new Scanner(System.in);
        System.out.println("Input a directory path:");
        String path = in.nextLine();
        File dir = new File(path);
        if(!dir.exists()){
            System.out.println("This directory does not exist: "+path);
            return;
        }
        System.out.println("Input a file extension:");
        String ext = in.nextLine();
        File[] listFiles = dir.listFiles(new ExtFilenameFilter(ext));
        Map<String,Long> filesMap = new HashMap<>();
        Date date = new Date();
        long cur = date.getTime();
        long min = cur;
        if(listFiles.length == 0) {
            System.out.println("There is no files with the extension "+ ext+" in directory "+path);
            return;
        } else {
            BasicFileAttributes attr;
            for(File f : listFiles) {
                try {
                    attr = Files.readAttributes(f.toPath(), BasicFileAttributes.class);
                    filesMap.put(f.toString(),attr.creationTime().toMillis());
                    long diff = cur-attr.creationTime().toMillis();
                    if(diff < min) {
                        min=diff;
                    }
                } catch (IOException e) {
                    System.out.println("Exception handled when trying to get file " +f + " attributes: " + e.getMessage());

                }
            }
            for(Map.Entry<String,Long> entry : filesMap.entrySet()) {
                if(cur-entry.getValue()<=min+10) {
                    System.out.println("File: "+entry.getKey());
                }
            }

        }

    }
}
