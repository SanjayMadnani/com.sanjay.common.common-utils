/**
 * 
 */
package com.sanjay.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;

/**
 * @author SANJAY
 * 
 */
public class PropertyUtilTest {

    private PropertyUtil propertyUtil1;
    private PropertyUtil propertyUtil2;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        propertyUtil2 = new PropertyUtil("");
        propertyUtil1 = new PropertyUtil("", "");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        propertyUtil1 = null;
        propertyUtil2 = null;
    }

    /**
     * Test method for {@link com.sanjay.common.util.PropertyUtil#getValue(java.lang.String)}.
     */
    // @Test
    public void testGetValue() {
        assertEquals("", propertyUtil1.getValue(""));
        assertEquals("", propertyUtil2.getValue(""));
        fail("Not yet implemented");
    }

}
