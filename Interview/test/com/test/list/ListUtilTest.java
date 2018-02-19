package com.test.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
    public void testDelelte(){

    }
}
