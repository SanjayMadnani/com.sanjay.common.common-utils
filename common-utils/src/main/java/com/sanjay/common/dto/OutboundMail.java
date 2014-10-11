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

/**
 * Required for Out bound service to send mail. Uses {@code OutboundMailBuilder to build OutboundMail}.
 * 
 * @author SANJAY
 * @see com.sanjay.common.util.OutboundMailUtil
 * @see MailTransferProperties
 * @see OutboundMailBuilder
 */
public class OutboundMail {
    /**
     * Builder to build {@link OutboundMail}.
     * 
     * @author SANJAY
     * @see OutboundMail
     */
    public static final class OutboundMailBuilder {
        private String smtpUserId;
        private String smtpUserPassword;
        private String smtpHost;
        private String smtpPort;
        private MailTransferProperties smtpAuth;
        private MailTransferProperties smtpSslEnable;
        private MailTransferProperties smtpStarttlsEnable;
        private MailTransferProperties debugMode;

        /**
         * @param smtpUserId the smtpUserId to set
         * @return OutboundMailBuilder current instance.
         */
        public OutboundMailBuilder smtpUserId(String smtpUserId) {
            this.smtpUserId = smtpUserId;
            return this;
        }

        /**
         * @param smtpUserPassword the smtpUserPassword to set
         * @return OutboundMailBuilder current instance.
         */
        public OutboundMailBuilder smtpUserPassword(String smtpUserPassword) {
            this.smtpUserPassword = smtpUserPassword;
            return this;
        }

        /**
         * @param smtpHost the smtpHost to set
         * @return OutboundMailBuilder current instance.
         */
        public OutboundMailBuilder smtpHost(String smtpHost) {
            this.smtpHost = smtpHost;
            return this;
        }

        /**
         * @param smtpPort the smtpPort to set
         * @return OutboundMailBuilder current instance.
         */
        public OutboundMailBuilder smtpPort(String smtpPort) {
            this.smtpPort = smtpPort;
            return this;
        }

        /**
         * @param smtpAuth the smtpAuth to set
         * @return OutboundMailBuilder current instance.
         */
        public OutboundMailBuilder smtpAuth(MailTransferProperties smtpAuth) {
            this.smtpAuth = smtpAuth;
            return this;
        }

        /**
         * @param smtpSslEnable the smtpSslEnable to set
         * @return OutboundMailBuilder current instance.
         */
        public OutboundMailBuilder smtpSslEnable(MailTransferProperties smtpSslEnable) {
            this.smtpSslEnable = smtpSslEnable;
            return this;
        }

        /**
         * @param smtpStarttlsEnable the smtpStarttlsEnable to set
         * @return OutboundMailBuilder current instance.
         */
        public OutboundMailBuilder smtpStarttlsEnable(MailTransferProperties smtpStarttlsEnable) {
            this.smtpStarttlsEnable = smtpStarttlsEnable;
            return this;
        }

        /**
         * @param debugMode the debugMode to set
         * @return OutboundMailBuilder current instance.
         */
        public OutboundMailBuilder debugMode(MailTransferProperties debugMode) {
            this.debugMode = debugMode;
            return this;
        }

        /**
         * Used to build {@code OutboundMail} instance by using {@code OutboundMailBuilder} properties.
         * 
         * @return OutboundMail Instance.
         * @throws NullPointerException if any filed of {@link OutboundMail} is null.
         */
        public OutboundMail build() {
            OutboundMail outboundMail = new OutboundMail(this);
            if (outboundMail.smtpUserId == null) {
                throw new NullPointerException("smtpUserId can not be null");
            }
            if (outboundMail.smtpUserPassword == null) {
                throw new NullPointerException("smtpUserPassword can not be null");
            }
            if (outboundMail.smtpHost == null) {
                throw new NullPointerException("smtpHost can not be null");
            }
            if (outboundMail.smtpPort == null) {
                throw new NullPointerException("smtpPort can not be null");
            }
            if (outboundMail.smtpAuth == null) {
                throw new NullPointerException("smtpAuth can not be null");
            }
            if (outboundMail.smtpSslEnable == null) {
                throw new NullPointerException("smtpSslEnable can not be null");
            }
            if (outboundMail.smtpStarttlsEnable == null) {
                throw new NullPointerException("smtpStarttlsEnable can not be null");
            }
            if (outboundMail.debugMode == null) {
                throw new NullPointerException("debugMode can not be null");
            }
            return outboundMail;
        }
    }

    private final String smtpUserId;
    private final String smtpUserPassword;
    private final String smtpHost;
    private final String smtpPort;
    private final MailTransferProperties smtpAuth;
    private final MailTransferProperties smtpSslEnable;
    private final MailTransferProperties smtpStarttlsEnable;
    private final MailTransferProperties debugMode;

    /**
     * Private Constructor to avoid externally object creation. Object of {@link OutboundMail} can only be created by
     * {@link OutboundMailBuilder} only.
     * 
     * @param outboundBuilder builder object to create {@link OutboundMail} object by using all its fields.
     */
    private OutboundMail(OutboundMailBuilder outboundBuilder) {
        this.smtpUserId = outboundBuilder.smtpUserId;
        this.smtpUserPassword = outboundBuilder.smtpUserPassword;
        this.smtpHost = outboundBuilder.smtpHost;
        this.smtpPort = outboundBuilder.smtpPort;
        this.smtpAuth = outboundBuilder.smtpAuth;
        this.smtpSslEnable = outboundBuilder.smtpSslEnable;
        this.smtpStarttlsEnable = outboundBuilder.smtpStarttlsEnable;
        this.debugMode = outboundBuilder.debugMode;
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

    /**
     * Overwritten method to print object.
     * 
     * @return String representation of {@link OutboundMail} instance properties.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "OutboundMail [smtpUserId=" + smtpUserId + ", smtpUserPassword=" + smtpUserPassword + ", smtpHost=" +
                smtpHost + ", smtpPort=" + smtpPort + ", smtpAuth=" + smtpAuth + ", smtpSslEnable=" + smtpSslEnable +
                ", smtpStarttlsEnable=" + smtpStarttlsEnable + ", debugMode=" + debugMode + "]";
    }

}
