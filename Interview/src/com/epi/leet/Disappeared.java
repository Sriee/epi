package com.epi.leet;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Disappeared{

    private static List<Integer> dis(int[] nums){
        // Optimized working version
        List<Integer> list = new ArrayList<>();

        if(nums == null || nums.length == 0) return list;

        for(int i = 0; i < nums.length ;i++) {
            int idx = Math.abs(nums[i]) - 1;
            if(!(nums[idx] < 0)) nums[idx] = -nums[idx];
        }

        for (int j = 0; j < nums.length; j++) {
            if(nums[j] < 0) list.add(j + 1);
        }
        return list;
    }

	private static List<Integer> disappeared(int[] nums){
        // Takes some time if the size of nums is very large
	    List<Integer> list = new ArrayList<>();

	    if(nums == null || nums.length == 0) return list;

        for(int i = 1; i <= nums.length ;i++) list.add(i);
        for(int j : nums) {
            int idx = list.indexOf(j);
            if( idx != -1) {
                list.remove(j - 1);
                list.add(idx, Integer.MIN_VALUE);
            }
        }

	    list.removeAll(Collections.singleton(Integer.MIN_VALUE));
        return list;
    }

	public static void main(String[] args){
	    int[] arr = {1, 2, 2};
		System.out.println(disappeared(arr).toString());
        System.out.println(dis(new int []{4, 6, 2, 1, 2, 4}).toString());
	}
}
