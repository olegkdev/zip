package zipper;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnzipTest {
    static String zippedSourceAbsolutePath = "C:\\Projects\\zip\\src\\main\\resources\\dir.zip";
    static String unzipDestinationAbsolutePath = "C:\\Projects\\zip\\src\\main\\resources\\dirUnzipped";

    @Test
    void testUnzip() throws IOException {
        String returnedUnzipDestinationAbsolutePath = Unzip.unzip(UnzipTest.zippedSourceAbsolutePath,
                UnzipTest.unzipDestinationAbsolutePath);

        assertEquals(UnzipTest.unzipDestinationAbsolutePath, returnedUnzipDestinationAbsolutePath);
    }
}
