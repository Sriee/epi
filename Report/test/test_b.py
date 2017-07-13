import unittest

from loader import DatabaseLoader


class TestB(unittest.TestCase):

    def test_one(self):
        self.assertEqual(1, 1)

    def test_two(self):
        self.assertEqual(2, 2)

    def test_exception(self):
        # with self.assertRaises(FileNotFoundError):
        #     DatabaseLoader.mike()
        self.assertRaises(IOError, lambda : DatabaseLoader.mike())

if __name__ == '__main__':
    unittest.main()
