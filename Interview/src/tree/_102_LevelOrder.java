package tree;
import java.util.*;

public class _102_LevelOrder {

    private void levelOrderRecursive(TreeNode node, int level, List<List<Integer>> result) {
        if(node == null)
            return;

        if(result.size() == level) result.add(new ArrayList<>());

        result.get(level).add(node.val);
        levelOrderRecursive(node.left, level + 1, result);
        levelOrderRecursive(node.right, level + 1, result);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        Random rand = new Random();
        List<List<Integer>> result = new ArrayList<>();

        if(rand.nextInt(2) == 0) {
            System.out.println("Level order traversal: Using Recursive Approach");
            levelOrderRecursive(root, 0, result);
        } else {
            System.out.println("Level order traversal: Using Iterative Approach");
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while(!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> innerList = new ArrayList<>();

                for(int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    innerList.add(node.val);
                    if(node.left != null) queue.offer(node.left);
                    if(node.right != null) queue.offer(node.right);
                }

                result.add(innerList);
            }
        }
        return result;
    }
}
