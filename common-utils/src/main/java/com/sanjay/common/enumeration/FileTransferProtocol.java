/* Copyright (C) 2014, 2015 Sanjay Madnani
 * 
 * This file is free to use: you can redistribute it and/or modify it under the terms of the GPL General Public License
 * V2 as published by the Free Software Foundation, subject to the following conditions:
 * 
 * The above copyright notice should never be changed and should always included wherever this file is used.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY.
 * See the GNU General Public License V2 for more details. */
package com.sanjay.common.enumeration;

import java.util.HashMap;
import java.util.Map;

/**
 * Keeps protocols names for file transfer.
 * 
 * @author SANJAY
 * @see com.sanjay.common.util.FileUtil#transferFile(String, String, String, java.io.File, FileTransferProtocol)
 * 
 */
public enum FileTransferProtocol {
    /**
     * Secure File Transfer Protocol
     */
    SFTP ("sftp");

    private static Map<String, FileTransferProtocol> mapRepresentation;
    private final String ftpStringRepresentation;

    private FileTransferProtocol(final String ftpStringRepresentation) {
        this.ftpStringRepresentation = ftpStringRepresentation;
    }

    /**
     * Return String value of a caller enumeration.
     * 
     * @return String value of a enumeration.
     */
    public String ftpStringRepresentation() {
        return this.ftpStringRepresentation;
    }

    /**
     * Maintains all Enumeration in a map where key is String value of enumeration and map value is enumeration
     * constant.
     * 
     * @param ftpStringRepresentation String value corresponding to key.
     * @return Enumeration constant corresponding to string key.
     */
    public static FileTransferProtocol valueOfFtpStringRepresentation(final String ftpStringRepresentation) {
        if (mapRepresentation == null) {
            mapRepresentation = new HashMap<String, FileTransferProtocol>();
            for (FileTransferProtocol types : values()) {
                mapRepresentation.put(types.ftpStringRepresentation, types);
            }
        }
        return mapRepresentation.get(ftpStringRepresentation);
    }
}
