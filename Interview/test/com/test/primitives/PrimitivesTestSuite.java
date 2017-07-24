package com.test.primitives;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
		BaseConversionTest.class,
		DecodeExcelTest.class,
		ParityTest.class,
		PowerSetTest.class,
		ReverseBitsTest.class,
		StringIntTest.class,
		SwapBitsTest.class,
		GCDTest.class,
		PrimeGeneratorTest.class
})

public class PrimitivesTestSuite {

}
