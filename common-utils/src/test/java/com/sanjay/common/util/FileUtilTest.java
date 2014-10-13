/* Copyright (C) 2014, 2015 Sanjay Madnani
 * 
 * This file is free to use: you can redistribute it and/or modify it under the terms of the GPL General Public License
 * V2 as published by the Free Software Foundation, subject to the following conditions:
 * 
 * The above copyright notice should never be changed and should always included wherever this file is used.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY.
 * See the GNU General Public License V2 for more details. */
package com.sanjay.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sanjay.common.enumeration.FileTransferProtocol;
import com.sanjay.common.exception.ApplicationException;

/**
 * Test case for {@link FileUtil}
 * 
 * @author sanjay.madnani
 * 
 */
public class FileUtilTest {
    private static final Logger LOGGER = LogManager.getLogger(FileUtilTest.class);
    private File inputFile;
    private File outputFile;
    private File inputZipFile;
    private File outputZipFile;
    private String inputFileDir;
    private String outputFileDir;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        inputFileDir = "src/test/resources/FileOperation/InputFile";
        outputFileDir = "src/test/resources/FileOperation/OutputFile";
        inputFile = new File(inputFileDir, "licence.txt");
        outputFile = new File(outputFileDir, "licence.txt");
        inputZipFile = new File(inputFileDir, "licence.txt.gz");
        outputZipFile = new File(outputFileDir, "licence.txt.gz");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        inputFile = null;
        outputFile = null;
        inputZipFile = null;
        outputZipFile = null;
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#getFileSize(java.io.File)}.
     */
    @Test
    public final void testGetFileSize() {
        LOGGER.trace("Invoking testGetFileSize...");
        String fileSize = FileUtil.getFileSize(inputFile);
        LOGGER.debug("File Size: " + fileSize);
        assertEquals("17 KB", fileSize);
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#getFileSize(java.io.File)}.
     */
    @Test
    public final void testGetFileSizeNull() {
        LOGGER.trace("Invoking testGetFileSizeNull...");
        assertNull(FileUtil.getFileSize(null));
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#getFileSize(java.io.File)}.
     */
    @Test
    public final void testGetFileSizeMB() {
        LOGGER.trace("Invoking testGetFileSizeMB...");
        String fileSize = FileUtil.getFileSize(new File(inputFileDir, "AF103733479.PDF"));
        LOGGER.debug("File Size: " + fileSize);
        assertEquals("1 MB", fileSize);
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#getFileSize(java.io.File)}.
     */
    @Test
    public final void testGetFileSizeBytes() {
        LOGGER.trace("Invoking testGetFileSizeBytes...");
        String fileSize = FileUtil.getFileSize(new File(inputFileDir, "bytes.txt"));
        LOGGER.debug("File Size: " + fileSize);
        assertEquals("14 bytes", fileSize);
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#transferFile(java.io.File)}.
     */
    @Test
    public final void testTransferFile() {
        LOGGER.trace("Invoking testTransferFile...");
        try {
            FileUtil.transferFile(null, null, null, inputFile, FileTransferProtocol.SFTP);
        } catch (ApplicationException ex) {
            LOGGER.debug("host must not be null.", ex.getErrorKey());
            assertEquals("host must not be null.", ex.getErrorKey());
        }
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#compressToGzipFormat(java.io.File, java.io.File)}.
     */
    @Test
    public final void testCompressToGzipFormat() throws Exception {
        LOGGER.trace("Invoking testCompressToGzipFormat...");
        final File file = FileUtil.compressToGzipFormat(inputFile, outputFileDir);
        LOGGER.debug("File Compressed : " + inputFile.getAbsolutePath() + " to Dir: " + outputFileDir);
        assertTrue(file.exists());
        assertEquals(outputZipFile.getName(), file.getName());
        assertEquals(outputZipFile.getParent(), file.getParent());
        File file2 = new File("Z:/xyz/abc");
        try {
            FileUtil.compressToGzipFormat(file2, outputFileDir);
            LOGGER.debug("File Compressed : " + file2.getAbsolutePath() + " to Dir: " + outputFileDir);
        } catch (ApplicationException e) {
            assertNotNull(e.getErrorKey());
        }
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#decompressGzipFile(java.io.File, java.io.File)}.
     * 
     * @throws ApplicationException
     */
    @Test
    public final void testDecompressGzipFile() throws ApplicationException {
        LOGGER.trace("Invoking testDecompressGzipFile...");
        File file = FileUtil.decompressGzipFile(inputZipFile, outputFileDir);
        LOGGER.debug("Decompressed File: " + inputZipFile.getAbsolutePath() + " to Dir: " + outputFileDir);
        assertTrue(file.exists());
        assertEquals(outputFile.getName(), file.getName());
        assertEquals(outputFile.getParent(), file.getParent());

        File file2 = new File("Z:/xyz/abc");
        try {
            FileUtil.decompressGzipFile(file2, outputFileDir);
            LOGGER.debug("Decompressed File: " + inputZipFile.getAbsolutePath() + " to Dir: " + outputFileDir);
        } catch (ApplicationException e) {
            assertNotNull(e.getErrorKey());
        }
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#deleteFile(java.io.File)}.
     */
    @Test
    public final void testDeleteFile() {
        LOGGER.trace("Invoking testDeleteFile...");
        LOGGER.debug("Delete File: " + outputFile.getAbsolutePath());
        FileUtil.deleteFile(outputFile);
        LOGGER.debug("Delete File: " + outputZipFile.getAbsolutePath());
        FileUtil.deleteFile(outputZipFile);
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#copyFile(java.io.File, java.io.File)}.
     * 
     * @throws Exception
     */
    @Test
    public final void testCopyFile() throws Exception {
        LOGGER.trace("Invoking testCopyFile...");
        LOGGER.debug("Source File: " + inputFile.getAbsolutePath() + ", Destination: " +
                outputFile.getParentFile().getAbsolutePath());
        assertTrue(FileUtil.copyFile(inputFile, outputFile.getParentFile()));
        LOGGER.debug("Source File: " + inputZipFile.getAbsolutePath() + ", Destination: " +
                outputZipFile.getParentFile().getAbsolutePath());
        assertTrue(FileUtil.copyFile(inputZipFile, outputZipFile.getParentFile()));
    }
}
