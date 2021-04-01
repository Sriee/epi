package util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayUtilAddAllTest {

    @Test
    public void testByteArrayAddAll() {
        byte[] firstEmpty = null;
        byte[] secondEmpty = null;
        byte[] empty = {};
        byte[] first = { -2, 21, -67, 0 };
        byte[] second = { -2, 21, -67, 0 };
        byte[] combined = { -2, 21, -67, 0, -2, 21, -67, 0 };

        assertArrayEquals(ArrayUtil.addAll(firstEmpty, secondEmpty), empty);
        assertArrayEquals(ArrayUtil.addAll(empty, second), second);
        assertArrayEquals(ArrayUtil.addAll(first, empty), first);
        assertArrayEquals(ArrayUtil.addAll(first, second), combined);
    }

    @Test
    public void testShortArrayAddAll() {
        short[] firstEmpty = null;
        short[] secondEmpty = null;
        short[] empty = {};
        short[] first = { -2, 21, -67, 0 };
        short[] second = { -2, 21, -67, 0 };
        short[] combined = { -2, 21, -67, 0, -2, 21, -67, 0 };

        assertArrayEquals(ArrayUtil.addAll(firstEmpty, secondEmpty), empty);
        assertArrayEquals(ArrayUtil.addAll(empty, second), second);
        assertArrayEquals(ArrayUtil.addAll(first, empty), first);
        assertArrayEquals(ArrayUtil.addAll(first, second), combined);
    }

    @Test
    public void testBooleanArrayAddAll() {
        boolean[] firstEmpty = null;
        boolean[] secondEmpty = null;
        boolean[] empty = {};
        boolean[] first = { false, true, false, true };
        boolean[] second = { true, true, false, false };
        boolean[] combined = { false, true, false, true, true, true, false, false };

        assertArrayEquals(ArrayUtil.addAll(firstEmpty, secondEmpty), empty);
        assertArrayEquals(ArrayUtil.addAll(empty, second), second);
        assertArrayEquals(ArrayUtil.addAll(first, empty), first);
        assertArrayEquals(ArrayUtil.addAll(first, second), combined);
    }

    @Test
    public void testCharacterArrayAddAll() {
        char[] firstEmpty = null;
        char[] secondEmpty = null;
        char[] empty = {};
        char[] first = { 'f', 'y', 'u', 'o', 'i', 'h', 'j' };
        char[] second = { 'f', 'y', 'u', 'o', 'i', 'h', 'j' };
        char[] combined = { 'f', 'y', 'u', 'o', 'i', 'h', 'j', 'f', 'y', 'u', 'o', 'i', 'h', 'j' };

        assertArrayEquals(ArrayUtil.addAll(firstEmpty, secondEmpty), empty);
        assertArrayEquals(ArrayUtil.addAll(empty, second), second);
        assertArrayEquals(ArrayUtil.addAll(first, empty), first);
        assertArrayEquals(ArrayUtil.addAll(first, second), combined);
    }

    @Test
    public void testIntArrayAddAll() {
        int[] firstEmpty = null;
        int[] secondEmpty = null;
        int[] empty = {};
        int[] first = { 467, -80, -6, 15319, 7123, 1, 27 };
        int[] second = { 467, -80, -6, 15319, 7123, 1, 27 };
        int[] combined = { 467, -80, -6, 15319, 7123, 1, 27, 467, -80, -6, 15319, 7123, 1, 27 };

        assertArrayEquals(ArrayUtil.addAll(firstEmpty, secondEmpty), empty);
        assertArrayEquals(ArrayUtil.addAll(empty, second), second);
        assertArrayEquals(ArrayUtil.addAll(first, empty), first);
        assertArrayEquals(ArrayUtil.addAll(first, second), combined);
    }

    @Test
    public void testLongArrayAddAll() {
        long[] firstEmpty = null;
        long[] secondEmpty = null;
        long[] empty = {};
        long[] first = { 467, -80, -6, 15319, 7123, 1, 27 };
        long[] second = { 467, -80, -6, 15319, 7123, 1, 27 };
        long[] combined = { 467, -80, -6, 15319, 7123, 1, 27, 467, -80, -6, 15319, 7123, 1, 27 };

        assertArrayEquals(ArrayUtil.addAll(firstEmpty, secondEmpty), empty);
        assertArrayEquals(ArrayUtil.addAll(empty, second), second);
        assertArrayEquals(ArrayUtil.addAll(first, empty), first);
        assertArrayEquals(ArrayUtil.addAll(first, second), combined);
    }

    @Test
    public void testFloatArrayAddAll() {
        float[] firstEmpty = null;
        float[] secondEmpty = null;
        float[] empty = {};
        float[] first = { (float) 2304.4717, (float) 5979.8823, (float) 3807.3364, (float) 7541.264 };
        float[] second = { (float) 2304.4717, (float) 5979.8823, (float) 3807.3364, (float) 7541.264 };
        float[] combined = { (float) 2304.4717, (float) 5979.8823, (float) 3807.3364, (float) 7541.264,
                (float) 2304.4717, (float) 5979.8823, (float) 3807.3364, (float) 7541.264 };

        assertArrayEquals(ArrayUtil.addAll(firstEmpty, secondEmpty), empty, 0.0002f);
        assertArrayEquals(ArrayUtil.addAll(empty, second), second, 0.0002f);
        assertArrayEquals(ArrayUtil.addAll(first, empty), first, 0.0002f);
        assertArrayEquals(ArrayUtil.addAll(first, second), combined, 0.0002f);
    }

    @Test
    public void testDoubleArrayAddAll() {
        double[] firstEmpty = null;
        double[] secondEmpty = null;
        double[] empty = {};
        double[] first = { 2304.4717, 5979.8823, 3807.3364, 7541.264, 3473.358 };
        double[] second = { 2304.4717, 5979.8823, 3807.3364, 7541.264, 3473.358 };
        double[] combined = { 2304.4717, 5979.8823, 3807.3364, 7541.264, 3473.358, 2304.4717, 5979.8823, 3807.3364,
                7541.264, 3473.358 };

        assertArrayEquals(ArrayUtil.addAll(firstEmpty, secondEmpty), empty, 0.0002f);
        assertArrayEquals(ArrayUtil.addAll(empty, second), second, 0.0002f);
        assertArrayEquals(ArrayUtil.addAll(first, empty), first, 0.0002f);
        assertArrayEquals(ArrayUtil.addAll(first, second), combined, 0.0002f);
    }

    @Test
    public void testGenericAddAll() {
        String[] firstEmpty = null;
        String[] secondEmpty = null;
        String[] empty = {};
        String[] first = { "May", "the", "force", "be", "with", "you" };
        String[] second = { "May", "the", "force", "be", "with", "you" };
        String[] combined = { "May", "the", "force", "be", "with", "you", "May", "the", "force", "be", "with", "you" };

        assertArrayEquals(ArrayUtil.addAll(String.class, firstEmpty, secondEmpty), empty);
        assertArrayEquals(ArrayUtil.addAll(String.class, empty, second), second);
        assertArrayEquals(ArrayUtil.addAll(String.class, first, empty), first);
        assertArrayEquals(ArrayUtil.addAll(String.class, first, second), combined);
    }
}
