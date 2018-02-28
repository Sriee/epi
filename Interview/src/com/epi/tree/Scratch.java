package com.epi.tree;

import java.util.List;
import java.util.LinkedList;


public class Scratch {

    public static void main(String[] args) {
    		List<Integer> list1 = new LinkedList<>(), list2 = new LinkedList<>();
    		
    		list1.add(1);
    		list1.add(2);
    		
    		list2.add(3);
    		list2.add(4);
    		
    		list1.addAll(list2);
    		
    		System.out.println(list1.toString());
    }
}
