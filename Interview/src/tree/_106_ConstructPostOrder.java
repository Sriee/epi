package tree;

import java.util.*;

public class _106_ConstructPostOrder {
    Map<Integer, Integer> map;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0)
            return null;

        map = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode build(int[] inorder, int iStart, int iEnd, int[] postorder, int pStart, int pEnd) {
        if (iStart > iEnd || pStart > pEnd)
            return null;

        int root = map.get(postorder[pEnd]);
        int rs = iEnd - root;

        return new TreeNode(inorder[root],
                build(inorder, iStart, root - 1, postorder, pStart, pEnd - rs - 1),
                build(inorder, root + 1, iEnd, postorder, pEnd - rs, pEnd - 1)
        );
    }

    private void print(TreeNode root, _102_LevelOrder helper) {
        List<List<Integer>> tree = helper.levelOrder(root);

        for (List<Integer> row : tree)
            System.out.println(row.toString());
    }

    public static void main(String[] args) {
        _106_ConstructPostOrder construct = new _106_ConstructPostOrder();
        _102_LevelOrder helper = new _102_LevelOrder();

        int[] inOrder = {9, 3, 15, 20, 7};
        int[] postOrder = {9, 15, 7, 20, 3};

        TreeNode root = construct.buildTree(inOrder, postOrder);
        construct.print(root, helper);
    }
}
