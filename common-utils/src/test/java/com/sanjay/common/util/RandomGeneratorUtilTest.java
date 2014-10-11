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

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.sanjay.common.constants.CommonConstants;
import com.sanjay.common.util.RandomGeneratorUtil.CombinationOf;

/**
 * Test case for {@link RandomGeneratorUtil}.
 * 
 * @author SANJAY
 * 
 */
public class RandomGeneratorUtilTest {

    private static final Logger LOG = LogManager.getLogger(RandomGeneratorUtilTest.class);

    /**
     * Test method for {@link com.sanjay.common.util.RandomGeneratorUtil#generatePassword(int, CombinationOf)} .
     */
    @Test
    public final void testGeneratePassword() {
        LOG.trace("Invoking testGeneratePassword...");
        int passwordSize = 2;
        try {
            RandomGeneratorUtil.generatePassword(passwordSize, CombinationOf.ALPHABET);
        } catch (IllegalArgumentException ex) {
            LOG.debug(ex.getMessage());
            assertEquals("Invalid password size: " + passwordSize, ex.getMessage());
        }
    }

    /**
     * Test method for {@link com.sanjay.common.util.RandomGeneratorUtil#generatePassword(int, CombinationOf)} .
     */
    @Test
    public final void testGeneratePasswordAlphabet() {
        LOG.trace("Invoking testGeneratePasswordAlphabet...");
        String password = RandomGeneratorUtil.generatePassword(6, CombinationOf.ALPHABET);
        LOG.debug("6 digit ALPHABET password: " + password);
        assertTrue(password.length() == 6);
        assertTrue(password.matches("[a-zA-Z]+"));
    }

    /**
     * Test method for {@link com.sanjay.common.util.RandomGeneratorUtil#generatePassword(int, CombinationOf)} .
     */
    @Test
    public final void testGeneratePasswordNumber() {
        LOG.trace("Invoking testGeneratePasswordNumber...");
        String password = RandomGeneratorUtil.generatePassword(8, CombinationOf.NUMBER);
        LOG.debug("8 digit NUMBER password: " + password);
        assertTrue(password.length() == 8);
        assertTrue(password.matches("[\\d]+"));
    }

    /**
     * Test method for {@link com.sanjay.common.util.RandomGeneratorUtil#generatePassword(int, CombinationOf)} .
     */
    @Test
    public final void testGeneratePasswordAlphabetNumber() {
        LOG.trace("Invoking testGeneratePasswordAlphabetNumber...");
        String password = RandomGeneratorUtil.generatePassword(15, CombinationOf.ALPHABET_NUMBER);
        LOG.debug("15 digit ALPHABET_NUMBER password: " + password);
        assertTrue(password.length() == 15);
        assertTrue(password.matches("[\\p{Alnum}]+"));
        assertTrue(password.matches(CommonConstants.CONTAINS_ALPHABATE));
        assertTrue(password.matches(CommonConstants.CONTAINS_NUMBER));
    }

    /**
     * Test method for {@link com.sanjay.common.util.RandomGeneratorUtil#generatePassword(int, CombinationOf)} .
     */
    @Test
    public final void testGeneratePasswordAlphabetSpecialcharacter() {
        LOG.trace("Invoking testGeneratePasswordAlphabetSpecialcharacter...");
        String password = RandomGeneratorUtil.generatePassword(9, CombinationOf.ALPHABET_SPECIALCHARACTER);
        LOG.debug("9 digit ALPHABET_SPECIALCHARACTER password: " + password);
        assertTrue(password.length() == 9);
        assertTrue(password.matches(CommonConstants.CONTAINS_ALPHABATE));
        assertFalse(password.matches(CommonConstants.CONTAINS_NUMBER));
        assertTrue(password.matches(CommonConstants.CONTAINS_SPECIAL_CHAR));
    }

    /**
     * Test method for {@link com.sanjay.common.util.RandomGeneratorUtil#generatePassword(int, CombinationOf)} .
     */
    @Test
    public final void testGeneratePasswordNumberSpecialCharacter() {
        LOG.trace("Invoking testGeneratePasswordNumberSpecialCharacter...");
        String password = RandomGeneratorUtil.generatePassword(11, CombinationOf.NUMBER_SPECIALCHARACTER);
        LOG.debug("11 digit NUMBER_SPECIALCHARACTER password: " + password);
        assertTrue(password.length() == 11);
        assertFalse(password.matches(CommonConstants.CONTAINS_ALPHABATE));
        assertTrue(password.matches(CommonConstants.CONTAINS_NUMBER));
        assertTrue(password.matches(CommonConstants.CONTAINS_SPECIAL_CHAR));
    }

    /**
     * Test method for {@link com.sanjay.common.util.RandomGeneratorUtil#generatePassword(int, CombinationOf)} .
     */
    @Test
    public final void testGeneratePasswordAlphabetNumberSpecialcharacter() {
        LOG.trace("Invoking testGeneratePasswordAlphabetNumberSpecialcharacter...");
        String password = RandomGeneratorUtil.generatePassword(16, CombinationOf.ALPHABET_NUMBER_SPECIALCHARACTER);
        LOG.debug("16 digit ALPHABET_NUMBER_SPECIALCHARACTER password: " + password);
        assertTrue(password.length() == 16);
        assertTrue(password.matches(CommonConstants.CONTAINS_ALPHABATE));
        assertTrue(password.matches(CommonConstants.CONTAINS_NUMBER));
        assertTrue(password.matches(CommonConstants.CONTAINS_SPECIAL_CHAR));
    }

    /**
     * Test method for {@link com.sanjay.common.util.RandomGeneratorUtil#generatePin(int)}.
     */
    @Test
    public final void testGeneratePinInt() {
        LOG.trace("Invoking testGeneratePinInt...");
        String pin = RandomGeneratorUtil.generatePin(6);
        LOG.debug("Int Pin: " + pin);
        assertTrue(RandomGeneratorUtil.generatePin(5).length() == 5);
    }

    /**
     * Test method for {@link com.sanjay.common.util.RandomGeneratorUtil#generatePin(int, int)}.
     */
    @Test
    public final void testGeneratePinIntInt() {
        LOG.trace("Invoking testGeneratePinIntInt...");
        String pin = RandomGeneratorUtil.generatePin(2, 6);
        LOG.debug("Min Max String Pin: " + pin);
        assertTrue(pin.length() >= 2 && pin.length() <= 6);
    }

    /**
     * Test method for {@link com.sanjay.common.util.RandomGeneratorUtil#randomIntValue(int, int)}.
     */
    @Test
    public final void testRandomIntValue() {
        LOG.trace("Invoking testRandomIntValue...");
        int randomValue = RandomGeneratorUtil.randomIntValue(15, 700);
        LOG.debug("RandomIntValue: " + randomValue);
        assertTrue(randomValue >= 15 && randomValue <= 700);
    }

    /**
     * Test method for {@link com.sanjay.common.util.RandomGeneratorUtil#randomDoubleValue(double, double)}.
     */
    @Test
    public final void testRandomDoubleValue() {
        LOG.trace("Invoking testRandomDoubleValue...");
        double randomValue = RandomGeneratorUtil.randomDoubleValue(10.23, 81.678);
        LOG.debug("Random Double Value: " + randomValue);
        assertTrue(randomValue >= 10.23 && randomValue <= 81.678);
    }

}
