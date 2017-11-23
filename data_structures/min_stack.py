class MinStack:

    def __init__(self):
        """
        initialize your data structure here.
        """
        self.min, self.stack = None, []
         

    def push(self, x):
        """
        :type x: int
        :rtype: void
        """
        if self.min is None:
            self.min = x
        elif x < self.min:
            self.min = x
            
        self.stack.append((x, self.min))

    def pop(self):
        """
        :rtype: void
        """
        self.stack.pop()

    def top(self):
        """
        :rtype: int
        """
        return self.stack[-1][0]

    def getMin(self):
        """
        :rtype: int
        """
        return self.stack[-1][1] 
	
	def __str__(self):
		return ''.join(self.stack)

		
if __name__ == '__main__':
	obj = MinStack()
	obj.push(0)
	obj.push(1)
	print obj.getMin()
	obj.push(0)
	print obj.stack
	print obj.getMin()
	obj.pop()
	print obj.getMin()