class HashTable:
    """Dictionary data structure implementation using Python list. There are
       two list. Key List which holds the keys for the hash table and Value
       List which holds the values in the position with respect to the key
       list.
    """

    def __init__(self, size=11):
        """Initializes the Hash Table

           @param size: The default value is 11. The size of the hash table has
                        to be in prime numbers to make the hash function to be
                        spread out evenly.
        """
        self.size = size
        self.key = [None] * size
        self.value = [None] * size

    def hash_function(self, value):
        """Calculates the hash for the key.

           @param value : The value for which hash will be calculates
        """
        return value % self.size

    def rehash(self, old):
        """Rehashing function to overcome hash collision. The technique used
           here is 'Linear Probing'. In this technique if there is a collision
           for the value with already existing key, then we traverse through
           the hash table for next empty slot.

           @param old : The old hash value
        """
        return (old + 1) % self.size

    def put(self, key, value):
        """Inserts the key, value pair inside the hash table. If there is
           collision new key is calculated using rehashing.

           @param  key : Hash Table key
           @param value: Hash Table Value
        """
        h = self.hash_function(key)

        if self.key[h] is None:
            self.key[h] = key
            self.value[h] = value
        else:
            found, old, new = False, h, None
            while h != new and not found:
                new = self.rehash(old)
                if self.key[new] is None:
                    self.key[new] = key
                    self.value[new] = value
                    found = True
                old = new

            if not found:
                self.key[h] = key
                self.value[h] = value

    def get(self, key):
        """Returns the value for the key which is O(1) time complexity.

           @param  key  : Hash Table key
           @return value: Hash Table Value
        """
        h = self.hash_function(key)
        result = None
        if self.key[h] == key:
            return self.value[h]
        else:
            found, old, new = False, h, None
            while h != new and not found:
                new = self.rehash(old)
                if self.key[new] == key:
                    result = self.value[new]
                    found = True
                old = new

        return result

    def keys(self):
        """Returns only the keys of the hash table."""
        return self.key

    def items(self):
        """Returns only the values of the hash table."""
        return self.value

    def __setitem__(self, key, value):
        """Supports array indexing technique for storing key, value pair
           self[key] = value

           @param key  : Hash Table key
           @param value: Hash Table Value
        """
        self.put(key, value)

    def __getitem__(self, key):
        """Supports array indexing technique for retriebing value for a key
           self[key]

           @param  key  : Hash Table key
           @return value: Hash Table Value
        """
        return self.get(key)

    def __contains__(self, key):
        """Supports 'in' functionality. key in hash table

           @param key: Hash Table key
           @returns  : 'True' If the key is present in the Hash Table, 'False'
                       otherwise
        """
        return self.get(key) is not None

    def __delitem__(self, key):
        """Supports del self[key] construct. Deletes the key and value present
           in self[key]

           @param key: Hash Table key
        """
        h = self.hash_function(key)

        if self.key[h] != key:
            h = self.rehash(key)

        self.key[h] = None
        self.value[h] = None

    def __len__(self):
        """Calculates the length of the hash table. 'None' keys & values are
           ignored. Supports len(self) construct.

           @return : Length of hash table.
        """
        length, i = 0, 0
        while i < self.size:
            if self.key[i] is not None:
                length += 1
            i += 1
        return length

    def __str__(self):
        """Prints the hash table in Python dictionary format { key:value, ... }
        """
        string, i = "{ ", 0

        while i < self.size - 1:
            string = string + str(self.key[i]) + ": " + str(self.value[i]) + ", "
            i += 1

        string = string + str(self.key[i]) + ": " + str(self.value[i]) + " }"

        return string
