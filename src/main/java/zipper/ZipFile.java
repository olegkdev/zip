package zipper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFile {
    private static final int BUFFER = 1024;
    public static String zip(String fileToZipAbsolutePath) throws IOException {
        File fileToZip = new File(fileToZipAbsolutePath);
        FileInputStream fileInputStream = new FileInputStream(fileToZip);

        String zipDestinationFileName = fileToZip.getName().split("\\.")[0] + ".zip";
        String zipDestinationAbsoluteFilePath = fileToZip.getParent() + "\\" + zipDestinationFileName;
        FileOutputStream fileOutputStream = new FileOutputStream(zipDestinationAbsoluteFilePath);
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zipOutputStream.putNextEntry(zipEntry);

        byte[] buffer = new byte[ZipFile.BUFFER];
        int length;
        while ((length = fileInputStream.read(buffer)) != -1) zipOutputStream.write(buffer, 0, length);

        zipOutputStream.closeEntry();
        zipOutputStream.close();
        fileInputStream.close();
        fileOutputStream.close();

        return zipDestinationAbsoluteFilePath;
    }
}
