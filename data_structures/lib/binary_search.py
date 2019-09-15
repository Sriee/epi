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

    def find_min(self, node):
        """Helper method to find the node with minimum value in a BST"""
        current = node
        while current.left:
            current = current.left
        return current

    def find_max(self, node):
        """Helper method to find the node with maximum value in a BST"""
        current = node
        while current.right:
            current = current.right
        return current

    def delete_node(self, node, val):
        """Helper method to delete the node

        :param node: Current node
        :param val: value to delete
        :return: node after deletion
        """
        if not node:
            return node

        if val < node.val:
            node.left = self.delete_node(node.left, val)
        elif val > node.val:
            node.right = self.delete_node(node.right, val)
        elif node.left and node.right:
            node.val = self.find_min( node.right ).val
            node.right = self.delete_node(node.right, node.val)
        else:
            node = node.left if node.left else node.right

        return node

    def delete(self, val):
        """Delete a node in a BST

        :param val: node to delete
        """
        if self._size > 1:
            self.delete_node(self.root, val)
        elif self._size == 1 and self.root.val == val:
            self._size -= 1
            self.root = None
        else:
            raise KeyError("Error, Key not in tree.")

    def insert_iterative(self, node, val):
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

    def insert_recursive(self, node, val):
        """
        Insert into BST (recursive implementation)

        :param node: current node
        :param val: val to insert
        """
        if val < node.val:
            if node.left:
                self.insert_recursive(node.left, val)
            else:
                node.left = TreeNode(val)
        else:
            if node.right:
                self.insert_recursive(node.right, val)
            else:
                node.right = TreeNode(val)

    def insert(self, val):
        """Insert into BST"""
        if self.root:
            # Choose recursive/iterative implementation randomly
            if randint(0, 1) == 0:
                self.insert_recursive(self.root, val)
            else:
                self.insert_iterative(self.root, val)
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
