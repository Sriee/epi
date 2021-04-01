package util;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class ArrayUtilRemoveTest {

    @Test
    public void testInsertByte() {
        byte[] array = { -2, 21, -67, 0 };
        assertArrayEquals(ArrayUtil.remove(array, 2), new byte[] { -2, 21, 0 });
        assertArrayEquals(ArrayUtil.remove(array, 3, 0), new byte[] { 21, -67 });
    }

    @Test
    public void testRemoveShort() {
        short[] array = { -2, 21, -67, 0 };
        assertArrayEquals(ArrayUtil.remove(array, 2), new short[] { -2, 21, 0 });
        assertArrayEquals(ArrayUtil.remove(array, 2, 2, 0), new short[] { 21, 0 });
    }

    @Test
    public void testRemoveBoolean() {
        boolean[] array = { false, true, false, true, false };
        assertArrayEquals(ArrayUtil.remove(array, 4), new boolean[] { false, true, false, true });
        assertArrayEquals(ArrayUtil.remove(array, 4, 2, 0), new boolean[] { true, true });
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveChar() {
        char[] array = { 'f', 'y', 'u', 'o', 'i' };
        assertArrayEquals(ArrayUtil.remove(array, -1), new char[] { 'f', 'y', 'u', 'o', 'i' });
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveInt() {
        int[] array = null;
        assertArrayEquals(ArrayUtil.remove(array, 0), new int[] {});
    }

    @Test
    public void testRemoveLong() {
        long[] array = { 1267890087655L, -80, 15319, 7123, 27 };
        assertArrayEquals(ArrayUtil.remove(array, 0), new long[] { -80, 15319, 7123, 27 });
        assertArrayEquals(ArrayUtil.remove(array, 4, 2, 3), new long[] { 1267890087655L, -80 });
    }

    @Test
    public void testRemoveFloat() {
        float[] array = { 2304.4717f, 5979.8823f, 3807.3364f, 7541.264f };
        assertArrayEquals(ArrayUtil.remove(array, 0), new float[] { 5979.8823f, 3807.3364f, 7541.264f }, 0.0002f);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveDouble() {
        double[] array = { 2304.4717, 5979.8823, 3807.3364, 7541.264 };
        assertArrayEquals(ArrayUtil.remove(array, 123, 0, 2), new double[] { 5979.8823, 7541.264 }, 0.0002);
    }

    @Test
    public void testRemoveGeneric() {
        String[] array = { "May", "the", "be", "with", "you" };

        assertArrayEquals(ArrayUtil.remove(String.class, array, 3, 4, 2, 1, 0), new String[] {});
    }
}
