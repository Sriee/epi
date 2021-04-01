package util;

import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertArrayEquals;

public class ArrayUtilInsertTest {

    @Test
    public void testInsertByte() {
        byte[] none = null;
        byte[] empty = {};
        byte[] array = { -2, 21, -67, 0 };
        byte value = 12;
        int indexLessThanZero = -11, indexGreaterThanArrayLength = array.length + 10;

        assertNull(ArrayUtil.insert(array, indexLessThanZero, value));
        assertNull(ArrayUtil.insert(array, indexGreaterThanArrayLength, value));

        assertArrayEquals(ArrayUtil.insert(none, 1, value), new byte[] { value });
        assertArrayEquals(ArrayUtil.insert(empty, 1, value), new byte[] { value });

        assertArrayEquals(ArrayUtil.insert(array, 0, new byte[] { 31, 68 }), new byte[] { 31, 68, -2, 21, -67, 0 });
        assertArrayEquals(ArrayUtil.insert(array, 2, new byte[] { 31, 68 }), new byte[] { -2, 21, 31, 68, -67, 0 });
    }

    @Test
    public void testInsertShort() {
        short[] none = null;
        short[] empty = {};
        short[] array = { -2, 21, -67, 0 };
        short value = 12;
        int indexLessThanZero = -11, indexGreaterThanArrayLength = array.length + 10;

        assertNull(ArrayUtil.insert(array, indexLessThanZero, value));
        assertNull(ArrayUtil.insert(array, indexGreaterThanArrayLength, value));

        assertArrayEquals(ArrayUtil.insert(none, 1, value), new short[] { value });
        assertArrayEquals(ArrayUtil.insert(empty, 1, value), new short[] { value });

        assertArrayEquals(ArrayUtil.insert(array, 0, new short[] { 31, 68 }), new short[] { 31, 68, -2, 21, -67, 0 });
        assertArrayEquals(ArrayUtil.insert(array, 2, new short[] { 31, 68 }), new short[] { -2, 21, 31, 68, -67, 0 });
    }

    @Test
    public void testInsertBoolean() {
        boolean[] none = null;
        boolean[] empty = {};
        boolean[] array = { false, true, false, true, false };
        boolean value = true;
        int indexLessThanZero = -11, indexGreaterThanArrayLength = array.length + 10;

        assertNull(ArrayUtil.insert(array, indexLessThanZero, value));
        assertNull(ArrayUtil.insert(array, indexGreaterThanArrayLength, value));

        assertArrayEquals(ArrayUtil.insert(none, 1, value), new boolean[] { value });
        assertArrayEquals(ArrayUtil.insert(empty, 1, value), new boolean[] { value });

        assertArrayEquals(ArrayUtil.insert(array, 0, new boolean[] { true, true }),
                new boolean[] { true, true, false, true, false, true, false });
        assertArrayEquals(ArrayUtil.insert(array, 2, new boolean[] { false, true }),
                new boolean[] { false, true, false, true, false, true, false });

    }

    @Test
    public void testInsertChar() {
        char[] none = null;
        char[] empty = {};
        char[] array = { 'f', 'y', 'u', 'o', 'i' };
        char value = 'j';
        int indexLessThanZero = -11, indexGreaterThanArrayLength = array.length + 10;

        assertNull(ArrayUtil.insert(array, indexLessThanZero, value));
        assertNull(ArrayUtil.insert(array, indexGreaterThanArrayLength, value));

        assertArrayEquals(ArrayUtil.insert(none, 1, value), new char[] { value });
        assertArrayEquals(ArrayUtil.insert(empty, 1, value), new char[] { value });

        assertArrayEquals(ArrayUtil.insert(array, 0, new char[] { 'n', 'i' }),
                new char[] { 'n', 'i', 'f', 'y', 'u', 'o', 'i' });
        assertArrayEquals(ArrayUtil.insert(array, 2, new char[] { 'g', 'g' }),
                new char[] { 'f', 'y', 'g', 'g', 'u', 'o', 'i' });

    }

    @Test
    public void testInsertInt() {
        int[] none = null;
        int[] empty = {};
        int[] array = { -2, 21, -67, 0 };
        int value = 12;
        int indexLessThanZero = -11, indexGreaterThanArrayLength = array.length + 10;

        assertNull(ArrayUtil.insert(array, indexLessThanZero, value));
        assertNull(ArrayUtil.insert(array, indexGreaterThanArrayLength, value));

        assertArrayEquals(ArrayUtil.insert(none, 1, value), new int[] { value });
        assertArrayEquals(ArrayUtil.insert(empty, 1, value), new int[] { value });

        assertArrayEquals(ArrayUtil.insert(array, 0, new int[] { 31, 68 }), new int[] { 31, 68, -2, 21, -67, 0 });
        assertArrayEquals(ArrayUtil.insert(array, 2, new int[] { 31, 68 }), new int[] { -2, 21, 31, 68, -67, 0 });
    }

