package com.epi.leet;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class NextGreat {

    /**
     * [1,3,5,2,4]
     [6,5,4,3,2,1,7]
     */
    private static int[] nextGreat(int[] nums1, int[] nums2){

        if(nums1 == null || nums2 == null)
            return new int[]{};

        if(nums1.length == 0 || nums2.length == 0)
            return new int[]{};

        List<Integer> list = new ArrayList<>();
        for(int num : nums2) list.add(num);

        int []result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            int idx = list.indexOf(nums1[i]);
            int highIdx = -1, curMax = Integer.MIN_VALUE;

            if(idx == nums2.length - 1) {
                result[i] = -1;
                continue;
            }

            idx += 1;
            while(idx < nums2.length){
                if(nums2[idx] > nums1[i] && nums2[idx] > curMax){
                    curMax = nums2[idx];
                    highIdx = idx;
                }
                idx++;
            }

            result[i] = (highIdx == -1) ? highIdx : nums2[highIdx];
        }
        return result;
    }
    public static void main(String[] args) {
        int[] nums1 = {1,3,5,2,4};
        int[] nums2 = {6,5,4,3,2,1,7};
        System.out.println(Arrays.toString(nextGreat(nums1, nums2)));
        System.out.println();
    }
}
