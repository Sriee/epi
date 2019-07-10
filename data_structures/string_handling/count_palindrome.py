if __name__ == '__main__':
	ls = 'hellolle'
	for center in range(len(ls)):
	# check how many characters to the left and right of |center|
	# build a palindrome
		maxoffs = min(center, len(ls)-center-1)
		offs = 0
		while offs <= maxoffs and ls[center-offs] == ls[center+offs]:
			offs += 1
		offs -= 1
		print ls[center-offs : center+offs+1]                                    

	# check for even palindromes
	for center in range(len(ls)-1):
		maxoffs = min(center, len(ls)-center-2)
		offs = 0
		while offs <= maxoffs and ls[center-offs] == ls[center+offs+1]:
			offs += 1
		offs -= 1
		
		if offs >= 0:
			print ls[center-offs : center+offs+2]
	