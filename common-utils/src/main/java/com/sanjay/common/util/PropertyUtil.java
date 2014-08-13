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
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.text.MessageFormat;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sanjay.common.exception.ApplicationException;
import com.sanjay.common.exception.ApplicationSeverity;

/**
 * Utility for Loading & Retrieving Properties values.
 * 
 * @author SANJAY
 * @see Properties
 * @see MessageFormat
 */
public final class PropertyUtil {

    private static final Logger logger = LogManager.getLogger(PropertyUtil.class);
    private transient Properties properties;

    /**
     * Construct Object using the specified resourceBundleName.
     * 
     * @param baseName - the base name of the resource bundle.
     * @throws ApplicationException : IOException
     * @exception NullPointerException - if resourceBundleName is null.
     */
    public PropertyUtil(final String baseName) throws ApplicationException {
        logger.trace("Invoking Constructor with baseName: " + baseName + "...");
        try {
            properties = new Properties();
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(baseName);
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(e.getMessage(), ApplicationSeverity.ERROR, e.getCause(), e, baseName);
        }
    }

    /**
     * Construct Object using the specified resourceBundleName.
     * 
     * @param pathDirectory String directory of baseName.
     * @param baseName - the base name of the resource bundle.
     * @throws ApplicationException : IOException
     * @exception NullPointerException - if resourceBundleName is null.
     */
    public PropertyUtil(final String pathDirectory, final String baseName) throws ApplicationException {
        logger.trace("Invoking Constructor with path: " + pathDirectory + ", baseName: " + baseName + "...");
        try {
            properties = new Properties();
            Reader reader = new FileReader(new File(pathDirectory, baseName));
            properties.load(reader);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(e.getMessage(), ApplicationSeverity.ERROR, e.getCause(), e, pathDirectory,
                    baseName);
        }
    }

    /**
     * Returns value corresponding to key.
     * 
     * @param key the key whose associated value is to be returned.
     * @return value corresponding to key.
     */
    public Object getValue(String key) {
        logger.trace("Invoking getValue by passing key: " + key + "...");
        return properties.get(key);
    }
}
