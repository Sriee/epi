package com.epi.tree;

import sun.swing.SwingUtilities2;

import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

public class Scratch {

    public List<List<Integer>> binaryTree() {
        List<List<Integer>> temp = new ArrayList<>();

        // Inner List 1
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(0);
        a.add(0);

        // Inner List 2
        List<Integer> b = new ArrayList<>();
        b.add(1);
        b.add(0);
        b.add(1);

        // Inner List 3
        List<Integer> c = new ArrayList<>();
        c.add(1);
        c.add(1);
        c.add(0);

        temp.add(a);
        temp.add(b);
        temp.add(c);

        return temp;
    }

    public static void main(String[] args) {
        Scratch s = new Scratch();

        List<List<Integer>> list1 = s.binaryTree();

        for(List<Integer> i : list1) {
            i.add(0, 1);
        }

        System.out.println("Printing List of List");
        int total = 0;
        for(List<Integer> i : list1) {
            int level = i.size() - 1;
            int sum = 0;

            for(Integer j : i) {
                sum = sum + (j * (int)Math.pow(2, level));
                level--;
            }
            total += sum;
            System.out.print("Sum of " + i.toString() + " is " + sum + "\n");
        }
        System.out.println(Math.pow(2, 3));
        System.out.println("Total sum: " + total);
    }
}
