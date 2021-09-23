package string;

public class _1844_ReplaceDigits {
    public String replaceDigits(String s) {
        char[] charArr = s.toCharArray();

        for (int i = 1; i < s.length(); i += 2) {
            charArr[i] = (char) (charArr[i] + charArr[i - 1] - '0');
        }

        return new String(charArr);
    }

    public static void main(String[] args) {
        _1844_ReplaceDigits rd = new _1844_ReplaceDigits();
        for (String s : new String[] {"a1c1e1", "a1b2c3d4e", "a0"}) {
            System.out.println(rd.replaceDigits(s));
        }

        System.out.println("agghsdkjfhkdsfyweury".length());
    }
}
