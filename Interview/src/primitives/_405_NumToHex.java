package primitives;

public class _405_NumToHex {

    public String decimalToHex(int num) {
        if (num == 0)
            return "0";

        StringBuilder sb = new StringBuilder();
        char[] literals = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

        while (num != 0) {
            int idx = num & 15;
            sb.append(literals[idx]);
            num = num >>> 4;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        _405_NumToHex nh = new _405_NumToHex();

        System.out.println(nh.decimalToHex(26));
        System.out.println(nh.decimalToHex(-1));
    }
}
