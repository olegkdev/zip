package zipper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipDirectory {
    private static final int BUFFER = 1024;
    private static final List<String> filesInDirectoryAbsolutePaths = new ArrayList<>();

    public static String zip(String directoryToZipAbsolutePath) throws IOException {
        ZipDirectory.setFilesInDirectoryAbsolutePaths(directoryToZipAbsolutePath);

        File directory = new File(directoryToZipAbsolutePath);
        String zipDestination = directory.getAbsolutePath() + ".zip";

        FileOutputStream fileOutputStream = new FileOutputStream(zipDestination);
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

        for (String fileInDirectoryAbsolutePath : ZipDirectory.filesInDirectoryAbsolutePaths) {
            ZipEntry zipEntry =
                    new ZipEntry(fileInDirectoryAbsolutePath.substring(directoryToZipAbsolutePath.length() + 1,
                            fileInDirectoryAbsolutePath.length())); // "filename.ext" or "subdir/filename.ext"
            zipOutputStream.putNextEntry(zipEntry);

            FileInputStream fileInputStream = new FileInputStream(fileInDirectoryAbsolutePath);
            byte[] buffer = new byte[ZipDirectory.BUFFER];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) zipOutputStream.write(buffer, 0, length);
            zipOutputStream.closeEntry();
            fileInputStream.close();
        }

        zipOutputStream.close();
        fileOutputStream.close();

        return zipDestination;
    }

    public static void setFilesInDirectoryAbsolutePaths(String directoryAbsolutePath) {
        File directory = new File(directoryAbsolutePath);
        File[] filesInDirectory = directory.listFiles();

        for (File fileInDirectory : filesInDirectory) {
            if (fileInDirectory.isFile())
                ZipDirectory.filesInDirectoryAbsolutePaths.add(fileInDirectory.getAbsolutePath());
            else setFilesInDirectoryAbsolutePaths(fileInDirectory.getAbsolutePath()); // recursive call
        }
    }

    public static List<String> getFilesInDirectoryAbsolutePaths() {
        return ZipDirectory.filesInDirectoryAbsolutePaths;
    }

}
