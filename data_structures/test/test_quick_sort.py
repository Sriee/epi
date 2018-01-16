from sort import quick_sort
import unittest


class TestQuickSort(unittest.TestCase):

    def setUp(self):
        self.qs = quick_sort.QuickSort()

    def test_sort(self):
        self.assertEqual(self.qs.sort([54, 26, 93, 17, 77, 31, 44, 55, 20]),
                          [17, 20, 26, 31, 44, 54, 55, 77, 93])
        self.assertEqual(self.qs.sort([84, 51, 32, 35, 68, 95, 2]),
                         [2, 32, 35, 51, 68, 84, 95])
        self.assertRaises(TypeError,lambda: self.qs.sort())


if __name__ == "__main__":
    unittest.main()

