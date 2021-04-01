package util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayUtilToObjectAndTypeTest {

    @Test
    public void testToObjectByteArray() {

        byte[] actual = { -2, 21, -67, 0 };
        Byte[] expected = { -2, 21, -67, 0 };
        assertArrayEquals(ArrayUtil.toObject(actual), expected);
        assertTrue(ArrayUtil.isSameType(ArrayUtil.toObject(actual), expected));
    }

    @Test
    public void testToObjectShortArray() {

        short[] actual = { -2, 21, -67, 0 };
        Short[] expected = { -2, 21, -67, 0 };
        assertArrayEquals(ArrayUtil.toObject(actual), expected);
        assertTrue(ArrayUtil.isSameType(ArrayUtil.toObject(actual), expected));
    }

    @Test
    public void testToObjectBooleanArray() {

        boolean[] actual = { true, true, false, true, false, true, true, false, false, true, true };
        Boolean[] expected = { true, true, false, true, false, true, true, false, false, true, true };

        assertArrayEquals(ArrayUtil.toObject(actual), expected);
        assertTrue(ArrayUtil.isSameType(ArrayUtil.toObject(actual), expected));
    }

    @Test
    public void testToObjectCharArray() {

        char[] actual = { 'n', 'y', 'o', 'i', 'j', 'g', 'g', 'a' };
        Character[] expected = { 'n', 'y', 'o', 'i', 'j', 'g', 'g', 'a' };

        assertArrayEquals(ArrayUtil.toObject(actual), expected);
        assertTrue(ArrayUtil.isSameType(ArrayUtil.toObject(actual), expected));
    }

    @Test
    public void testToObjectIntArray() {

        int[] actual = { 467, -80, 15319, 7123, 27 };
        Integer[] expected = { 467, -80, 15319, 7123, 27 };

        assertArrayEquals(ArrayUtil.toObject(actual), expected);
        assertTrue(ArrayUtil.isSameType(ArrayUtil.toObject(actual), expected));
    }

    @Test
    public void testToObjectLongArray() {

        long[] actual = { (long) 467, (long) -80, (long) -6, (long) 15319, (long) 7123, (long) 1, (long) 27 };
        Long[] expected = { (long) 467, (long) -80, (long) -6, (long) 15319, (long) 7123, (long) 1, (long) 27 };

        assertArrayEquals(ArrayUtil.toObject(actual), expected);
        assertTrue(ArrayUtil.isSameType(ArrayUtil.toObject(actual), expected));
    }

    @Test
    public void testToObjectFloatArray() {

        float[] actual = { (float) 2304.4717, (float) 3807.3364, (float) 7541.264 };
        Float[] expected = { (float) 2304.4717, (float) 3807.3364, (float) 7541.264 };

        assertArrayEquals(ArrayUtil.toObject(actual), expected);
        assertTrue(ArrayUtil.isSameType(ArrayUtil.toObject(actual), expected));
    }

    @Test
    public void testToObjectDoubleArray() {

        double[] actual = { 553.6981, 320.6328, 342.0801, 592.1744, 136.482, 559.7219, 585.7122, 507.4747, 398.7696,
                244.8682, 219.3931, 153.3111, 550.569, 68.0965, 418.9474, 450.5304, 119.2867, 551.1855, 621.159,
                402.7367 };
        Double[] expected = { 553.6981, 320.6328, 342.0801, 592.1744, 136.482, 559.7219, 585.7122, 507.4747, 398.7696,
                244.8682, 219.3931, 153.3111, 550.569, 68.0965, 418.9474, 450.5304, 119.2867, 551.1855, 621.159,
                402.7367 };

        assertArrayEquals(ArrayUtil.toObject(actual), expected);
        assertTrue(ArrayUtil.isSameType(ArrayUtil.toObject(actual), expected));
    }
}
