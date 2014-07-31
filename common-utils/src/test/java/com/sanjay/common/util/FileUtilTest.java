/**
 * 
 */
package com.sanjay.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        inputFile = new File("src/test/resources", "licence.txt");
        outputFile = new File("src/test/resources", "Zlicence.txt.gz");
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
        assertEquals("17 KB", FileUtil.getFileSize(inputFile));
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#transferFile(java.io.File)}.
     */
    // @Test
    public final void testTransferFile() {
        // FTP Server required to test file transfer functionality.
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#compressToGzipFormat(java.io.File, java.io.File)}.
     */
    @Test
    public final void testCompressToGzipFormat() {
        FileUtil.compressToGzipFormat(inputFile, outputFile);
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#decompressGzipFile(java.io.File, java.io.File)}.
     */
    @Test
    public final void testDecompressGzipFile() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#archiveFile(java.io.File, java.lang.String)}.
     * 
     * @throws Exception
     */
    @Test
    public final void testArchiveFile() throws Exception {
        System.out.println(outputFile.getParent());
        FileUtil.archiveFile(inputFile, outputFile.getParent());
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#deleteFile(java.io.File)}.
     */
    @Test
    public final void testDeleteFile() {
        fail("Not yet implemented");
    }

}
