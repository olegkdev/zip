package zipper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class ZipFileTest {
    static String fileAbsolutePath = "C:\\Projects\\zip\\src\\main\\resources\\file.txt";
    static String zipDestinationAbsolutePath = "C:\\Projects\\zip\\src\\main\\resources\\file.zip";

    @Test
    void testZipFile() throws IOException {
        String zippedAbsoluteFilePath = ZipFile.zip(ZipFileTest.fileAbsolutePath);

        assertInstanceOf(String.class, zippedAbsoluteFilePath);
        assertEquals(zipDestinationAbsolutePath, zippedAbsoluteFilePath);
    }
}
