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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;

import com.sanjay.common.exception.ApplicationException;

/**
 * @author SANJAY
 * 
 */
public class BundleUtilTest {

    private BundleUtil bundleUtil;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        bundleUtil = new BundleUtil("");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        bundleUtil = null;
    }

    /**
     * Test method for {@link com.sanjay.common.util.BundleUtil#getStringMessage(java.lang.String)}.
     * 
     * @throws ApplicationException
     */
    // @Test
    public void testGetStringMessage() throws ApplicationException {
        assertEquals("", bundleUtil.getStringMessage(""));
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link com.sanjay.common.util.BundleUtil#getFormatedMessage(java.lang.String, java.lang.Object[])}.
     * 
     * @throws ApplicationException
     */
    // @Test
    public void testGetFormatedMessage() throws ApplicationException {
        String actualResult = bundleUtil.getFormatedMessage("", "", "");
        String expectedResult = "";
        assertEquals(expectedResult, actualResult);
        fail("Not yet implemented");
    }

}
