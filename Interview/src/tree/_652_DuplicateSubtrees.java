package tree;

import java.util.*;

class _652_DuplicateSubtrees {

    /**
     * Design custom key technique.
     * <p>
     * In this problem we serialize sub tree's and store them in a map. When we see another entry in the map (aka
     * duplicate subtree) we add the node of the subtree to the result.
     * <p>
     * This question was asked in our AWS Interview 2018 - IAM Team.
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        serializeBT(root, map, res);

        return res;
    }

    private StringBuilder serializeBT(TreeNode curr, Map<String, Integer> map, List<TreeNode> res) {
        StringBuilder sb = new StringBuilder();
        if (curr == null) {
            sb.append("#").append(",");
        } else {
            sb.append(curr.val).append(",");
            sb.append(serializeBT(curr.left, map, res));
            sb.append(serializeBT(curr.right, map, res));
            String serialId = sb.toString();

            map.put(serialId, map.getOrDefault(serialId, 0) + 1);

            if (map.get(serialId) == 2)
                res.add(curr);
        }

        return sb;
    }
}