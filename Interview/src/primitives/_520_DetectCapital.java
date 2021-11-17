package primitives;

public class _520_DetectCapital {

    public boolean detectCapitalUse(String word) {
        int n = word.length();
        boolean last = isSmall(word.charAt(n - 1));

        for (int i = n - 2; i > 0; i--) {
            boolean curr = isSmall(word.charAt(i));
            if (curr != last)
                return false;
        }

        return last == true ? last : !isSmall(word.charAt(0));
    }

    private boolean isSmall(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    public static void main(String[] args) {
        _520_DetectCapital dc = new _520_DetectCapital();

        for (String s : new String[] {"India", "CanaDa", "DON", "dooder"}) {
            System.out.println(dc.detectCapitalUse(s));
        }
    }
}
