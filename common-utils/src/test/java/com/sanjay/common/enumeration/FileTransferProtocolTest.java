/**
 * 
 */
package com.sanjay.common.enumeration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author sanjay.madnani
 * 
 */
public class FileTransferProtocolTest {
    /**
     * Test method for {@link com.sanjay.common.enumeration.FileTransferProtocol#ftpTypeStringRepresentation()}.
     */
    @Test
    public final void testFtpStringRepresentation() {
        assertEquals("ftp", FileTransferProtocol.FTP.ftpStringRepresentation());
        assertEquals("sftp", FileTransferProtocol.SFTP.ftpStringRepresentation());
    }

    /**
     * Test method for
     * {@link com.sanjay.common.enumeration.FileTransferProtocol#valueOfFtpStringRepresentation(java.lang.String)}.
     */
    @Test
    public final void testValueOfFtpStringRepresentation() {
        assertEquals(FileTransferProtocol.FTP, FileTransferProtocol.valueOfFtpStringRepresentation("ftp"));
        assertEquals(FileTransferProtocol.SFTP, FileTransferProtocol.valueOfFtpStringRepresentation("sftp"));
    }

}
