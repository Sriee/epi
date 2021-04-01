package util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

public class ArrayUtilInsertRemoveObjectTest {

    private static Axis[] axes = null;

    @Before
    public void setUp() throws Exception {
        Axis a00 = new Axis();
        Axis a50 = new Axis(5, 0);
        Axis b04 = new Axis(0, 4);
        Axis b54 = new Axis(5, 4);

        axes = new Axis[4];
        axes[0] = a00;
        axes[1] = a50;
        axes[2] = b04;
        axes[3] = b54;
    }

    @After
    public void tearDown() throws Exception {
        axes = null;
    }

    @Test
    public void testInsertOneObject() {
        Axis[] none = null;
        Axis[] empty = {};
        Axis a22 = new Axis(2, 2);
        Axis[] expected = new Axis[axes.length + 1];

        for (int i = 0; i < axes.length; i++) {
            expected[i] = axes[i];
        }

        expected[axes.length] = a22;

        int indexLessThanZero = -11, indexGreaterThanArrayLength = axes.length + 10;

        assertNull(ArrayUtil.insert(axes, indexLessThanZero, a22));
        assertNull(ArrayUtil.insert(axes, indexGreaterThanArrayLength, a22));

        assertArrayEquals(ArrayUtil.insert(none, 1, a22), new Axis[] { a22 });
        assertArrayEquals(ArrayUtil.insert(empty, 1, a22), new Axis[] { a22 });

        assertArrayEquals(ArrayUtil.insert(Axis.class, axes, 4, a22), expected);
    }

    @Test
    public void testInsertMultipleObject() {
        Axis a22 = new Axis(2, 2);
        Axis a36 = new Axis(3, 6);
        Axis[] expected = new Axis[axes.length + 2];

        expected[0] = axes[0];
        expected[3] = axes[1];
        expected[1] = a22;
        expected[4] = axes[2];
        expected[2] = a36;
        expected[5] = axes[3];
        assertArrayEquals(ArrayUtil.insert(Axis.class, axes, 1, a22, a36), expected);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveNullObject() {
        Axis[] none = null;
        Axis a22 = new Axis(2, 2);
        assertArrayEquals(ArrayUtil.remove(none, 2), new Axis[] { a22 });
    }

    @Test(expected = NegativeArraySizeException.class)
    public void testRemoveEmptyObject() {
        Axis[] empty = {};
        Axis a22 = new Axis(2, 2);
        assertArrayEquals(ArrayUtil.remove(empty, 2), new Axis[] { a22 });
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveObject() {
        Axis a22 = new Axis(2, 2);
        assertArrayEquals(ArrayUtil.remove(axes, 20), new Axis[] { a22 });
    }

    @Test
    public void testRemoveOneObject() {
        Axis[] expected = new Axis[axes.length - 1];
        expected[0] = axes[0];
        expected[1] = axes[1];
        expected[2] = axes[3];
        assertArrayEquals(ArrayUtil.remove(axes, 2), expected);
    }

    @Test
    public void testRemoveMultipleObject() {
        Axis[] expected = new Axis[axes.length - 2];
        expected[0] = axes[0];
        expected[1] = axes[1];
        assertArrayEquals(ArrayUtil.remove(axes, 2, 3), expected);
        assertArrayEquals(ArrayUtil.remove(axes, 1, 0, 3, 3, 2), new Axis[] {});
    }
}
