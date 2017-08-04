package util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import util.generator.BooleanGenerator;
import util.generator.CharacterGenerator;
import util.generator.DoubleGenerator;
import util.generator.FloatGenerator;
import util.generator.IntegerGenerator;
import util.generator.LongGenerator;

public class ArrayUtilContainsTest {

    @Test
    public void testContainsByteArray() {
        byte[] bNull = null;
        byte[] bEmpty = {};
        byte[] by = {-2, 21, -67, 0};
        byte valueToFind = 21, valueNotPresent = 93;

        assertFalse(ArrayUtil.contains(bNull, valueToFind));
        assertFalse(ArrayUtil.contains(bEmpty, valueToFind));
        assertTrue(ArrayUtil.contains(by, valueToFind));
        assertFalse(ArrayUtil.contains(by, valueNotPresent));
    }

    @Test
    public void testContainsShortArray() {
        short[] shNull = null;
        short[] shEmpty = {};
        short[] sh = {-2, 21, -67, 0};
        short valueToFind = 21, valueNotPresent = 93;

        assertFalse(ArrayUtil.contains(shNull, valueToFind));
        assertFalse(ArrayUtil.contains(shEmpty, valueToFind));
        assertTrue(ArrayUtil.contains(sh, valueToFind));
        assertFalse(ArrayUtil.contains(sh, valueNotPresent));
    }

    @Test
    public void testContainsBooleanArray() {
        boolean[] bNull = null;
        boolean[] bEmpty = {};
        boolean[] bool = {false, false, false, false};
        boolean valueToFind = false, valueNotPresent = true;

        assertFalse(ArrayUtil.contains(bNull, valueToFind));
        assertFalse(ArrayUtil.contains(bEmpty, valueToFind));
        assertTrue(ArrayUtil.contains(bool, valueToFind));
        assertFalse(ArrayUtil.contains(bool, valueNotPresent));
    }

    @Test
    public void testContainsCharArray() {
        char[] chNull = null;
        char[] chEmpty = {};
        char[] ch = {'f', 'y', 'u', 'o', 'i', 'h', 'j'};
        char valueToFind = 'y', valueNotPresent = 'a';

        assertFalse(ArrayUtil.contains(chNull, valueToFind));
        assertFalse(ArrayUtil.contains(chEmpty, valueToFind));
        assertTrue(ArrayUtil.contains(ch, valueToFind));
        assertFalse(ArrayUtil.contains(ch, valueNotPresent));
    }

    @Test
    public void testContainsIntArray() {
        int[] intNull = null;
        int[] intEmpty = {};
        int[] i = {467, -80, -6, 15319, 7123, 1, 27};
        int valueToFind = 7123, valueNotPresent = -67;

        assertFalse(ArrayUtil.contains(intNull, valueToFind));
        assertFalse(ArrayUtil.contains(intEmpty, valueToFind));
        assertTrue(ArrayUtil.contains(i, valueToFind));
        assertFalse(ArrayUtil.contains(i, valueNotPresent));
    }

    @Test
    public void testContainsLongArray() {
        long[] lNull = null;
        long[] lEmpty = {};
        long[] l = {467, -80, -6, 15319, 7123, 1, 27};
        long valueToFind = 7123, valueNotPresent = -67;

        assertFalse(ArrayUtil.contains(lNull, valueToFind));
        assertFalse(ArrayUtil.contains(lEmpty, valueToFind));
        assertTrue(ArrayUtil.contains(l, valueToFind));
        assertFalse(ArrayUtil.contains(l, valueNotPresent));
    }

    @Test
    public void testContainsFloatArray() {
        float[] fNull = null;
        float[] fEmpty = {};
        float[] f = {(float) 2304.4717, (float) 5979.8823, (float) 3807.3364, (float) 7541.264};
        float valueToFind = (float) 5979.8823, valueNotPresent = -67;

        assertFalse(ArrayUtil.contains(fNull, valueToFind));
        assertFalse(ArrayUtil.contains(fEmpty, valueToFind));
        assertTrue(ArrayUtil.contains(f, valueToFind));
        assertFalse(ArrayUtil.contains(f, valueNotPresent));
    }

