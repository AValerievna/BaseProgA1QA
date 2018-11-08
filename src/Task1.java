import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class Task1 {
    private static final int SEC_DIFF = 10000;
    private static final String STR_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
    private String path;
    private String ext;
    private File dir;

    public Task1(String path, String ext) {
        this.path = path;
        dir = new File(path);
        if (!dir.exists()) {
            System.out.println("This directory does not exist: " + path);
            return;
        }
        this.ext = ext;
    }

    private class ExtFilenameFilter implements FilenameFilter {

        private String ext;

        ExtFilenameFilter(String ext) {
            this.ext = ext;
        }

        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(ext);
        }
    }

     void findMostFreshFilesWithExtension() {

        File[] listFiles = dir.listFiles(new ExtFilenameFilter(ext));
        Map<String, Long> filesMap = new HashMap<>();
        Date date = new Date();
        long cur = date.getTime();
        long min = cur;

        if (listFiles != null) {
            if (listFiles.length == 0) {
                System.out.println("There is no files with the extension " + ext + " in directory " + path);
            } else {
                min = getMinAndFilesMap(listFiles, filesMap, cur, min);
                outputTheMostFreshFiles(filesMap, cur, min);

            }
        } else {
            System.out.println("Files list is unexpectedly null");
        }

    }

    private void outputTheMostFreshFiles(Map<String, Long> filesMap, long cur, long min) {
        for (Map.Entry<String, Long> entry : filesMap.entrySet()) {
            long time = entry.getValue();
            if (cur - time <= min + SEC_DIFF) {
                System.out.println("File: " + entry.getKey()
                +"; creation time: "
                +new SimpleDateFormat(STR_DATE_FORMAT).format(time));
            }
        }
    }

    private long getMinAndFilesMap(File[] listFiles, Map<String, Long> filesMap, long cur, long min) {
        BasicFileAttributes attr;
        for (File f : listFiles) {
            try {
                attr = Files.readAttributes(f.toPath(), BasicFileAttributes.class);
                filesMap.put(f.toString(), attr.creationTime().toMillis());
                long diff = cur - attr.creationTime().toMillis();
                if (diff < min) {
                    min = diff;
                }
            } catch (IOException e) {
                System.out.println("Exception handled when trying to get file " + f + " attributes: ");
                e.printStackTrace();
            }
        }
        return min;
    }
}
