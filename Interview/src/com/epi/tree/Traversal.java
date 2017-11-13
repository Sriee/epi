package com.epi.tree;

import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

public class Traversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> innerList = new LinkedList<>();
        
        int current = 1, next = 0; 
        queue.add(root);
        while(!queue.isEmpty()){
            
            TreeNode node = queue.remove();
            current--;
            if(node != null){
                innerList.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
                next += 2;
            }
            
            if(current == 0){
                if(!innerList.isEmpty()){
                    result.add(innerList);
                }
                innerList = new LinkedList<>();
                current = next;
                next = 0;
            }
        }
        return result;
    }
}
