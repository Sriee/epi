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

    def search_iterative(self, node, val):
        """Search in BST (iterative implementation)

        :param node: current node
        :param val: val to find
        :return: node if found else None
        """
        current = node
        while current:
            if val < current.val:
                current = current.left
            elif val > current.val:
                current = current.right
            else:
                return current

        return None

    def search_recursive(self, node, val):
        """Search in BST (recursive implementation)

        :param node: current node
        :param val: val to find
        :return: node if found else None
        """
        if not node:
            return None

        # Trick: By checking < & > we are avoiding multiple checks for equality
        # followed by < & > check. This way helps to increase the run time
        if val < node.val:
            return self.search_recursive(node.left, val)
        elif val > node.val:
            return self.search_recursive(node.right, val)
        else:
            return node

    def search(self, val):
        """Search in BST"""

        # Randomly select iterative/recursive implementation
        if randint(0, 1) == 0:
            return self.search_recursive(self.root, val)
        else:
            return self.search_iterative(self.root, val)

    def __contains__(self, item):
        """Search in BST

        :param item: val to search
        :return: True if the node is found False otherwise
        """
        node = self.search(item)
        return True if node else False

    def __len__(self):
        return self._size

    def is_empty(self):
        return self._size == 0
