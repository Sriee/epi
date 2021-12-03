package primitives;

public class _13_RomanToInt {

    /**
     * Simplified version of Roman to Integers
     *
     * How to handle the edge cases 4, 9, 40, 90, 400, 900?
     *   i. Iterate from right to left
     *  ii. For normal cases, the value keeps increasing as we progress from right to left, but for edge cases, the
     *  value at i will be less than value at (i + 1).
     */
    public int romanToInt(String s) {
        int sum = 0, prev = 0, curr = 0;

        for (int i = s.length() - 1; i >=0; i--) {
            switch(s.charAt(i)) {
                case 'I':
                    curr = 1;
                    break;
                case 'V':
                    curr = 5;
                    break;
                case 'X':
                    curr = 10;
                    break;
                case 'L':
                    curr = 50;
                    break;
                case 'C':
                    curr = 100;
                    break;
                case 'D':
                    curr = 500;
                    break;
                case 'M':
                    curr = 1000;
                    break;
                default:
                    curr = 0;
                    break;
            }

            // Handling edge case with previous and current value check
            if (prev > curr)
                sum -= curr;
            else
                sum += curr;

            prev = curr;
        }

        return sum;
    }

    public static void main(String[] args) {
        _13_RomanToInt rti = new _13_RomanToInt();

        System.out.println(rti.romanToInt("XXXVI"));
        System.out.println(rti.romanToInt("MDCCCLXXXIV"));
        System.out.println(rti.romanToInt("MMXII"));
    }
}
