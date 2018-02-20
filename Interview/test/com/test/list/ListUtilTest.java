package com.test.list;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.epi.list.ListUtil;


import com.epi.list.*;
import org.junit.Test;


public class ListUtilTest {

    @Test
    public void testListLength(){

        ListNode<Integer> intList = ListUtil.generate(5);
        assertEquals(0, ListUtil.length(null));
        assertEquals(5, ListUtil.length(intList));
    }

    @Test
    public void testPrint(){
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
}
