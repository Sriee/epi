package string;

import java.util.Arrays;

public class _186_ReverseString {

    private void reverse(char[] arr, int start, int end) {
        if (start >= arr.length)
            return;

        char temp;
        while (start < end) {
            temp = arr[start];
            arr[start++] = arr[end];
            arr[end--] = temp;
        }

        // System.out.println(Arrays.toString(arr));
    }

    public void reverseWords(char[] s) {
        reverse(s, 0, s.length - 1);
        int start = 0, i;

        for (i = 0; i < s.length; i++) {
            if (s[i] == ' ') {
                reverse(s, start, i - 1);
                start = i + 1;
            }
        }

        // Last token
        reverse(s, start, i - 1);
    }

    public static void main(String[] args) {
        _186_ReverseString r = new _186_ReverseString();
        char[] arr;

        // 1
        arr = new char[]{'b', 'a', 'l', 'a', ' ', 'l', 'a', 'q', ' ', 's', 'a', 'i', ' ', 'k', 'a', ' ', 'd',
                'j', 'i', 'n'};
        r.reverseWords(arr);
        System.out.println(Arrays.toString(arr));

        // 2
        arr = new char[]{'t', 'h', 'e', ' ', 's', 'k', 'y', ' ', 'i', 's', ' ', 'b', 'l', 'u', 'e'};
        r.reverseWords(arr);
        System.out.println(Arrays.toString(arr));
    }
}
