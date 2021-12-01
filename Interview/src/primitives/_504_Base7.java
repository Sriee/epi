package primitives;

public class _504_Base7 {

    private String base7(int num) {
        if (num == 0)
            return "0";

        StringBuilder sb = new StringBuilder();
        boolean isNegative = false;
        if (num < 0) {
            isNegative = true;
            num = num * -1;
        }

        while (num != 0) {
            sb.append(num % 7);
            num /= 7;
        }
        if (isNegative)
            sb.append("-");

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        _504_Base7 b7 = new _504_Base7();

        System.out.println(b7.base7(100));
        System.out.println(b7.base7(-7));
    }
}
