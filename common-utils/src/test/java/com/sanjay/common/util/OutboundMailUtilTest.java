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
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.Session;

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

    private static OutboundMailUtil mailUtil;
    private static OutboundMail outboundMail;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        mailUtil = new OutboundMailUtil();
        outboundMail =
                new OutboundMail.OutboundMailBuilder().smtpUserId("Javacode.study@gmail.com")
                        .smtpUserPassword("codestudy").smtpHost("smtp.gmail.com").smtpPort("465")
                        .smtpAuth(MailTransferProperties.TRUE).smtpSslEnable(MailTransferProperties.TRUE)
                        .smtpStarttlsEnable(MailTransferProperties.FALSE).debugMode(MailTransferProperties.FALSE)
                        .build();
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
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
    @Test
    public final void testSendMail() {
        Session session = mailUtil.getSmtpSession(outboundMail);
        List<String> toList = new ArrayList<String>();
        toList.add("sanjay.madnani@outlook.com");
        List<String> ccList = new ArrayList<String>();
        ccList.add("sanjay_madnani2006@yahoo.com");
        List<String> bccList = new ArrayList<String>();
        bccList.add("its.sanjay.madnani@gmail.com");
        String msgSubject = "Java JUnit test case";
        String msgBody = "Ignore this mail after reciving.<br/><b>Just for testing purpose only.</b>";
        File file = null;
        try {
            assertTrue(mailUtil.sendMail(session, toList, ccList, bccList, msgSubject, msgBody, file));
        } catch (MessagingException ex) {
            assertEquals("Unknown SMTP host: " + session.getProperty("mail.smtp.host"), ex.getMessage());
        }
    }

}
