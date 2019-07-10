class TreeNode(object):

    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


def buildSubTree(post_order, post_start, post_end, in_start, in_end, m):
    if post_end <= post_start or in_end <= in_start:
        return None

    root_idx = m[post_order[post_end - 1]]
    left_tree_size = root_idx - in_start

    node = TreeNode(post_order[post_end - 1])
    node.left = buildSubTree(post_order, post_start, post_start + left_tree_size,
                             in_start, root_idx, m)
    node.right = buildSubTree(post_order, post_start + left_tree_size + 1, post_end,
                              root_idx + 1, in_end, m)
    return node


def buildTree(post_order, in_order):

    m = {}
    for v, k in enumerate(in_order):
        m[k] = v

    return buildSubTree(post_order, 0, len(post_order), 0, len(in_order), m)


def in_order(root):
    if root is None:
        return

    print(root.val)
    in_order(root.left)
    in_order(root.right)


if __name__ == '__main__':
    root = buildTree([9, 15, 7, 20, 3], [9, 3, 15, 20, 7])

    print(root)
