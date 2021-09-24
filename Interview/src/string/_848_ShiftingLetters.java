package string;

public class _848_ShiftingLetters {

    /**
     * This approach uses extra space to handle wrapping around alphabets. This is not the fastest solution.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public String shiftingLetters(String s, int[] shifts) {
        int toShift = 0, i = 0, counter = 0;
        char[] srcArr = s.toCharArray();
        char[] alphabets = new char[52];

        for (i = 0; i < 26; i++) alphabets[i] = (char) ('a' + counter++);
        counter = 0;
        for (; i < 52; i++) alphabets[i] = (char) ('a' + counter++);

        for (i = shifts.length - 1; i >= 0; i--) {
            toShift += shifts[i];
            toShift %= 26;
            srcArr[i] = alphabets[(char) (srcArr[i] - 'a' + toShift)];
        }

        return String.valueOf(srcArr);
    }

    public static void main(String[] args) {
        _848_ShiftingLetters sl = new _848_ShiftingLetters();
        String[] inputs = {
            "abc",
            "rpl",
            "mkgfzkkuxownxvfvxasy"
        };
        int[][] shifts = {
            {3, 5, 9},
            {1, 2, 3},
            {
                505870226, 437526072, 266740649, 224336793, 532917782, 311122363, 567754492, 595798950,
                81520022, 684110326, 137742843, 275267355, 856903962, 148291585, 919054234, 467541837,
                622939912,
                116899933, 983296461, 536563513
            }
        };

        for (int i = 0; i < inputs.length; i++) {
            System.out.println(sl.shiftingLetters(inputs[i], shifts[i]));
        }
    }
}
