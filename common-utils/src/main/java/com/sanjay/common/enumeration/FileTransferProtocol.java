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

public enum FileTransferProtocol {
    SFTP ("sftp");

    private static Map<String, FileTransferProtocol> mapRepresentation;
    private String ftpStringRepresentation;

    private FileTransferProtocol(String ftpStringRepresentation) {
        this.ftpStringRepresentation = ftpStringRepresentation;
    }

    public String ftpStringRepresentation() {
        return this.ftpStringRepresentation;
    }

    public static FileTransferProtocol valueOfFtpStringRepresentation(String ftpStringRepresentation) {
        if (mapRepresentation == null) {
            mapRepresentation = new HashMap<String, FileTransferProtocol>();
            for (FileTransferProtocol types : values()) {
                mapRepresentation.put(types.ftpStringRepresentation, types);
            }
        }
        return mapRepresentation.get(ftpStringRepresentation);
    }
}
