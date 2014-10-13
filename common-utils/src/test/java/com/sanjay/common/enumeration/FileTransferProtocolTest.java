/* Copyright (C) 2014, 2015 Sanjay Madnani
 * 
 * This file is free to use: you can redistribute it and/or modify it under the terms of the GPL General Public
 * License
 * V2 as published by the Free Software Foundation, subject to the following conditions:
 * 
 * The above copyright notice should never be changed and should always included wherever this file is used.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY.
 * See the GNU General Public License V2 for more details. */
package com.sanjay.common.enumeration;

import static org.junit.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * Test case for {@link FileTransferProtocol}.
 * 
 * @author SANJAY
 * 
 */
public class FileTransferProtocolTest {
    private static final Logger LOGGER = LogManager.getLogger(FileTransferProtocolTest.class);

    /**
     * Test method for {@link com.sanjay.common.enumeration.FileTransferProtocol#ftpTypeStringRepresentation()}.
     */
    @Test
    public final void testFtpStringRepresentation() {
        LOGGER.trace("Invoking testFtpStringRepresentation...");
        assertEquals("sftp", FileTransferProtocol.SFTP.ftpStringRepresentation());
    }

    /**
     * Test method for
     * {@link com.sanjay.common.enumeration.FileTransferProtocol#valueOfFtpStringRepresentation(java.lang.String)}.
     */
    @Test
    public final void testValueOfFtpStringRepresentation() {
        LOGGER.trace("Invoking testValueOfFtpStringRepresentation...");
        assertEquals(FileTransferProtocol.SFTP, FileTransferProtocol.valueOfFtpStringRepresentation("sftp"));
    }

}
