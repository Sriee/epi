def pre_order(tree):
    """Pre-order tree traversal implementation.

       Root-Left-Right
    """
    if tree:
        print(tree.get_value())
        pre_order(tree.get_left_child())
        pre_order(tree.get_right_child())


def post_order(tree):
    """Post-order tree traversal implementation.

       Left-Right-Root
    """
    if tree is not None:
        post_order(tree.get_left_child())
        post_order(tree.get_right_child())
        print(tree.get_value())


def in_order(tree):
    """In-order tree travel implementation.

       Left-Root-Right
    """
    if tree is not None:
        in_order(tree.get_left_child())
        print(tree.get_value())
        in_order(tree.get_right_child())


def is_valid(node, minimum, maximum):
    """Helper method to check is BST is valid or not

    :param node: subtree
    :param minimum: Minimum value of the subtree
    :param maximum: Maximum value of the subtree
    :return: True if BST subtree is valid, False otherwise
    """
    if not node:
        return True

    if minimum is not None and node.val <= minimum:
        return False

    if maximum is not None and node.val >= maximum:
        return False

    return is_valid(node.left, minimum, node.val) and is_valid(node.right, node.val,
                                                               maximum)


def is_valid_BST(root):
    """Given the root node of a Binary Search Tree, find out whether it is valid not not

    :param root: BST root
    :return: True if BST is valid, False otherwise
    """
    return is_valid(root, None, None)