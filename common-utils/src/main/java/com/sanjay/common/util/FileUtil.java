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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.sanjay.common.constants.CommonConstants;
import com.sanjay.common.enumeration.FileTransferProtocol;
import com.sanjay.common.exception.ApplicationException;
import com.sanjay.common.exception.ApplicationSeverity;

/**
 * @author SANJAY
 * 
 */
public final class FileUtil {
    private static final Logger logger = LogManager.getLogger(FileUtil.class);

    private static boolean isValidOperation(File file) {
        logger.trace("Invoking isValidOperation...");
        if (file != null && file.exists() && file.isFile()) {
            logger.trace("File " + file.getName() + " is Valid to perform Operation.");
            return true;
        } else {
            logger.error("File is invalid to perform operation.");
            return false;
        }
    }

    /**
     * 
     * @param file
     * @return
     */
    public static String getFileSize(File file) {
        logger.trace("Invoking getFileSize...");
        if (isValidOperation(file)) {
            long fileSize = file.length();
            if (fileSize / CommonConstants.ONE_GB > 0) {
                return String.valueOf(fileSize / CommonConstants.ONE_GB) + " " + CommonConstants.GB;
            } else if (fileSize / CommonConstants.ONE_MB > 0) {
                return String.valueOf(fileSize / CommonConstants.ONE_MB) + " " + CommonConstants.MB;
            } else if (fileSize / CommonConstants.ONE_KB > 0) {
                return String.valueOf(fileSize / CommonConstants.ONE_KB) + " " + CommonConstants.KB;
            } else {
                return String.valueOf(fileSize) + " " + CommonConstants.BYTES;
            }
        } else {
            return null;
        }

    }

    public static File compressToGzipFormat(final File inputFile, final String gzipOutputDir)
        throws ApplicationException {
        logger.trace("Invoking compressToGzipFormat...");
        try (final FileInputStream fis = new FileInputStream(inputFile);
                final FileOutputStream fos =
                        new FileOutputStream(gzipOutputDir + CommonConstants.FORWARD_SLASH + inputFile.getName() +
                                CommonConstants.GZ);
                final GZIPOutputStream gzipOS = new GZIPOutputStream(fos);) {
            final byte[] buffer = new byte[CommonConstants.ONE_KB];
            int length;
            while ((length = fis.read(buffer)) != -1) {
                gzipOS.write(buffer, 0, length);
            }
            logger.debug(inputFile.getName() + ".gz is created in Dir: " + gzipOutputDir);
            return new File(gzipOutputDir, inputFile.getName() + CommonConstants.GZ);
        } catch (IOException e) {
            // TODO Exception Handling should be modified.
            logger.error(e.getMessage(), e);
            throw new ApplicationException("", ApplicationSeverity.ERROR, e, inputFile, gzipOutputDir);
        }
    }

    public static File decompressGzipFile(final File gzipFile, final String fileOutputDir) throws ApplicationException {
        logger.trace("Invoking decompressGzipFile...");
        try (final FileInputStream fis = new FileInputStream(gzipFile);
                final GZIPInputStream gis = new GZIPInputStream(fis);
                final FileOutputStream fos =
                        new FileOutputStream(new File(fileOutputDir, gzipFile.getName().replaceAll("(.gz)$", "")));) {
            final byte[] buffer = new byte[CommonConstants.ONE_KB];
            int length;
            while ((length = gis.read(buffer)) != -1) {
                fos.write(buffer, 0, length);
            }
            logger.debug(gzipFile.getName() + " File decompressed to dir: " + fileOutputDir);
            return new File(fileOutputDir, gzipFile.getName().replaceAll("(.gz)$", ""));
        } catch (IOException e) {
            // TODO Exception Handling should be modified.
            logger.error(e.getMessage(), e);
            throw new ApplicationException("", ApplicationSeverity.ERROR, e, gzipFile, fileOutputDir);
        }
    }

    public static void deleteFile(File file) {
        logger.trace("Invoking deleteFile...");
        if (isValidOperation(file)) {
            if ( !file.delete()) {
                logger.debug(file.getName() + " File will delete on exit");
                file.deleteOnExit();
            } else {
                logger.debug(file.getName() + " File is deleted successfully");
            }
        }
    }

    /**
     * Transfer a file to remote destination via JSCH library using sFTP protocol
     * 
     * @param username
     * @param password
     * @param host
     * @param file
     * @param transferProtocol
     * @return
     * @throws ApplicationException
     */
    public boolean transferFile(final String username, final String password, final String host, final File file,
                                final FileTransferProtocol transferProtocol) throws ApplicationException {
        // TODO currently can deal with sftp only.
        JSch jsch = new JSch();
        try {
            Session session = jsch.getSession(username, host);
            logger.debug("Session Host: " + session.getHost());
            session.setPassword(password);
            Properties properties = new Properties();
            properties.put("StrictHostKeyChecking", "no");
            session.setConfig(properties);
            logger.debug("Connecting to a session Host Server...");
            session.connect();
            logger.debug("session is established with host server.");
            Channel channel = session.openChannel(transferProtocol.ftpStringRepresentation());
            logger.debug("Connecting to a sftp Channel...");
            channel.connect();
            logger.debug("Connected with sftp Channel.");
            ChannelSftp channelSftp = (ChannelSftp) channel;
            channelSftp.put(new FileInputStream(file), file.getName());
            logger.debug("File transfered successfully");
            channelSftp.exit();
            logger.debug("sftp channel disconnected.");
            channel.disconnect();
            logger.debug("channel disconnected.");
            session.disconnect();
            logger.debug("session disconnected.");
            return true;
        } catch (JSchException | FileNotFoundException | SftpException e) {
            logger.error(e.getMessage(), e.getCause());
            throw new ApplicationException(e.getMessage(), ApplicationSeverity.ERROR, e.getCause(), e);
        }
    }
}
