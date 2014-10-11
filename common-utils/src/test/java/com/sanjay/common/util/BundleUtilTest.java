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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sanjay.common.exception.ApplicationException;

/**
 * Test case for {@link BundleUtil}.
 * 
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
        bundleUtil = new BundleUtil("test-common-messages");
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
    @Test
    public void testGetStringMessage() throws ApplicationException {
        assertEquals("MOBILE1000: Mobile Number is Invalid.", bundleUtil.getStringMessage("ERROR.MOBILE.1000"));
    }

    /**
     * Test method for
     * {@link com.sanjay.common.util.BundleUtil#getFormatedMessage(java.lang.String, java.lang.Object[])}.
     * 
     * @throws ApplicationException
     */
    @Test
    public void testGetFormatedMessage() throws ApplicationException {
        String actualResult = bundleUtil.getFormatedMessage("ACK.SUCCESS.1000", "user1", "454585");
        String expectedResult = "Dear user1, Your Registration Id is 454585.";
        assertEquals(expectedResult, actualResult);
    }

}
