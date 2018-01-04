class BinarySearchTree:
    """Binary Search Tree Implementation.

       The disadvantage in a Binary search tree is that search, delete or insert
       operation may lead to O(n) time complexity especially when the operations
       include the nodes to be inserted at the leaf of the tree.

       This implementation contains AVL Balance tree as well. The big performance
       improvement with AVL Tree is that it guarantees the insert, delete and
       search time to be at log(N)

       How can AVL Tree achieve this. Using Balance Factor

       Balance Factor = height(left_subtree) - height(right_subtree)

       Balance Factor should be always -1,0,1
    """

    def __init__(self):
        """Initializes Binary Search Tree."""
        self.root = None
        self.current_size = 0

    def put(self, key, value):
        """Inserts key, value pair to the binary search tree."""
        if self.root:
            self._put(key, value, self.root)
        else:
            self.root = TreeNode(key, value)

        self.current_size += 1

    def _put(self, key, value, current_node):
        """Private method for put method which recursively goes through the tree
           to insert the key, value pair.
        """
        if key == current_node.key:
            current_node.pay_load = value
        elif key < current_node.key:
            if current_node.has_left_child():
                self._put(key, value, current_node.left_child)
            else:
                current_node.left_child = TreeNode(key, value,
                                                   parent=current_node)
        else:
            if current_node.has_right_child():
                self._put(key, value, current_node.right_child)
            else:
                current_node.right_child = TreeNode(key, value,
                                                    parent=current_node)

    def get(self, key):
        """Gets the value associated with the key."""
        if self.root:
            res = self._get(key, self.root)
            if res:
                return res.pay_load
            else:
                return None
        else:
            return None

    def _get(self, key, current_node):
        """Private method for get which recursively searches the tree for the
           matching key and returns the value.
        """
        if not current_node:
            return None
        elif key == current_node.key:
            return current_node
        elif key < current_node.key:
            return self._get(key, current_node.left_child)
        else:
            return self._get(key, current_node.right_child)

    def delete(self, key):
        """Deletes the key, value pair in the binary search tree.

        :param key: Key
        :return: None
        """
        if self.current_size > 1:
            # Search the node
            to_remove = self._get(key, self.root)
            if to_remove:
                self.remove(to_remove)
                self.current_size -= 1
            else:
                raise KeyError
        elif self.current_size == 1 and self.root.key == key:
            self.root = None
            self.current_size -= 1
        else:
            raise KeyError

    def remove(self, current_node):
        if current_node.is_leaf():
            if current_node.parent.left_child == current_node:
                current_node.parent.left_child = None
            elif current_node.parent.right_child == current_node:
                current_node.parent.right_child = None
        elif current_node.has_both_children():
            succ = current_node.find_successor()
            succ.spliceout()
            current_node.key = succ.key
            current_node.pay_load = succ.pay_load
        elif current_node.has_any_children():
            if current_node.has_left_child():
                if current_node.is_left_child():
                    current_node.parent.left_child = current_node.left_child
                    current_node.left_child.parent = current_node.parent
                elif current_node.is_right_child():
                    current_node.parent.right_child = current_node.right_child
                    current_node.right_child.parent = current_node.parent
                else:
                    current_node.replace_node_data(current_node.left_child.key,
                                                   current_node.left_child.pay_load,
                                                   current_node.left_child.left_child,
                                                   current_node.left_child.right_child
                                                   )
            else:
                if current_node.is_left_child():
                    current_node.parent.left_child = current_node.right_child
                    current_node.right_child.parent = current_node.parent
                elif current_node.is_right_child():
                    current_node.parent.right_child = current_node.right_child
                    current_node.right_child.parent = current_node.parent
                else:
                    current_node.replace_node_data(current_node.right_child.key,
                                                   current_node.right_child.pay_load,
                                                   current_node.right_child.left_child,
                                                   current_node.right_child.right_child
                                                   )

        else:
            raise KeyError


    def __len__(self):
        """Calculates the length of the binary search tree. Implements Pythonic
           len(binary search tree object) functionality.
        """
        return self.current_size

    def __setitem__(self, key, value):
        """Implements Pythonic bstObject['key'] = "value" functionality. Uses
           the put method for the functionality.
        """
        return self.put(key, value)

    def __getitem__(self, item):
        """Implements Pythonic way to access value of the key. Uses the get
           method for the functionality.
        """
        return self.get(item)

    def __contains__(self, item):
        """Implements the 'in' functionality. "key" in bstObject."""
        if self._get(item, self.root):
            return True
        else:
            return False

    def __del__(self, key):
        """Implements del["key"] pythonic functionality."""
        self.delete(key)

    def __iter__(self):
        """Retunrs the iterator fot the root object."""
        return self.root.__iter__()

    def length(self):
        """Helper method to get the length of the binary search tree."""
        return self.current_size


class TreeNode:
    """Node object for the binary search tree."""

    def __init__(self, key, value, parent=None, left_child=None,
                 right_child=None):
        """Initializes a node for the binary search tree."""
        self.key = key
        self.pay_load = value
        self.parent = parent
        self.left_child = left_child
        self.right_child = right_child

    def has_left_child(self):
        """:return True: If the node has a left child
                  False: Otherwise
        """
        return self.left_child

    def has_right_child(self):
        """:return True: If the node has a right child
                  False: Otherwise
        """
        return self.right_child

    def has_any_children(self):
        """:return True: If the node has a left child or a right child
                  False: Otherwise
        """
        return self.left_child or self.right_child

    def has_both_children(self):
        """:return True: If the node has both left child and right child
                  False: Otherwise
        """
        return self.left_child and self.right_child

    def is_left_child(self):
        """:return True: If the node is a left child of the parent
                  False: Otherwise
        """
        return self.left_child and self.parent.left_child == self

    def is_right_child(self):
        """:return True: If the node is a right child of the parent
                  False: Otherwise
        """
        return self.right_child and self.parent.right_child == self

    def is_root(self):
        """:return True: If the current node is a parent
                  False: Otherwise
        """
        return not self.parent

    def is_leaf(self):
        """:return True: If the current node is a leaf node which does not have
                         any children
                  False: Otherwise
        """
        return not (self.left_child and self.right_child)

    def replace_node_data(self, k, v, lc, rc):
        """Replaces an already existing node with another node

        :param k: Key
        :param v: Value
        :param lc: Left child
        :param rc: Right_child
        :return: None
        """
        self.key = k
        self.pay_load = v
        self.left_child = lc
        self.right_child = rc
        if self.left_child:
            self.left_child.parent = self
        if self.right_child:
            self.right_child.parent = self

    def find_successor(self):
        """Selects the successor as the minimum element from the right sub tree."""
        return self.right_child.find_min()

    def find_min(self):
        """Finds the minimum value in the left subtree of the right child node"""
        min_node = self
        while self.has_left_child():
            min_node = self.left_child

        return min_node

    def splice_out(self):
        """Purges the node of the successor which was replaced."""
        if self.is_leaf():
            if self.is_left_child():
                self.left_child.parent = None
            else:
                self.right_child.parent = None
        elif self.has_any_children():
            if self.has_left_child():
                if self.is_left_child():
                    self.parent.left_child = self.left_child
                else:
                    self.parent.right_child = self.left_child
                self.left_child.parent = self.parent
            else:
                if self.is_left_child():
                    self.parent.left_child = self.right_child
                else:
                    self.parent.right_child = self.right_child
                self.right_child.parent = self.parent
