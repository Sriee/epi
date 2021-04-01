package util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import util.ArrayUtil;

public class ArrayUtilNotEmptyTest {

    @Test
    public void testisNotEmptyByteArray() {
        byte[] checkNull = null;
        assertFalse(ArrayUtil.isNotEmpty(checkNull));

        byte[] checkEmpty = {};
        assertFalse(ArrayUtil.isNotEmpty(checkEmpty));

        byte[] actual = { -50, 120, 0, -4 };
        assertTrue(ArrayUtil.isNotEmpty(actual));
    }

    @Test
    public void testisNotEmptyShortArray() {
        short[] checkNull = null;
        assertFalse(ArrayUtil.isNotEmpty(checkNull));

        short[] checkEmpty = {};
        assertFalse(ArrayUtil.isNotEmpty(checkEmpty));

        short[] actual = { -50, 120, 0, -4 };
        assertTrue(ArrayUtil.isNotEmpty(actual));
    }

    @Test
    public void testisNotEmptyBooleanArray() {
        boolean[] checkNull = null;
        assertFalse(ArrayUtil.isNotEmpty(checkNull));

        boolean[] checkEmpty = {};
        assertFalse(ArrayUtil.isNotEmpty(checkEmpty));

        boolean[] actual = { false, true, true, false };
        assertTrue(ArrayUtil.isNotEmpty(actual));
    }

    @Test
    public void testisNotEmptyCharArray() {
        char[] checkNull = null;
        assertFalse(ArrayUtil.isNotEmpty(checkNull));

        char[] checkEmpty = {};
        assertFalse(ArrayUtil.isNotEmpty(checkEmpty));

        char[] actual = { 'T', 'e', 's', 't', 'i', 'n', 'g' };
        assertTrue(ArrayUtil.isNotEmpty(actual));
    }

    @Test
    public void testisNotEmptyIntArray() {
        int[] checkNull = null;
        assertFalse(ArrayUtil.isNotEmpty(checkNull));

        int[] checkEmpty = {};
        assertFalse(ArrayUtil.isNotEmpty(checkEmpty));

        int[] actual = { 56780, -8720, 0, 5671 };
        assertTrue(ArrayUtil.isNotEmpty(actual));
    }

    @Test
    public void testisNotEmptyLongArray() {
        long[] checkNull = null;
        assertFalse(ArrayUtil.isNotEmpty(checkNull));

        long[] checkEmpty = {};
        assertFalse(ArrayUtil.isNotEmpty(checkEmpty));

        long[] actual = { -50, 67890, 56789, -564, 13 };
        assertTrue(ArrayUtil.isNotEmpty(actual));
    }

    @Test
    public void testisNotEmptyFloatArray() {
        float[] checkNull = null;
        assertFalse(ArrayUtil.isNotEmpty(checkNull));

        float[] checkEmpty = {};
        assertFalse(ArrayUtil.isNotEmpty(checkEmpty));

        float[] actual = { (float) 2304.4717, (float) 5979.8823, (float) 3807.3364, (float) 7541.264 };
        assertTrue(ArrayUtil.isNotEmpty(actual));
    }

    @Test
    public void testisNotEmptyDoubleArray() {
        double[] checkNull = null;
        assertFalse(ArrayUtil.isNotEmpty(checkNull));

        double[] checkEmpty = {};
        assertFalse(ArrayUtil.isNotEmpty(checkEmpty));

        double[] actual = { 6194.5681, 6721.0002, 6455.8963, 5049.3596, 2499.4945, 6371.5027, 1726.3004 };
        assertTrue(ArrayUtil.isNotEmpty(actual));
    }

    @Test
    public void testisNotEmptyTArray() {
        Byte[] b = null;
        Short[] sh = null;
        Boolean[] bool = null;
        Character[] ch = null;
        Integer[] in = null;
        Long[] l = null;
        Float[] f = null;
        Double[] d = null;
        String[] s = null;

        assertFalse(ArrayUtil.isNotEmpty(b));
        assertFalse(ArrayUtil.isNotEmpty(sh));
        assertFalse(ArrayUtil.isNotEmpty(bool));
        assertFalse(ArrayUtil.isNotEmpty(ch));
        assertFalse(ArrayUtil.isNotEmpty(in));
        assertFalse(ArrayUtil.isNotEmpty(l));
        assertFalse(ArrayUtil.isNotEmpty(f));
        assertFalse(ArrayUtil.isNotEmpty(d));
        assertFalse(ArrayUtil.isNotEmpty(s));

        Byte[] bEmpty = {};
        Short[] shEmpty = {};
        Boolean[] boolEmpty = {};
        Character[] chEmpty = {};
        Integer[] intEmpty = {};
        Long[] lEmpty = {};
        Float[] fEmpty = {};
        Double[] dEmpty = {};
        String[] sEmpty = {};

        assertFalse(ArrayUtil.isNotEmpty(bEmpty));
        assertFalse(ArrayUtil.isNotEmpty(shEmpty));
        assertFalse(ArrayUtil.isNotEmpty(boolEmpty));
        assertFalse(ArrayUtil.isNotEmpty(chEmpty));
        assertFalse(ArrayUtil.isNotEmpty(intEmpty));
        assertFalse(ArrayUtil.isNotEmpty(lEmpty));
        assertFalse(ArrayUtil.isNotEmpty(fEmpty));
        assertFalse(ArrayUtil.isNotEmpty(dEmpty));
        assertFalse(ArrayUtil.isNotEmpty(sEmpty));
    }
}
