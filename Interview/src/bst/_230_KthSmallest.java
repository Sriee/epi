package bst;

import java.util.*;

class _230_KthSmallest {
    Random rand = new Random();

    public int inorderApproach(BSTNode root, int k) {
        List<Integer> lst = new ArrayList<>();
        inOrder(root, lst, k);
        return lst.get(k - 1);
    }

    private void inOrder(BSTNode node, List<Integer> lst, int k) {
        if (node != null && lst.size() < k) {
            inOrder(node.left, lst, k);

            if (lst.size() < k) {
                lst.add(node.val);
                inOrder(node.right, lst, k);
            }
        }
    }

    public int stackApproach(BSTNode root, int k) {
        // Note the use of deque for stack operations
        Deque<BSTNode> stack = new ArrayDeque<>();
        BSTNode curr = root;

        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            BSTNode node = stack.pop();
            k--;
            if (k == 0)
                return node.val;

            curr = node.right;
        }

        return -1;
    }

    public int kthSmallest(BSTNode root, int k) {
        int res = -1;
        switch (rand.nextInt(2)) {
            case 0:
                System.out.println("Stack Approach");
                res = stackApproach(root, k);
                break;
            case 1:
                System.out.println(" Approach");
                res = inorderApproach(root, k);
                break;
        }

        return res;
    }

    public static void main(String[] args) {
        _230_KthSmallest ks = new _230_KthSmallest();

        // [3,1,4,null,2]
        BSTNode root = new BSTNode(3);
        root.left = new BSTNode(1);
        root.right = new BSTNode(4);
        root.left.right = new BSTNode(2);

        System.out.println(ks.kthSmallest(root, 3));
    }
}