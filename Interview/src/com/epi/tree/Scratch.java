package com.epi.tree;

import java.util.Arrays;


public class Scratch {

    private TreeNode construct(int[] values, int idx){
        if(idx >= values.length)
            return null;

        TreeNode node = new TreeNode(values[idx]);
        node.left = this.construct(values, 2 * idx + 1);
        node.right = this.construct(values, 2 *idx + 2);
        return node;
    }

    public void inOrder(TreeNode root){
        if(root == null) return;

        this.inOrder(root.left);
        System.out.print(root.val + " ");
        this.inOrder(root.right);
    }

    public void preOrder(TreeNode root){
        if(root == null) return;

        System.out.print(root.val  + " ");
        this.preOrder(root.left);
        this.preOrder(root.right);
    }

    public void postOrder(TreeNode root){
        if(root == null) return;
        this.postOrder(root.left);
        this.postOrder(root.right);
        System.out.print(root.val  + " ");
    }

    public static void main(String[] args) {
        Scratch s = new Scratch();
        int[] values = new int[3];

        for(int i = 1; i < 4; i++){
            values[i - 1]= i;
        }

        System.out.println(Arrays.toString(values));
        TreeNode root = s.construct(values, 0);
        System.out.print("InOrder: ");
        s.preOrder(root);

    }
}
