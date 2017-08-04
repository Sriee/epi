package util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
		ArrayUtilAppendTest.class,
		ArrayUtilCloneTest.class,
		ArrayUtilContainsTest.class,
		ArrayUtilEmptyTest.class,
		ArrayUtilIndexOfTest.class,
		ArrayUtilNotEmptyTest.class,
		ArrayUtilNullEmptyTest.class,
		ArrayUtilReverseTest.class,
		ArrayUtilSubarrayTest.class,
		ArrayUtilToObjectAndTypeTest.class,
		ArrayUtilToPrimitiveTest.class,
})

public class ArrayUtilTestSuite {

}