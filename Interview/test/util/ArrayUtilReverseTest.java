package util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayUtilReverseTest {

    @Test
    public void testReverseByteArray() {
        byte[] byteActual = { -2, 21, -67, 0 };
        byte[] byteExpected = { 0, -67, 21, -2 };

        assertArrayEquals(ArrayUtil.reverse(byteActual), byteExpected);
    }

    @Test
    public void testReverseShortArray() {
        short[] shActual = { -2, 21, -67, 0 };
        short[] shExpected = { 0, -67, 21, -2 };

        assertArrayEquals(ArrayUtil.reverse(shActual), shExpected);
    }

    @Test
    public void testReverseBooleanArray() {
        Boolean[] boolActual = { true, true, false, true, false, true, true, false, false, true, true, true, true, true,
                false, true };

        Boolean[] boolExpected = { true, false, true, true, true, true, true, false, false, true, true, false, true,
                false, true, true };

        assertArrayEquals(ArrayUtil.reverse(boolActual), boolExpected);
    }

    @Test
    public void testReverseCharacterArray() {
        char[] chActual = { 'y', 'o', 'i', 'j' };
        char[] chExpected = { 'j', 'i', 'o', 'y' };

        assertArrayEquals(ArrayUtil.reverse(chActual), chExpected);
    }

    @Test
    public void testReverseIntegerArray() {

        int[] intActual = { 467, -80, 15319, 7123, 27 };
        int[] intExpected = { 27, 7123, 15319, -80, 467 };
        assertArrayEquals(ArrayUtil.reverse(intActual), intExpected);
    }

    @Test
    public void testReverseLongArray() {

        Long[] lActual = { (long) 467, (long) -80, (long) -6, (long) 15319, (long) 7123, (long) 1, (long) 27 };
        Long[] lExpected = { (long) 27, (long) 1, (long) 7123, (long) 15319, (long) -6, (long) -80, (long) 467 };
        assertArrayEquals(ArrayUtil.reverse(lActual), lExpected);
    }

    @Test
    public void testReverseFloatArray() {

        Float[] fActual = { (float) 2304.4717, (float) 3807.3364, (float) 7541.264 };
        Float[] fExpected = { (float) 7541.264, (float) 3807.3364, (float) 2304.4717 };
        assertArrayEquals(ArrayUtil.reverse(fActual), fExpected);
    }

    @Test
    public void testReverseDoubleArray() {

        double[] dActual = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
        double[] dExpected = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
        assertArrayEquals(ArrayUtil.reverse(dActual), dExpected, 0.001);
    }
}
