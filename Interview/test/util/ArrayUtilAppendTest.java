package util;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class ArrayUtilAppendTest {

    @Test
    public void testAppendByteArray() {
        byte[] none = null;
        byte[] actual = { -50, 120, 0, -4 };
        byte valueToAppend = 59;
        byte[] expected = { 59 };

        assertArrayEquals(ArrayUtil.append(none, valueToAppend), expected);
        assertArrayEquals(ArrayUtil.append(actual, valueToAppend), new byte[] { -50, 120, 0, -4, 59 });
    }

    @Test
    public void testAppendShortArray() {
        short[] none = null;
        short[] actual = { -50, 120, 0, -4 };
        short valueToAppend = 59;
        short[] expected = { 59 };

        assertArrayEquals(ArrayUtil.append(none, valueToAppend), expected);
        assertArrayEquals(ArrayUtil.append(actual, valueToAppend), new short[] { -50, 120, 0, -4, 59 });
    }

    @Test
    public void testAppendBooleanArray() {
        boolean[] none = null;
        boolean[] actual = { false, true, true, false };
        boolean valueToAppend = true;
        boolean[] expected = { true };

        assertArrayEquals(ArrayUtil.append(none, valueToAppend), expected);
        assertArrayEquals(ArrayUtil.append(actual, valueToAppend), new boolean[] { false, true, true, false, true });
    }

    @Test
    public void testAppendCharArray() {
        char[] none = null;
        char[] actual = { 'T', 'e', 's', 't', 'i', 'n', 'g' };
        char valueToAppend = 'a';
        char[] expected = { 'a' };

        assertArrayEquals(ArrayUtil.append(none, valueToAppend), expected);
        assertArrayEquals(ArrayUtil.append(actual, valueToAppend),
                new char[] { 'T', 'e', 's', 't', 'i', 'n', 'g', 'a' });
    }

    @Test
    public void testAppendIntArray() {
        int[] none = null;
        int[] actual = { 56780, -8720, 0, 5671 };
        int valueToAppend = -9863;
        int[] expected = { -9863 };

        assertArrayEquals(ArrayUtil.append(none, valueToAppend), expected);
        assertArrayEquals(ArrayUtil.append(actual, valueToAppend), new int[] { 56780, -8720, 0, 5671, -9863 });
    }

    @Test
    public void testAppendLongArray() {
        long[] none = null;
        long[] actual = { 56780, -8720, 0, 5671 };
        long valueToAppend = -9863;
        long[] expected = { -9863 };

        assertArrayEquals(ArrayUtil.append(none, valueToAppend), expected);
        assertArrayEquals(ArrayUtil.append(actual, valueToAppend), new long[] { 56780, -8720, 0, 5671, -9863 });
    }

    @Test
    public void testAppendFloatArray() {
        float[] none = null;
        float[] actual = { (float) 5979.8823, (float) 3807.3364, (float) 7541.264, (float) 6007.075, (float) 3134.0088,
                (float) 5867.4204, (float) 26.1774, (float) 3473.358, (float) 1370.6934, (float) 1290.1748,
                (float) 926.0253, (float) 3618.7249, (float) 5131.829, (float) 7275.712 };
        float valueToAppend = (float) 2304.4717;
        float[] expected = { (float) 2304.4717 };

        assertArrayEquals(ArrayUtil.append(none, valueToAppend), expected, 0.0002f);
        assertArrayEquals(ArrayUtil.append(actual, valueToAppend),
                new float[] { (float) 5979.8823, (float) 3807.3364, (float) 7541.264, (float) 6007.075,
                        (float) 3134.0088, (float) 5867.4204, (float) 26.1774, (float) 3473.358, (float) 1370.6934,
                        (float) 1290.1748, (float) 926.0253, (float) 3618.7249, (float) 5131.829, (float) 7275.712,
                        (float) 2304.4717 },
                0.0002f);
    }

    @Test
    public void testAppendDoubleArray() {
        double[] none = null;
        double[] actual = { 6721.0002, 6455.8963, 5049.3596, 2499.4945, 6371.5027, 1726.3004, 2127.0672, 8105.5676,
                104.5984, 4230.1216, 4908.8545, 3075.7336, 7986.601, 1121.1457 };
        double valueToAppend = 6194.5681;
        double[] expected = { 6194.5681 };

        assertArrayEquals(ArrayUtil.append(none, valueToAppend), expected, 0.0002f);
        assertArrayEquals(ArrayUtil.append(actual, valueToAppend),
                new double[] { 6721.0002, 6455.8963, 5049.3596, 2499.4945, 6371.5027, 1726.3004, 2127.0672, 8105.5676,
                        104.5984, 4230.1216, 4908.8545, 3075.7336, 7986.601, 1121.1457, 6194.5681 },
                0.0002f);
    }

    @Test
    public void testAppendGenericArray() {
        Character[] empty = null;
        Character[] actualCharacter = { 's', 't', 'x', 'k', 's', 'q', 'k', 'k', 'a', 'u' };

        assertArrayEquals(ArrayUtil.append(Character.class, empty, 'Z'), new Character[] { 'Z' });
        assertArrayEquals(ArrayUtil.append(Character.class, actualCharacter, 'P'),
                new Character[] { 's', 't', 'x', 'k', 's', 'q', 'k', 'k', 'a', 'u', 'P' });
    }

}
