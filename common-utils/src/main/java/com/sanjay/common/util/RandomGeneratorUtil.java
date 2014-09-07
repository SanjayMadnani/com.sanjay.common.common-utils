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

/**
 * @author SANJAY
 * 
 */
// TODO Logger & Exception implementation pending.
public class RandomGeneratorUtil {
    public static enum CombinationOf {
        ALPHABET, NUMBER, ALPHABET_NUMBER, ALPHABET_SPECIALCHARACTER, NUMBER_SPECIALCHARACTER,
        ALPHABET_NUMBER_SPECIALCHARACTER;
    }

    private static Random random = new Random();

    // TODO Implementation Pending.
    public static String generatePassword(int size, CombinationOf passwordCombination) {
        if (size < 3) {
            // TODO write log and throw exception.
        }
        List<Integer> alphabetList = getAlphabetList();
        // TODO Delete below line if not required at all.
        // List<Integer> numberList = getNumberList();
        List<Integer> specialCharacterList = getSpecialCharacterList();
        StringBuffer passwordBuffer = new StringBuffer();
        for (int i = 0; i < size; i++)
            switch (passwordCombination) {
                case ALPHABET:
                    int randomInt = randomIntValue(0, alphabetList.size());
                    char randomChar = (char) alphabetList.get(randomInt).intValue();
                    passwordBuffer.append(randomChar);
                    break;
                case NUMBER:
                    passwordBuffer.append(random.nextInt(10));
                    break;
                case ALPHABET_NUMBER:
                    // TODO create a function which will give ALPHABET, Number & Special Char as per int value
                    break;
                case ALPHABET_SPECIALCHARACTER:

                    break;
                case NUMBER_SPECIALCHARACTER:

                    break;
                case ALPHABET_NUMBER_SPECIALCHARACTER:

                    break;
                default:
                    // throw Exception
                    break;
            }
        // TODO check password combination
        // if all then random number from 1 to 3, where 1 is alphabet, 2 is number and 3 is specialchar
        // in last if combination is not appropriate then replace any random word to construct proper combination.
        return passwordBuffer.toString();
    }

    // TODO Delete below commented code if not required at all.
    // private static List<Integer> getNumberList() {
    // List<Integer> numberList = new ArrayList<Integer>();
    // for (int i = 48; i <= 57; i++) {
    // numberList.add(i);
    // }
    // return numberList;
    // }

    private static List<Integer> getSpecialCharacterList() {
        List<Integer> specialCharacterList = new ArrayList<Integer>();
        for (int i = 33; i <= 64; i++) {
            if ((i > 33 && i < 42) || (i > 43 && i < 58)) {
                continue;
            }
            specialCharacterList.add(i);
        }
        return specialCharacterList;
    }

    private static List<Integer> getAlphabetList() {
        List<Integer> alphabetList = new ArrayList<Integer>();
        for (int i = 65; i <= 90; i++) {
            alphabetList.add(i);
        }
        for (int i = 97; i <= 122; i++) {
            alphabetList.add(i);
        }
        return alphabetList;
    }

    public static String generatePin(int size) {
        StringBuffer randomPin = new StringBuffer();
        for (int i = 0; i < size; i++) {
            randomPin.append(random.nextInt(10));
        }
        return randomPin.toString();
    }

    public static String generatePin(int minimumSize, int maximumSize) {
        int size = randomIntValue(minimumSize, maximumSize) + 1;
        return generatePin(size);
    }

    public static int randomIntValue(int minimumValue, int maximumValue) {
        return (minimumValue + random.nextInt(maximumValue - minimumValue));
    }

    public static double randomDoubleValue(double minimumValue, double maximumValue) {
        return (minimumValue + (Math.random() * (maximumValue - minimumValue)));
    }
}
