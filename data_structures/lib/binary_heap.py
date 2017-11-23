class BinaryHeapMin:
    """Binary Heap(Min) implementation. Heap implementation is for the purpose
       of priority queue. It is based on complete binary tree and heap order
       property.
    """

    def __init__(self):
        """Initialize min binary heap."""
        self.heap_list = [0]
        self.currentSize = 0

    def insert(self, value):
        """Insert a new value to the binary heap."""
        self.heap_list.append(value)
        self.currentSize += 1
        self.percolate_up(self.currentSize)

    def percolate_up(self, cur_pos):
        """Helps to maintain the heap order property. The new item is appened
           to the end of the list. It helps to maintain the complete tree order
           property but violates the heap order property while inserting.

           The element which is inserted is compared to the parent. If the parent
           value is greater than the node inserted, we swap the values. This
           process gets computed until we reach the root.
        """
        while cur_pos // 2 > 0:
            if self.heap_list[cur_pos] < self.heap_list[cur_pos // 2]:
                self.heap_list[cur_pos], self.heap_list[cur_pos // 2] = \
                    self.heap_list[cur_pos // 2], self.heap_list[cur_pos]
            cur_pos //= 2

    def find_min(self):
        """Returns the minimum element in the heap list."""
        return self.heap_list[1]

    def del_min(self):
        """Deletes the root value and returns the result. The problem is that
           it destroys the heap order property. To make the tree back to normal
           form we replace the root with the last element in the list. We then
           percolate down the tree replacing the root with lowest value of the
           children.
        """
        min_value = self.heap_list[1]
        self.heap_list[1] = self.heap_list.pop()
        self.currentSize -= 1
        self.percolate_down(1)
        return min_value

    def min_child(self, index):
        """Given the position this routine checks to see if there is a value at
           the end and returns left or right child position.
        """
        if index * 2 + 1 > self.currentSize:
            return index * 2
        elif self.heap_list[index * 2] < self.heap_list[index * 2 + 1]:
            return index * 2
        else:
            return index * 2 + 1

    def percolate_down(self, cur_pos):
        """While percolate down we need to check both the childs of the root to
           find the bigger one then the now replaced root value.
        """
        while cur_pos * 2 <= self.currentSize:
            mc = self.min_child(cur_pos)
            if self.heap_list[cur_pos] > self.heap_list[mc]:
                self.heap_list[cur_pos], self.heap_list[mc] = \
                    self.heap_list[mc], self.heap_list[cur_pos]
            cur_pos = mc

    def is_empty(self):
        """Checks if the heap implementation is empty or not."""
        return self.currentSize == 0

    def size(self):
        """Returns the size of the heap."""
        return self.currentSize

    def build_min_heap(self, lst):
        """Build min heap from the list given as input.

           By Insert method : If we start inserting the values of the list one
           by one using insert method it becomes O(n log n) operations.

           By the below method, we start from the middle of the list and work
           our way up to the root. Since we are starting from the middle the
           rest of the tree values will be leaf nodes with no children
        """
        self.currentSize = len(lst)
        self.heap_list = [0] + lst[:]
        i = len(lst) // 2
        while i > 0:
            self.percolate_down(i)
            i -= 1

    def __str__(self):
        """Prints the max heap list."""
        return str(self.heap_list[1:])


class BinaryHeapMax:
    """Binary Heap(Max) implementation. Heap implementation is for the purpose
       of priority queue. It is based on complete binary tree and heap order
       property.
    """

    def __init__(self):
        """Initialize max binary heap."""
        self.max_heap_list = [0]
        self.current_size = 0

    def insert(self, value):
        """Insert a new value to the binary heap."""
        self.max_heap_list.append(value)
        self.current_size += 1
        self.percolate_up(self.current_size)

    def percolate_up(self, cur_pos):
        """Helps to maintain the heap order property. The new item is appened
           to the end of the list. It helps to maintain the complete tree order
           property but violates the heap order property while inserting.

           The element which is inserted is compared to the parent. If the parent
           value is lesser than the node inserted, we swap the values. This
           process gets computed until we reach the root.
        """
        while cur_pos // 2 > 0:
            if self.max_heap_list[cur_pos] > self.max_heap_list[cur_pos // 2]:
                self.max_heap_list[cur_pos], self.max_heap_list[cur_pos // 2] = \
                self.max_heap_list[cur_pos // 2], self.max_heap_list[cur_pos]
            cur_pos //= 2

    def del_max(self):
        """Deletes the root value and returns the result. The problem is that
           it destroys the heap order property. To make the tree back to normal
           form we replace the root with the last element in the list. We then
           percolate down the tree replacing the root with highest value of the
           children.
        """
        max_value = self.max_heap_list[1]
        self.max_heap_list[1] = self.max_heap_list.pop()
        self.current_size -= 1
        self.percolate_down(1)
        return max_value

    def percolate_down(self, cur_pos):
        """While percolate down we need to check both the childs of the root to
           find the bigger one then the now replaced root value.
        """
        while cur_pos < self.current_size:
            i = self.max_child(cur_pos)
            if self.max_heap_list[i] > self.max_heap_list[cur_pos]:
                self.max_heap_list[i], self.max_heap_list[cur_pos] = \
                    self.max_heap_list[cur_pos], self.max_heap_list[i]
            cur_pos = i

    def max_child(self, i):
        """Given the position this routine checks to see if there is a value at
           the end and returns left or right child position.
        """
        if 2 * i + 1 > self.current_size:
            return 2 * i
        else:
            if self.max_heap_list[2 * i] > self.max_heap_list[2 * i + 1]:
                return 2 * i
            else:
                return 2 * i + 1

    def build_max(self, lst):
        """Build min heap from the list given as input.

           By Insert method : If we start inserting the values of the list one
           by one using insert method it becomes O(n log n) operations.

           By the below method, we start from the middle of the list and work
           our way up to the root. Since we are starting from the middle the
           rest of the tree values will be leaf nodes with no children
        """
        self.current_size = len(lst)
        self.max_heap_list = [0] + lst
        index = len(lst) // 2
        while index > 0:
            self.percolate_down(index)
            index -= 1

    def size(self):
        """Returns the size of the heap."""
        return self.current_size

    def is_empty(self):
        """Checks if the heap implementation is empty or not."""
        return self.current_size == 0

    def find_max(self):
        """Returns the maximum element in the heap list."""
        return self.max_heap_list[1]

    def __str__(self):
        """Prints the max heap list."""
        return str(self.max_heap_list[1:])