    @Test
    public void testInsertLong() {
        long[] none = null;
        long[] empty = {};
        long[] array = { 467, -80, 15319, 7123, 27 };
        long value = 1267890087655L;
        int indexLessThanZero = -11, indexGreaterThanArrayLength = array.length + 10;

        assertNull(ArrayUtil.insert(array, indexLessThanZero, value));
        assertNull(ArrayUtil.insert(array, indexGreaterThanArrayLength, value));

        assertArrayEquals(ArrayUtil.insert(none, 1, value), new long[] { value });
        assertArrayEquals(ArrayUtil.insert(empty, 1, value), new long[] { value });

        assertArrayEquals(ArrayUtil.insert(array, 0, new long[] { 3156268 }),
                new long[] { 3156268, 467, -80, 15319, 7123, 27 });
        assertArrayEquals(ArrayUtil.insert(array, 2, new long[] { 3167868 }),
                new long[] { 467, -80, 3167868, 15319, 7123, 27 });
    }

    @Test
    public void testInsertFloatArrayFloat() {
        float[] none = null;
        float[] empty = {};
        float[] array = { 2304.4717f, 5979.8823f, 3807.3364f, 7541.264f };
        float value = 347367.367858f;
        int indexLessThanZero = -11, indexGreaterThanArrayLength = array.length + 10;

        assertNull(ArrayUtil.insert(array, indexLessThanZero, value));
        assertNull(ArrayUtil.insert(array, indexGreaterThanArrayLength, value));

        assertArrayEquals(ArrayUtil.insert(none, 1, value), new float[] { value }, 0.0002f);
        assertArrayEquals(ArrayUtil.insert(empty, 1, value), new float[] { value }, 0.0002f);

        assertArrayEquals(ArrayUtil.insert(array, 0, new float[] { 3156268.6789f }),
                new float[] { 3156268.6789f, 2304.4717f, 5979.8823f, 3807.3364f, 7541.264f }, 0.0002f);
        assertArrayEquals(ArrayUtil.insert(array, 2, new float[] { 3156268.6789f }),
                new float[] { 2304.4717f, 5979.8823f, 3156268.6789f, 3807.3364f, 7541.264f }, 0.0002f);
    }

    @Test
    public void testInsertDouble() {
        double[] none = null;
        double[] empty = {};
        double[] array = { 2304.4717, 5979.8823, 3807.3364, 7541.264 };
        double value = 347367.367858;
        int indexLessThanZero = -11, indexGreaterThanArrayLength = array.length + 10;

        assertNull(ArrayUtil.insert(array, indexLessThanZero, value));
        assertNull(ArrayUtil.insert(array, indexGreaterThanArrayLength, value));

        assertArrayEquals(ArrayUtil.insert(none, 1, value), new double[] { value }, 0.0002);
        assertArrayEquals(ArrayUtil.insert(empty, 1, value), new double[] { value }, 0.0002);

        assertArrayEquals(ArrayUtil.insert(array, 0, new double[] { 3156268.6789 }),
                new double[] { 3156268.6789, 2304.4717, 5979.8823, 3807.3364, 7541.264 }, 0.0002);
        assertArrayEquals(ArrayUtil.insert(array, 2, new double[] { 3156268.6789 }),
                new double[] { 2304.4717, 5979.8823, 3156268.6789, 3807.3364, 7541.264 }, .0002);
    }

    @Test
    public void testInsertGeneric() {

        // Check Boolean array contains
        String[] none = null;
        String[] empty = {};
        String[] array = { "May", "the", "be", "with", "you" };
        String value = "force";
        int indexLessThanZero = -11, indexGreaterThanArrayLength = array.length + 10;

        assertNull(ArrayUtil.insert(array, indexLessThanZero, value));
        assertNull(ArrayUtil.insert(array, indexGreaterThanArrayLength, value));

        assertArrayEquals(ArrayUtil.insert(String.class, none, 1, value), new String[] { value });
        assertArrayEquals(ArrayUtil.insert(String.class, empty, 1, value), new String[] { value });

        assertArrayEquals(ArrayUtil.insert(String.class, array, 2, new String[] { value }),
                new String[] { "May", "the", "force", "be", "with", "you" });
    }
}
