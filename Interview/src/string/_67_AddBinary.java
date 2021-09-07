package string;

class _67_AddBinary {
    public String addBinary(String a, String b) {
        if (a.length() < b.length())
            return addBinary(b, a);

        char carry = '0';
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;

        for (int j = b.length() - 1; j >= 0; i--, j--)
            carry = add(a.charAt(i), b.charAt(j), carry, sb);

        for (; i >= 0; i--)
            carry = add(a.charAt(i), '0', carry, sb);

        if (carry != '0')
            sb.append("1");

        return sb.reverse().toString();
    }

    private char add(char a, char b, char carry, StringBuilder sb) {
        if (carry == '0') {
            if (a == '1' && b == '1') {
                sb.append("0");
                carry = '1';
            } else if (a == '1' || b == '1') {
                sb.append("1");
                carry = '0';
            } else {
                sb.append("0");
                carry = '0';
            }
        } else {
            if (a == '0' && b == '0') {
                sb.append("1");
                carry = '0';
            } else if (a == '1' && b == '1') {
                sb.append("1");
                carry = '1';
            } else {
                sb.append("0");
                carry = '1';
            }
        }

        return carry;
    }

    public static void main(String[] args) {
        _67_AddBinary ab = new _67_AddBinary();

        System.out.println(ab.addBinary("11", "1"));
        System.out.println(ab.addBinary("1101", "1010"));
    }
}