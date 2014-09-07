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

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.sanjay.common.util.RandomGeneratorUtil.CombinationOf;

/**
 * @author SANJAY
 * 
 */
public class RandomGeneratorUtilTest {

    /**
     * Test method for
     * {@link com.sanjay.common.util.RandomGeneratorUtil#generatePassword(int, com.sanjay.common.util.RandomGeneratorUtil.CombinationOf)}
     * .
     */
    @Test
    public final void testGeneratePassword() {
        String password = RandomGeneratorUtil.generatePassword(6, CombinationOf.ALPHABET);
        System.out.println("Passowrd Alphabet: " + password);
        assertTrue(password.length() == 6);

        password = RandomGeneratorUtil.generatePassword(8, CombinationOf.NUMBER);
        System.out.println("Passowrd Number: " + password);
        assertTrue(password.length() == 8);
    }

    /**
     * Test method for {@link com.sanjay.common.util.RandomGeneratorUtil#generatePin(int)}.
     */
    @Test
    public final void testGeneratePinInt() {
        String pin = RandomGeneratorUtil.generatePin(6);
        System.out.println("Int Pin: " + pin);
        assertTrue(RandomGeneratorUtil.generatePin(5).length() == 5);
    }

    /**
     * Test method for {@link com.sanjay.common.util.RandomGeneratorUtil#generatePin(int, int)}.
     */
    @Test
    public final void testGeneratePinIntInt() {
        String pin = RandomGeneratorUtil.generatePin(2, 6);
        System.out.println("Min Max String Pin: " + pin);
        assertTrue(pin.length() >= 2 && pin.length() <= 6);
    }

    /**
     * Test method for {@link com.sanjay.common.util.RandomGeneratorUtil#randomIntValue(int, int)}.
     */
    @Test
    public final void testRandomIntValue() {
        int randomValue = RandomGeneratorUtil.randomIntValue(15, 700);
        System.out.println("RandomIntValue: " + randomValue);
        assertTrue(randomValue >= 15 && randomValue <= 700);
    }

    /**
     * Test method for {@link com.sanjay.common.util.RandomGeneratorUtil#randomDoubleValue(double, double)}.
     */
    @Test
    public final void testRandomDoubleValue() {
        double randomValue = RandomGeneratorUtil.randomDoubleValue(10.23, 81.678);
        System.out.println("Random Double Value: " + randomValue);
        assertTrue(randomValue >= 10.23 && randomValue <= 81.678);
    }

}
