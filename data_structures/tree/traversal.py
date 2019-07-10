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
