package tree;

import java.util.*;

public class _429_NaryLevelOrder {
    public List<List<Integer>> levelOrder(NaryNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;

        Deque<NaryNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                NaryNode current = queue.poll();
                currentLevel.add(current.val);

                for (NaryNode child : current.children) {
                    if (child != null)
                        queue.offer(child);
                }
            }

            result.add(currentLevel);
        }

        return result;
    }

    public static void main(String[] args) {
        _429_NaryLevelOrder nlo = new _429_NaryLevelOrder();

        NaryNode root = new NaryNode(1);
        for (int i = 2; i < 5; i++)
            root.children.add(new NaryNode(i));

        NaryNode node13 = root.children.get(1);
        node13.children.add(new NaryNode(5));
        node13.children.add(new NaryNode(6));

        List<List<Integer>> result = nlo.levelOrder(root);
        System.out.println(result);
    }
}
