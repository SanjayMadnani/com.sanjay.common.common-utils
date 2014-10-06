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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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
    private static final Logger LOGGER = LogManager.getLogger(FileUtil.class);

    private static boolean isValidOperation(File file) {
        LOGGER.trace("Invoking isValidOperation...");
        if (file != null && file.exists() && file.isFile()) {
            LOGGER.trace("File " + file.getName() + " is Valid to perform Operation.");
            return true;
        } else {
            LOGGER.error("File is invalid to perform operation.");
            return false;
        }
    }

    /**
     * 
     * @param file
     * @return
     */
    public static String getFileSize(File file) {
        LOGGER.trace("Invoking getFileSize...");
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
        LOGGER.trace("Invoking compressToGzipFormat...");
        try (final FileInputStream fis = new FileInputStream(inputFile);
                final FileOutputStream fos =
                        new FileOutputStream(new File(gzipOutputDir, inputFile.getName() + CommonConstants.GZ));
                final GZIPOutputStream gzipOS = new GZIPOutputStream(fos);) {
            final byte[] buffer = new byte[CommonConstants.ONE_KB];
            int length;
            while ((length = fis.read(buffer)) != -1) {
                gzipOS.write(buffer, 0, length);
            }
            LOGGER.debug(inputFile.getName() + ".gz is created in Dir: " + gzipOutputDir);
            return new File(gzipOutputDir, inputFile.getName() + CommonConstants.GZ);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ApplicationException(e.getMessage(), ApplicationSeverity.ERROR, e, inputFile, gzipOutputDir);
        }
    }

    public static File decompressGzipFile(final File gzipFile, final String fileOutputDir) throws ApplicationException {
        LOGGER.trace("Invoking decompressGzipFile...");
        try (final FileInputStream fis = new FileInputStream(gzipFile);
                final GZIPInputStream gis = new GZIPInputStream(fis);
                final FileOutputStream fos =
                        new FileOutputStream(new File(fileOutputDir, gzipFile.getName().replaceAll("(.gz)$", "")));) {
            final byte[] buffer = new byte[CommonConstants.ONE_KB];
            int length;
            while ((length = gis.read(buffer)) != -1) {
                fos.write(buffer, 0, length);
            }
            LOGGER.debug(gzipFile.getName() + " File decompressed to dir: " + fileOutputDir);
            return new File(fileOutputDir, gzipFile.getName().replaceAll("(.gz)$", ""));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ApplicationException(e.getMessage(), ApplicationSeverity.ERROR, e, gzipFile, fileOutputDir);
        }
    }

    public static void deleteFile(File file) {
        LOGGER.trace("Invoking deleteFile...");
        if (isValidOperation(file)) {
            if ( !file.delete()) {
                LOGGER.debug(file.getName() + " File will delete on exit");
                file.deleteOnExit();
            } else {
                LOGGER.debug(file.getName() + " File is deleted successfully");
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
        // currently can deal with sftp only.
        LOGGER.trace("Invoking transferFile...");
        JSch jsch = new JSch();
        try {
            Session session = jsch.getSession(username, host);
            LOGGER.debug("Session Host: " + session.getHost());
            session.setPassword(password);
            Properties properties = new Properties();
            properties.put("StrictHostKeyChecking", "no");
            session.setConfig(properties);
            LOGGER.debug("Connecting to a session Host Server...");
            session.connect();
            LOGGER.debug("session is established with host server.");
            Channel channel = session.openChannel(transferProtocol.ftpStringRepresentation());
            LOGGER.debug("Connecting to a sftp Channel...");
            channel.connect();
            LOGGER.debug("Connected with sftp Channel.");
            ChannelSftp channelSftp = (ChannelSftp) channel;
            channelSftp.put(new FileInputStream(file), file.getName());
            LOGGER.debug("File transfered successfully");
            channelSftp.exit();
            LOGGER.debug("sftp channel disconnected.");
            channel.disconnect();
            LOGGER.debug("channel disconnected.");
            session.disconnect();
            LOGGER.debug("session disconnected.");
            return true;
        } catch (JSchException | FileNotFoundException | SftpException e) {
            LOGGER.error(e.getMessage(), e.getCause());
            throw new ApplicationException(e.getMessage(), ApplicationSeverity.ERROR, e.getCause(), e);
        }
    }

    public static boolean copyFile(File srcFile, File destLocation) throws IOException {
        LOGGER.trace("Invoking copyFile...");
        if (isValidOperation(srcFile)) {
            if ( !destLocation.exists()) {
                destLocation.mkdirs();
                LOGGER.debug(destLocation.getPath() + " Directory created.");
            }
            Path path =
                    Files.copy(srcFile.toPath(), destLocation.toPath().resolve(srcFile.getName()),
                            StandardCopyOption.REPLACE_EXISTING);
            LOGGER.debug(path.toString() + " copied.");
            return true;
        }
        LOGGER.error("file is not copied.");
        return false;
    }

}
