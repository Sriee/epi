import name

class Borrower(object):

	def __init__(self, first=None, last=None, email=None):
		self.name = name.Name(first, last)
		self.email = email
		self._secret = "Lets see whether I can access this"
		
	def __getitem__(self, item):
		
		if item in vars(self):
			return vars(self)[item]
		else:
			raise KeyError('Invalid key.')
