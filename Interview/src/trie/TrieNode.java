package trie;

public class TrieNode {

    public char letter;
    public boolean isWord = false;
    public TrieNode[] child = null;

    public TrieNode() {
        this.child = new TrieNode[26];
    }

    public TrieNode(char ch) {
        this.letter = ch;
        this.child = new TrieNode[26];
    }

    @Override
    public String toString() {
        return "TryNode(" + this.letter + ", " + this.isWord + ")";
    }
}
