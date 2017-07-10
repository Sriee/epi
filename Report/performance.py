import os
import json

class Performance(object):

	def __init__(self, uuid):
		self.uuid = uuid
		self.test_case = {}
		self.load_object()

	def load_object(self):

		with open(os.path.join('Performance', self.uuid), 'r') as rp:
			inp = json.load(rp)
		
		for item in inp['testcases']:
		    name = item['name']
		    self.test_case[name]={}
		    self.test_case[name]['status'] = item['status']
		    self.test_case[name]['item_id'] = item['item_id']
		    self.test_case[name]['iterations'] = item['iterations']
		    self.test_case[name]['total_time'] = item['total_time']
		else:
			print 'Object loaded'

	def __contains__(self, item):
		return item in self.test_case

	def __len__(self):
		if self.test_case is None:
			return 0
		else:
			return len(self.test_case)

	def __iter__(self):
		self.index = -1
		self.keys = self.test_case.keys()
		return self

	def next(self):
		try:
			while self.index < self.__len__:
				self.index += 1
				return self.keys[self.index]
		except IndexError:
			raise StopIteration

	def __getitem__(self, item):
		print 'getitem called'
		return self.test_case[item] if item in self.test_case else None 

	def get_keys(self):
		return self.test_case.keys()

	def __str__(self):
		return 'Performance(uuid: {}, Test_cases: {}' \
				.format(self.uuid, self.test_case.keys() if self.test_case else 'Empty')

	def __repr__(self):
		return str(self.test_case)
"""
def compare(first, second):
	first_set, second_set = set(), set()


	first_set = first.get_keys()
	second_set = second.get_keys()

	# Finding the intersection
	common = first_set.intersection(second_set)

	print '*' * 50

	if len(common) != 0:
		for element in common:
			print '{}'
		pass # Do the comparison here 

	# Finding the difference
	diff = list(first_set ^ second_set)

	for element in diff:
		if element in first:
"""

def pretty_print():
	print 'l' + '-' * 100


if __name__ == '__main__':
	first = Performance('DHCPv4_smoke.json')
 	second = Performance('DHCPv4_smoke_pim.json')
	pretty_print()