package string;

import java.util.Arrays;

public class _500_KeyBoardRow {

    int[] table = {
        2, 3, 3, 2, 1,
        2, 2, 2, 1, 2,
        2, 2, 3, 3, 1,
        1, 1, 1, 2, 1,
        1, 3, 1, 3, 1,
        3
    };

    public String[] findWords(String[] words) {
        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            int row = -1; boolean skip = false;
            for (int i = 0; i < word.length() && !skip; i++) {
                char ch = Character.toLowerCase(word.charAt(i));
                if (row == -1)
                    row = table[ch - 'a'];
                else if (row != table[ch - 'a'])
                    skip = true;
            }

            if (!skip) {
                sb.append(word).append(",");
            }
        }

        return sb.isEmpty() ? new String[] {} : sb.toString().split(",");
    }

    public static void main(String[] args) {
        _500_KeyBoardRow kr = new _500_KeyBoardRow();

        System.out.println(Arrays.toString(kr.findWords(new String[] {"Hello", "Alaska", "Dad", "Peace"})));
        System.out.println(Arrays.toString(kr.findWords(new String[] {"omk"})));
        System.out.println(Arrays.toString(kr.findWords(new String[] {"adsdf", "sfd"})));
    }
}