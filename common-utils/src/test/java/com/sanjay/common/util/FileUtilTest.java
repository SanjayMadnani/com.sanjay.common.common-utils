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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sanjay.common.constants.CommonConstants;
import com.sanjay.common.exception.ApplicationException;

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
        System.out.println("File Size: " + fileSize);
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
     * 
     * @throws ApplicationException
     */
    @Test
    public final void testDecompressGzipFile() throws ApplicationException {
        File gZipFile = new File(outputFileDir, inputFileName + CommonConstants.GZ);
        if (gZipFile.exists()) {
            File outputFile = FileUtil.decompressGzipFile(gZipFile, outputFileDir);
            assertNotNull(outputFile);
            assertTrue(outputFile.exists());
            System.out.println("Decompressed successfully");
        }
    }

    /**
     * Test method for {@link com.sanjay.common.util.FileUtil#deleteFile(java.io.File)}.
     */
    @Test
    public final void testDeleteFile() {
        File file = new File(outputFileDir, inputFileName + ".gz");
        FileUtil.deleteFile(file);
        System.out.println(file.getName() + " deleted successfully");
    }

}
