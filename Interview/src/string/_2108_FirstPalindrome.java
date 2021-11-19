package string;

public class _2108_FirstPalindrome {
    public String firstPalindrome(String[] words) {
        for (String word : words) {
            if (isPalindrome(word))
                return word;
        }
        return "";
    }

    private boolean isPalindrome(String s) {
        for(int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j))
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        _2108_FirstPalindrome fp = new _2108_FirstPalindrome();

        System.out.println(fp.firstPalindrome(new String[] {"abc", "car", "ada", "racecar", "cool"}));
        System.out.println(fp.firstPalindrome(new String[] {"notapalindrome", "racecar"}));
        System.out.println(fp.firstPalindrome(new String[] {"def", "ghi"}));
    }
}
