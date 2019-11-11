from collections import deque


class AvlNode(object):

    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
        # Maintain height information at each node to calculate the balance factor
        self.height = 0
        # Variable to handle duplicates in the AVL Tree
        self.occurrence = 1

    def __str__(self):
        s = 'AvlNode({}, left={}, right={}, height={}, occurrence={})'.format(
            self.val,
            self.left.val if self.left else None,
            self.right.val if self.right else None,
            self.height,
            self.occurrence
        )
        return s


class AvlTree(object):
    """AVL Tree Implementation

    Reference: algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/AVLTreeST.java.html
    """
    def __init__(self):
        self.root = None
        self._size = 0

    @property
    def size(self):
        """Number of elements in the tree"""
        return self._size

    def balance(self, node: AvlNode):
        """Balance the subtree. We need to handle 4 cases for balancing the subtree

        Case 1: Left - Left [Single rotation] Hint: the opposite side
        Case 2: Left - Right [Double rotation]
        Case 3: Right - Left [Double rotation]
        Case 4: Right - Right [Single rotation]

        :param node: the subtree
        :return: balanced subtree
        """
        if self.balance_factor(node) > 1:
            # Handle left heavy tree

            if self.balance_factor(node.left) < 0:
                """Case 2: Left - Right
                    
                    1           1
                   /           / 
                  2    -->    3  
                   \         /
                    3       2
                """
                node.left = self.rotate_left(node.left)

            # Case 1: Left - Left
            node = self.rotate_right(node)
        elif self.balance_factor(node) < -1:
            # Handle right heavy tree
            if self.balance_factor(node.right) > 0:
                """Case 3: Right - Left

                    1         1
                     \         \
                      2  -->    3
                     /           \
                    3             2
                """
                node.right = self.rotate_right(node.right)

            # Case 4: Right - Right
            node = self.rotate_left(node)
        return node

    def balance_factor(self, node: AvlNode):
        """Find the balance factor of the subtree. Balance factor is defined as the
        difference between height of left & right tree

        :param node: the subtree
        :return: balance factor
        """
        return self._height(node.left) - self._height(node.right)

    def contains(self, node, val):
        """Find whether the AVL tree has the node with the given value.

        :param node: the subtree
        :param val: value to find
        :return: True if found False otherwise
        """
        if not node:
            return False

        if val < node.val:
            return self.contains(node.left, val)
        elif val > node.val:
            return self.contains(node.right, val)
        else:
            return True

    def delete(self, x):
        """Delete a node with the given value. If a node is not found do nothing.

        :param x: value
        :return: None
        """
        self.root = self._delete(self.root, x)
        self._size -= 1

    def _delete(self, node: AvlNode, x):
        """Helper method to delete a node in the subtree

        :param node: the subtree
        :param x: value whose node has to be removed
        :return: balanced subtree
        """
        if not node:
            return node

        if x < node.val:
            node.left = self._delete(node.left, x)
        elif x > node.val:
            node.right = self._delete(node.right, x)
        else:
            node.occurrence -= 1
            if node.occurrence != 0:
                return node

            if node.left and node.right:
                y = node
                node = self.find_min(y.right)
                node.right = self._delete(node.right, node.val)
                node.left = y.left
            else:
                return node.left if node.left else node.right
        node.height = max(self._height(node.left), self._height(node.right)) + 1
        return self.balance(node)

    def find_min(self, node):
        """Find the minimum node in the subtree. Minimum node would be the left most
        node in the subtree

        :param node: the subtree
        :return: minimum node in the subtree
        """
        x = node
        while x.left:
            x = x.left
        return x

    def height(self):
        """Calculate the height of the root"""
        return self._height(self.root)

    def _height(self, node: AvlNode):
        """Height of the subtree

        :param node: the subtree
        :return: subtree height or -1
        """
        return node.height if node else -1

    def is_empty(self):
        """Helper method to check if the tree is empty"""
        return self._size == 0

    def put(self, x):
        """Insert a value in AVL Tree

        :param x: value
        :return: None
        """
        self.root = self._put(self.root, x)
        self._size += 1

    def _put(self, node: AvlNode, x):
        """Helper method to insert a value to the subtree. If the value is already is
        present in the AVL Tree, occurrence's count will be updated.

        :param node: the subtree
        :param x: value to insert
        :return:
        """
        if not node:
            return AvlNode(x)

        if x < node.val:
            node.left = self._put(node.left, x)
        elif x > node.val:
            node.right = self._put(node.right, x)
        else:   # Found duplicate node
            node.occurrence += 1

        # Update height
        node.height = max(self._height(node.left), self._height(node.right)) + 1
        return self.balance(node)  # Balance the node

    def rotate_left(self, k2: AvlNode):
        """Rotates the given subtree to the left

            1               2
             \             / \
              2     -->   1   3
               \
                3

        :param k2: the subtree
        :return: root of the rotated subtree
        """
        k1 = k2.right

        k2.right = k1.left
        k1.left = k2

        # Update heights
        k2.height = max(self._height(k2.left), self._height(k2.right)) + 1
        k1.height = max(k2.height, self._height(k1.right)) + 1
        return k1

    def rotate_right(self, k2: AvlNode):
        """Rotate the given subtree to the right
                1             2
               /             / \
              2     -->     1   3
             /
            3

        :param k2: the subtree
        :return: root of the rotated subtree
        """
        k1 = k2.left

        # Since we are assigning k2's left to k1 it is guaranteed that k2's left
        # is empty. Also k1's right is guaranteed to be < k2
        k2.left = k1.right
        k1.right = k2

        # Update height's of k1 & k2
        k2.height = max(self._height(k2.left), self._height(k2.right)) + 1
        k1.height = max(self._height(k1.left), k2.height) + 1
        return k1

    def __contains__(self, val):
        return self.contains(self.root, val)

    def __len__(self):
        return self._size


def print_avl(root: AvlNode):
    """Level order traversal"""
    res, queue = [], deque()
    queue.append(root)

    while queue:
        current = []
        size = len(queue)

        for _ in range(size):
            node = queue.popleft()

            if node:
                if node is None:
                    print('Why did I get here')
                else:
                    current.append(str(node))
                queue.append(node.left)
                queue.append(node.right)
            else:
                current.append('None')

        res.append(current)

    for i in res:
        print(i)


if __name__ == '__main__':
    a = AvlTree()
    for i in range(5):
        a.put(i)
    a.put(3)
    print_avl(a.root)
    print('*' * 20)

    a.delete(3)
    print_avl(a.root)
    print('*' * 20)

    a.delete(3)
    print_avl(a.root)
    print('*' * 20)
