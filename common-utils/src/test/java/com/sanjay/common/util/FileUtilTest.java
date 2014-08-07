/**
 * 
 */
package com.sanjay.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author sanjay.madnani
 * 
 */
public class FileUtilTest {

    private File inputFile;
    private File outputFile;

    private String inputFileDir;
    private String inputFileName;
    private String outputFileDir;
    private String outputFileName;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        inputFileDir = "src/test/resources/FileOperation";
        inputFileName = "licence.txt";
        outputFileDir = "src/test/resources/FileOperation";
        outputFileName = "Zlicence.txt.gz";
        inputFile = new File(inputFileDir, inputFileName);
        outputFile = new File(outputFileDir, outputFileName);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        inputFile = null;
        outputFile = null;
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#getFileSize(java.io.File)}.
     */
    @Test
    public final void testGetFileSize() {
        String fileSize = FileUtil.getFileSize(inputFile);
        if (fileSize != null)
            assertEquals("17 KB", fileSize);
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#transferFile(java.io.File)}.
     */
    // @Test
    public final void testTransferFile() {
        // TODO FTP Server required to test file transfer functionality.
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#compressToGzipFormat(java.io.File, java.io.File)}.
     */
    @Test
    public final void testCompressToGzipFormat() throws Exception {
        final File file = FileUtil.compressToGzipFormat(inputFile, outputFileDir);
        assertEquals(inputFileName + ".gz", file.getName());
        assertEquals(outputFile.getParent(), file.getParent());
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#decompressGzipFile(java.io.File, java.io.File)}.
     */
    // @Test
    public final void testDecompressGzipFile() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#deleteFile(java.io.File)}.
     */
    @Test
    public final void testDeleteFile() {
        File file = new File(outputFileDir, inputFileName + ".gz");
        if (file.exists()) {
            FileUtil.deleteFile(file);
            System.out.println(file.getName() + " deleted successfully");
        }
    }

}