    @Test
    public void testContainsDoubleArray() {
        double[] dNull = null;
        double[] dEmpty = {};
        double[] d = {2304.4717, 5979.8823, 3807.3364, 7541.264, 3473.358, 1370.6934, 1290.1748, 926.0253,};
        double valueToFind = 1370.6934, valueNotPresent = -67;

        assertFalse(ArrayUtil.contains(dNull, valueToFind));
        assertFalse(ArrayUtil.contains(dEmpty, valueToFind));
        assertTrue(ArrayUtil.contains(d, valueToFind));
        assertFalse(ArrayUtil.contains(d, valueNotPresent));
    }

    @Test
    public void testContainsGenericArray() {

        // Check Boolean array contains
        BooleanGenerator bg = new BooleanGenerator();
        Boolean[] boolNone = null;
        Boolean[] boolEmpty = {};
        Boolean[] boolActual = ArrayUtil.toObject(bg.generate(20));
        boolean boolValueToFind = boolActual[2];

        assertFalse(ArrayUtil.contains(boolNone, boolValueToFind));
        assertFalse(ArrayUtil.contains(boolEmpty, boolValueToFind));
        assertTrue(ArrayUtil.contains(boolActual, boolValueToFind));

        // Check Character array contains
        CharacterGenerator ch = new CharacterGenerator();
        Character[] chNone = null;
        Character[] chEmpty = {};
        Character[] actualCharacter = ArrayUtil.toObject(ch.generate(20));
        char chValueToFind = actualCharacter[14];

        assertFalse(ArrayUtil.contains(chNone, chValueToFind));
        assertFalse(ArrayUtil.contains(chEmpty, chValueToFind));
        assertTrue(ArrayUtil.contains(actualCharacter, chValueToFind));

        // Check Integer array contains
        IntegerGenerator i = new IntegerGenerator(1, 2678);
        Integer[] iNone = null;
        Integer[] iEmpty = {};
        Integer[] actualInteger = ArrayUtil.toObject(i.generate(20));
        int intValueToFind = actualInteger[19];

        assertFalse(ArrayUtil.contains(iNone, intValueToFind));
        assertFalse(ArrayUtil.contains(iEmpty, intValueToFind));
        assertTrue(ArrayUtil.contains(actualInteger, intValueToFind));

        // Check Long array contains
        LongGenerator lg = new LongGenerator(1, 6398);
        Long[] lNone = null;
        Long[] lEmpty = {};
        Long[] actualLong = ArrayUtil.toObject(lg.generate(20));
        long longValueToFind = actualLong[5];

        assertFalse(ArrayUtil.contains(lNone, longValueToFind));
        assertFalse(ArrayUtil.contains(lEmpty, longValueToFind));
        assertTrue(ArrayUtil.contains(actualLong, longValueToFind));

        // Check Float array contains
        FloatGenerator fg = new FloatGenerator(7, 629);
        Float[] fNone = null;
        Float[] fEmpty = {};
        Float[] actualFloat = ArrayUtil.toObject(fg.generate(20));
        float floatValueToFind = actualFloat[10];

        assertFalse(ArrayUtil.contains(fNone, floatValueToFind));
        assertFalse(ArrayUtil.contains(fEmpty, floatValueToFind));
        assertTrue(ArrayUtil.contains(actualFloat, floatValueToFind));

        // Check Double clone array
        DoubleGenerator dg = new DoubleGenerator(1, 927);
        Double[] dNone = null;
        Double[] dEmpty = {};
        Double[] actualDouble = ArrayUtil.toObject(dg.generate(20));
        double doubleValueToFind = actualDouble[7];

        assertFalse(ArrayUtil.contains(dNone, doubleValueToFind));
        assertFalse(ArrayUtil.contains(dEmpty, doubleValueToFind));
        assertTrue(ArrayUtil.contains(actualDouble, doubleValueToFind));
    }
}
