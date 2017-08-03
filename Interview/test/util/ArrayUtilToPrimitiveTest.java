package util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayUtilToPrimitiveTest {

    @Test
    public void testToPrimitiveByteArray() {

        Byte[] actual = {-2, 21, -67, 0};
        byte[] expected = {-2, 21, -67, 0};

        assertArrayEquals(ArrayUtil.toPrimitive(actual), expected);
        assertTrue(ArrayUtil.isSameType(ArrayUtil.toPrimitive(actual), expected));
    }

    @Test
    public void testToPrimitiveShortArray() {

        Short[] actual = {-2, 21, -67, 0};
        short[] expected = {-2, 21, -67, 0};

        assertArrayEquals(ArrayUtil.toPrimitive(actual), expected);
        assertTrue(ArrayUtil.isSameType(ArrayUtil.toPrimitive(actual), expected));
    }

    @Test
    public void testToPrimitiveBooleanArray() {

        Boolean[] actual = {true, true, false, true, false, true, true, false, false, true, true};
        boolean[] expected = {true, true, false, true, false, true, true, false, false, true, true};

        assertArrayEquals(ArrayUtil.toPrimitive(actual), expected);
        assertTrue(ArrayUtil.isSameType(ArrayUtil.toPrimitive(actual), expected));
    }

    @Test
    public void testToPrimitiveCharacterArray() {

        Character[] actual = {'n', 'y', 'o', 'i', 'j', 'g', 'g', 'a'};
        char[] expected = {'n', 'y', 'o', 'i', 'j', 'g', 'g', 'a'};

        assertArrayEquals(ArrayUtil.toPrimitive(actual), expected);
        assertTrue(ArrayUtil.isSameType(ArrayUtil.toPrimitive(actual), expected));
    }

    @Test
    public void testToPrimitiveIntegerArray() {

        Integer[] actual = {467, -80, 15319, 7123, 27};
        int[] expected = {467, -80, 15319, 7123, 27};

        assertArrayEquals(ArrayUtil.toPrimitive(actual), expected);
        assertTrue(ArrayUtil.isSameType(ArrayUtil.toPrimitive(actual), expected));
    }

    @Test
    public void testToPrimitiveLongArray() {

        Long[] actual = {(long) 467, (long) -80, (long) -6, (long) 15319, (long) 7123, (long) 1, (long) 27};
        long[] expected = {(long) 467, (long) -80, (long) -6, (long) 15319, (long) 7123, (long) 1, (long) 27};

        assertArrayEquals(ArrayUtil.toPrimitive(actual), expected);
        assertTrue(ArrayUtil.isSameType(ArrayUtil.toPrimitive(actual), expected));
    }

    @Test
    public void testToPrimitiveFloatArray() {

        Float[] actual = {(float) 2304.4717, (float) 3807.3364, (float) 7541.264};
        float[] expected = {(float) 2304.4717, (float) 3807.3364, (float) 7541.264};

        assertArrayEquals(ArrayUtil.toPrimitive(actual), expected, (float) 0.0001);
        assertTrue(ArrayUtil.isSameType(ArrayUtil.toPrimitive(actual), expected));
    }

    @Test
    public void testToPrimitiveDoubleArray() {

        double[] expected = {553.6981, 320.6328, 342.0801, 592.1744, 136.482, 559.7219, 585.7122, 507.4747,
                398.7696, 244.8682, 219.3931, 153.3111, 550.569, 68.0965, 418.9474, 450.5304, 119.2867, 551.1855,
                621.159, 402.7367};
        Double[] actual = {553.6981, 320.6328, 342.0801, 592.1744, 136.482, 559.7219, 585.7122, 507.4747,
                398.7696, 244.8682, 219.3931, 153.3111, 550.569, 68.0965, 418.9474, 450.5304, 119.2867, 551.1855,
                621.159, 402.7367};

        assertArrayEquals(ArrayUtil.toPrimitive(actual), expected, 0.0001);
        assertTrue(ArrayUtil.isSameType(ArrayUtil.toPrimitive(actual), expected));
    }
}
