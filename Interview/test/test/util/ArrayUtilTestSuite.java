package test.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
		ArrayUtilCloneTest.class,
		ArrayUtilContainsTest.class,
		ArrayUtilEmptyTest.class,
		ArrayUtilIndexOfTest.class,
		ArrayUtilNotEmptyTest.class,
		ArrayUtilNullEmptyTest.class,
		ArrayUtilReverseTest.class,
		ArrayUtilSubarrayTest.class,
		ArrayUtilToObjectTest.class,
		ArrayUtilToPrimitiveTest.class,
		ArrayUtilTypeTest.class
})

public class ArrayUtilTestSuite {

}
