/* Copyright (C) 2014, 2015 Sanjay Madnani
 * 
 * This file is free to use: you can redistribute it and/or modify it under the terms of the GPL General Public
 * License
 * V2 as published by the Free Software Foundation, subject to the following conditions:
 * 
 * The above copyright notice should never be changed and should always included wherever this file is used.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY.
 * See the GNU General Public License V2 for more details. */
package com.sanjay.common.exception;

import static org.junit.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * Test case for {@link ApplicationSeverity}.
 * 
 * @author SANJAY
 * 
 */
public class ApplicationSeverityTest {

    private static final Logger LOGGER = LogManager.getLogger(ApplicationSeverityTest.class);

    /**
     * Test case for {@link ApplicationSeverity}.
     */
    @Test
    public void testApplicationSeverity() {
        LOGGER.trace("Invoking testApplicationSeverity...");
        assertEquals("DEBUG", ApplicationSeverity.DEBUG.name());
        assertEquals("INFO", ApplicationSeverity.INFO.name());
        assertEquals("WARN", ApplicationSeverity.WARN.name());
        assertEquals("ERROR", ApplicationSeverity.ERROR.name());
        assertEquals("FATAL", ApplicationSeverity.FATAL.name());
    }

}
