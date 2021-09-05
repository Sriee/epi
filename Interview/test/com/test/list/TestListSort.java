package com.test.list;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import list.*;

public class TestListSort {

    private static ListSort is = null;

    @Before
    public void setUp() {
        is = new ListSort();
    }

    @Test
    public void testInsertionSortEdgeCases() {

        ListNode<Integer> toSort = new ListNode<>(1);

        assertEquals(toSort, is.insertionSort(toSort));
        assertEquals(null, is.insertionSort(null));
    }

    @Test
    public void testInsertionSortCorrectness() {

        // To sort
        ListNode<Integer> toSort = new ListNode<>(Integer.MIN_VALUE);
        toSort.next = new ListNode<>(0);
        toSort.next.next = new ListNode<>(-41);
        toSort.next.next.next = new ListNode<>(Integer.MAX_VALUE);
        toSort.next.next.next.next = new ListNode<>(77);
        toSort.next.next.next.next.next = new ListNode<>(3167);
        toSort.next.next.next.next.next.next = new ListNode<>(44);
        toSort.next.next.next.next.next.next.next = new ListNode<>(-1);

        // Sorted
        ListNode<Integer> expected = new ListNode<>(Integer.MIN_VALUE);
        expected.next = new ListNode<>(-41);
        expected.next.next = new ListNode<>(-1);
        expected.next.next.next = new ListNode<>(0);
        expected.next.next.next.next = new ListNode<>(44);
        expected.next.next.next.next.next = new ListNode<>(77);
        expected.next.next.next.next.next.next = new ListNode<>(3167);
        expected.next.next.next.next.next.next.next = new ListNode<>(Integer.MAX_VALUE);

        ListNode<Integer> sorted = is.insertionSort(toSort);
        assertEquals(8, ListUtil.length(sorted));

        ListNode<Integer> _ex = expected, _so = sorted;

        while (_ex != null) {
            assertEquals(_ex.val, _so.val);
            _ex = _ex.next;
            _so = _so.next;
        }
    }

    @Test
    public void testMergeSortEdgeCases() {

        ListNode<Integer> toSort = new ListNode<>(1);

        assertEquals(toSort, is.mergeSort(toSort));
        assertEquals(null, is.mergeSort(null));

        toSort.next = new ListNode<>(-2);
        ListNode<Integer> sorted = is.mergeSort(toSort);
        assertEquals(-2, (int) sorted.val);
        assertEquals(1, (int) sorted.next.val);
    }

    @Test
    public void testMergeSortCorrectness() {

        // To sort
        ListNode<Integer> toSort = new ListNode<>(Integer.MIN_VALUE);
        toSort.next = new ListNode<>(0);
        toSort.next.next = new ListNode<>(-41);
        toSort.next.next.next = new ListNode<>(Integer.MAX_VALUE);
        toSort.next.next.next.next = new ListNode<>(77);
        toSort.next.next.next.next.next = new ListNode<>(3167);
        toSort.next.next.next.next.next.next = new ListNode<>(44);
        toSort.next.next.next.next.next.next.next = new ListNode<>(-1);

        // Sorted
        ListNode<Integer> expected = new ListNode<>(Integer.MIN_VALUE);
        expected.next = new ListNode<>(-41);
        expected.next.next = new ListNode<>(-1);
        expected.next.next.next = new ListNode<>(0);
        expected.next.next.next.next = new ListNode<>(44);
        expected.next.next.next.next.next = new ListNode<>(77);
        expected.next.next.next.next.next.next = new ListNode<>(3167);
        expected.next.next.next.next.next.next.next = new ListNode<>(Integer.MAX_VALUE);

        ListNode<Integer> sorted = is.mergeSort(toSort);
        assertEquals(8, ListUtil.length(sorted));

        ListNode<Integer> _ex = expected, _so = sorted;

        while (_ex != null) {
            assertEquals(_ex.val, _so.val);
            _ex = _ex.next;
            _so = _so.next;
        }
    }
}