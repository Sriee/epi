package trie;

/**
 * Leet code. Solution -> Accepted Run Time: 79 ms. Above average run time.
 * Optimal solution uses a hack without storing the actual letter
 */
public class WordDictionary {

    private TrieNode root;

    public WordDictionary() {
        this.root = new TrieNode();
    }

    /**
     * Add word to the word dictionary
     * 
     * @param word given word
     */
    public void addWord(String word) {
        TrieNode iter = this.root;

        for (char ch : word.toCharArray()) {
            if (iter.child[ch - 'a'] == null)
                iter.child[ch - 'a'] = new TrieNode(ch);
            iter = iter.child[ch - 'a'];
        }

        iter.isWord = true;
    }

    /**
     * Helper method for word search
     * 
     * @param node current iteration node
     * @param word Substring to match
     * @return true if there is a path in the trie else false
     */
    public boolean regexSearch(TrieNode node, String word) {
        if (word.equals(""))
            return node.isWord;

        TrieNode iter = node;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (ch == '.') {
                boolean found = false;
                for (int j = 0; j < 26; j++) {
                    if (iter.child[j] == null)
                        continue;

                    found = this.regexSearch(iter.child[j], word.substring(i + 1));
                    if (found)
                        return found;
                }
                return found;
            } else {
                if (iter.child[ch - 'a'] == null)
                    return false;
            }
            iter = iter.child[ch - 'a'];
        }

        return iter.isWord;
    }

    /**
     * Search for a word in the word dictionary. Should support regex "." Example:
     * Word Dictionary [bad, mad] search("b.d") -> true search(".") -> false; since
     * there is no word ending with single character
     * 
     * @param word to search
     * @return true if the word is found false otherwise
     */
    public boolean search(String word) {
        TrieNode temp = this.root;

        return this.regexSearch(temp, word);
    }

    public static void main(String[] args) {
        WordDictionary dict = new WordDictionary();

        dict.addWord("bad");
        dict.addWord("dad");
        dict.addWord("mad");

        System.out.println(dict.search("pad"));
        System.out.println(dict.search("bad"));
        System.out.println(dict.search(".ad"));
        System.out.println(dict.search("b.."));
    }

}
