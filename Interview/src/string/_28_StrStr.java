package string;

public class _28_StrStr {

    /**
     * Naive Solution:
     *
     * For test cases 3 and 4, the time complexity degrades to O(m * (n - m + 1)). This solution is not the optimal
     * and should not be used.
     */
    public int strStr(String text, String pattern) {
        if (pattern.length() == 0)
            return 0;

        if (text.length() < pattern.length())
            return -1;

        int n = text.length(), m = pattern.length();

        for (int i = 0; i < n - m + 1; i++) {
            if (text.substring(i, i + m).equals(pattern))
                return i;
        }

        return -1;
    }

    public static void main(String[] args) {
        _28_StrStr st = new _28_StrStr();
        String[] texts = {"sabbutsad", "leetcode", "AAAAAAAAAAAAA", "AAAABAAAAB"};
        String[] patterns = {"sad", "leeto", "AAAA", "AAAB"};

        for (int i = 0; i < texts.length; i++) {
            System.out.println(st.strStr(texts[i], patterns[i]));
        }
    }
}
