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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sanjay.common.constants.CommonConstants;

/**
 * Utility to generate random password, double number, int number with criteria.
 * 
 * @author SANJAY
 * @see RandomGeneratorUtil.CombinationOf
 */
public class RandomGeneratorUtil {
    private static final Logger LOG = LogManager.getLogger(RandomGeneratorUtil.class);
    private static final List<Integer> ALPHABET_LIST;
    private static final List<Integer> SPECIAL_CHAR_LIST;
    private static final Random RANDOM_OBJ = new Random();

    private RandomGeneratorUtil() {

    }

    static {
        // Alphabet list
        ALPHABET_LIST = new ArrayList<Integer>();
        for (int i = 65; i <= 90; i++) {
            ALPHABET_LIST.add(i);
        }
        for (int i = 97; i <= 122; i++) {
            ALPHABET_LIST.add(i);
        }
        LOG.trace("ALPHABET_LIST created");
        // Special Character List
        SPECIAL_CHAR_LIST = new ArrayList<Integer>();
        for (int i = 33; i <= 64; i++) {
            if ((i > 33 && i < 42) || (i > 43 && i < 58)) {
                continue;
            }
            SPECIAL_CHAR_LIST.add(i);
        }
        LOG.trace("SPECIAL_CHAR_LIST created");
    }

    /**
     * Enumeration required for password combination {@link RandomGeneratorUtil#generatePassword(int, CombinationOf)}.
     * 
     * @author SANJAY
     * @see RandomGeneratorUtil
     */
    public static enum CombinationOf {
        ALPHABET, NUMBER, ALPHABET_NUMBER, ALPHABET_SPECIALCHARACTER, NUMBER_SPECIALCHARACTER,
        ALPHABET_NUMBER_SPECIALCHARACTER;
    }

    /**
     * Returns a random {@code String} password of a {@code int} size with {@code CombinationOf} {@link CombinationOf}.
     * 
     * @param size {@code int} value of a password, should not less than 3 otherwise IllegalArgumentException will be
     *            thrown:
     * 
     *            <pre>
     * {@code
     *  if (size < 3) {
     *             LOG.error("Password size is less then 3");
     *             throw new IllegalArgumentException("Invalid password size: " + size);
     *         }
     * }
     * </pre>
     * @param passwordCombination {@code CombinationOf} enumeration value for password combination.
     * @return a random {@code String} password of a {@code int} size with {@code CombinationOf} {@link CombinationOf}.
     * @throws IllegalArgumentException if {@code int size is less than 3}.
     */
    public static String generatePassword(final int size, final CombinationOf passwordCombination) {
        LOG.trace("Invoking generatePassword of size" + size + "and CobinationOf: " + passwordCombination);
        if (size < 3) {
            LOG.error("Password size is less then 3");
            throw new IllegalArgumentException("Invalid password size: " + size);
        }
        StringBuilder passwordBuffer = new StringBuilder();
        for (int i = 0; i < size; i++) {
            switch (passwordCombination) {
                case ALPHABET:
                    passwordBuffer.append(getPasswordCharacter(0));
                    break;
                case NUMBER:
                    passwordBuffer.append(getPasswordCharacter(1));
                    break;
                case ALPHABET_NUMBER:
                    passwordBuffer.append(getPasswordCharacter(randomIntValue(0, 2)));
                    break;
                case ALPHABET_SPECIALCHARACTER:
                    int randomNo = randomIntValue(0, 2);
                    final String randomValue = (randomNo == 0) ? getPasswordCharacter(0) : getPasswordCharacter(2);
                    passwordBuffer.append(randomValue);
                    break;
                case NUMBER_SPECIALCHARACTER:
                    passwordBuffer.append(getPasswordCharacter(randomIntValue(1, 3)));
                    break;
                case ALPHABET_NUMBER_SPECIALCHARACTER:
                    passwordBuffer.append(getPasswordCharacter(randomIntValue(0, 3)));
                    break;
                default:
                    break;
            }
        }
        return correctPassword(passwordBuffer.toString(), passwordCombination);
    }

