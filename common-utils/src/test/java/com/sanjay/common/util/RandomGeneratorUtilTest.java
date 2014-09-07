/**
 * 
 */
package com.sanjay.common.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sanjay.common.util.RandomGeneratorUtil.CombinationOf;

/**
 * @author SANJAY
 * 
 */
public class RandomGeneratorUtilTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link com.sanjay.common.util.RandomGeneratorUtil#generatePassword(int, com.sanjay.common.util.RandomGeneratorUtil.CombinationOf)}
     * .
     */
    @Test
    public final void testGeneratePassword() {
        String password = RandomGeneratorUtil.generatePassword(6, CombinationOf.ALPHABET);
        System.out.println("Passowrd: " + password);
        assertTrue(password.length() == 6);
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
        int pin = RandomGeneratorUtil.generatePin(2, 6);
        System.out.println("Min Max Int Pin: " + pin);
        assertTrue(pin > 9 && pin < 1000000);
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
