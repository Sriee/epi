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

    def put_iterative(self, node, val):
        """Insert into BST (iterative implementation)

        :param node: current node
        :param val: val to insert
        """
        current = node
        while current:
            if val < current.val:
                if current.left is None:
                    current = current.left
                else:
                    current.left = TreeNode(val)
                    current = None
            else:
                if current.right:
                    current = current.right
                else:
                    current.right = TreeNode(val)
                    current = None

    def put_recursive(self, node, val):
        """
        Insert into BST (recursive implementation)

        :param node: current node
        :param val: val to insert
        """
        if val < node.val:
            if node.left:
                self.put_recursive(node.left, val)
            else:
                node.left = TreeNode(val)
        else:
            if node.right:
                self.put_recursive(node.right, val)
            else:
                node.right = TreeNode(val)

    def put(self, val):
        """Insert into BST"""
        if self.root:
            # Choose recursive/iterative implementation randomly
            if randint(0, 1) == 0:
                self.put_recursive(self.root, val)
            else:
                self.put_iterative(self.root, val)
        else:
            self.root = TreeNode(val)
        self._size += 1

    def __len__(self):
        return self._size

    def is_empty(self):
        return self._size == 0