    private static String correctPassword(final String strPassword, final CombinationOf passwordCombination) {
        LOG.trace("Invoking correctPassword...");
        switch (passwordCombination) {
            case ALPHABET_NUMBER:
                if ( !strPassword.matches(CommonConstants.CONTAINS_ALPHABATE)) {
                    strPassword.replace(strPassword.charAt(randomIntValue(0, strPassword.length())),
                            getPasswordCharacter(0).charAt(0));
                } else if ( !strPassword.matches(CommonConstants.CONTAINS_NUMBER)) {
                    strPassword.replace(strPassword.charAt(randomIntValue(0, strPassword.length())),
                            getPasswordCharacter(1).charAt(0));
                }
                break;
            case ALPHABET_SPECIALCHARACTER:
                if ( !strPassword.matches(CommonConstants.CONTAINS_ALPHABATE)) {
                    strPassword.replace(strPassword.charAt(randomIntValue(0, strPassword.length())),
                            getPasswordCharacter(0).charAt(0));
                } else if ( !strPassword.matches(CommonConstants.CONTAINS_SPECIAL_CHAR)) {
                    strPassword.replace(strPassword.charAt(randomIntValue(0, strPassword.length())),
                            getPasswordCharacter(2).charAt(0));
                }
                break;
            case NUMBER_SPECIALCHARACTER:
                if ( !strPassword.matches(CommonConstants.CONTAINS_NUMBER)) {
                    strPassword.replace(strPassword.charAt(randomIntValue(0, strPassword.length())),
                            getPasswordCharacter(1).charAt(0));
                } else if ( !strPassword.matches(CommonConstants.CONTAINS_SPECIAL_CHAR)) {
                    strPassword.replace(strPassword.charAt(randomIntValue(0, strPassword.length())),
                            getPasswordCharacter(2).charAt(0));
                }
                break;
            case ALPHABET_NUMBER_SPECIALCHARACTER:
                if ( !strPassword.matches(CommonConstants.CONTAINS_ALPHABATE)) {
                    strPassword.replace(strPassword.charAt(0), getPasswordCharacter(0).charAt(0));
                }
                if ( !strPassword.matches(CommonConstants.CONTAINS_NUMBER)) {
                    strPassword.replace(strPassword.charAt(1), getPasswordCharacter(1).charAt(0));
                }
                if ( !strPassword.matches(CommonConstants.CONTAINS_SPECIAL_CHAR)) {
                    strPassword.replace(strPassword.charAt(2), getPasswordCharacter(2).charAt(0));
                }
                break;
            default:
                break;
        }
        return strPassword;
    }

    private static String getPasswordCharacter(final int combination) {
        LOG.trace("Invoking getPasswordCharacter...");
        switch (combination) {
            case 0:
                // Alphabet
                int randomInt = randomIntValue(0, ALPHABET_LIST.size());
                final Character alphabet = (char) ALPHABET_LIST.get(randomInt).intValue();
                return alphabet.toString();
            case 1:
                // Number
                return RANDOM_OBJ.nextInt(10) + "";
            case 2:
                // Special character
                randomInt = randomIntValue(0, SPECIAL_CHAR_LIST.size());
                final Character specialCharacter = (char) SPECIAL_CHAR_LIST.get(randomInt).intValue();
                return specialCharacter.toString();
            default:
                LOG.error("Invalid argument: " + combination);
                throw new IllegalArgumentException("Invalid combination: " + combination);
        }
    }

    /**
     * Returns a random {@code String} representation of int value of length {@code int} abs size {@link Math#abs(int)}.
     * 
     * @param size the length of random number to be returned as String.
     * @return a random {@code String} value of length {@code int} abs size {@link Math#abs(int)}.
     */
    public static String generatePin(final int size) {
        LOG.trace("Invoking generatePin of size: " + size);
        final StringBuilder randomPin = new StringBuilder();
        for (int i = 0; i < Math.abs(size); i++) {
            randomPin.append(RANDOM_OBJ.nextInt(10));
        }
        return randomPin.toString();
    }

    /**
     * Returns a random {@code String} representation of int value of random length from {@code minimumSize} to
     * {@code maximumSize}.
     * 
     * @param minimumSize (inclusive) minimum length of random number.
     * @param maximumSize (exclusive) maximum length of random number.
     * @return a random {@code String} representation of int value of random length from {@code minimumSize} to
     *         {@code maximumSize}.
     * @throws IllegalArgumentException as if:
     * 
     *             <pre>
     * {@code maximumSize - minimumSize} is not positive.
     * </pre>
     * @see RandomGeneratorUtil#randomIntValue(int, int)
     * @see RandomGeneratorUtil#generatePin(int)
     */
    public static String generatePin(final int minimumSize, final int maximumSize) {
        LOG.trace("Invoking generatePin with minimumSize: " + maximumSize + ", MaximumSize: " + maximumSize);
        final int size = randomIntValue(minimumSize, maximumSize);
        return generatePin(size);
    }

    /**
     * Returns a {@code int} value between {@code int minimumValue}(inclusive) and the {@code int maximumValue}
     * (exclusive).
     * 
     * @param minimumValue (inclusive) random number minimum value.
     * @param maximumValue (exclusive) random number value less than.
     * @return a {@code int} value between {@code int minimumValue}(inclusive) and the {@code int maximumValue}
     *         (exclusive).
     * @throws IllegalArgumentException as if:
     * 
     *             <pre>
     * {@code maximumValue - minimumValue} is not positive.
     * </pre>
     */
    public static int randomIntValue(final int minimumValue, final int maximumValue) {
        LOG.trace("Invoking randomIntValue with minimumValue: " + minimumValue + ", maximumValue: " + maximumValue);
        return minimumValue + RANDOM_OBJ.nextInt(maximumValue - minimumValue);
    }

    /**
     * Returns a {@code double} value , greater than or equal to {@code double minimumValue} and less than
     * {@code double maximumValue}.
     * 
     * @param minimumValue (inclusive) random number minimum value.
     * @param maximumValue (exclusive) random number value less than.
     * @return a {@code double} value , greater than or equal to {@code double minimumValue} and less than
     *         {@code double maximumValue}.
     */
    public static double randomDoubleValue(final double minimumValue, final double maximumValue) {
        LOG.trace("Invoking randomDoubleValue with minimumValue: " + minimumValue + ", maximumValue: " + maximumValue);
        return minimumValue + Math.random() * (maximumValue - minimumValue);
    }
}
