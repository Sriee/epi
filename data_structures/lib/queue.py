class Queue:
    """Queue data structure implementaion using Python lists."""
    def __init__(self):
        """Initializes the queue."""
        self.queue = []

    def enqueue(self, item):
        """Adds the item in the front of the queue to satisfy Last In First Out 
           LIFO ordering. 
 
           @param item : The item to be added to the queue
        """
        self.queue.insert(0, item)

    def deque(self):
        """Returns the items which was inserted first."""
        return self.queue.pop()

    def size(self):
        """Calculates the length of the queue.
 
           @return : The length of the queue 
        """
        return len(self.queue)
   
    def is_empty(self):
        """Checks if the queue is Empty."""
        return len(self.queue) == 0
 
    def contains(self, item):
        """Searches through the queue to find the item in the queue
         
           @return : 'True' if the item is present in the queue 'False' 
                     otherwise   
        """
        return item in self.queue

    def __str__(self):
        """Prints the queue."""
        q_string = ""
        for i in range(0, len(self.queue)):
            q_string += self.queue[i]

        return q_string
