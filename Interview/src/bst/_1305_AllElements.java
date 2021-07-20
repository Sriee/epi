package bst;

import java.util.*;

class _1305_AllElements {

    /**
     * Optimal run time but the following approach looks hacky.
     */
    public List<Integer> getAllElements2(BSTNode root1, BSTNode root2) {
        List<Integer> res = new ArrayList<>();

        BSTNode[] s1 = new BSTNode[2500], s2 = new BSTNode[2500];
        int t1 = -1, t2 = -1;
        BSTNode iter1 = root1, iter2 = root2;

        while (t1 != -1 || t2 != -1 || iter1 != null || iter2 != null) {

            while (iter1 != null) {
                s1[++t1] = iter1;
                iter1 = iter1.left;
            }

            while (iter2 != null) {
                s2[++t2] = iter2;
                iter2 = iter2.left;
            }

            if (t2 == -1 || t1 != -1 && (s1[t1].val <= s2[t2].val)) {
                BSTNode n1 = s1[t1--];
                res.add(n1.val);
                iter1 = n1.right;
            } else {
                BSTNode n2 = s2[t2--];
                res.add(n2.val);
                iter2 = n2.right;
            }
        }

        return res;
    }

    public List<Integer> getAllElements(BSTNode root1, BSTNode root2) {
        List<Integer> lst1 = new ArrayList<>(), lst2 = new ArrayList<>();

        // Inorder traversal will give sorted list
        inOrder(root1, lst1);
        inOrder(root2, lst2);

        // Merge two sorted list like we do in merge sort
        return merge(lst1, lst2);
    }

    private void inOrder(BSTNode node, List<Integer> lst) {
        if (node == null)
            return;

        inOrder(node.left, lst);
        lst.add(node.val);
        inOrder(node.right, lst);
    }

    private List<Integer> merge(List<Integer> l1, List<Integer> l2) {
        if (l1.size() > l2.size())
            return merge(l2, l1);

        List<Integer> res = new ArrayList<>();
        int i = 0, j = 0;
        while (i < l1.size() && j < l2.size()) {
            int f = l1.get(i), s = l2.get(j);
            if (f < s) {
                res.add(f);
                i++;
            } else {
                res.add(s);
                j++;
            }
        }

        while (i < l1.size()) res.add(l1.get(i++));
        while (j < l2.size()) res.add(l2.get(j++));

        return res;
    }

    public static void main(String[] args) {
        _1305_AllElements ae = new _1305_AllElements();
        BSTNode root1 = new BSTNode(0);
        root1.left = new BSTNode(-10);
        root1.right = new BSTNode(10);

        BSTNode root2 = new BSTNode(5);
        root2.left = new BSTNode(1);
        root2.right = new BSTNode(7);
        root2.left.left = new BSTNode(0);
        root2.left.right = new BSTNode(2);

        // root1 = [], root2 = [5,1,7,0,2]
        List<Integer> res = ae.getAllElements(null, root2);
        System.out.println(res);

        // root1 = [0,-10,10], root2 = []
        res = ae.getAllElements(root1, null);
        System.out.println(res);

        // root1 = [0,-10,10], root2 = [5,1,7,0,2]
        res = ae.getAllElements2(root1, root2);
        System.out.println(res);
    }
}