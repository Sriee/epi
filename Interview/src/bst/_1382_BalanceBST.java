package bst;

class _1382_BalanceBST {

    /**
     * Naive Approach
     * 1. Do an inorder walk and store the sorted values in a list
     * 2. Construct BST from sorted array as in _108_ArrToBST.java
     * <p>
     * A hack to improve run time is to use an array instead of List data type.
     * <p>
     * DSW Algorithm
     * <p>
     * See See <a href="https://csactor.blogspot.com/2018/08/dsw-day-stout-warren-algorithm-dsw.html">DSW Algorithm</a>
     */
    public BSTNode balanceBST(BSTNode root) {
        BSTNode grand = new BSTNode(-1);
        grand.right = root;

        // Step 1: Make vine - Right shift till we create a vine
        int n = makeVine(grand);
        int h = (int) (Math.log(n + 1) / Math.log(2));
        int m = (int) (Math.pow(2, h) - 1);

        // Step 2: Construct a balanced BST - Left shift
        construct(grand, n - m);
        for (m = m / 2; m > 0; m /= 2) {
            construct(grand, m);
        }

        return grand.right;
    }

    private int makeVine(BSTNode grand) {
        int count = 0;
        BSTNode curr = grand.right;

        while (curr != null) {
            if (curr.left != null) {
                BSTNode temp = curr;
                curr = curr.left;
                temp.left = curr.right;
                curr.right = temp;
                grand.right = curr;
            } else {
                count++;
                grand = curr;
                curr = curr.right;
            }
        }

        return count;
    }

    private void construct(BSTNode grand, int m) {
        BSTNode curr = grand.right, temp;

        while (m-- > 0) {
            temp = curr;
            curr = curr.right;
            grand.right = curr;
            temp.right = curr.left;
            curr.left = temp;
            grand = curr;
            curr = curr.right;
        }
    }

    public static void main(String[] args) {
        _1382_BalanceBST bb = new _1382_BalanceBST();
        BSTNode root = new BSTNode(1);
        root.right = new BSTNode(2);
        root.right.right = new BSTNode(3);
        root.right.right.right = new BSTNode(4);

        BSTNode balancedRoot = bb.balanceBST(root);
        System.out.println(balancedRoot);
    }
}