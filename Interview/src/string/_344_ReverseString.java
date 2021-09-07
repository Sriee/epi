package string;

import java.util.Arrays;

class _344_ReverseString {
    public void reverseString(char[] s) {
        int i = 0, j = s.length - 1;
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        _344_ReverseString rs = new _344_ReverseString();
        char[][] chars = {
                {'h', 'e', 'l', 'l', 'o'},
                {'H', 'a', 'n', 'n', 'a', 'h'}
        };

        for (char[] c : chars) {
            rs.reverseString(c);
            System.out.println(Arrays.toString(c));
        }
    }
}