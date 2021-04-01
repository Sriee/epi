package leet;

public class AddBinary {

    private static String add(String a, String b) {

        if (a == null || a.length() == 0)
            return b;
        if (b == null || b.length() == 0)
            return a;
        StringBuilder sb = new StringBuilder();
        String large = "", small = "", carry = "0";
        if (a.length() > b.length()) {
            large = a;
            small = b;
        } else {
            large = b;
            small = a;
        }
        int l = large.length() - 1, s = small.length() - 1;

        while (s >= 0) {
            if (carry.equals("0")) {
                if (large.substring(l, l + 1).equals("0") && small.substring(s, s + 1).equals("0")) {
                    sb.insert(0, "0");
                    carry = "0";
                } else if (large.substring(l, l + 1).equals("1") && small.substring(s, s + 1).equals("1")) {
                    sb.insert(0, "0");
                    carry = "1";
                } else {
                    sb.insert(0, "1");
                    carry = "0";
                }
            } else {
                if (large.substring(l, l + 1).equals("0") && small.substring(s, s + 1).equals("0")) {
                    sb.insert(0, "1");
                    carry = "0";
                } else if (large.substring(l, l + 1).equals("1") && small.substring(s, s + 1).equals("1")) {
                    sb.insert(0, "1");
                    carry = "1";
                } else {
                    sb.insert(0, "0");
                    carry = "1";
                }
            }
            s--;
            l--;
        }

        while (l >= 0) {
            if (carry.equals("0") && large.substring(l, l + 1).equals("0")) {
                sb.insert(0, "0");
                carry = "0";
            } else if (carry.equals("1") && large.substring(l, l + 1).equals("1")) {
                sb.insert(0, "0");
                carry = "1";
            } else {
                sb.insert(0, "1");
                carry = "0";
            }
            l--;
        }

        if (carry.equals("1"))
            sb.insert(0, carry);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(add("100", "110010"));

    }

}
