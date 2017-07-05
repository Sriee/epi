import json


class Borrower(object):

	def __init__(self, first=None, last=None, email=None):
		self._firstname = first
		self._lastname = last
		self._email = email
		
	def __getitem__(self, item):
		
		print "getitem getting called."
		if item in vars(self):
			return vars(self)[item]
		else:
			raise KeyError('Invalid key.')

	@property
	def firstname(self):
		return self._firstname

	@firstname.setter
	def firstname(self, value):
		self._firstname = value

	@property
	def lastname(self):
		return self._lastname

	@lastname.setter
	def lastname(self, value):
		self._lastname = value

	@property
	def email(self):
		return self._email

	@email.setter
	def email(self, value):
		self._email = value

	def dump(self):
		myDict = {}
		for item in vars(self):
			myDict[item] = vars(self)[item]

		with open('para.json', 'w') as wp:
			json.dump(myDict, wp, indent=4, sort_keys=True) 