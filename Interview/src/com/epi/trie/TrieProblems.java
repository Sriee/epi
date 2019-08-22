package com.epi.trie;

import java.util.List;
import java.util.ArrayList;


/**
 * Leet code. Solution -> Accepted
 * 
 * Run Time: 10 ms. Above Average run time.
 * 
 * Given a list of words in a dictionary find the longest words that can be built one character at a time.
 */
class TrieProblems {

	private TNode root;

	public TrieProblems() { 
		root = new TNode(); 
	}

	/**
	 * Insert word in Trie (Prefix tree)
	 * 
	 * @param word
	 */
	public void insert(String word) {
		TNode iter = this.root;

		for(char ch: word.toCharArray()) {
			int idx = ch - 'a';

			if(iter.child[idx] == null)
				iter.child[idx] = new TNode();

			iter = iter.child[idx];
		}

		iter.word = word;
	}

	/**
	 * Helper method to compute the longest word in a dictionary.
	 * 
	 * We are using breath first search instead of dfs. While using dfs 
	 * 
	 * DFS Approach: 
	 * 		For each recursive call we need to accumulate/add characters of a string at each node of the trie. 
	 * 	We will compare the result againt each string which end with a word. We will visiting O(n * w) nodes 
	 *  where
	 *  	n is the number of words in the dictionary and
	 *  	w is the length of each word in the dictionary
	 *  Also added space complexity because of recursion
	 *  
	 * BFS Approach:
	 * 		We will stop adding nodes in the stack, if we find that a node doesn't have a word in it. 		
	 * 
	 * @param size
	 * @return longest word in dictionary
	 */
	public String longestWord(int size) {
		String result = "";
		TNode iter = this.root;
		TNode[] stack = new TNode[size];
		int top = -1;

		for(TNode child : iter.child) {
			if(child == null)
				continue;

			stack[++top] = child;
		}

		while(top != -1) {
			TNode node = stack[top--];

			if(node.word != null) {

				if(node.word.length() > result.length() || (node.word.length() == result.length() && node.word.compareTo(result) < 0))
					result = node.word;

				for(TNode child : node.child) {
					if(child == null)
						continue;

					stack[++top] = child;
				}
			}
		}
		return result;
	}

	/**
	 * Helper utility to replace words
	 * 
	 * @param token 
	 * @return token if the root word is not found in dictionary else root word 
	 */
	private String replaceWordsHelper(String token) {
		TNode iter = this.root;
		
		for(char ch : token.toCharArray()) {
			int idx = ch - 'a';
			if(iter.child[idx] == null)
				return token;
			else if(iter.child[idx].word != null)
				return iter.child[idx].word;
			else
				iter = iter.child[idx];
		}
		
		return token;
	}
	
	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 8 ms. Optimal solution.
	 * 
	 * Given a sentence, replace the words in the sentence with the root words which are stored in the dictionary. 
	 * 
	 * @param dict Dictionary of words 
	 * @param sentence 
	 * @return senten
	 */
	public String replaceWords(List<String> dict, String sentence) {
		for(String word : dict)
			this.insert(word);
		
		StringBuffer sb = new StringBuffer(); 
		
		for(String token : sentence.split(" ")) {
			String res = this.replaceWordsHelper(token); 
			System.out.println("I got " + res + " for " + token);
			sb.append(res);
			sb.append(" ");
		}
		
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	} 
	
	public static void main(String[] args) {
		TrieProblems tp = new TrieProblems();
		/*
		String[] words = new String[] {"ap", "a", "app", "appl", "apple", "banana", "apply"};

		for(String word : words)
			tp.insert(word);

		System.out.println(tp.longestWord(words.length));
		*/
		
		List<String> dict = new ArrayList<>();
		String[] words = new String[] {"cat", "bat", "batter", "mat"};
		
		for(String word: words)
			dict.add(word);
		
		System.out.println(tp.replaceWords(dict, "the cattle was rattled by the batter matter"));
	}

}
