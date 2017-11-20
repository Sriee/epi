class Stack:
    """Stack data structure implementation using lists."""

    def __init__(self):
        """Initializes Stack."""
        self.stack = []

    def is_empty(self):
        """Check if the stack is empty.

           @return True: If the stack is empty
                  False: if the stack is not empty
        """
        return len(self.stack) == 0

    def push(self, item):
        """Inserts the item inside the stack.

           @param item: The item to be inserted in the stack
        """
        self.stack.append(item)

    def pop(self):
        """Pops the recently inserted number in the stack.

           @return : Top element of the stack and removes the item in the stack
        """
        return self.stack.pop()

    def peek(self):
        """Returns the most recently added item on the stack without removing it.

           @return : Top element of the stack without removing it
        """
        return self.stack[-1]

    def size(self):
        """Calculates the size of the stack."""
        return len(self.stack)

    def contains(self, item):
        """Searches for the item in the stack.

           @param item : The item to search in the stack
           @return True: If the item is present in the stack
                  False: otherwise
        """
        return item in self.stack



