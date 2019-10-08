class AvlNode(object):

    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
        self.height = 0
        self.occurrence = 0

    def __str__(self):
        s = 'AvlNode({}, left={}, right={}, height={}, occurrence={})'.format(
            self.val,
            self.left.val if self.val else None,
            self.right.val if self.val else None,
            self.height,
            self.occurrence
        )
        return s


class AvlTree(object):

    def __init__(self):
        self.root = None
        self._size = 0

    @property
    def size(self):
        return self._size

    def balance(self, node: AvlNode):

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
                node = self.rotate_left(node)

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
                node = self.rotate_right(node)

            # Case 4: Right - Right
            node = self.rotate_left(node)
        return node

    def balance_factor(self, node: AvlNode):
        return self._height(node.left) - self._height(node.right)

    def contains(self, node, val):
        if not node:
            return False

        if val < node.val:
            return self.contains(node.left, val)
        elif val > node.val:
            return self.contains(node.right, val)
        else:
            return True

    def height(self):
        return self._height(self.root)

    def _height(self, node: AvlNode):
        return node.height if node else -1

    def is_empty(self):
        return self._size == 0

    def put(self, x):
        self.root = self._put(self.root, x)
        self._size += 1

    def _put(self, node: AvlNode, x):
        if not node:
            return AvlNode(x)

        if x < node.val:
            return self._put(node.left, x)
        elif x > node.val:
            return self._put(node.right, x)
        else:   # Found duplicate node
            node.occurrence += 1

        # Update height
        node.height = max(self._height(node.left), self._height(node.right)) + 1
        self.balance(node)  # Balance the node

    def rotate_left(self, k2: AvlNode):
        """Rotate left

            1               2
             \             / \
              2     -->   1   3
               \
                3

        :param k2:
        :return:
        """
        k1: AvlNode = k2.right

        k2.right = k1.left
        k1.left = k2

        # Update heights
        k2.height = max(self._height(k2.left), self._height(k2.right)) + 1
        k1.height = max(k2.height, self._height(k1.right)) + 1
        return k1

    def rotate_right(self, k2: AvlNode):
        """Rotate right
                1             2
               /             / \
              2     -->     1   3
             /
            3

        :param k2:
        :return:
        """
        k1: AvlNode = k2.left

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


if __name__ == '__main__':
    a = AvlTree()
