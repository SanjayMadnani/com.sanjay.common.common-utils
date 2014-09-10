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

import com.sanjay.common.exception.ApplicationException;
import com.sanjay.common.exception.ApplicationSeverity;

/**
 * Utility for getting i18n value from Properties file.
 * 
 * @author SANJAY
 * @see ResourceBundle
 * @see Locale
 * @see MessageFormat
 */
public final class BundleUtil {
    private static final Logger LOG = LogManager.getLogger(BundleUtil.class);

    private transient ResourceBundle resourceBundle;

    /**
     * Construct Object using the specified resourceBundleName.
     * 
     * @param resourceBundleName - the base name of the resource bundle.
     * @throws ApplicationException if resource bundle is null.
     */
    public BundleUtil(final String resourceBundleName) throws ApplicationException {
        LOG.trace("Invoking 1 arg Constructor by bundle: " + resourceBundleName + "...");
        try {
            this.resourceBundle = ResourceBundle.getBundle(resourceBundleName);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new ApplicationException(e.getMessage(), ApplicationSeverity.ERROR, e.getCause(), e);
        }
    }

    /**
     * Construct Object using the specified resourceBundleName and locale.
     * 
     * @param resourceBundleName - the base name of the resource bundle.
     * @param locale - the locale for which a resource bundle is desired.
     * @throws ApplicationException: MissingResourceException, NullPointerException
     */
    public BundleUtil(final String resourceBundleName, final Locale locale) throws ApplicationException {
        LOG.trace("Invoking 2 args Constructor by bundle: " + resourceBundleName + "...");
        try {
            this.resourceBundle = ResourceBundle.getBundle(resourceBundleName, locale);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new ApplicationException(e.getMessage(), ApplicationSeverity.ERROR, e.getCause(), e);
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
     * @throws ApplicationException: MissingResourceException, ClassCastException, NullPointerException
     */
    public String getStringMessage(final String key) throws ApplicationException {
        LOG.trace("Invoking getStringMessage...");
        try {
            return this.resourceBundle.getString(key);
        } catch (NullPointerException | MissingResourceException | ClassCastException ex) {
            LOG.error(ex.getMessage(), ex);
            throw new ApplicationException(ex.getMessage(), ApplicationSeverity.ERROR, ex.getCause(), ex);
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
     * @throws ApplicationException : NullPointerException, MissingResourceException, ClassCastException,
     *             IllegalArgumentException
     */
    public String getFormatedMessage(final String key, final Object... arguments) throws ApplicationException {
        LOG.trace("Invoking getFormatedMessage...");
        try {
            return MessageFormat.format(this.resourceBundle.getString(key), arguments);
        } catch (NullPointerException | MissingResourceException | ClassCastException | IllegalArgumentException ex) {
            LOG.error(ex.getMessage(), ex);
            throw new ApplicationException(ex.getMessage(), ApplicationSeverity.ERROR, ex.getCause(), ex, arguments);
        }
    }
}
