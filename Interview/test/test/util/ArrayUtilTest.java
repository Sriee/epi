package test.util;

import static org.junit.Assert.*;
import org.junit.Test;

import util.ArrayUtil;
import util.generator.*;

public class ArrayUtilTest {

	@Test
	public void testCloneByteArray() {
		byte[] checkNull = null;
		assertNull(ArrayUtil.clone(checkNull));
		
		byte[] checkEmpty = {};
		assertNull(ArrayUtil.clone(checkEmpty));
		
		byte[] src = {-50, 120, 0, -4};
		byte[] dest = ArrayUtil.clone(src);
		assertArrayEquals(src, dest);
	}

	@Test
	public void testCloneShortArray() {
		short[] checkNull = null;
		assertNull(ArrayUtil.clone(checkNull));
		
		short[] checkEmpty = {};
		assertNull(ArrayUtil.clone(checkEmpty));
		
		short[] src = {-50, 120, 0, -4};
		short[] dest = ArrayUtil.clone(src);
		assertArrayEquals(src, dest);
	}

	@Test
	public void testCloneBooleanArray() {
		boolean[] checkNull = null;
		assertNull(ArrayUtil.clone(checkNull));
		
		boolean[] checkEmpty = {};
		assertNull(ArrayUtil.clone(checkEmpty));
		
		boolean[] src = {false, true, true, false};
		boolean[] dest = ArrayUtil.clone(src);
		assertArrayEquals(src, dest);
	}

	@Test
	public void testCloneCharArray() {
		char[] checkNull = null;
		assertNull(ArrayUtil.clone(checkNull));
		
		char[] checkEmpty = {};
		assertNull(ArrayUtil.clone(checkEmpty));
		
		char[] src = {'T', 'e', 's', 't', 'i', 'n', 'g'};
		char[] dest = ArrayUtil.clone(src);
		assertArrayEquals(src, dest);
	}

	@Test
	public void testCloneIntArray() {
		int[] checkNull = null;
		assertNull(ArrayUtil.clone(checkNull));
		
		int[] checkEmpty = {};
		assertNull(ArrayUtil.clone(checkEmpty));
		
		int[] src = {56780, -8720, 0, 5671};
		int[] dest = ArrayUtil.clone(src);
		assertArrayEquals(src, dest);
	}

	@Test
	public void testCloneLongArray() {
		long[] checkNull = null;
		assertNull(ArrayUtil.clone(checkNull));
		
		long[] checkEmpty = {};
		assertNull(ArrayUtil.clone(checkEmpty));
		
		long[] src = {-50, 67890, 56789, -564, 13};
		long[] dest = ArrayUtil.clone(src);
		assertArrayEquals(src, dest);
	}

	@Test
	public void testCloneFloatArray() {
		float[] checkNull = null;
		assertNull(ArrayUtil.clone(checkNull));
		
		float[] checkEmpty = {};
		assertNull(ArrayUtil.clone(checkEmpty));
		
		float[] src = { (float) 2304.4717, (float) 5979.8823, (float) 3807.3364, (float) 7541.264, 
						(float) 6007.075, (float) 3134.0088, (float) 5867.4204, (float) 26.1774, 
						(float) 3473.358, (float) 1370.6934, (float) 1290.1748, (float) 926.0253, 
						(float) 3618.7249, (float) 5131.829, (float) 7275.712};

		float[] dest = ArrayUtil.clone(src);
		assertArrayEquals(src, dest, 0.0002f);
	}

	@Test
	public void testCloneDoubleArray() {
		double[] checkNull = null;
		assertNull(ArrayUtil.clone(checkNull));
		
		double[] checkEmpty = {};
		assertNull(ArrayUtil.clone(checkEmpty));
		
		double[] src = {6194.5681, 6721.0002, 6455.8963, 5049.3596, 2499.4945, 6371.5027, 
						1726.3004, 2127.0672, 8105.5676, 104.5984, 4230.1216, 4908.8545, 
						3075.7336, 7986.601, 1121.1457};

		double[] dest = ArrayUtil.clone(src);
		assertArrayEquals(src, dest, 0.0002f);
	}

	@Test
	public void testCloneGenericArrayForNull() {
		Byte[] b = null;
		Short[] sh = null;
		Boolean[] bool = null;
		Character[] ch = null;
		Integer[] in = null;
		Long[] l = null;
		Float[] f = null;
		Double[] d = null;
		String[] s = null;
		
		assertNull(ArrayUtil.clone(Byte.class, b));
		assertNull(ArrayUtil.clone(Short.class, sh));
		assertNull(ArrayUtil.clone(Boolean.class, bool));
		assertNull(ArrayUtil.clone(Character.class, ch));
		assertNull(ArrayUtil.clone(Integer.class, in));
		assertNull(ArrayUtil.clone(Long.class,l));
		assertNull(ArrayUtil.clone(Float.class, f));
		assertNull(ArrayUtil.clone(Double.class, d));
		assertNull(ArrayUtil.clone(String.class, s));
		
		Byte[] bEmpty = {};
		Short[] shEmpty = {};
		Boolean[] boolEmpty = {};
		Character[] chEmpty = {};
		Integer[] intEmpty = {};
		Long[] lEmpty = {};
		Float[] fEmpty = {};
		Double[] dEmpty = {};
		String[] sEmpty = {};
		
		
		assertNull(ArrayUtil.clone(Byte.class, bEmpty));
		assertNull(ArrayUtil.clone(Short.class, shEmpty));
		assertNull(ArrayUtil.clone(Boolean.class, boolEmpty));
		assertNull(ArrayUtil.clone(Character.class, chEmpty));
		assertNull(ArrayUtil.clone(Integer.class, intEmpty));
		assertNull(ArrayUtil.clone(Long.class,lEmpty));
		assertNull(ArrayUtil.clone(Float.class, fEmpty));
		assertNull(ArrayUtil.clone(Double.class, dEmpty));
		assertNull(ArrayUtil.clone(String.class, sEmpty));
	}

