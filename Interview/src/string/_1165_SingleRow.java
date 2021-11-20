package string;

public class _1165_SingleRow {
    public int calculateTime(String keyboard, String word) {
        int sum = 0;
        int[] table = new int[27];

        for (int i = 0; i < 26; i++) {
            table[keyboard.charAt(i) - 'a'] = i;
        }

        for (int i = 0, prevIdx = 0, currIdx = 26; i < word.length(); i++) {
            prevIdx = currIdx;
            currIdx = word.charAt(i) - 'a';

            sum += Math.abs(table[prevIdx] - table[currIdx]);
        }

        return sum;
    }

    public static void main(String[] args) {
        _1165_SingleRow sr = new _1165_SingleRow();

        System.out.println(sr.calculateTime("", "")); //
    }
}
