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

import java.util.Random;

/**
 * @author SANJAY
 * 
 */
public class RandomGeneratorUtil {
    private static Random random = new Random();

    // TODO Implementation Pending.
    public static String generatePassword(int size) {
        return null;
    }

    public static String generatePin(int size) {
        StringBuffer randomPin = new StringBuffer();
        for (int i = 0; i < size; i++) {
            randomPin.append(random.nextInt(9));
        }
        return randomPin.toString();
    }

    public static int generatePin(int minimumSize, int maximumSize) {
        int size = randomIntValue(minimumSize, maximumSize);
        int number = 0;
        for (int j = 1, i = 0; i < size; i++) {
            number += randomIntValue(0, 9) * j;
            j = j * 10;
        }
        return number;
    }

    public static int randomIntValue(int minimumValue, int maximumValue) {
        return (minimumValue + random.nextInt(maximumValue - minimumValue));
    }

    public static double randomDoubleValue(double minimumValue, double maximumValue) {
        return (minimumValue + (Math.random() * (maximumValue - minimumValue)));
    }
}