	@Test
	public void testCloneGenericArrayFunctionality(){
		
		// Check Boolean clone array 
		BooleanGenerator bg = new BooleanGenerator();
		Boolean[] actualBoolean = ArrayUtil.toObject(bg.generate(20));
		Boolean[] expectedBoolean = ArrayUtil.clone(Boolean.class, actualBoolean);
		assertArrayEquals(actualBoolean, expectedBoolean);
		
		// Check Character clone array 
		CharacterGenerator ch = new CharacterGenerator();
		Character[] actualCharacter = ArrayUtil.toObject(ch.generate(20));
		Character[] expectedCharacter = ArrayUtil.clone(Character.class, actualCharacter);
		assertArrayEquals(actualCharacter, expectedCharacter);
		
		// Check Integer clone array 
		IntegerGenerator i = new IntegerGenerator();
		Integer[] actualInteger = ArrayUtil.toObject(i.generate(20));
		Integer[] expectedInteger = ArrayUtil.clone(Integer.class, actualInteger);
		assertArrayEquals(actualInteger, expectedInteger);
						
		// Check Long clone array
		LongGenerator lg = new LongGenerator();
		Long[] actualLong = ArrayUtil.toObject(lg.generate(20));
		Long[] expectedLong = ArrayUtil.clone(Long.class, actualLong);
		assertArrayEquals(actualLong, expectedLong);
		
		// Check Float clone array
		FloatGenerator fg = new FloatGenerator();
		Float[] actualFloat = ArrayUtil.toObject(fg.generate(20));
		Float[] expectedFloat = ArrayUtil.clone(Float.class, actualFloat);
		assertArrayEquals(actualFloat, expectedFloat);

		// Check Double clone array
		DoubleGenerator dg = new DoubleGenerator();
		Double[] actualDouble = ArrayUtil.toObject(dg.generate(20));
		Double[] expectedDouble = ArrayUtil.clone(Double.class, actualDouble);
		assertArrayEquals(actualDouble, expectedDouble);
	}
	
	@Test
	public void testContainsByteArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testContainsShortArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testContainsBooleanArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testContainsCharArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testContainsIntArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testContainsLongArrayLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testContainsFloatArrayFloat() {
		fail("Not yet implemented");
	}

	@Test
	public void testContainsDoubleArrayDouble() {
		fail("Not yet implemented");
	}

	@Test
	public void testContainsYArrayY() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsEmptyByteArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsEmptyShortArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsEmptyBooleanArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsEmptyCharArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsEmptyIntArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsEmptyLongArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsEmptyFloatArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsEmptyDoubleArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsEmptyTArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIndexOfByteArrayByte() {
		fail("Not yet implemented");
	}

	@Test
	public void testIndexOfShortArrayShort() {
		fail("Not yet implemented");
	}

	@Test
	public void testIndexOfBooleanArrayBoolean() {
		fail("Not yet implemented");
	}

	@Test
	public void testIndexOfCharArrayChar() {
		fail("Not yet implemented");
	}

	@Test
	public void testIndexOfIntArrayInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testIndexOfLongArrayLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testIndexOfFloatArrayFloat() {
		fail("Not yet implemented");
	}

	@Test
	public void testIndexOfDoubleArrayDouble() {
		fail("Not yet implemented");
	}

	@Test
	public void testIndexOfIArrayI() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsNotEmptyByteArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsNotEmptyShortArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsNotEmptyBooleanArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsNotEmptyCharArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsNotEmptyIntArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsNotEmptyLongArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsNotEmptyFloatArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsNotEmptyDoubleArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsNotEmptyTArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsSameLengthShortArrayShortArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsSameLengthByteArrayByteArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsSameLengthBooleanArrayBooleanArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsSameLengthCharArrayCharArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsSameLengthIntArrayIntArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsSameLengthLongArrayLongArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsSameLengthFloatArrayFloatArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsSameLengthDoubleArrayDoubleArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsSameLengthSArraySArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsSameType() {
		fail("Not yet implemented");
	}

	@Test
	public void testNullToEmptyByteArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testNullToEmptyShortArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testNullToEmptyBooleanArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testNullToEmptyCharacterArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testNullToEmptyIntegerArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testNullToEmptyLongArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testNullToEmptyFloatArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testNullToEmptyDoubleArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testNullToEmptyStringArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testReverseByteArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testReverseShortArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testReverseBooleanArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testReverseCharArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testReverseIntArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testReverseLongArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testReverseFloatArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testReverseDoubleArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testReverseRArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testSubarrayByteArrayIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testSubarrayShortArrayIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testSubarrayBooleanArrayIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testSubarrayCharArrayIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testSubarrayIntArrayIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testSubarrayLongArrayIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testSubarrayFloatArrayIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testSubarrayDoubleArrayIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testSubarrayClassOfNNArrayIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testSubarrayObjectArrayIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testToObjectByteArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testToObjectShortArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testToObjectBooleanArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testToObjectCharArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testToObjectIntArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testToObjectLongArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testToObjectFloatArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testToObjectDoubleArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testToPrimitiveByteArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testToPrimitiveShortArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testToPrimitiveBooleanArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testToPrimitiveCharacterArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testToPrimitiveIntegerArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testToPrimitiveLongArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testToPrimitiveFloatArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testToPrimitiveDoubleArray() {
		fail("Not yet implemented");
	}

}
