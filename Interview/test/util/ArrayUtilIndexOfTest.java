package util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayUtilIndexOfTest {

    @Test
    public void testIndexOfByteArray() {
        byte[] bNull = null;
        byte[] bEmpty = {};
        byte[] by = {-2, 21, -67, 0};
        byte valueToFind = 21, valueNotPresent = 93;

        assertEquals(ArrayUtil.indexOf(bNull, valueToFind), -1);
        assertEquals(ArrayUtil.indexOf(bEmpty, valueToFind), -1);
        assertEquals(ArrayUtil.indexOf(by, valueToFind), 1);
        assertEquals(ArrayUtil.indexOf(by, valueNotPresent), -1);
    }

    @Test
    public void testIndexOfShortArray() {
        short[] shNull = null;
        short[] shEmpty = {};
        short[] sh = {-2, 21, -67, 0};
        short valueToFind = 21, valueNotPresent = 93;

        assertEquals(ArrayUtil.indexOf(shNull, valueToFind), -1);
        assertEquals(ArrayUtil.indexOf(shEmpty, valueToFind), -1);
        assertEquals(ArrayUtil.indexOf(sh, valueToFind), 1);
        assertEquals(ArrayUtil.indexOf(sh, valueNotPresent), -1);
    }

    @Test
    public void testIndexOfBooleanArray() {
        boolean[] bNull = null;
        boolean[] bEmpty = {};
        boolean[] bool = {false, false, false, false};
        boolean valueToFind = false, valueNotPresent = true;

        assertEquals(ArrayUtil.indexOf(bNull, valueToFind), -1);
        assertEquals(ArrayUtil.indexOf(bEmpty, valueToFind), -1);
        assertEquals(ArrayUtil.indexOf(bool, valueToFind), 0);
        assertEquals(ArrayUtil.indexOf(bool, valueNotPresent), -1);
    }

    @Test
    public void testIndexOfCharArray() {
        char[] chNull = null;
        char[] chEmpty = {};
        char[] ch = {'f', 'y', 'u', 'o', 'i', 'h', 'j'};
        char valueToFind = 'j', valueNotPresent = 'a';

        assertEquals(ArrayUtil.indexOf(chNull, valueToFind), -1);
        assertEquals(ArrayUtil.indexOf(chEmpty, valueToFind), -1);
        assertEquals(ArrayUtil.indexOf(ch, valueToFind), 6);
        assertEquals(ArrayUtil.indexOf(ch, valueNotPresent), -1);
    }

    @Test
    public void testIndexOfIntArray() {
        int[] intNull = null;
        int[] intEmpty = {};
        int[] i = {467, -80, -6, 15319, 7123, 1, 27};
        int valueToFind = 7123, valueNotPresent = -67;

        assertEquals(ArrayUtil.indexOf(intNull, valueToFind), -1);
        assertEquals(ArrayUtil.indexOf(intEmpty, valueToFind), -1);
        assertEquals(ArrayUtil.indexOf(i, valueToFind), 4);
        assertEquals(ArrayUtil.indexOf(i, valueNotPresent), -1);
    }

    @Test
    public void testIndexOfLongArray() {
        long[] lNull = null;
        long[] lEmpty = {};
        long[] l = {467, -80, -6, 15319, 7123, 1, 27};
        long valueToFind = 7123, valueNotPresent = -67;

        assertEquals(ArrayUtil.indexOf(lNull, valueToFind), -1);
        assertEquals(ArrayUtil.indexOf(lEmpty, valueToFind), -1);
        assertEquals(ArrayUtil.indexOf(l, valueToFind), 4);
        assertEquals(ArrayUtil.indexOf(l, valueNotPresent), -1);
    }

    @Test
    public void testIndexOfFloatArrayFloat() {
        float[] fNull = null;
        float[] fEmpty = {};
        float[] f = {(float) 2304.4717, (float) 5979.8823, (float) 3807.3364, (float) 7541.264};
        float valueToFind = (float) 3807.3364, valueNotPresent = -67;

        assertEquals(ArrayUtil.indexOf(fNull, valueToFind), -1);
        assertEquals(ArrayUtil.indexOf(fEmpty, valueToFind), -1);
        assertEquals(ArrayUtil.indexOf(f, valueToFind), 2);
        assertEquals(ArrayUtil.indexOf(f, valueNotPresent), -1);
    }

    @Test
    public void testIndexOfDoubleArray() {
        double[] dNull = null;
        double[] dEmpty = {};
        double[] d = {2304.4717, 5979.8823, 3807.3364, 7541.264, 3473.358, 1370.6934, 1290.1748, 926.0253};
        double valueToFind = 1370.6934, valueNotPresent = -67;

        assertEquals(ArrayUtil.indexOf(dNull, valueToFind), -1);
        assertEquals(ArrayUtil.indexOf(dEmpty, valueToFind), -1);
        assertEquals(ArrayUtil.indexOf(d, valueToFind), 5);
        assertEquals(ArrayUtil.indexOf(d, valueNotPresent), -1);
    }

    @Test
    public void testIndexOfGenericArray() {

        // Check Boolean array contains
        Boolean[] bNone = null;
        Boolean[] bEmpty = {};
        Boolean[] bActual = {true, true, true, true, true, true, true, true, false, false, false, true, true, false,
                true, true, true, false, true, false};
        Boolean bValueToFind = false;

        assertEquals(ArrayUtil.indexOf(bNone, bValueToFind), -1);
        assertEquals(ArrayUtil.indexOf(bEmpty, bValueToFind), -1);
        assertEquals(ArrayUtil.indexOf(bActual, bValueToFind), 8);

        // Check Character array contains
        Character[] chNone = null;
        Character[] chEmpty = {};
        Character[] actualCharacter = {'s', 't', 'x', 'k', 's', 'q', 'k', 'k', 'a', 'u', 'b', 'z', 'w', 'b', 'f', 'b',
                's', 'z', 'y', 't'};
        Character chValueToFind = 'z';

        assertEquals(ArrayUtil.indexOf(chNone, chValueToFind), -1);
        assertEquals(ArrayUtil.indexOf(chEmpty, chValueToFind), -1);
        assertEquals(ArrayUtil.indexOf(actualCharacter, chValueToFind), 11);

        // Check Integer array contains
        Integer[] iNone = null;
        Integer[] iEmpty = {};
        Integer[] actualInteger = {2049, 2165, 417, 751, 1117, 1996, 501, 2063, 99, 1326, 308, 1740, 1914, 1043, 1750,
                1226, 1440, 658, 2243, 314};
        Integer intValueToFind = 314;

        assertEquals(ArrayUtil.indexOf(iNone, intValueToFind), -1);
        assertEquals(ArrayUtil.indexOf(iEmpty, intValueToFind), -1);
        assertEquals(ArrayUtil.indexOf(actualInteger, intValueToFind), 19);

        // Check Long array contains
        Long[] lNone = null;
        Long[] lEmpty = {};
        Long[] actualLong = {(long) 654, (long) 4048, (long) 3338, (long) 6306, (long) 3963, (long) 5575, (long) 5677,
                (long) 2442, (long) 3826, (long) 3696, (long) 2233, (long) 3431, (long) 5346, (long) 1667,
                (long) 4684, (long) 4007, (long) 3876, (long) 3245, (long) 86, (long) 4855};
        Long longValueToFind = (long) 5575;

        assertEquals(ArrayUtil.indexOf(lNone, longValueToFind), -1);
        assertEquals(ArrayUtil.indexOf(lEmpty, longValueToFind), -1);
        assertEquals(ArrayUtil.indexOf(actualLong, longValueToFind), 5);

        // Check Float array contains
        Float[] fNone = null;
        Float[] fEmpty = {};
        Float[] actualFloat = {(float) 469.3946, (float) 124.9198, (float) 893.9692, (float) 269.7879, (float) 238.9134,
                (float) 794.5137, (float) 576.5812, (float) 667.9545, (float) 415.4388, (float) 684.2454, (float) 276.4853,
                (float) 274.0731, (float) 569.1978, (float) 458.5947, (float) 14.6265, (float) 660.5167, (float) 600.5318,
                (float) 247.9708, (float) 317.594, (float) 242.2006};

        Float floatValueToFind = (float) 458.5947;

        assertEquals(ArrayUtil.indexOf(fNone, floatValueToFind), -1);
        assertEquals(ArrayUtil.indexOf(fEmpty, floatValueToFind), -1);
        assertEquals(ArrayUtil.indexOf(actualFloat, floatValueToFind), 13);

        // Check Double clone array
        Double[] dNone = null;
        Double[] dEmpty = {};
        Double[] actualDouble = {553.6981, 320.6328, 342.0801, 592.1744, 136.482, 559.7219, 585.7122, 507.4747,
                398.7696, 244.8682, 219.3931, 153.3111, 550.569, 68.0965, 418.9474, 450.5304, 119.2867, 551.1855,
                621.159, 402.7367};

        Double doubleValueToFind = 219.3931;

        assertEquals(ArrayUtil.indexOf(dNone, doubleValueToFind), -1);
        assertEquals(ArrayUtil.indexOf(dEmpty, doubleValueToFind), -1);
        assertEquals(ArrayUtil.indexOf(actualDouble, doubleValueToFind), 10);
    }
}
