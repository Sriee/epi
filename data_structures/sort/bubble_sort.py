class BubbleSort:
    def __init__(self, a_list=None):
        """Initialize Bubble Sort"""
        self.aList = a_list

    def sort(self, a_list=None):
        """Implementation of bubble sort. Bubble sort is the most inefficient
           way of sorting. It starts with comparing numbers, swaps when the
           comparison condition fails. At the end of first pass last element
           will hold the largest value. Next subsequent passes take n-1
           iteration. Time Complexity is O(n^2)
        """
        a_list = self.aList if a_list is None else a_list
        length = len(a_list)

        for j in range(length - 1, 0, -1):
            for i in range(j):
                if a_list[i] > a_list[i + 1]:
                    a_list[i], a_list[i + 1] = a_list[i + 1], a_list[i]

        return a_list

    def short_sort(self, a_list=None):
        """Implementation of 'short bubble sort'. Difference between bubble
           sort and short bubble sort is that bubble sort continues it's n-1
           comparison even though the list is sorted. Short bubble sort stops
           when the list becomes sorted in midst of sorting.
        """
        a_list = self.aList if a_list is None else a_list
        j, swap = len(a_list) - 1, True

        while j > 0 and swap:
            swap = False
            for i in range(j):
                if a_list[i] > a_list[i + 1]:
                    swap = True
                    a_list[i], a_list[i + 1] = a_list[i + 1], a_list[i]
            j -= 1
        return a_list
