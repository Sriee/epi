package trie;

/**
 * Leet code. Solution -> Accepted
 * 
 * Trie (Prefix Tree) Implementation
 * 
 */
public class TrieUtils {

	public TrieNode root = null;
	
	public TrieUtils() {
		this.root = new TrieNode();
	}
	
	public void insert(String word) {
		TrieNode iter = this.root;
		
		for(char ch: word.toCharArray()) {
			int idx = ch - 'a';
			if(iter.child[idx] == null) {
				iter.child[idx] = new TrieNode(ch);
			}
			iter = iter.child[idx];
		}
		iter.isWord = true;
	}
	
	public boolean search(String word) {
		TrieNode iter = this.root;
		
		for(char ch : word.toCharArray()) {
			int idx = ch - 'a';
			if(iter.child[idx] == null)
				return false;
			iter = iter.child[idx];
		}
		return iter.isWord;
	}
	
	public boolean startsWith(String prefix) {
		TrieNode iter = this.root;
		
		for(char ch : prefix.toCharArray()) {
			int idx = ch - 'a';
			if(iter.child[idx] == null)
				return false;
			iter = iter.child[idx];
		}
		return true;
	}
}
