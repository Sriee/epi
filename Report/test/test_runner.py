import unittest

from test_a import TestA
from test_b import TestB

if __name__ == '__main__':
    runner = unittest.TextTestRunner()
    suite_a = unittest.TestLoader().loadTestsFromTestCase(TestA)
    suite_b = unittest.TestLoader().loadTestsFromTestCase(TestB)
    runner.run(suite_a)
    runner.run(suite_b)