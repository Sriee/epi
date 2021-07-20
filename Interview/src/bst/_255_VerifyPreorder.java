package bst;

class _255_VerifyPreorder {
    public boolean verifyPreorder(int[] preorder) {
        return verify(preorder, 0, preorder.length - 1, null, null);
    }

    private boolean verify(int[] preOrder, int start, int end, Integer low, Integer high) {
        if (start >= end)
            return true;

        int root = preOrder[start], mid = start + 1;
        for (; mid <= end && preOrder[mid] < root; mid++) {
            if (low != null && preOrder[mid] <= low)
                return false;

            if (high != null && preOrder[mid] >= high)
                return false;

        }

        return verify(preOrder, start + 1, mid - 1, low, root) && verify(preOrder, mid, end, root, high);
    }

    public boolean stackApproach(int[] preorder) {
        int top = -1, low = Integer.MIN_VALUE;

        for (int i : preorder) {
            if (i < low) return false;

            while (top >= 0 && i > preorder[top])
                low = preorder[top--];

            preorder[++top] = i;
        }

        return true;
    }

    public static void main(String[] args) {
        _255_VerifyPreorder vp = new _255_VerifyPreorder();

        System.out.println(vp.verifyPreorder(new int[]{5, 2, 1, 3, 6}));
        System.out.println(vp.verifyPreorder(new int[]{5, 2, 6, 1, 3}));
        System.out.println(vp.stackApproach(new int[]{8, 5, 1, 7, 10, 12}));
    }
}