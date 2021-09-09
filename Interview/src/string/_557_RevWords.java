package string;

class _557_RevWords {
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int n = s.length();

        /*
         * This technique of using multiple reverse in a method is used in other
         * reverse string problems.
         */
        for (int begin = 0, end = 0; end < n; end++) {
            begin = end;
            while (end < n && chars[end] != ' ')
                end++;

            reverse(chars, begin, end - 1);
        }

        return String.valueOf(chars);
    }

    private void reverse(char[] chars, int begin, int end) {
        while (begin < end) {
            char temp = chars[begin];
            chars[begin++] = chars[end];
            chars[end--] = temp;
        }
    }

    public static void main(String[] args) {
        _557_RevWords rw = new _557_RevWords();
        String[] sentences = {
                "Let's take LeetCode contest",
                "God Ding"
        };

        for (String s : sentences) System.out.println(rw.reverseWords(s));
    }
}