/* Copyright (C) 2014, 2015 Sanjay Madnani
 * 
 * This file is free to use: you can redistribute it and/or modify it under the terms of the GPL General Public License
 * V2 as published by the Free Software Foundation, subject to the following conditions:
 * 
 * The above copyright notice should never be changed and should always included wherever this file is used.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY.
 * See the GNU General Public License V2 for more details. */
package com.sanjay.common.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.sanjay.common.enumeration.MailTransferProperties;

/**
 * Test case for {@link OutboundMail} and {@link OutOfMemoryError}
 * 
 * @author SANJAY
 * 
 */
public class OutboundMailTest {
    private Logger LOGGER = LogManager.getLogger(OutboundMailTest.class);
    private OutboundMail outboundMail;

    /**
     * Test method for {@link com.sanjay.common.dto.OutboundMail#getSmtpUserId()}.
     */
    @Before
    public void setUp() {
        outboundMail =
                new OutboundMail.OutboundMailBuilder().smtpUserId("Javacode.study@gmail.com")
                        .smtpUserPassword("codestudy").smtpHost("smtp.gmail.com").smtpPort("465")
                        .smtpAuth(MailTransferProperties.TRUE).smtpSslEnable(MailTransferProperties.TRUE)
                        .smtpStarttlsEnable(MailTransferProperties.FALSE).debugMode(MailTransferProperties.FALSE)
                        .build();
    }

    @Test
    public void testGetSmtpUserId() {
        LOGGER.trace("Invoking testGetSmtpUserId...");
        assertEquals("Javacode.study@gmail.com", outboundMail.getSmtpUserId());
        try {
            new OutboundMail.OutboundMailBuilder().smtpUserPassword("codestudy").smtpHost("smtp.gmail.com")
                    .smtpPort("465").smtpAuth(MailTransferProperties.TRUE).smtpSslEnable(MailTransferProperties.TRUE)
                    .smtpStarttlsEnable(MailTransferProperties.FALSE).debugMode(MailTransferProperties.FALSE).build();
        } catch (NullPointerException ex) {
            LOGGER.debug(ex.getMessage());
            assertEquals("smtpUserId can not be null", ex.getMessage());
        }
    }

    /**
     * Test method for {@link com.sanjay.common.dto.OutboundMail#getSmtpUserPassword()}.
     */
    @Test
    public void testGetSmtpUserPassword() {
        LOGGER.trace("Invoking testGetSmtpUserPassword...");
        assertEquals("codestudy", outboundMail.getSmtpUserPassword());
        try {
            new OutboundMail.OutboundMailBuilder().smtpUserId("Javacode.study@gmail.com").smtpHost("smtp.gmail.com")
                    .smtpPort("465").smtpAuth(MailTransferProperties.TRUE).smtpSslEnable(MailTransferProperties.TRUE)
                    .smtpStarttlsEnable(MailTransferProperties.FALSE).debugMode(MailTransferProperties.FALSE).build();
        } catch (NullPointerException ex) {
            LOGGER.debug(ex.getMessage());
            assertEquals("smtpUserPassword can not be null", ex.getMessage());
        }
    }

    /**
     * Test method for {@link com.sanjay.common.dto.OutboundMail#getSmtpHost()}.
     */
    @Test
    public void testGetSmtpHost() {
        LOGGER.trace("Invoking testGetSmtpHost...");
        assertEquals("smtp.gmail.com", outboundMail.getSmtpHost());
        try {
            new OutboundMail.OutboundMailBuilder().smtpUserId("Javacode.study@gmail.com").smtpUserPassword("codestudy")
                    .smtpPort("465").smtpAuth(MailTransferProperties.TRUE).smtpSslEnable(MailTransferProperties.TRUE)
                    .smtpStarttlsEnable(MailTransferProperties.FALSE).debugMode(MailTransferProperties.FALSE).build();
        } catch (NullPointerException ex) {
            LOGGER.debug(ex.getMessage());
            assertEquals("smtpHost can not be null", ex.getMessage());
        }
    }

