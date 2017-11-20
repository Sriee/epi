class Node:
    """Node data structure used for Linked List implementation."""

    def __init__(self, data=None, next=None):
        self.data = data
        self.next = next

    def __str__(self):
        """Prints the data of the node when used in 'print' statements."""
        return str(self.data)


class List:
    """Unordered Linked List implementation in Python."""

    def __init__(self):
        """Initializes the list."""
        self.head = None

    def is_empty(self):
        """Checks if the list is empty. 
 
           @return : True - if the list is empty
                     False - if the list is not empty 
        """
        return self.head is None

    def append(self, item):
        """Inserts the element to the end of the list. 
       
           @param : item - The item to be inserted in the list
        """
        new_node = Node(item)

        if self.is_empty():
            self.head = new_node
        else:
            current = self.head
            while current.next:
                current = current.next
            current.next = new_node

    def insert(self, pos, item):
        """Insert an item into the list at a specified position. 

           @param pos : Position to insert the element 
           @param item: The item to be inserted into the list 
        """
        new_node = Node(item)
        cur_pos = 0
        current = self.head
        prev = None

        while cur_pos != pos:
            prev = current
            current = current.next
            cur_pos += 1

        if prev is None:
            new_node.next = self.head  # At the begining
            self.head = new_node
        elif current.next is None:  # At the end
            current.next = new_node
        else:  # In the middle
            new_node.next = current
            prev.next = new_node

    def index(self, item):
        """Returns the index of the item in the list. 

           @param item : The item whose index needs to be searched in the list 
           @return pos : The position of the item in the list. 
                         -1 is returned if the item is not found in the list 
        """
        current = self.head
        found = False
        pos = 0
        if current.data == item:
            found = True
        else:
            while current.next and not found:
                pos += 1
                current = current.next
                if current.data == item:
                    found = True

        if not found:
            pos = -1

        return pos

    def pop(self):
        """Pops the last element in the list."""

        if self.head.next is None:
            pop_data = self.head.data
            self.head = None
        else:
            current = self.head

            while current.next:
                last_before = current
                current = current.next

            last_before.next = None
            pop_data = current.data

        return pop_data

    def pop(self, pos):
        """Pop's an element at the specified position.
 
           @param pos: Position of the item to be popped from the list
        """
        current = self.head
        prev = None
        cur_pos = 0
        pop_data = -1

        while cur_pos < pos:
            prev = current
            current = current.next
            cur_pos += 1

        if prev is None:  # At the begining
            pop_data = current.data
            current = current.next
            self.head = current
        elif current is None:  # At the end
            pop_data = prev.data
            prev.next = None
        else:  # In-between the list
            pop_data = current.data
            prev.next = current.next

        return pop_data

    def search(self, item):
        """Searches for an item in the list. 

           @param item : The item to be searched
           @return True: If the item is present in the list
                  False: If the item is not present in the list
        """
        current = self.head

        if self.is_empty():
            found = False
        elif current.data == item:
            found = True
        else:
            found = False
            while current.next and not found:
                if current.data == item:
                    found = True
                    current = current.next

        return found

    def size(self):
        """The length of the list."""

        current = self.head
        size = 1

        if self.is_empty():
            size = 0
        else:
            while current.next:
                size += 1
                current = current.next

        return size

    def __str__(self):
        """Prints the contents of the list when used in 'print' construct. """

        if self.is_empty():
            return "[]"

        list_string = "["
        temp = self.head
        list_string += str(temp.data)
        while temp.next:
            temp = temp.next
            list_string = list_string + ", " + str(temp.data)

        list_string += "]"

        return list_string
