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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

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
        String fileSize = FileUtil.getFileSize(inputFile);
        System.out.println("File Size: " + fileSize);
        assertEquals("17 KB", fileSize);
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#getFileSize(java.io.File)}.
     */
    @Test
    public final void testGetFileSizeNull() {
        assertNull(FileUtil.getFileSize(null));
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#getFileSize(java.io.File)}.
     */
    @Test
    public final void testGetFileSizeMB() {
        String fileSize = FileUtil.getFileSize(new File(inputFileDir, "AF103733479.PDF"));
        System.out.println("File Size: " + fileSize);
        assertEquals("1 MB", fileSize);
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#getFileSize(java.io.File)}.
     */
    @Test
    public final void testGetFileSizeBytes() {
        String fileSize = FileUtil.getFileSize(new File(inputFileDir, "bytes.txt"));
        System.out.println("File Size: " + fileSize);
        assertEquals("14 bytes", fileSize);
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#transferFile(java.io.File)}.
     */
    @Test
    public final void testTransferFile() {
        try {
            FileUtil.transferFile(null, null, null, inputFile, FileTransferProtocol.SFTP);
        } catch (ApplicationException ex) {
            assertEquals("host must not be null.", ex.getErrorKey());
        }
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#compressToGzipFormat(java.io.File, java.io.File)}.
     */
    @Test
    public final void testCompressToGzipFormat() throws Exception {
        final File file = FileUtil.compressToGzipFormat(inputFile, outputFileDir);
        assertTrue(file.exists());
        assertEquals(outputZipFile.getName(), file.getName());
        assertEquals(outputZipFile.getParent(), file.getParent());
        File file2 = new File("Z:/xyz/abc");
        try {
            FileUtil.compressToGzipFormat(file2, outputFileDir);
        } catch (ApplicationException e) {
            assertEquals(file2.getAbsolutePath() + " (The system cannot find the path specified)", e.getErrorKey());
        }
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#decompressGzipFile(java.io.File, java.io.File)}.
     * 
     * @throws ApplicationException
     */
    @Test
    public final void testDecompressGzipFile() throws ApplicationException {
        File file = FileUtil.decompressGzipFile(inputZipFile, outputFileDir);
        assertTrue(file.exists());
        assertEquals(outputFile.getName(), file.getName());
        assertEquals(outputFile.getParent(), file.getParent());

        File file2 = new File("Z:/xyz/abc");
        try {
            FileUtil.decompressGzipFile(file2, outputFileDir);
        } catch (ApplicationException e) {
            assertEquals(file2.getAbsolutePath() + " (The system cannot find the path specified)", e.getErrorKey());
        }
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#deleteFile(java.io.File)}.
     */
    @Test
    public final void testDeleteFile() {
        FileUtil.deleteFile(outputFile);
        FileUtil.deleteFile(outputZipFile);
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#copyFile(java.io.File, java.io.File)}.
     * 
     * @throws Exception
     */
    @Test
    public final void testCopyFile() throws Exception {
        assertTrue(FileUtil.copyFile(inputFile, outputFile.getParentFile()));
        assertTrue(FileUtil.copyFile(inputZipFile, outputZipFile.getParentFile()));
    }
}
