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

import com.sanjay.common.enumeration.MailTransferProperties;

/**
 * @author sanjay.madnani
 * 
 */
public class OutboundMailUtil {

    private String smtpUserId = null;

    private <T> boolean isNull(List<T> list) {
        if (list != null && list.size() > 0)
            return false;
        return true;
    }

    /**
     * Create a Simple Mail transfer mail protocol session required for sending mail.
     * 
     * @param smtpUserId String sender email id.
     * @param smtpUserPassword String Sender email password.
     * @param smtpHost String smtp host address.
     * @param smtpPort String port number.
     * @param smtpAuth MailTransferProperties true or false.
     * @param smtpSslEnable MailTransferProperties true or false.
     * @param smtpStarttlsEnable MailTransferProperties true or false.
     * @param debugMode MailTransferProperties true or false.
     * @return smtpSession javax.mail.Session
     */
    public Session getSmtpSession(final String smtpUserId, final String smtpUserPassword, final String smtpHost,
                                  final String smtpPort, final MailTransferProperties smtpAuth,
                                  final MailTransferProperties smtpSslEnable,
                                  final MailTransferProperties smtpStarttlsEnable,
                                  final MailTransferProperties debugMode) {
        this.smtpUserId = smtpUserId;
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.auth", smtpAuth.strMailTransferProperty());
        props.put("mail.debug", debugMode.strMailTransferProperty());
        props.put("mail.smtp.ssl.enable", smtpSslEnable.strMailTransferProperty());
        props.put("mail.smtp.starttls.enable", smtpStarttlsEnable.strMailTransferProperty());
        props.put("mail.smtp.port", smtpPort);
        if (smtpStarttlsEnable == MailTransferProperties.False && smtpSslEnable == MailTransferProperties.True) {
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpUserId, smtpUserPassword);
            }
        });
        return session;
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
     * @throws MessagingException.
     */
    public boolean sendMail(final Session smtpSession, final List<String> toList, final List<String> ccList,
                            final List<String> bccList, final String msgSubject, final String msgBody, final File file)
        throws MessagingException {
        // Creates MimeMessage by SMTP Session.
        final MimeMessage message = new MimeMessage(smtpSession);
        message.setFrom(new InternetAddress(this.smtpUserId));
        final Address[] toAddress = new InternetAddress[toList.size()];
        for (int i = 0; i < toAddress.length; i++) {
            toAddress[i] = new InternetAddress(toList.get(i));
        }
        // sets the to list for sending mail.
        message.addRecipients(Message.RecipientType.TO, toAddress);
        if ( !isNull(ccList)) {
            final Address[] toCcAddress = new InternetAddress[ccList.size()];
            for (int i = 0; i < toCcAddress.length; i++) {
                toCcAddress[i] = new InternetAddress(ccList.get(i));
            }
            // sets the cc list for sending mail.
            message.addRecipients(Message.RecipientType.CC, toCcAddress);
        }

        if ( !isNull(bccList)) {
            final Address[] toBccAddress = new InternetAddress[bccList.size()];
            for (int i = 0; i < toBccAddress.length; i++) {
                toBccAddress[i] = new InternetAddress(ccList.get(i));
            }
            // sets the bcc list for sending mail.
            message.addRecipients(Message.RecipientType.BCC, toBccAddress);
        }

        final Multipart multipart = new MimeMultipart();
        // Attach a file with mail if present.
        if (file != null) {
            final MimeBodyPart messageBodyPart2 = new MimeBodyPart();
            final DataSource source = new FileDataSource(file);
            messageBodyPart2.setDataHandler(new DataHandler(source));
            messageBodyPart2.setFileName(file.getName());
            multipart.addBodyPart(messageBodyPart2);
        }
        message.setSubject(msgSubject);
        message.setSentDate(new java.util.Date());
        final BodyPart messageBodyPart1 = new MimeBodyPart();
        // Sends the message in html format.
        messageBodyPart1.setContent(msgBody, "text/html");
        multipart.addBodyPart(messageBodyPart1);
        message.setContent(multipart);
        Transport.send(message);
        return true;
    }

}
