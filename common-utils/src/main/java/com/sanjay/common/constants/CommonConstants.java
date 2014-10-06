/* Copyright (C) 2014, 2015 Sanjay Madnani
 * 
 * This file is free to use: you can redistribute it and/or modify it under the terms of the GPL General Public License
 * V2 as published by the Free Software Foundation, subject to the following conditions:
 * 
 * The above copyright notice should never be changed and should always included wherever this file is used.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY.
 * See the GNU General Public License V2 for more details. */
package com.sanjay.common.constants;

/**
 * @author SANJAY
 * 
 */
public final class CommonConstants {

    public static final Object NULL = null;

    public static final Integer ONE_KB = 1024;
    public static final Integer ONE_MB = ONE_KB * ONE_KB;
    public static final long ONE_GB = ONE_KB * ONE_MB;
    public static final String KB = "KB";
    public static final String MB = "MB";
    public static final String GB = "GB";
    public static final String BYTES = "bytes";
    public static final String FORWARD_SLASH = "/";
    public static final String GZ = ".gz";

    // Exception Messages
    public static final String EXCEPTION_STRING_APPENDER1 = "ApplicationRuntimeException: ";
    public static final String EXCEPTION_STRING_APPENDER2 = "Errorkey: ";
    public static final String EXCEPTION_STRING_APPENDER3 = ". Severity: ";
    public static final String EXCEPTION_STRING_APPENDER4 = ". Root Cause: ";
    public static final String EXCEPTION_STRING_APPENDER5 = "null";
    public static final String EXCEPTION_STRING_APPENDER6 = " Additional information associated to exception: ";

    // Regular Expression
    public static final String CONTAINS_ALPHABATE = "(.*?)[a-zA-Z]+(.*?)";
    public static final String CONTAINS_NUMBER = "(.*?)(\\d)+(.*?)";
    public static final String CONTAINS_SPECIAL_CHAR = "(.*?)[^a-zA-Z\\d]+(.*?)";

    private CommonConstants() {

    }
}
