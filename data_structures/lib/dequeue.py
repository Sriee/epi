class Deque:
    """Deque Data structure implementation using Python lists."""

    def __init__(self):
        """Initializes Deque."""
        self.deque = []

    def add_front(self, item):
        """Adds the element in the front end of the queue.

           @param item : Item to be inserted in the front end of the queue.
        """
        self.deque.insert(0, item)

    def add_rear(self, item):
        """Adds the element in the rear end of the queue.

           @param item : Item to be inserted in the rear end of the queue.
        """
        self.deque.append(item)

    def remove_front(self):
        """Removes the element in the front end of the queue."""
        return self.deque.pop(0)

    def remove_rear(self):
        """Removes the element in the rear end of the queue."""
        return self.deque.pop()

    def is_empty(self):
        """Checks to see if the deque is empty.

           @return : 'True' if the deque is empty 'False' otherwise
        """
        return len(self.deque) == 0

    def size(self):
        """Calculates the length of deque.

           @returns : length of deque
        """
        return len(self.deque)

    def contains(self, item):
        """Searches for the item in deque.

           @param item : The item to be searched in the deque
           @returns : 'True' if the item is found in deque 'False' otherwise
        """
        return item in self.deque

    def print_deque(self):
        """Prints the deque data structure."""
        for i in self.deque:
            print(i)
        print()
