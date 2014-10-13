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

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sanjay.common.dto.OutboundMail;
import com.sanjay.common.enumeration.MailTransferProperties;

/**
 * Send mails using SMTP session.
 * 
 * @author sanjay.madnani
 * @see com.sanjay.common.dto.OutboundMail
 */
public class OutboundMailUtil {
    private static final Logger LOG = LogManager.getLogger(OutboundMailUtil.class);

    private String smtpUserId = null;

    private <T> boolean isNull(final List<T> list) {
        if ((list != null) && ( !list.isEmpty())) {
            return false;
        }
        return true;
    }

    /**
     * Create a Simple Mail transfer mail protocol session required for sending mail.
     * 
     * @param outboundMail keeps details like id, password, host, port and so on.
     *            {@link com.sanjay.common.dto.OutboundMail}
     * @return smtpSession javax.mail.Session
     */
    public Session getSmtpSession(final OutboundMail outboundMail) {
        LOG.trace("Invoking getSmtpSession by providing details: " + outboundMail.toString() + " ...");
        this.smtpUserId = outboundMail.getSmtpUserId();
        Properties props = new Properties();
        props.put("mail.smtp.host", outboundMail.getSmtpHost());
        props.put("mail.smtp.auth", outboundMail.getSmtpAuth().strMailTransferProperty());
        props.put("mail.debug", outboundMail.getDebugMode().strMailTransferProperty());
        props.put("mail.smtp.ssl.enable", outboundMail.getSmtpSslEnable().strMailTransferProperty());
        props.put("mail.smtp.starttls.enable", outboundMail.getSmtpStarttlsEnable().strMailTransferProperty());
        props.put("mail.smtp.port", outboundMail.getSmtpPort());
        if (outboundMail.getSmtpStarttlsEnable() == MailTransferProperties.FALSE &&
                outboundMail.getSmtpSslEnable() == MailTransferProperties.TRUE) {
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }
        return Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpUserId, outboundMail.getSmtpUserPassword());
            }
        });
    }

    /**
     * Sends Mail to list of user(to and cc and bcc list) with attachment. send mails via secure socket layer
     * Authentication. Simple mail Transfer Protocol is used.
     * 
     * @param smtpSession Session see {@link #getSmtpSession}.
     * @param toList List of email-Ids.
     * @param ccList (Optional) List of email-Ids, can be null.
     * @param bccList (Optional) List of email-Ids, can be null.
     * @param msgSubject String subject line of mail.
     * @param msgBody String TEXT/HTML message to deliver.
     * @param file (Optional) File to send as attachment.
     * @return boolean true is mail is send otherwise false.
     * @throws MessagingException.
     */
    public boolean sendMail(final Session smtpSession, final List<String> toList, final List<String> ccList,
                            final List<String> bccList, final String msgSubject, final String msgBody, final File file)
        throws MessagingException {
        LOG.trace("Invoking SendMail...");
        // Creates MimeMessage by SMTP Session.
        final MimeMessage message = new MimeMessage(smtpSession);
        LOG.debug("MimeMessage is created by smtpSession");
        message.setFrom(new InternetAddress(this.smtpUserId));
        final Address[] toAddress = new InternetAddress[toList.size()];
        for (int i = 0; i < toAddress.length; i++) {
            toAddress[i] = new InternetAddress(toList.get(i));
        }
        // sets the to list for sending mail.
        message.addRecipients(Message.RecipientType.TO, toAddress);
        LOG.debug("toList Recipients added");
        if ( !isNull(ccList)) {
            final Address[] toCcAddress = new InternetAddress[ccList.size()];
            for (int i = 0; i < toCcAddress.length; i++) {
                toCcAddress[i] = new InternetAddress(ccList.get(i));
            }
            // sets the cc list for sending mail.
            message.addRecipients(Message.RecipientType.CC, toCcAddress);
            LOG.debug("ccList Recipients added");
        }

        if ( !isNull(bccList)) {
            final Address[] toBccAddress = new InternetAddress[bccList.size()];
            for (int i = 0; i < toBccAddress.length; i++) {
                toBccAddress[i] = new InternetAddress(ccList.get(i));
            }
            // sets the bcc list for sending mail.
            message.addRecipients(Message.RecipientType.BCC, toBccAddress);
            LOG.debug("bccList Recipients added");
        }

        final Multipart multipart = new MimeMultipart();
        // Attach a file with mail if present.
        if (file != null) {
            final MimeBodyPart messageBodyPart2 = new MimeBodyPart();
            final DataSource source = new FileDataSource(file);
            messageBodyPart2.setDataHandler(new DataHandler(source));
            messageBodyPart2.setFileName(file.getName());
            multipart.addBodyPart(messageBodyPart2);
            LOG.debug(file.getName() + " file is attached.");
        }
        message.setSubject(msgSubject);
        java.util.Date sentDate = new java.util.Date();
        message.setSentDate(sentDate);
        LOG.debug("Subject : " + msgSubject + " is set for message with sentDate: " + sentDate);
        final BodyPart messageBodyPart1 = new MimeBodyPart();
        // Sends the message in html format.
        messageBodyPart1.setContent(msgBody, "text/html");
        LOG.trace("Message Body is set to text/html");
        multipart.addBodyPart(messageBodyPart1);
        message.setContent(multipart);
        Transport.send(message);
        LOG.debug("Message sent successfully.");
        return true;
    }

}
