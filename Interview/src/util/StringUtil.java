package util;

public class StringUtil {

    public static String toBinaryString(int number) {
        if (number == 0)
            return "0";

        StringBuilder binary = new StringBuilder();

        for (int i = 15; i >= 0; i--) {
            // Shift the number to the right by 'i' bits and check the least significant bit
            int bit = (number >> i) & 1;
            // Append the bit to the binary string
            binary.append(bit);
        }

        return binary.toString();
    }
}
