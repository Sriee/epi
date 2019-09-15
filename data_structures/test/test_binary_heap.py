from lib import binary_heap as bh
import unittest

class TestBinaryHeap():

    def test_insert(self):
        mxhp = bh.BinaryHeapMax()
        mxhp.insert(5)
        mxhp.insert(9)
        mxhp.insert(12)
        print(mxhp)

if __name__ == "__main__":
    unittest.main()