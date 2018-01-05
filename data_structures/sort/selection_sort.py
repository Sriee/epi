class SelectionSort:
    """Selection Sort Implementation."""

    def __init__(self, a_list=None):
        """Initializes selection sort."""
        self.a_list = a_list

    def sort(self, a_list=None):
        """Selection sort has O(n^2) time complexity but is slightly faster
           than bubble sort because of lesser number of swapping. Selection
           sort first scans the list for largest number and swaps the nth
           element with the largest number.
        """
        self.a_list = self.a_list if a_list is None else a_list
        length = len(a_list) - 1

        for j in range(length, 0, -1):
            cur_largest_pos = 0
            for i in range(1, j + 1):
                if a_list[i] > a_list[cur_largest_pos]:
                    cur_largest_pos = i

            a_list[cur_largest_pos], a_list[j] = a_list[j], a_list[cur_largest_pos]

        return a_list
