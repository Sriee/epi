/**
 * 
 */
package com.test.primitives;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import primitives.Mul;

/**
 * @author sriee
 */
public class MultiplyTest {

    @Test
    public void checkNegativeResult() {
        assertTrue(Mul.multiply(-123, 456) < 0);
        assertTrue(Mul.multiply(123, -46) < 0);
        assertFalse(Mul.multiply(123, 456) < 0);
        assertFalse(Mul.multiply(-123, -456) < 0);
    }

    @Test
    public void checkMultiplication() {
        assertEquals(Mul.multiply(13, 13), 169);
        assertEquals(Mul.multiply(-123, 4984), -613032);
        assertEquals(Mul.multiply(-852, -54205), 46182660);
    }
}
