package zipper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzip {
    private static int BUFFER = 1024;

    public static String unzip(String zippedSourceAbsolutePath, String unzipDestinationAbsolutePath) throws IOException {
        File unzipDestinationDirectory = new File(unzipDestinationAbsolutePath);
        if (!unzipDestinationDirectory.exists()) unzipDestinationDirectory.mkdirs();

        FileInputStream fileInputStream = new FileInputStream(zippedSourceAbsolutePath);
        ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
        ZipEntry zipEntry = zipInputStream.getNextEntry();

        byte[] buffer = new byte[Unzip.BUFFER];
        while (zipEntry != null) {
            String fileName = zipEntry.getName();
            File newFile = new File(unzipDestinationAbsolutePath + File.separator + fileName);
            new File(newFile.getParent()).mkdirs(); // create subdirectories

            FileOutputStream fileOutputStream = new FileOutputStream(newFile);
            int length;
            while ((length = zipInputStream.read(buffer)) != -1) fileOutputStream.write(buffer, 0, length);
            fileOutputStream.close();
            zipInputStream.closeEntry();
            zipEntry = zipInputStream.getNextEntry();
        }

        zipInputStream.closeEntry();
        zipInputStream.close();
        fileInputStream.close();

        return unzipDestinationAbsolutePath;
    }

}
