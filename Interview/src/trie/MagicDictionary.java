package trie;

/**
 * Leet code. Solution -> Accepted
 * 
 * Run Time: 47 ms. Average run time. Optimal soultion involves a hack of not storing letters in a Trie Node
 *  
 * Implement Magic Dictionary
 *
 */
public class MagicDictionary {

	private TrieNode root;
	
	public MagicDictionary() {
		this.root = new TrieNode();
	}
	
	/**
	 * Add word to the Magic Dictionary
	 * 
	 * @param word
	 */
	private void add(String word) {
		TrieNode iter = this.root;
		
		for(char ch : word.toCharArray()) {
			if(iter.child[ch - 'a'] == null)
				iter.child[ch - 'a'] = new TrieNode(ch);
			iter = iter.child[ch - 'a'];
		}
		iter.isWord = true;
	}
	
	/**
	 * Build Magic Dictionary given a list of words
	 * 
	 * @param dict
	 */
	public void buildDict(String[] dict) {
		for(String word : dict)
			this.add(word);
	}
	
	/**
	 * Helper method to perfom search
	 * 
	 * @param arr String converted to character array
	 * @param k index of the string
	 * @param diff number of character differences taken into account
	 * @param node traversed Trie Node
	 * @return true if the word to search is found with only one diff, false otherwise
	 */
	private boolean match(char[] arr, int k, int diff, TrieNode node) {
		if(k == arr.length) {
			if(diff == 0 || diff > 1)
				return false;
			else
				return node.isWord;
		}  

		for(TrieNode child : node.child) {
			if(child == null)
				continue;
			
			if(child.letter == arr[k]) {
				if(this.match(arr, k + 1, diff, child))
					return true;
			} else {
				if(this.match(arr, k + 1, diff + 1, child))
					return true;	
			}
		}
		return false;
	}
	
	/**
	 * Search Magic Dictionary for a word. The word should match only if atmost one character is changed  
	 * 
	 * @param word to search
	 * @return true if found, false otherwise
	 */
	public boolean search(String word) {
		TrieNode iter = this.root;
		return this.match(word.toCharArray(), 0, 0, iter);
	}
	
	public static void main(String[] args) {
		MagicDictionary md = new MagicDictionary();
		
		md.buildDict(new String[] {"hello", "hallo", "leetcode"});

		// true -> modifying 'a' to 'e' in 'hallo' gives us 'hello'  
		System.out.println(md.search("hallo"));	 
		System.out.println(md.search("hhllo"));
		System.out.println(md.search("hell"));
		System.out.println(md.search("leetcoded")); 
	}
}
