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

import com.sanjay.common.enumeration.MailTransferProperties;
import com.sanjay.common.util.OutboundMailUtil;

/**
 * Required for Out bound service to send mail.
 * 
 * @author SANJAY
 * @see OutboundMailUtil
 * @see MailTransferProperties
 */
public class OutboundDTO {

    private final String smtpUserId;
    private final String smtpUserPassword;
    private final String smtpHost;
    private final String smtpPort;
    private final MailTransferProperties smtpAuth;
    private final MailTransferProperties smtpSslEnable;
    private final MailTransferProperties smtpStarttlsEnable;
    private final MailTransferProperties debugMode;

    /**
     * Create a object required for OutboundMailUtil
     * {@link OutboundMailUtil#getSmtpSession(String, String, String, String, MailTransferProperties, MailTransferProperties, MailTransferProperties, MailTransferProperties)}
     * .
     * 
     * @param smtpUserId String sender email id.
     * @param smtpUserPassword String Sender email password.
     * @param smtpHost String SMTP host address.
     * @param smtpPort String port number.
     * @param smtpAuth MailTransferProperties true or false.
     * @param smtpSslEnable MailTransferProperties true or false.
     * @param smtpStarttlsEnable MailTransferProperties true or false.
     * @param debugMode MailTransferProperties true or false.
     * @return smtpSession javax.mail.Session
     */
    public OutboundDTO(final String smtpUserId, final String smtpUserPassword, final String smtpHost,
            final String smtpPort, final MailTransferProperties smtpAuth, final MailTransferProperties smtpSslEnable,
            final MailTransferProperties smtpStarttlsEnable, final MailTransferProperties debugMode) {
        super();
        this.smtpUserId = smtpUserId;
        this.smtpUserPassword = smtpUserPassword;
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.smtpAuth = smtpAuth;
        this.smtpSslEnable = smtpSslEnable;
        this.smtpStarttlsEnable = smtpStarttlsEnable;
        this.debugMode = debugMode;
    }

    /**
     * @return the smtpUserId
     */
    public String getSmtpUserId() {
        return smtpUserId;
    }

    /**
     * @return the smtpUserPassword
     */
    public String getSmtpUserPassword() {
        return smtpUserPassword;
    }

    /**
     * @return the smtpHost
     */
    public String getSmtpHost() {
        return smtpHost;
    }

    /**
     * @return the smtpPort
     */
    public String getSmtpPort() {
        return smtpPort;
    }

    /**
     * @return the smtpAuth
     */
    public MailTransferProperties getSmtpAuth() {
        return smtpAuth;
    }

    /**
     * @return the smtpSslEnable
     */
    public MailTransferProperties getSmtpSslEnable() {
        return smtpSslEnable;
    }

    /**
     * @return the smtpStarttlsEnable
     */
    public MailTransferProperties getSmtpStarttlsEnable() {
        return smtpStarttlsEnable;
    }

    /**
     * @return the debugMode
     */
    public MailTransferProperties getDebugMode() {
        return debugMode;
    }

}