    /**
     * Test method for {@link com.sanjay.common.dto.OutboundMail#getSmtpPort()}.
     */
    @Test
    public void testGetSmtpPort() {
        LOGGER.trace("Invoking testGetSmtpPort...");
        assertEquals("465", outboundMail.getSmtpPort());
        try {
            new OutboundMail.OutboundMailBuilder().smtpUserId("Javacode.study@gmail.com").smtpUserPassword("codestudy")
                    .smtpHost("smtp.gmail.com").smtpAuth(MailTransferProperties.TRUE)
                    .smtpSslEnable(MailTransferProperties.TRUE).smtpStarttlsEnable(MailTransferProperties.FALSE)
                    .debugMode(MailTransferProperties.FALSE).build();
        } catch (NullPointerException ex) {
            LOGGER.debug(ex.getMessage());
            assertEquals("smtpPort can not be null", ex.getMessage());
        }
    }

    /**
     * Test method for {@link com.sanjay.common.dto.OutboundMail#getSmtpAuth()}.
     */
    @Test
    public void testGetSmtpAuth() {
        LOGGER.trace("Invoking testGetSmtpAuth...");
        assertEquals(MailTransferProperties.TRUE, outboundMail.getSmtpAuth());
        try {
            new OutboundMail.OutboundMailBuilder().smtpUserId("Javacode.study@gmail.com").smtpUserPassword("codestudy")
                    .smtpHost("smtp.gmail.com").smtpPort("465").smtpSslEnable(MailTransferProperties.TRUE)
                    .smtpStarttlsEnable(MailTransferProperties.FALSE).debugMode(MailTransferProperties.FALSE).build();
        } catch (NullPointerException ex) {
            LOGGER.debug(ex.getMessage());
            assertEquals("smtpAuth can not be null", ex.getMessage());
        }
    }

    /**
     * Test method for {@link com.sanjay.common.dto.OutboundMail#getSmtpSslEnable()}.
     */
    @Test
    public void testGetSmtpSslEnable() {
        LOGGER.trace("Invoking testGetSmtpSslEnable...");
        assertEquals(MailTransferProperties.TRUE, outboundMail.getSmtpSslEnable());
        try {
            new OutboundMail.OutboundMailBuilder().smtpUserId("Javacode.study@gmail.com").smtpUserPassword("codestudy")
                    .smtpHost("smtp.gmail.com").smtpPort("465").smtpAuth(MailTransferProperties.TRUE)
                    .smtpStarttlsEnable(MailTransferProperties.FALSE).debugMode(MailTransferProperties.FALSE).build();
        } catch (NullPointerException ex) {
            LOGGER.debug(ex.getMessage());
            assertEquals("smtpSslEnable can not be null", ex.getMessage());
        }
    }

    /**
     * Test method for {@link com.sanjay.common.dto.OutboundMail#getSmtpStarttlsEnable()}.
     */
    @Test
    public void testGetSmtpStarttlsEnable() {
        LOGGER.trace("Invoking testGetSmtpStarttlsEnable...");
        assertEquals(MailTransferProperties.FALSE, outboundMail.getSmtpStarttlsEnable());
        try {
            new OutboundMail.OutboundMailBuilder().smtpUserId("Javacode.study@gmail.com").smtpUserPassword("codestudy")
                    .smtpHost("smtp.gmail.com").smtpPort("465").smtpAuth(MailTransferProperties.TRUE)
                    .smtpSslEnable(MailTransferProperties.TRUE).debugMode(MailTransferProperties.FALSE).build();
        } catch (NullPointerException ex) {
            LOGGER.debug(ex.getMessage());
            assertEquals("smtpStarttlsEnable can not be null", ex.getMessage());
        }
    }

    /**
     * Test method for {@link com.sanjay.common.dto.OutboundMail#getDebugMode()}.
     */
    @Test
    public void testGetDebugMode() {
        LOGGER.trace("Invoking testGetDebugMode...");
        assertEquals(MailTransferProperties.FALSE, outboundMail.getDebugMode());
        try {
            new OutboundMail.OutboundMailBuilder().smtpUserId("Javacode.study@gmail.com").smtpUserPassword("codestudy")
                    .smtpHost("smtp.gmail.com").smtpPort("465").smtpAuth(MailTransferProperties.TRUE)
                    .smtpSslEnable(MailTransferProperties.TRUE).smtpStarttlsEnable(MailTransferProperties.FALSE)
                    .build();
        } catch (NullPointerException ex) {
            LOGGER.debug(ex.getMessage());
            assertEquals("debugMode can not be null", ex.getMessage());
        }
    }

    /**
     * Test method for {@link com.sanjay.common.dto.OutboundMail#toString()}.
     */
    @Test
    public void testToString() {
        LOGGER.trace("Invoking testToString...");
        assertNotNull(outboundMail.toString());
        LOGGER.debug(outboundMail.toString());
    }

}
