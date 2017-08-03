package util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayUtilSubarrayTest {

    @Test
    public void testSubarrayByteArray() {
        byte[] byteActual = {-2, 21, -67, 0};
        byte[] byteExpected = {-2, 21, -67};
        assertArrayEquals(ArrayUtil.subarray(byteActual, 0, 2), byteExpected);
    }

    @Test
    public void testSubarrayShortArray() {
        short[] shActual = {-2, 21, -67, 0};
        short[] shExpected = {-2};

        assertArrayEquals(ArrayUtil.subarray(shActual, 0, 0), shExpected);
    }

    @Test
    public void testSubarrayBooleanArray() {
        Boolean[] boolActual = {true, true, false, true, false, true, true, false, false, true, true,
                true, true, true, false, true};

        Boolean[] boolExpected = {false, true, false, true, true, false, false, true, true, true};

        assertArrayEquals(ArrayUtil.subarray(boolActual, 2, 11), boolExpected);
    }

    @Test
    public void testSubarrayCharArray() {

        char[] chActual = {'n', 'y', 'o', 'i', 'j', 'g', 'g', 'a'};
        char[] chExpected = {'g', 'g', 'a'};

        assertArrayEquals(ArrayUtil.subarray(chActual, 5, 7), chExpected);
    }

    @Test
    public void testSubarrayIntArray() {

        int[] intActual = {467, -80, 15319, 7123, 27};
        assertNull(ArrayUtil.subarray(intActual, -1, 2));
    }

    @Test
    public void testSubarrayLongArray() {

        long[] lActual = {(long) 467, (long) -80, (long) -6, (long) 15319, (long) 7123, (long) 1, (long) 27};
        assertNull(ArrayUtil.subarray(lActual, 1, -2));
    }

    @Test
    public void testSubarrayFloatArray() {
        float[] fActual = null;
        assertNull(ArrayUtil.subarray(fActual, 0, 1));
    }

    @Test
    public void testSubarrayDoubleArray() {

        double[] dActual = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        assertNull(ArrayUtil.subarray(dActual, 8, 0));
    }

    @Test
    public void testSubarrayGenericArray() {

        Float[] fActual = {(float) 2304.4717, (float) 3807.3364, (float) 7541.264};
        Float[] fExpected = {(float) 3807.3364, (float) 7541.264};
        assertArrayEquals(ArrayUtil.subarray(fActual, 1, 2), fExpected);
    }
}
