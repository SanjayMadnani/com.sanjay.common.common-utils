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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sanjay.common.dto.OutboundMail;
import com.sanjay.common.enumeration.MailTransferProperties;

/**
 * Test case for {@link OutboundMailUtil}.
 * 
 * @author sanjay.madnani
 * 
 */
public class OutboundMailUtilTest {

    private static final Logger LOGGER = LogManager.getLogger(OutboundMailUtilTest.class);
    private static OutboundMailUtil mailUtil;
    private static OutboundMail outboundMail;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        LOGGER.trace("Invoking setUpBeforeClass...");
        mailUtil = new OutboundMailUtil();
        outboundMail =
                new OutboundMail.OutboundMailBuilder().smtpUserId("").smtpUserPassword("").smtpHost("smtp.gmail.com")
                        .smtpPort("465").smtpAuth(MailTransferProperties.TRUE)
                        .smtpSslEnable(MailTransferProperties.TRUE).smtpStarttlsEnable(MailTransferProperties.FALSE)
                        .debugMode(MailTransferProperties.FALSE).build();
        LOGGER.debug("outboundMail Obj constructed: " + outboundMail.toString());
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        LOGGER.trace("Invoking tearDownAfterClass...");
        mailUtil = null;
        outboundMail = null;
    }

    /**
     * Test method for
     * {@link com.sanjay.common.util.OutboundMailUtil#getSmtpSession(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.sanjay.common.enumeration.MailTransferProperties, com.sanjay.common.enumeration.MailTransferProperties, com.sanjay.common.enumeration.MailTransferProperties, com.sanjay.common.enumeration.MailTransferProperties)}
     * .
     */
    @Test
    public final void testGetSmtpSession() {
        LOGGER.trace("Invoking testGetSmtpSession...");
        Session session = mailUtil.getSmtpSession(outboundMail);
        assertFalse(session.getDebug());
        assertEquals("smtp.gmail.com", session.getProperty("mail.smtp.host"));
        assertEquals("465", session.getProperty("mail.smtp.port"));
        assertEquals("false", session.getProperty("mail.smtp.starttls.enable"));
        assertEquals("true", session.getProperty("mail.smtp.ssl.enable"));
        assertEquals("true", session.getProperty("mail.smtp.auth"));
    }

    /**
     * Test method for
     * {@link com.sanjay.common.util.OutboundMailUtil#sendMail(javax.mail.Session, java.util.List, java.util.List, java.util.List, java.lang.String, java.lang.String, java.io.File)}
     * .
     * 
     * @throws MessagingException
     */
    // @Test
    public final void testSendMail() {
        LOGGER.trace("Invoking testSendMail...");
        Session session = mailUtil.getSmtpSession(outboundMail);
        List<String> toList = new ArrayList<String>();
        toList.add("");
        List<String> ccList = new ArrayList<String>();
        ccList.add("");
        List<String> bccList = new ArrayList<String>();
        bccList.add("");
        String msgSubject = "Java JUnit test case";
        String msgBody = "Ignore this mail after reciving.<br/><b>Just for testing purpose only.</b>";
        File file = new File("src/test/resources/FileOperation/InputFile", "bytes.txt");
        Arrays.toString(toList.toArray());
        toList.toArray();
        LOGGER.debug("Invoking send mail with parameter: toList: " + Arrays.toString(toList.toArray()) + ", ccList: " +
                Arrays.toString(ccList.toArray()) + ", bccList: " + Arrays.toString(bccList.toArray()) +
                ", msgSubject: " + msgSubject + ", msgBody: " + msgBody + ", file: " + file.getAbsolutePath());
        try {
            assertTrue(mailUtil.sendMail(session, toList, ccList, bccList, msgSubject, msgBody, file));
        } catch (MessagingException ex) {
            assertEquals("Unknown SMTP host: " + session.getProperty("mail.smtp.host"), ex.getMessage());
        }
    }

}
