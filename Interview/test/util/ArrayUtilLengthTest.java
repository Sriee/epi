package util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import util.generator.*;

public class ArrayUtilLengthTest {

    @Test
    public void testIsSameLengthByteArray() {
        byte[] src = {-50, 120, 0, -4};
        byte[] dest = ArrayUtil.clone(src);
        assertTrue(ArrayUtil.isSameLength(src, dest));
    }

    @Test
    public void testIsSameLengthShortArray() {
        short[] src = {-50, 120, 0, -4};
        short[] dest = ArrayUtil.clone(src);
        assertTrue(ArrayUtil.isSameLength(src, dest));
    }

    @Test
    public void testIsSameLengthBooleanArray() {
        boolean[] src = {false, true, true, false};
        boolean[] dest = ArrayUtil.clone(src);
        assertTrue(ArrayUtil.isSameLength(src, dest));
    }

    @Test
    public void testIsSameLengthCharArray() {
        char[] src = {'T', 'e', 's', 't', 'i', 'n', 'g'};
        char[] dest = ArrayUtil.clone(src);
        assertTrue(ArrayUtil.isSameLength(src, dest));
    }

    @Test
    public void testIsSameLengthIntArray() {
        int[] src = {56780, -8720, 0, 5671};
        int[] dest = ArrayUtil.clone(src);
        assertTrue(ArrayUtil.isSameLength(src, dest));
    }

    @Test
    public void testIsSameLengthLongArray() {
        long[] src = {-50, 67890, 56789, -564, 13};
        long[] dest = ArrayUtil.clone(src);
        assertTrue(ArrayUtil.isSameLength(src, dest));
    }

    @Test
    public void testIsSameLengthFloatArray() {
        float[] src = {(float) 2304.4717, (float) 5979.8823, (float) 3807.3364, (float) 7541.264, (float) 6007.075,
                (float) 3134.0088, (float) 5867.4204, (float) 26.1774, (float) 3473.358, (float) 1370.6934,
                (float) 1290.1748, (float) 926.0253, (float) 3618.7249, (float) 5131.829, (float) 7275.712};

        float[] dest = ArrayUtil.clone(src);
        assertTrue(ArrayUtil.isSameLength(src, dest));
    }

    @Test
    public void testIsSameLengthDoubleArray() {
        double[] src = {6194.5681, 6721.0002, 6455.8963, 5049.3596, 2499.4945, 6371.5027, 1726.3004, 2127.0672,
                8105.5676, 104.5984, 4230.1216, 4908.8545, 3075.7336, 7986.601, 1121.1457};

        double[] dest = ArrayUtil.clone(src);
        assertTrue(ArrayUtil.isSameLength(src, dest));
    }

    @Test
    public void testIsSameLengthGenericArray() {
        // Check Boolean clone array
        BooleanGenerator bg = new BooleanGenerator();
        Boolean[] actualBoolean = ArrayUtil.toObject(bg.generate(20));
        Boolean[] expectedBoolean = ArrayUtil.clone(Boolean.class, actualBoolean);
        assertTrue(ArrayUtil.isSameLength(actualBoolean, expectedBoolean));

        // Check Character clone array
        CharacterGenerator ch = new CharacterGenerator();
        Character[] actualCharacter = ArrayUtil.toObject(ch.generate(20));
        Character[] expectedCharacter = ArrayUtil.clone(Character.class, actualCharacter);
        assertTrue(ArrayUtil.isSameLength(actualCharacter, expectedCharacter));

        // Check Integer clone array
        IntegerGenerator i = new IntegerGenerator(0, 5000);
        Integer[] actualInteger = ArrayUtil.toObject(i.generate(20));
        Integer[] expectedInteger = ArrayUtil.clone(Integer.class, actualInteger);
        assertTrue(ArrayUtil.isSameLength(actualInteger, expectedInteger));

        // Check Long clone array
        LongGenerator lg = new LongGenerator(0, 50000);
        Long[] actualLong = ArrayUtil.toObject(lg.generate(20));
        Long[] expectedLong = ArrayUtil.clone(Long.class, actualLong);
        assertTrue(ArrayUtil.isSameLength(actualLong, expectedLong));

        // Check Float clone array
        FloatGenerator fg = new FloatGenerator(1, 50000);
        Float[] actualFloat = ArrayUtil.toObject(fg.generate(20));
        Float[] expectedFloat = ArrayUtil.clone(Float.class, actualFloat);
        assertTrue(ArrayUtil.isSameLength(actualFloat, expectedFloat));

        // Check Double clone array
        DoubleGenerator dg = new DoubleGenerator(1, 50000);
        Double[] actualDouble = ArrayUtil.toObject(dg.generate(20));
        Double[] expectedDouble = ArrayUtil.clone(Double.class, actualDouble);
        assertTrue(ArrayUtil.isSameLength(actualDouble, expectedDouble));
    }
}
