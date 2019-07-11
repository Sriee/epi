from lib import binary_heap as bh
import unittest


class TestBinaryHeapMax(unittest.TestCase):

    def setUp(self):
        self.max_heap = bh.BinaryHeapMax()

    def test_insert_max(self):
        self.max_heap.insert(5)
        self.max_heap.insert(9)
        self.max_heap.insert(12)

        self.assertEqual(self.max_heap.find_max(), 12)

    def test_helper(self):
        self.max_heap.insert(5)
        self.max_heap.insert(9)
        self.max_heap.insert(12)

        self.assertEqual(self.max_heap.find_max(), 12)
        self.assertEqual(self.max_heap.size(), 3)
        self.assertFalse(self.max_heap.is_empty())


if __name__ == "__main__":
    unittest.main()
