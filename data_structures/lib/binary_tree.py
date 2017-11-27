class BinaryTree:
    """Binary Tree Implementation."""

    def __init__(self, root_obj=None):
        """Initializes the binary tree with a value.

        :param root_obj : Node value of the new node created
        """
        self.key = root_obj
        self.leftChild = None
        self.rightChild = None

    def insert_left(self, new_value):
        """Inserts a value to the binary tree's left child.

           We will first check whether the left child pointer is None. If it is
           None then there is no left child for the node and we insert a new
           left child.

           If there is a left child a new node is inserted as the node's left
           child and already existing left child becomes the left child of the
           node that we created.

           :param new_value : Value of the node to be inserted as the left child
        """
        if self.leftChild is None:
            self.leftChild = BinaryTree(new_value)
        else:
            left = BinaryTree(new_value)
            left.leftChild = self.leftChild
            self.leftChild = left

    def insert_right(self, new_value):
        """Inserts a value to the binary tree's right child.

           We will first check whether the right child pointer is None. If it is
           None then there is no right child for the node and we insert a new
           right child.

           If there is a right child a new node is inserted as the node's right
           child and already existing right child becomes the right child of the
           node that we created.

           :param new_value : Value of the node to be inserted as the right child
        """
        if self.rightChild is None:
            self.rightChild = BinaryTree(new_value)
        else:
            right = BinaryTree(new_value)
            right.leftChild = self.rightChild
            self.leftChild = right

    def set_value(self, value):
        """Setter method to set the value of the node.

           :param value : Sets the key value to the node.
        """
        self.key = value

    def get_value(self):
        """Getter method to get the value of the node.

          :return : Gets the key value to the node.
        """
        return self.key

    def get_left_child(self):
        """Getter method to get the left child node object.

           :returns Left child node object
        """
        return self.leftChild

    def get_right_child(self):
        """Getter method to get the right child node object.

           :returns Right child node object
        """
        return self.rightChild

    def __str__(self):
        """Prints the key value."""
        return "Key = " + str(self.key)
