package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class SegmentTree {

    public int build(int[] heights, int[] st, int start, int end, int index) {
        if(start > end)
            return -1;
        
        if(start == end) {
            st[index] = start;
        } else {
            int mid = start + (end - start) / 2;
            st[index] = Math.min(build(heights, st, start, mid, 2*index + 1), build(heights, st, start, mid, 2*index + 2));
        }
        
        return st[index];
    }

    public int findMinIndex(int[] heights, int[] st, int start, int end, int queryStart, int queryEnd, int index) {
        if(queryStart <= start && queryEnd >= end)
            return st[index];
        
        if(end < queryStart || start > queryEnd)
            return -1;
        
        int mid = start + (end - start) / 2;
        int l = findMinIndex(heights, st, start, mid, queryStart, queryEnd, 2 * index + 1);
        int r = findMinIndex(heights, st, mid + 1, end, queryStart, queryEnd, 2 * index + 2);

        if(l == -1)
            return r;
        else if(r == -1)
            return l;
        else
            return (heights[l] < heights[r]) ? l : r;
    }

    public SegmentTreeNode buildTree(int[] heights, int start, int end) {
        if(start > end)
            return null;

        SegmentTreeNode node = new SegmentTreeNode(start, end);

        if(start == end) {
            node.minIndex = start;
        } else {
            int mid = start + (end - start) / 2;

            node.left = buildTree(heights, start, mid);
            node.right = buildTree(heights, mid + 1, end);

            if (node.left != null && node.right != null)
                node.minIndex = (heights[node.left.minIndex] < heights[node.right.minIndex]) ? node.left.minIndex : node.right.minIndex;
            else if (node.left == null)
                node.minIndex = node.right.minIndex;
            else
                node.minIndex = node.left.minIndex;
        }

        return node;
    }
    
    public int findIndex(int[] heights, SegmentTreeNode node, int start, int end) {
        if(node == null || start > end || end < node.start || start > node.end)
            return -1;

        if(start == node.start && end == node.end)
            return node.minIndex;

        int mid = node.start + (node.end - node.start) / 2;
        int l = findIndex(heights, node.left, start, Math.min(mid, end));
        int r = findIndex(heights, node.right, Math.max(start, mid + 1), end);

        if(l == -1)
            return r;
        else if(r == -1)
            return l;
        else
            return (heights[l] < heights[r]) ? l : r;
    }
    
    // Print the segment tree
    public List<List<int[]>> levelOrder(SegmentTreeNode root) {
        Queue<SegmentTreeNode> queue = new LinkedList<>();
        List<List<int[]>> result = new ArrayList<>();
        List<int[]> innerList;

        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            innerList = new ArrayList<>();
            
            for(int i = 0; i < size; i++) {
                SegmentTreeNode node = queue.remove();
                
                int[] trio = new int[]{ node.start, node.end, node.minIndex};
                innerList.add(trio);
                
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            
            result.add(innerList);
        }
        return result;
    }
    
    public void print(List<List<int[]>> elements) {
        for (List<int[]> inner : elements) {
            System.out.print("[");
            for (int[] val : inner) {
                System.out.print("(" + val[0] + ", " + val[1] + ", " + val[2] + ") ");
            }
            System.out.println("]");
        }
        
    }
}
