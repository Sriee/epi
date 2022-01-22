package trie;

import java.util.*;

/**
 * Leet code. Solution -> Accepted Run Time: 10 ms. Above Average run time.
 * Given a list of words in a dictionary find the longest words that can be
 * built one character at a time.
 */
class TrieProblems {

    private TNode root;

    public TrieProblems() {
        root = new TNode();
    }

    /**
     * Helper method to check if a word cab be formed by concatenating multiple
     * words by traversing through the trie
     * 
     * @param word  ith word
     * @param idx   index traversed so far in the word
     * @param count number of concatenated word already formed
     * @return true if the word is formed by concatenating more than 1 word, false
     *         otherwise
     */
    private boolean verify(String word, int idx, int count) {
        if (word.length() == idx)
            return count > 1;

        TNode iter = this.root;
        for (int i = idx; i < word.length(); i++) {
            char ch = word.charAt(i);
            int j = ch - 'a';

            if (iter.children[j] == null)
                return false;
            else if (iter.children[j].word != null && this.verify(word, i + 1, count + 1))
                return true;

            iter = iter.children[j];
        }
        return false;
    }

    /**
     * Leet code. Solution -> Accepted Run Time: 41 ms. Optimal solution Given a
     * list of words, give all the words which can be formed by contatenating word
     * in the given list. Example: ["dog", "dogcat", "cat"] -> "dogcat" (because it
     * can be made by concatenating "dog" + "cat")
     * 
     * @param words Dictionary words
     * @return list of words that can be formed by concatenation
     */
    public List<String> concatenateWords(String[] words) {
        List<String> result = new LinkedList<>();
        if (words == null || words.length == 0)
            return result;

        for (String word : words)
            this.insert(word);

        for (String word : words) {
            if (this.verify(word, 0, 0))
                result.add(word);
        }

        return result;
    }

    /**
     * Insert word in Trie (Prefix tree)
     * 
     * @param word
     */
    public void insert(String word) {
        TNode iter = this.root;

        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';

            if (iter.children[idx] == null)
                iter.children[idx] = new TNode();

            iter = iter.children[idx];
        }

        iter.word = word;
    }

    /**
     * Helper method to compute the longest word in a dictionary. We are using
     * breath first search instead of dfs. While using dfs DFS Approach: For each
     * recursive call we need to accumulate/add characters of a string at each node
     * of the trie. We will compare the result againt each string which end with a
     * word. We will visiting O(n * w) nodes where n is the number of words in the
     * dictionary and w is the length of each word in the dictionary Also added
     * space complexity because of recursion BFS Approach: We will stop adding nodes
     * in the stack, if we find that a node doesn't have a word in it.
     * 
     * @param size
     * @return longest word in dictionary
     */
    public String longestWord(int size) {
        String result = "";
        TNode iter = this.root;
        TNode[] stack = new TNode[size];
        int top = -1;

        for (TNode child : iter.children) {
            if (child == null)
                continue;

            stack[++top] = child;
        }

        while (top != -1) {
            TNode node = stack[top--];

            if (node.word != null) {

                if (node.word.length() > result.length()
                        || (node.word.length() == result.length() && node.word.compareTo(result) < 0))
                    result = node.word;

                for (TNode child : node.children) {
                    if (child == null)
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

        for (char ch : token.toCharArray()) {
            int idx = ch - 'a';
            if (iter.children[idx] == null)
                return token;
            else if (iter.children[idx].word != null)
                return iter.children[idx].word;
            else
                iter = iter.children[idx];
        }

        return token;
    }

    /**
     * Leet code. Solution -> Accepted Run Time: 8 ms. Optimal solution. Given a
     * sentence, replace the words in the sentence with the root words which are
     * stored in the dictionary.
     * 
     * @param dict     Dictionary of words
     * @param sentence
     * @return senten
     */
    public String replaceWords(List<String> dict, String sentence) {
        for (String word : dict)
            this.insert(word);

        StringBuffer sb = new StringBuffer();

        for (String token : sentence.split(" ")) {
            String res = this.replaceWordsHelper(token);
            System.out.println("I got " + res + " for " + token);
            sb.append(res);
            sb.append(" ");
        }

        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * Leet code. Solution -> Accepted Run Time: 1 ms. Optimal Solution using
     * dynamic programming Given a string and list of words tell me whether it can
     * be made from more than than one word from the given list Note this may look
     * similar to concatenate words problem, but implementing the solution throw TLE
     * (Time Limit Exceeded) Example: s: leetcode, wordDict = ["leet", "code"] ->
     * true
     * 
     * @param s        String to find
     * @param wordDict List of words
     * @return true if the word can be made from word segments, false otherwise
     */
    public boolean wordBreak(String s, String[] wordDict) {
        // Populate the trie
        for (String word : wordDict)
            this.insert(word);

        boolean[] dp = new boolean[s.length() + 1];
        // DP base case
        dp[s.length()] = true;

        // Trick: Approached from right. Doing so we can avoid the following problem
        // ["apple", "apples", "sand"]; "applesand"
        for (int i = s.length() - 1; i >= 0; i--) {
            TNode iter = this.root;

            for (int j = i; j < s.length() && iter != null; j++) {
                iter = iter.children[s.charAt(j) - 'a'];
                if (iter != null && iter.word != null && dp[j + 1]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        TrieProblems tp = new TrieProblems();
        /*
         * String[] words = new String[] {"ap", "a", "app", "appl", "apple", "banana",
         * "apply"}; for(String word : words) tp.insert(word);
         * System.out.println(tp.longestWord(words.length)); List<String> dict = new
         * ArrayList<>(); String[] words = new String[] {"cat", "bat", "batter", "mat"};
         * for(String word: words) dict.add(word);
         * System.out.println(tp.replaceWords(dict,
         * "the cattle was rattled by the batter matter"));
         */
        String[] words = new String[] { "cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat",
                "ratcatdogcat" };
        System.out.println(tp.wordBreak("dogcat", words));
        /*
         * List<String> ans = tp.concatenateWords(words); for(String i : ans)
         * System.out.print(i + " ");
         */
    }

}
