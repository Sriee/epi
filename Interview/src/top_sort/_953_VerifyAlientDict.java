package top_sort;

import java.util.Arrays;

public class _953_VerifyAlientDict {

    /**
     * TC: O(m + n) where m = total number of characters in words
     *              & n = number of characters in "order"
     * but n = 26 always so TC = O(m + 26) = O(m)
     *
     * SC: O(1)
     */
    public boolean isAlienSorted(String[] words, String order) {
        int[] orderMap = new int[26];
        for (int i = 0; i < order.length(); i++) {
            orderMap[order.charAt(i) - 'a'] = i;
        }

        for (int i = 1; i < words.length; i++) {
            String curr = words[i - 1];
            String next = words[i];

            int len = Math.min(curr.length(), next.length());
            int j = 0;
            for (; j < len; j++) {
                if (curr.charAt(j) != next.charAt(j)) {
                    if (orderMap[curr.charAt(j) - 'a'] > orderMap[next.charAt(j) - 'a'])
                        return false;

                    break;
                }

                if (j < curr.length() - 1 && j == next.length() - 1)
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        _953_VerifyAlientDict vad = new _953_VerifyAlientDict();
        String[][] words = {
                {"alpha", "bravo", "charlie", "delta"},
                {"apple", "app"},
                {"app", "app"},
                {"martian"},
                {"jupyter", "ascending"},
                {"passengers", "to", "the", "unknown"}
        };
        String[] order = {
                "abcdefghijklmnopqrstuvwxyz",
                "abcdefghijklmnopqrstuvwxyz",
                "abcdefghijklmnopqrstuvwxyz",
                "mabcdefghijklnopqrstuvwxyz",
                "jabcdefghiklmnopqrstuvwxyz",
                "ptuhabcdefghijklmnoqrsvwxyz"
        };
        for (int i = 0; i < order.length; i++) {
            System.out.print(i + 1);
            System.out.print(".\tWords : " + Arrays.toString(words[i]));
            System.out.print("\n\tOrder : " + order[i]);
            System.out.println("\n\tAlien Dictionary verified: " + vad.isAlienSorted(words[i], order[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

}
