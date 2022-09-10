package zipper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class ZipDirectoryTest {
    static String directoryToZipAbsolutePath = "C:\\Projects\\zip\\src\\main\\resources\\dir";

    @Test
    void testSetFilesAbsolutePathsInDirectory() {
        ZipDirectory.setFilesInDirectoryAbsolutePaths(ZipDirectoryTest.directoryToZipAbsolutePath);
        List<String> filesAbsolutePathsInDirectory = ZipDirectory.getFilesInDirectoryAbsolutePaths();

        assertInstanceOf(List.class, filesAbsolutePathsInDirectory);
        assertEquals(4, filesAbsolutePathsInDirectory.size());
    }

    @Test
    void testZip() throws IOException {
        String zippedDirectoryAbsolutePath =  ZipDirectory.zip(ZipDirectoryTest.directoryToZipAbsolutePath);

        assertInstanceOf(String.class, zippedDirectoryAbsolutePath);
        assertEquals(ZipDirectoryTest.directoryToZipAbsolutePath + ".zip", zippedDirectoryAbsolutePath);
    }
}
