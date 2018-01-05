class InsertionSort:
    """Implements Insertion Sort."""

    def __init__(self, a_list=None):
        """Initializes Insertion sort."""
        self.a_list = a_list

    @staticmethod
    def sort(a_list):
        """Insertion sort time complexity is same as Bubble sort O(n^2) but due
           to shifting instead of exchanging. The processing time taken for
           shifting 1/3 rd of the processing time taken for swapping. Hence in
           benchmark studies insertion sort shows significant performance when
           compared to bubble sort.
        """
        length = len(a_list)
        for j in range(1, length):
            cur_item, i = a_list[j], j
            while i > 0 and a_list[i - 1] > cur_item:
                a_list[i] = a_list[i - 1]
                i -= 1
            a_list[i] = cur_item

        return a_list
