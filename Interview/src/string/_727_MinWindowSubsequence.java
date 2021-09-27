package string;

public class _727_MinWindowSubsequence {

    /**
     * Sliding Window Pattern
     *
     * TC: O(m * n) where m = len(s2) and n = len(s1)
     * SC: O(1)
     */
    private String minWindow(String s1, String s2) {
        int start = -1, len = Integer.MAX_VALUE, sLen = s1.length(), tLen = s2.length();
        char[] s = s1.toCharArray(), t = s2.toCharArray();

        for (int sIdx = 0, tIdx = 0; sIdx < sLen; sIdx++) {
            if (s[sIdx] == t[tIdx]) {
                if (++tIdx == tLen) {
                    int end = sIdx + 1;

                    while (--tIdx >= 0) {
                        while (s[sIdx--] != t[tIdx]) ;
                    }

                    sIdx++;
                    tIdx++;

                    if (end - sIdx < len) {
                        len = end - sIdx;
                        start = sIdx;
                    }
                }
            }
        }

        return start == -1 ? "" : s1.substring(start, start + len);
    }

    public static void main(String[] args) {
        _727_MinWindowSubsequence mws = new _727_MinWindowSubsequence();
        String[] s1 = {
            "abcdebdde",
            "fgrqsqsnodwmxzkzxwqegkndaa",
            "michmznaitnjdnjkdsnmichmznait",
            "afgegrwgwga",
            "abababa",
            "ffynmlzesdshlvugsigobutgaetsnjlizvqjdpccdylclqcbghhixpjihximvhapymfkjxyyxfwvsfyctmhwmfjyjidnfryiyajmtakisaxwglwpqaxaicuprrvxybzdxunypzofhpclqiybgniqzsdeqwrdsfjyfkgmejxfqjkmukvgygafwokeoeglanevavyrpduigitmrimtaslzboauwbluvlfqquocxrzrbvvplsivujojscytmeyjolvvyzwizpuhejsdzkfwgqdbwinkxqypaphktonqwwanapouqyjdbptqfowhemsnsl",
        };
        String[] s2 = {
            "bde",
            "kzed",
            "michmznait",
            "aa",
            "ba",
            "ntimcimzah"
        };

        for (int i = 0; i < s1.length; i++) {
            System.out.println(mws.minWindow(s1[i], s2[i]));
        }
    }
}

