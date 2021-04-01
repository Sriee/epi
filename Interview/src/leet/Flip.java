package leet;

import java.util.Stack;

public class Flip {

    public static int flipBit(int number) {
        if (number == 1)
            return 0;
        if (number == 0 || number == 2)
            return 1;

        int mask = 2;

        while (mask <= number) {
            mask = mask << 1;
            if (mask <= 0)
                break;
        }

        System.out.println(mask);
        mask -= 1;
        System.out.println(mask);
        return (mask ^ number);
    }

    public static int calPoints(String[] ops) {
        int sum = 0, num, b;
        if (ops == null || ops.length == 0)
            return sum;
        Stack<Integer> stack = new Stack<>();
        for (String token : ops) {
            if (token.equals("C")) {
                sum -= stack.pop();
            } else if (token.equals("D")) {
                num = 2 * stack.peek();
                stack.push(num);
                sum += num;
            } else if (token.equals("+")) {
                b = stack.pop();
                num = b + stack.peek();
                sum += num;
                stack.push(b);
                stack.push(num);
            } else {
                num = Integer.parseInt(token);
                sum += num;
                stack.push(num);
            }
        }
        return sum;
    }

    /**
     * Decimal to Hexadecimal conversion Handles negative number also
     * 
     * @param num
     */
    public static void decimalToHex(int num) {
        StringBuilder sb = new StringBuilder();
        char literals[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

        while (num != 0) {
            sb.append(literals[num & 15]);
            num = num >>> 4;
        }
        System.out.println(sb.reverse().toString());
    }

    public static void main(String[] args) {
        System.out.println(calPoints(new String[] { "5", "2", "C", "D", "+" }));
        System.out.println(calPoints(new String[] { "5", "-2", "4", "C", "D", "9", "+", "+" }));
        System.out.println(calPoints(new String[] { "5" }));
        /*
         * System.out.println(flipBit(2147483647)); decimalToHex(-2);
         */
    }
}
