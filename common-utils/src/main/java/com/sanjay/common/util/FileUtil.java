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
import java.util.zip.GZIPOutputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * @author SANJAY
 * 
 */
public final class FileUtil {
    

    /**
     * Transfer a file to remote destination via JSCH library using sFTP protocol
     * 
     * @param file
     *            : file to transfer
     * @throws IE2Exception
     */
    private void transferFile(File file) throws Exception {
        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;
        final String USER = "sanjay";// logMonitorProperties.getProperty (FTP_USERNAME);
        final String HOST = "10.14.76.187";// logMonitorProperties.getProperty (DESTINATION_HOST);
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(USER, HOST);
            System.out.println("Session Host: " + session.getHost());
            session.setPassword("sanjay");
            Properties prop = new Properties();
            prop.put("StrictHostKeyChecking", "no");
            session.setConfig(prop);

            System.out.println("Waiting for connecting a session Host Server");
            session.connect();
            System.out.println("Host server session established successfully.");
            channel = session.openChannel("sftp");
            channel.connect();
            System.out.println("Connection established with sftp channel");
            channelSftp = (ChannelSftp) channel;
            channelSftp.put(new FileInputStream(file), file.getName());
            System.out.println("File Kept Successfully");
        } catch (FileNotFoundException | JSchException | SftpException e) {
            throw new Exception();
        } finally {
            if (channelSftp != null) {
                channelSftp.exit();
            }
            if (channel != null) {
                channel.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }
    }

    /**
     * Archive file to local destination directory.
     * 
     * @param fileName
     *            File instance.
     * @throws IE2Exception
     *             e Checked exception thrown to indicate IE2 Error.
     */
    private void archiveFile(File fileName) throws Exception {
        FileOutputStream fos = null;
        GZIPOutputStream gos = null;
        FileInputStream fis = null;

        // String currentTimeStamp = DateTimeFormat.forPattern ("YYYY-MM-DD.HH24MM").print (new DateTime ());

        try {
            String gzipDirectory = "./logs/archive/";
            fos = new FileOutputStream(gzipDirectory + fileName.getName() + ".gz");
            gos = new GZIPOutputStream(fos);
            fis = new FileInputStream(fileName);
            byte[] tmp = new byte[4 * 1024];
            int size = 0;
            while ((size = fis.read(tmp)) != -1) {
                gos.write(tmp, 0, size);
            }
            // fos.flush();
            // gos.flush();
            gos.finish();

        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (gos != null) {
                    gos.close();
                }
            } catch (IOException e) {
                throw new Exception();
            }
        }
    }

    public boolean deleteIfExists(File file) {
        System.out.println("Is File Directory: " + file.isDirectory());
        if (!file.isDirectory()) {
            System.out.println("file Delete: " + file.delete());
            return true;
        }
        return false;
    }


}
