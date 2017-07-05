
class Context(object):

	def __init__(self, branch, harley, version, borrower):
		self._branch = branch
		self._harley = harley
		self._version = version
		self._borrower = borrower

	def toDict(self):
		ctxDict = {}
		for val in vars(self):
			key = val[1:]
			ctxDict[key] = vars(self)[val]
		return ctxDict
