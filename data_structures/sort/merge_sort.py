class MergeSort:
    """Merge sort implementation."""

    def __init__(self, a_list):
        """Initializing the list"""
        self.a_list = a_list

    def sort(self, a_list=None):
        """Merge sort follows divide and conquer strategy. This is the best
           performing sorting algorithm used. The time complexity is O(n log n)
           unlike quick sort whose time complexity fluctuates because of pivot
           element selection.

           The drawback with the below implementation is that it uses additional
           storage while dividing and merging.

           :param a_list- The list to be sorted. If the list is not passed in
                          as argument global list will be considered
           :returns     - Sorted List
        """
        a_list = self.a_list if a_list is None else a_list

        if len(a_list) > 1:
            middle = len(a_list) // 2

            left = a_list[:middle]
            right = a_list[middle:]
            self.sort(left)
            self.sort(right)

        i, j, k = 0, 0, 0

        while i < len(left) and j < len(right):
            if left[i] < right[j]:
                a_list[k] = left[i]
                i += 1
            else:
                a_list[k] = right[j]
                j += 1
        k += 1

        while i < len(left):
            a_list[k] = left[i]
            i += 1
            k += 1

        while j < len(right):
            a_list[k] = right[j]
            j += 1
            k += 1

        return a_list


    def better_sort(self, first, last):

        if first < last:
            middle = len(self.a_list[first:last]) // 2
            self.merge(self.better_sort(first, middle),
                       self.better_sort(middle + 1), last)
        return self.a_list

    def merge(self):
        pass