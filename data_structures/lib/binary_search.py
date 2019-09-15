from random import randint


class TreeNode(object):

    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None

    def __str__(self):
        s = 'TreeNode({}, left={}, right={})'.format(
            self.val,
            self.left.val if self.left else None,
            self.right.val if self.right else None
        )
        return s


class BinarySearchTree(object):

    def __init__(self):
        self.root = None
        self._size = 0

    @property
    def size(self):
        return self._size

    def __len__(self):
        return self._size

    def is_empty(self):
        return self._size == 0
