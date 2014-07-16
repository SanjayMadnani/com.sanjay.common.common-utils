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

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility for getting i18n value from Properties file.
 * 
 * @author SANJAY
 * @see ResourceBundle
 * @see Locale
 * @see MessageFormat
 */
public final class BundleUtil {
    private static final Logger logger = LogManager.getLogger(BundleUtil.class);

    private transient ResourceBundle resourceBundle;

    /**
     * Construct Object using the specified resourceBundleName.
     * 
     * @param resourceBundleName - the base name of the resource bundle.
     * @exception NullPointerException - if resourceBundleName is null.
     */
    public BundleUtil(final String resourceBundleName) {
        logger.debug("Invoking 1 arg Constructor by bundle: " + resourceBundleName + "...");
        try {
            this.resourceBundle = ResourceBundle.getBundle(resourceBundleName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            // TODO throw your custom exception
        }
    }

    /**
     * Construct Object using the specified resourceBundleName and locale.
     * 
     * @param resourceBundleName - the base name of the resource bundle.
     * @param locale - the locale for which a resource bundle is desired.
     * @exception NullPointerException - if resourceBundleName is null.
     * @exception MissingResourceException - if no resource bundle for the specified base name can be found.
     */
    public BundleUtil(final String resourceBundleName, final Locale locale) {
        logger.debug("Invoking 2 args Constructor by bundle: " + resourceBundleName + "...");        
        try {
            this.resourceBundle = ResourceBundle.getBundle(resourceBundleName, locale);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            // TODO throw your custom exception
        }
    }

    /**
     * Gets a string for the given key.
     * 
     * @param key - the key for the desired string.
     * @exception NullPointerException if key is null.
     * @exception MissingResourceException if no object for the given key can be found.
     * @exception ClassCastException if the object found for the given key is not a string.
     * @return the string for the given key.
     */
    public String getStringMessage(final String key) {
        logger.debug("Invoking getStringMessage...");
        try {
            return this.resourceBundle.getString(key);
        } catch (NullPointerException | MissingResourceException | ClassCastException ex) {
            logger.error(ex.getMessage(), ex);
            // TODO throw your custom exception rather than returning null.
            return null;
        }
    }

    /**
     * Gets a string for the given key and formats string with passed arguments.
     * 
     * @param key - the key for the desired string.
     * @param arguments - parameters for formatting string.
     * @exception NullPointerException if key is null.
     * @exception MissingResourceException if no object for the given key can be found.
     * @exception ClassCastException if the object found for the given key is not a string.
     * @exception IllegalArgumentException if the value of key is invalid, or if an argument in the arguments array is
     *                not of the type expected by the format element(s) that use it.
     * @return the formated string for the given key.
     */
    public String getFormatedMessage(final String key, final Object... arguments) {
        logger.debug("Invoking getFormatedMessage...");
        try {
            return MessageFormat.format(this.resourceBundle.getString(key), arguments);
        } catch (NullPointerException | MissingResourceException | ClassCastException | IllegalArgumentException ex) {
            logger.error(ex.getMessage(), ex);
         // TODO throw your custom exception rather than returning null.
            return null;
        }
    }
}
