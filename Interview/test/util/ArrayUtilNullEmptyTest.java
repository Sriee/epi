package util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayUtilNullEmptyTest {

    @Test
    public void testNullToEmptyByteArray() {
        Byte[] byteActual = { null, -2, 21, -67, 0, null };
        Byte[] byteExpected = { 0, -2, 21, -67, 0, 0 };

        assertArrayEquals(ArrayUtil.nullToEmpty(byteActual), byteExpected);
    }

    @Test
    public void testNullToEmptyShortArray() {
        Short[] shActual = { -2, 21, null, null, -67, 0, null };
        Short[] shExpected = { -2, 21, 0, 0, -67, 0, 0 };

        assertArrayEquals(ArrayUtil.nullToEmpty(shActual), shExpected);
    }

    @Test
    public void testNullToEmptyBooleanArray() {
        Boolean[] boolActual = { true, true, true, true, true, null, true, true, false, null, false, true, true, null,
                true, true, true, false, true, null };

        Boolean[] boolExpected = { true, true, true, true, true, false, true, true, false, false, false, true, true,
                false, true, true, true, false, true, false };

        assertArrayEquals(ArrayUtil.nullToEmpty(boolActual), boolExpected);
    }

    @Test
    public void testNullToEmptyCharacterArray() {
        Character[] chActual = { null, 'y', null, 'o', 'i', null, 'j' };
        Character[] chExpected = { ' ', 'y', ' ', 'o', 'i', ' ', 'j' };

        assertArrayEquals(ArrayUtil.nullToEmpty(chActual), chExpected);
    }

    @Test
    public void testNullToEmptyIntegerArray() {

        Integer[] intActual = { 467, -80, null, 15319, 7123, null, 27 };
        Integer[] intExpected = { 467, -80, 0, 15319, 7123, 0, 27 };
        assertArrayEquals(ArrayUtil.nullToEmpty(intActual), intExpected);
    }

    @Test
    public void testNullToEmptyLongArray() {

        // Arrays are meant to be equal
        Long[] lActual = { (long) 467, (long) -80, (long) -6, (long) 15319, (long) 7123, (long) 1, (long) 27 };
        Long[] lExpected = { (long) 467, (long) -80, (long) -6, (long) 15319, (long) 7123, (long) 1, (long) 27 };
        assertArrayEquals(ArrayUtil.nullToEmpty(lActual), lExpected);
    }

    @Test
    public void testNullToEmptyFloatArray() {

        Float[] fActual = { (float) 2304.4717, null, (float) 3807.3364, (float) 7541.264, null };
        Float[] fExpected = { (float) 2304.4717, (float) 0.0, (float) 3807.3364, (float) 7541.264, (float) 0.0 };
        assertArrayEquals(ArrayUtil.nullToEmpty(fActual), fExpected);
    }

    @Test
    public void testNullToEmptyDoubleArray() {

        Double[] dActual = { null, null, null, null, null, null, null, null, null, null };
        Double[] dExpected = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
        assertArrayEquals(ArrayUtil.nullToEmpty(dActual), dExpected);
    }

    @Test
    public void testNullToEmptyStringArray() {

        String[] sActual = { "Pikachu", "I", null, null, "choose", "you" };
        String[] sExpected = { "Pikachu", "I", " ", " ", "choose", "you" };
        assertArrayEquals(ArrayUtil.nullToEmpty(sActual), sExpected);
    }

}
