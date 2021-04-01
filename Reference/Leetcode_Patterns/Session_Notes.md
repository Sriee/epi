## Steps
- Solve the problem on the notepad. 
- Think about solutions sequentially
	- Think 1 solution. Do time complexity analysis
	- Move on to 2nd solution
- Finalize the approach and code it up
- If you couldn't solve the problem within 30 min mark. Look at the solution. Understand why you 
couldn't solve the problem. Note down the notes

*Things to remember*
- If you have already seen the problem. Don't try to re-collect the solution you did already instead focus on solving the problem that you do in your practice session
- After typing in the code, run through some test cases through your code & notepad. Make changes if needed.

## Arrays
---

### 26 - Remove duplicates from Sorted Array
- Recognized to use two pointer approach
- Ran through the code with different cases (edge case as well) and corrected it

### 122 - Best Time to Buy & Sell stock 2
- Figured out the pattern how the array is behaving by writing down different examples
- Tested assumption for our approach and then finalized our approach ++
- Debugged code using debugger --

### 189 - Rotate Array
- Incorrectly calculated the run time for shifting k items. Tried optimizing it while solving the problem --
- 2 non-intuitive approaches - Cyclic replacements, Reversing arrays 

### 283 - Move Zeros
- Had a feeling that we have solved the problem before. Then remembered Medium Meta article about the mistake of thinking of what we did previously and actually not solving the problem.
- Did not try to optimize the solution in the middle of another solution.
- Took 3 minutes to code the solution [15 lines]

### 384 - Shuffle Array
- Did not read the description correctly. The description say that the array won't be having duplicates.
- Tested out 2 approaches (using deque & HashMap). Then quickly went out to another approach. 
- *(Review)* Fisher Yates shuffle implementation

## Intervals
---

### 56 - Merge Intervals
*(Todo)* Add pattern to Anki 
- Read Interval pattern on educative.io

## Linked List
---

### 206 - Reverse Linked List 
- *(Review)*
- We got frustrated when we couldn't solve the problem within 30 min. It is supposed to be an easy one for us. Tried to remeber the trick instead of solving it

### 234 - Palindrome Linked List
- *(Review)*
- Solution route was correct. Fault was in coding. 

## Matrix
---

### 36 - Valid Sudoku
- Did not finish it within 30 min time frame. Tried to But was able to complete the problem in 50 min. 

### 48 - Rotate Image
- Did not finish it withhin 30 min. Completed in 1 hr 30 min
- We went on the right path, but did not write out the steps properly. We doubted our approach and hesitated to move forward
- We weren't writing the steps properly on the notepad. For this solution, writing the steps properly would have helped solve the problem

### 73 - Set Matrix to Zero
- *(Review)*
- We knew how to do it with additional space, ruled out brute force approach and implemented O(mn) 
solution with constant space. 
- There was one minor mistake with the implementation. We doubted our approach and though of different
approaches. Instead the problem was with a particular edge case @[0][0]
- Don't be deceived by a simple problem statement. If the problem looks simple, there will definitely 
be a catch 

## Numbers
---

### 191 - Number of 1 Bits
*TODO* Add trick to anki
- There is a trick with right shifting in Java, which will prevent numbers to overflow. 

## Strings
---

### 28 - Implement strStr()
- This problem exposed me to section of algorithm (String matching). We weren't able to answer this 
question in our Interview.io mock interview since we weren't aware of rolling hash, Rabin Karp & KMP
algorithm. 
- Many of the hard problems are based on text book algorithms, exercise problem (+ their variations) 
and algorithm used in libraries.

### 43 - Multiply Strings
- *(Review)* - Did not solve it under 45 min + incorrect answer
- Thought process was correct but the code implementation got complex. We are seeing the following pattern. 
	- We come up with a solution. Make sure that we handle all the edge cases, short circuit computation
	- Implement the code with some bugs in it. Some of the solutions pass, some of then fail
	- By this time, the time limit would be almost over. We get nervous and wouldn't solve the problem completely 

### 125 - Valid Palindrome
- *(Review)* - Suprised, another reason to focus & think through the solution, even if you know the problem is simple.

## Tree
---

### 98. Validate Binary Search Tree 
*TODO* Add trick to anki
- There is a trick for iterative traversal using stack.  

## Java Refresh

Follow this [tutorial](http://tutorials.jenkov.com/java-collections/index.html) to learn about methods available for
Java Collections.

### Random

import java.util.Random;
Random Methods - nextInt(), nextInt(seed), next()

### Arrays

import java.util.Arrays;
Methods

- fill(T[], value)
- toString(T[])
- deepToString(T[][]) Multi dimensional array
- sort(T[])
- sort(T[], fromIndex, toIndex)
- binarySearch(T[], value)
- binarySearch(T[], fromIndex, toIndex, value)

### List

Cannot convert array of primitives to array of Objects. 
	int[] arr = {1, 2, 3};
	Integer[] arrObj = Arrays.asList(arr); // Won't work

	// We should use the trivial solution of boxing individual elements
	List<Integer> ls = new ArrayList<>();
	for(int i : arr) ls.add(i);

### Stack

[Stack or List]<Integer> stk = new Stack<>();
stack.push(0);
stack.peek();
stack.pop();
stack.search(val); // + All methods of List Interface


### Set

Set<Integer> xs = new HashSet<>(), ys = new HashSet<>();
Union = xs.addAll(ys);
Intersection = xs.retainAll(ys);
Difference = xs.removeAll(ys);
