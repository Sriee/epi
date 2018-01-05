class QuickSort:
    """Quick sort implementation."""

    def __init__(self, a_list=None):
        """Initializes the list for quick sorting

           :param a_list - The list to be sorted.
        """
        self.a_list = a_list

    def sort(self, a_list=None, first=None, last=None):
        """Quick sort works on divide and conquer strategy. The implementation
           starts with choosing the pivot value. The left & right markers start
           comparing the values of the list with the pivot element. At a point
           when two markers cross we will have the 'split point'. Split point
           is the median (aka wall) which has elements lesser than this to the
           left and higher than to the right.

           :param a_list- The list to be sorted. If the list is not passed in
                           as argument global list will be considered
           :param first - First element of the list
           :param last  - Last element of the list
           :returns     - Sorted List
        """
        a_list = self.a_list if a_list is None else a_list
        first = 0 if first is None else first
        last = len(a_list) - 1 if last is None else last

        if first < last:
            split_point = self.partition(a_list, first, last)

            self.sort(a_list, first, split_point - 1)
            self.sort(a_list, split_point + 1, last)

        return a_list

    def partition(self, a_list, first, last):
        """Partition method is to calculate the split point.

           :param a_list- The list to be sorted. If the list is not passed in
                           as argument global list will be considered
           :param first - First element of the list
           :param last  - Last element of the list
           :returns     - Split point
        """
        left_mark, right_mark = first + 1, last
        done = False

        while not done:

            while left_mark <= right_mark and a_list[left_mark] < a_list[first]:
                left_mark += 1

            while right_mark >= left_mark and a_list[right_mark] > a_list[first]:
                right_mark -= 1

            if left_mark > right_mark:
                done = True
            else:
                a_list[left_mark], a_list[right_mark] = a_list[right_mark], \
                                                        a_list[left_mark]

        a_list[first], a_list[right_mark] = a_list[right_mark], a_list[first]

        return right_mark

    def __str__(self):
        """Prints the contents of the list."""
        return str(self.a_list)
