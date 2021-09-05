package com.test.list;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import list.*;

public class ListUtilTest {

    @Test
    public void testListLength() {

        ListNode<Integer> intList = ListUtil.generate(5);
        assertEquals(0, ListUtil.length(null));
        assertEquals(5, ListUtil.length(intList));
    }

    @Test
    public void testPrint() {
        final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ListNode<Integer> head = null;
        System.setOut(new PrintStream(outStream));

        // Single node
        head = new ListNode<>(1);
        ListUtil.print(head);
        assertEquals("1\n", outStream.toString());

        // Null node
        ListUtil.print(null);
        assertEquals("1\n", outStream.toString());

        // Multiple
        head = new ListNode<>(Integer.MIN_VALUE);
        head.next = new ListNode<>(0);
        head.next.next = new ListNode<>(-41);
        head.next.next.next = new ListNode<>(Integer.MAX_VALUE);
        head.next.next.next.next = new ListNode<>(-77);
        head.next.next.next.next.next = new ListNode<>(31789);
        head.next.next.next.next.next.next = new ListNode<>(44);
        head.next.next.next.next.next.next.next = new ListNode<>(555);
        ListUtil.print(head);
        assertEquals("1\n-2147483648 -> 0 -> -41 -> 2147483647 -> -77 -> 31789 -> 44 -> 555\n", outStream.toString());

        System.setOut(System.out);
    }

    @Test
    public void testMergeEdgeCases() {

        ListNode<Integer> a = new ListNode<>(-1);
        ListNode<Integer> b = new ListNode<>(3);

        ListNode<Integer> _res = ListUtil.merge(null, a);
        assertEquals(-1, (int) _res.val);
        assertEquals(1, ListUtil.length(_res));

        _res = ListUtil.merge(b, null);
        assertEquals(3, (int) _res.val);
        assertEquals(1, ListUtil.length(_res));

        _res = ListUtil.merge(a, b);
        assertEquals(2, ListUtil.length(_res));
        assertEquals(-1, (int) _res.val);
        assertEquals(3, (int) _res.next.val);

        a.next = new ListNode<>(10);
        _res = ListUtil.merge(a, b);
        assertEquals(3, ListUtil.length(_res));
        assertEquals(-1, (int) _res.val);
        assertEquals(3, (int) _res.next.val);
        assertEquals(10, (int) _res.next.next.val);
    }

    @Test
    public void testMegerCorrectness() {
        ListNode<Integer> a = new ListNode<>(-1);
        a.next = new ListNode<>(10);
        a.next.next = new ListNode<>(27);
        a.next.next.next = new ListNode<>(783);

        ListNode<Integer> b = new ListNode<>(Integer.MIN_VALUE);
        b.next = new ListNode<>(3);
        b.next.next = new ListNode<>(Integer.MAX_VALUE);

        ListNode<Integer> _res = ListUtil.merge(a, b);
        assertEquals(7, ListUtil.length(_res));

        // -2147483648 -> -1 -> 3 -> 10 -> 27 -> 783 -> 2147483647
        assertEquals(Integer.MIN_VALUE, (int) _res.val);
        assertEquals(-1, (int) _res.next.val);
        assertEquals(3, (int) _res.next.next.val);
        assertEquals(10, (int) _res.next.next.next.val);
        assertEquals(27, (int) _res.next.next.next.next.val);
        assertEquals(783, (int) _res.next.next.next.next.next.val);
        assertEquals(Integer.MAX_VALUE, (int) _res.next.next.next.next.next.next.val);
    }
}
