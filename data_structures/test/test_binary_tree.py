from lib import binary_tree
import unittest


class TestBinaryTree(unittest.TestCase):

    def setUp(self):
        self.t_alpha = binary_tree.BinaryTree('a')
        self.t_numeric = binary_tree.BinaryTree(45)
        self.t_float = binary_tree.BinaryTree(3.45)

    def test_initialization(self):
        self.assertIsInstance(self.t_alpha, binary_tree.BinaryTree)
        self.assertIsInstance(self.t_numeric, binary_tree.BinaryTree)
        self.assertIsInstance(self.t_float, binary_tree.BinaryTree)

    def test_set_get_value(self):
        self.t_alpha.set_value('b')
        self.t_float.set_value(65.678)
        self.t_numeric.set_value(4789)

        self.assertEqual('b', self.t_alpha.get_value())
        self.assertEqual(65.678, self.t_float.get_value())
        self.assertEqual(4789, self.t_numeric.get_value())

    def test_left_child(self):
        self.t_alpha.insert_left('c')
        self.t_float.insert_left(45.89)
        self.t_numeric.insert_left(53)

        self.assertEqual('c', self.t_alpha.get_left_child().get_value())
        self.assertEqual(45.89, self.t_float.get_left_child().get_value())
        self.assertEqual(53, self.t_numeric.get_left_child().get_value())

    def test_right_child(self):
        self.t_alpha.insert_right('d')
        self.t_float.insert_right(89754.89)
        self.t_numeric.insert_right(1952)

        self.assertEqual('d', self.t_alpha.get_right_child().get_value())
        self.assertEqual(89754.89, self.t_float.get_right_child().get_value())
        self.assertEqual(1952, self.t_numeric.get_right_child().get_value())


if __name__ == "__main__":
    unittest.main()
