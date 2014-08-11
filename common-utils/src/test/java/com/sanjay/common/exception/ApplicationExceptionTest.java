/* Copyright (C) 2014, 2015 Sanjay Madnani
 * 
 * This file is free to use: you can redistribute it and/or modify it under the terms of the GPL General Public License
 * V2 as published by the Free Software Foundation, subject to the following conditions:
 * 
 * The above copyright notice should never be changed and should always included wherever this file is used.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY.
 * See the GNU General Public License V2 for more details. */
package com.sanjay.common.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * @author sanjay.madnani
 * 
 */
public class ApplicationExceptionTest {

    /**
     * Test method for
     * {@link com.sanjay.common.exception.ApplicationException#ApplicationException(java.lang.String, com.sanjay.common.exception.ApplicationSeverity, java.lang.Throwable, java.lang.String[])}
     * .
     */
    @Test
    public void testApplicationExceptionStringApplicationSeverityThrowableStringArray() {
        try {
            throw new ApplicationException("INVALID_DATA1", ApplicationSeverity.ERROR, new Throwable("Cause1"),
                    "test case1");
        } catch (ApplicationException e) {
            assertEquals("INVALID_DATA1", e.getErrorKey());
            assertEquals(ApplicationSeverity.ERROR, e.getSeverity());
            String[] param = e.getParams();
            assertEquals("test case1", param[0]);
        }
    }

    /**
     * Test method for
     * {@link com.sanjay.common.exception.ApplicationException#ApplicationException(java.lang.String, com.sanjay.common.exception.ApplicationSeverity, java.lang.Throwable, java.lang.Object[])}
     * .
     */
    @Test
    public void testApplicationExceptionStringApplicationSeverityThrowableObjectArray() {
        Exception ex = null;
        try {
            throw new ApplicationException("INVALID_DATA2", ApplicationSeverity.ERROR, new Throwable("Cause2"), ex);
        } catch (ApplicationException e) {
            assertEquals("INVALID_DATA2", e.getErrorKey());
            assertEquals(ApplicationSeverity.ERROR, e.getSeverity());
            Object[] param = e.getObjects();
            assertEquals(null, param[0]);
        }
    }

    /**
     * Test method for
     * {@link com.sanjay.common.exception.ApplicationException#ApplicationException(java.lang.String, com.sanjay.common.exception.ApplicationSeverity, java.lang.Throwable)}
     * .
     */
    @Test
    public void testApplicationExceptionStringApplicationSeverityThrowable() {
        try {
            throw new ApplicationException("INVALID_DATA3", ApplicationSeverity.ERROR, new Throwable("Cause3"));
        } catch (ApplicationException e) {
            assertEquals("INVALID_DATA3", e.getErrorKey());
            assertEquals(ApplicationSeverity.ERROR, e.getSeverity());
            assertEquals("java.lang.Throwable: Cause3", e.getMessage());
        }
    }

    /**
     * Test method for
     * {@link com.sanjay.common.exception.ApplicationException#ApplicationException(int, com.sanjay.common.exception.ApplicationSeverity, java.lang.Throwable, java.lang.Object[])}
     * .
     */
    @Test
    public void testApplicationExceptionIntApplicationSeverityThrowableObjectArray() {
        Exception ex = null;
        try {
            throw new ApplicationException(10, ApplicationSeverity.ERROR, new Throwable("Cause4"), ex);
        } catch (ApplicationException e) {
            assertEquals(10, e.getErrorCode().intValue());
            assertEquals(ApplicationSeverity.ERROR, e.getSeverity());
            Object[] param = e.getObjects();
            assertEquals(null, param[0]);
        }
    }

    /**
     * Test method for
     * {@link com.sanjay.common.exception.ApplicationException#ApplicationException(java.lang.String, com.sanjay.common.exception.ApplicationSeverity)}
     * .
     */
    @Test
    public void testApplicationExceptionStringApplicationSeverity() {
        try {
            throw new ApplicationException("INVALID_DATA5", ApplicationSeverity.ERROR);
        } catch (ApplicationException e) {
            assertEquals("INVALID_DATA5", e.getErrorKey());
            assertEquals(ApplicationSeverity.ERROR, e.getSeverity());
        }
    }

    /**
     * Test method for
     * {@link com.sanjay.common.exception.ApplicationException#ApplicationException(int, com.sanjay.common.exception.ApplicationSeverity)}
     * .
     */
    @Test
    public void testApplicationExceptionIntApplicationSeverity() {
        try {
            throw new ApplicationException(10, ApplicationSeverity.ERROR);
        } catch (ApplicationException e) {
            assertEquals(10, e.getErrorCode().intValue());
            assertEquals(ApplicationSeverity.ERROR, e.getSeverity());
        }
    }

    /**
     * Test method for
     * {@link com.sanjay.common.exception.ApplicationException#ApplicationException(int, java.lang.Throwable)}.
     */
    @Test
    public void testApplicationExceptionIntThrowable() {
        try {
            throw new ApplicationException(10, new Throwable("Cause6"));
        } catch (ApplicationException e) {
            assertEquals(10, e.getErrorCode().intValue());
            assertNotNull(e.getMessage());
        }
    }

    /**
     * Test method for {@link com.sanjay.common.exception.ApplicationException#ApplicationException(java.lang.String)}.
     */
    @Test
    public void testApplicationExceptionString() {
        try {
            throw new ApplicationException("INVALID_DATA7");
        } catch (ApplicationException e) {
            assertEquals("INVALID_DATA7", e.getErrorKey());
        }
    }

    /**
     * Test method for {@link com.sanjay.common.exception.ApplicationException#toString()}.
     */
    @Test
    public void testToString() {
        String exceptionString =
                new ApplicationException("INVALID_DATA8", ApplicationSeverity.ERROR, new Throwable("Cause8"),
                        "test case8").toString();
        assertNotNull(exceptionString);
        assertEquals(
                "ApplicationRuntimeException: Errorkey: INVALID_DATA8. Severity: ERROR. Root Cause: java.lang.Throwable: Cause8 Additional information associated to exception: [test case8]",
                exceptionString);
    }

}
