package util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ArrayUtilEmptyTest {

    @Test
    public void testIsEmptyByteArray() {
        byte[] checkNull = null;
        assertTrue(ArrayUtil.isEmpty(checkNull));

        byte[] checkEmpty = {};
        assertTrue(ArrayUtil.isEmpty(checkEmpty));

        byte[] actual = {-50, 120, 0, -4};
        assertFalse(ArrayUtil.isEmpty(actual));
    }

    @Test
    public void testIsEmptyShortArray() {
        short[] checkNull = null;
        assertTrue(ArrayUtil.isEmpty(checkNull));

        short[] checkEmpty = {};
        assertTrue(ArrayUtil.isEmpty(checkEmpty));

        short[] actual = {-50, 120, 0, -4};
        assertFalse(ArrayUtil.isEmpty(actual));
    }

    @Test
    public void testIsEmptyBooleanArray() {
        boolean[] checkNull = null;
        assertTrue(ArrayUtil.isEmpty(checkNull));

        boolean[] checkEmpty = {};
        assertTrue(ArrayUtil.isEmpty(checkEmpty));

        boolean[] actual = {false, true, true, false};
        assertFalse(ArrayUtil.isEmpty(actual));
    }

    @Test
    public void testIsEmptyCharArray() {
        char[] checkNull = null;
        assertTrue(ArrayUtil.isEmpty(checkNull));

        char[] checkEmpty = {};
        assertTrue(ArrayUtil.isEmpty(checkEmpty));

        char[] actual = {'T', 'e', 's', 't', 'i', 'n', 'g'};
        assertFalse(ArrayUtil.isEmpty(actual));
    }

    @Test
    public void testIsEmptyIntArray() {
        int[] checkNull = null;
        assertTrue(ArrayUtil.isEmpty(checkNull));

        int[] checkEmpty = {};
        assertTrue(ArrayUtil.isEmpty(checkEmpty));

        int[] actual = {56780, -8720, 0, 5671};
        assertFalse(ArrayUtil.isEmpty(actual));
    }

    @Test
    public void testIsEmptyLongArray() {
        long[] checkNull = null;
        assertTrue(ArrayUtil.isEmpty(checkNull));

        long[] checkEmpty = {};
        assertTrue(ArrayUtil.isEmpty(checkEmpty));

        long[] actual = {-50, 67890, 56789, -564, 13};
        assertFalse(ArrayUtil.isEmpty(actual));
    }

    @Test
    public void testIsEmptyFloatArray() {
        float[] checkNull = null;
        assertTrue(ArrayUtil.isEmpty(checkNull));

        float[] checkEmpty = {};
        assertTrue(ArrayUtil.isEmpty(checkEmpty));

        float[] actual = {(float) 2304.4717, (float) 5979.8823, (float) 3807.3364, (float) 7541.264};
        assertFalse(ArrayUtil.isEmpty(actual));
    }

    @Test
    public void testIsEmptyDoubleArray() {
        double[] checkNull = null;
        assertTrue(ArrayUtil.isEmpty(checkNull));

        double[] checkEmpty = {};
        assertTrue(ArrayUtil.isEmpty(checkEmpty));

        double[] actual = {6194.5681, 6721.0002, 6455.8963, 5049.3596, 2499.4945, 6371.5027, 1726.3004};
        assertFalse(ArrayUtil.isEmpty(actual));
    }

    @Test
    public void testIsEmptyTArray() {
        Byte[] b = null;
        Short[] sh = null;
        Boolean[] bool = null;
        Character[] ch = null;
        Integer[] in = null;
        Long[] l = null;
        Float[] f = null;
        Double[] d = null;
        String[] s = null;

        assertTrue(ArrayUtil.isEmpty(b));
        assertTrue(ArrayUtil.isEmpty(sh));
        assertTrue(ArrayUtil.isEmpty(bool));
        assertTrue(ArrayUtil.isEmpty(ch));
        assertTrue(ArrayUtil.isEmpty(in));
        assertTrue(ArrayUtil.isEmpty(l));
        assertTrue(ArrayUtil.isEmpty(f));
        assertTrue(ArrayUtil.isEmpty(d));
        assertTrue(ArrayUtil.isEmpty(s));

        Byte[] bEmpty = {};
        Short[] shEmpty = {};
        Boolean[] boolEmpty = {};
        Character[] chEmpty = {};
        Integer[] intEmpty = {};
        Long[] lEmpty = {};
        Float[] fEmpty = {};
        Double[] dEmpty = {};
        String[] sEmpty = {};

        assertTrue(ArrayUtil.isEmpty(bEmpty));
        assertTrue(ArrayUtil.isEmpty(shEmpty));
        assertTrue(ArrayUtil.isEmpty(boolEmpty));
        assertTrue(ArrayUtil.isEmpty(chEmpty));
        assertTrue(ArrayUtil.isEmpty(intEmpty));
        assertTrue(ArrayUtil.isEmpty(lEmpty));
        assertTrue(ArrayUtil.isEmpty(fEmpty));
        assertTrue(ArrayUtil.isEmpty(dEmpty));
        assertTrue(ArrayUtil.isEmpty(sEmpty));
    }
}
