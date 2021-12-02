package primitives;

public class _13_RomanToInt {

    private int romanToInt(String s) {
        int value = 0, i = 0, n = s.length();

        while (i < n) {
            switch (s.charAt(i)) {
                case 'I':
                    if (i != n - 1 && s.charAt(i + 1) == 'V') {
                        value += 4;
                        i += 2;
                    } else if (i != n - 1 && s.charAt(i + 1) == 'X') {
                        value += 9;
                        i += 2;
                    } else {
                        value += 1;
                        i += 1;
                    }
                    break;
                case 'V':
                    value += 5;
                    i += 1;
                    break;
                case 'X':
                    if (i != n - 1 && s.charAt(i + 1) == 'L') {
                        value += 40;
                        i += 2;
                    } else if (i != n - 1 && s.charAt(i + 1) == 'C') {
                        value += 90;
                        i += 2;
                    } else {
                        value += 10;
                        i += 1;
                    }
                    break;
                case 'L':
                    value += 50;
                    i += 1;
                    break;
                case 'C':
                    if (i != n - 1 && s.charAt(i + 1) == 'D') {
                        value += 400;
                        i += 2;
                    } else if (i != n - 1 && s.charAt(i + 1) == 'M') {
                        value += 900;
                        i += 2;
                    } else {
                        value += 100;
                        i += 1;
                    }
                    break;
                case 'D':
                    value += 500;
                    i += 1;
                    break;
                case 'M':
                    value += 1000;
                    i += 1;
                default:
                    value += 0;
                    break;
            }
        }

        return value;
    }

    public static void main(String[] args) {
        _13_RomanToInt rti = new _13_RomanToInt();

        System.out.println(rti.romanToInt("XXXVI"));
        System.out.println(rti.romanToInt("MDCCCLXXXIV"));
        System.out.println(rti.romanToInt("MMXII"));
    }
}
