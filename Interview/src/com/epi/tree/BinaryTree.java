package com.epi.tree;

import java.util.List;
import java.util.ArrayList;

public class BinaryTree {

    public void addNode(List<List<Integer>> list, int val) {
        for(List<Integer> i : list)
            i.add(0, val);
    }

    public List<List<Integer>> binaryTree(TreeNode node) {
        if(node.left == null && node.right == null){
            List<List<Integer>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        } else if(node.left == null) {
            List<Integer> leftChild = new ArrayList<>();
            leftChild.add(node.val);

            List<List<Integer>> rightChild = this.binaryTree(node.right);
            this.addNode(rightChild, node.val);
            rightChild.add(0, leftChild);

            return rightChild;
        } else if (node.right == null) {
            List<List<Integer>> leftChild = this.binaryTree(node.left);
            this.addNode(leftChild, node.val);

            List<Integer> rightChild = new ArrayList<>();
            rightChild.add(node.val);

            leftChild.add(0, rightChild);
            return leftChild;
        } else {
            List<List<Integer>> leftChild = this.binaryTree(node.left);
            List<List<Integer>> rightChild = this.binaryTree(node.right);

            this.addNode(leftChild, node.val);
            for(List<Integer> i : rightChild) {
                i.add(node.val);
                leftChild.add(i);
            }
            return leftChild;
        }
    }

    public int sumOfBinaryTree(TreeNode root) {
        if(root == null)
            return 0;

        List<List<Integer>> result = this.binaryTree(root);

        int total = 0;
        for(List<Integer> i : result) {
            int level = i.size() - 1;
            int sum = 0;

            for(Integer j : i) {
                sum = sum + (j * (int)Math.pow(2, level));
                level--;
            }
            total += sum;
        }

        return total;
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        int total = bt.sumOfBinaryTree(null);
        System.out.println("Total sum: " + total);

        Traversal t = new Traversal();
        TreeNode root = t.construct(new int[] {1, 0, 1, 0, 1, 0, 1}, 0);
        total = bt.sumOfBinaryTree(root);
        System.out.println("Total sum: " + total);
    }

}
