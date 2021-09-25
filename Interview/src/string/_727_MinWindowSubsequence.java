package string;

public class _727_MinWindowSubsequence {

    /**
     * The below code is failing for test case No 6. This could be due to pointer handling and could not find out
     * the issue.
     */
    public String minWindow(String s, String t) {
        int len = Integer.MAX_VALUE;
        String result = "";
        char[] s1 = s.toCharArray(), s2 = t.toCharArray();

        for (int idx1 = 0, idx2 = 0; idx1 < s.length();) {
            if (s1[idx1] == s2[idx2]) {
                idx2++;

                if (idx2 == t.length()) {
                    idx2--;
                    int start = idx1 - 1, end = idx1;

                    while (idx2 >= 0) {
                        if (s1[start] == s2[idx2]) {
                            idx2--;
                        }

                        start--;
                    }

                    start++; idx2++;

                    if (end - start < len) {
                        len = end - start;
                        result = s.substring(start, end);
                        // System.out.println(len + " " + result);
                    }

                    idx1 = end;
                }
            }
            idx1++;
        }
        return result;
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
            System.out.println(mws.minWindow(s1[i] , s2[i]));
        }
    }
}

