package tree;

import java.util.*;

public class _105_ConstructPreOrder {
    Map<Integer, Integer> map;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (inorder == null || inorder.length == 0)
            return null;

        map = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return build(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int[] inorder, int pStart, int pEnd, int iStart, int iEnd) {
        if (pStart > pEnd || iStart > iEnd)
            return null;

        int rootIdx = map.get(preorder[pStart]);
        int ls = rootIdx - iStart;

        return new TreeNode(preorder[pStart],
                build(preorder, inorder, pStart + 1, pStart + ls, iStart, rootIdx - 1),
                build(preorder, inorder, pStart + ls + 1, pEnd, rootIdx + 1, iEnd)
        );
    }

    private void print(TreeNode root, _102_LevelOrder helper) {
        List<List<Integer>> tree = helper.levelOrder(root);

        for (List<Integer> row : tree)
            System.out.println(row.toString());
    }

    public static void main(String[] args) {
        _105_ConstructPreOrder construct = new _105_ConstructPreOrder();
        _102_LevelOrder helper = new _102_LevelOrder();

        int[] preOrder = {3, 9, 20, 15, 7};
        int[] inOrder = {9, 3, 15, 20, 7};

        TreeNode root = construct.buildTree(preOrder, inOrder);
        construct.print(root, helper);
    }
}
