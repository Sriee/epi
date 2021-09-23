package string;

class _76_MinWindowSubString {

    public String minWindow(String s, String t) {
        int[] table = new int[128];
        int counter = t.length(), begin = 0, end = 0, minStart = 0, len = Integer.MAX_VALUE;

        // Initialize the frequency table
        for (char ch : t.toCharArray())
            table[ch]++;    // Don't need to do idx = ch - 'a'. We can use char as index to our freq table

        while (end < s.length()) {
            char ech = s.charAt(end);
            table[ech]--;

            // Decrement the count for the character
            if (table[ech] >= 0) counter--;

            end++;

            // When counter reaches zero, we know that t has every character of s
            while (counter == 0) {

                // Logic to store the result.
                if (end - begin < len) {
                    len = end - begin;
                    minStart = begin;
                }

                // Trick: incrementing the table value as we slide right
                // This trick avoids initializing counters for a window again !
                char sch = s.charAt(begin);
                table[sch]++;

                if (table[sch] > 0)
                    counter++;

                begin++;
                /*
                 * Short hand for the above 5 lines
                if ( ++table[ s.charAt(start++) ] > 0)
                    counter++;
                */
            }
        }

        return len == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + len);
    }

    public static void main(String[] args) {
        _76_MinWindowSubString mws = new _76_MinWindowSubString();

        String[] s = {"ADOBECODEBANC", "aabcdebadd", "ABDFGDCKAB"};
        String[] t = {"ABC", "abd", "ABCD"};
        for (int i = 0; i < s.length; i++) {
            System.out.println(mws.minWindow(s[i], t[i]));
        }
    }
}