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
            self.left.val if self.left else None,
            self.right.val if self.right else None,
            self.height,
            self.occurrence
        )
        return s



class AVLTree(object):

    def __init__(self):
        self.root = None
        self._size = 0

    @property
    def size(self):
        return self._size

    def balance(self, node: AvlNode):
        if not node:
            return node


        node.height = max( self._height(node.left), self._height(node.right) ) + 1
        return node

    def rotate_right(self, k2: AvlNode):
        k1 = k2.left
        k2.left = k1.right
        k1.right = k2
        k2.height = max( self._height(k2.left), self._height(k2.right) ) + 1
        k1.height = max( self._height(k1.left), k2.height ) + 1
        return k1

    def rotate_left(self, k2: AvlNode):
        k1 = k2.right
        k2.right = k1.left
        k1.left = k2
        k2.height = max( self._height(k2.left), self._height(k2.right) ) + 1
        k1.height = max( self._height(k1.right), k2.height ) + 1
        return k1
        
    def insert(self, x, node: AvlNode):
        if not node:
            return AvlNode(x)

        if x.val < node.val:
            node.left = self.insert(x, node.left)
        elif x.val > node.val:
            node.right = self.insert(x, node.right)
        else:
            node.occurrence += 1

        return self.balance(node)

    def _height(self, node):
        return node.height if node else -1

    def is_empty(self):
        return self.size == 0

    def in_order(self, node):
        if node:
            print(node)
            self.in_order(node.left)
            self.in_order(node.right)

    def __len__(self):
        return self.size


if __name__ == '__main__':
    tree = AVLTree()
    t = AvlNode(3)
    t.left = AvlNode(2)
    t.left.left = AvlNode(1)
    after = tree.rotate_right(t)
    tree.in_order(after)
