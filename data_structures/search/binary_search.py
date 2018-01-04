class BinarySearch:
    """Binary Search Implementation."""

    def __init__(self, a_list, front=None, end=None):
        """Initialize variables required for Binary Search."""
        self.a_list = a_list
        self.first = 0 if front is None else int(front)
        self.last = len(a_list) - 1 if end is None else int(end)

    def search(self, to_search, first=None, last=None):
        """Binary Search is based on divide and conquer strategy.

           It works only when the list to be searched are sorted. The idea is to
           search only half of the list at once. If the number to be searched is
           less than the middle element, the right part of the middle element is
           left out and searched in the left part. This process occurs
           recursively. Time Complexity: O(n log n)

           :param to_search - The item to be searched
           :param first - Index of first element
           :param last - Index of last element

           Returns
           -------
           True - If the element is present in the list
           False - If the element is not present in the list
        """
        first = self.first if first is None else first
        last = self.last if last is None else last

        if first > last:
            return False
        else:
            middle = (first + last) // 2

            if to_search == self.a_list[middle]:
                return True
            elif to_search < self.a_list[middle]:
                return self.search(to_search, 0, middle)
            else:
                return self.search(to_search, middle + 1, len(self.a_list) - 1)

    def __str__(self):
        return "List = %s\nFirst = %d\nLast = %d\n" % (self.a_list, self.first,
                                                       self.last)
