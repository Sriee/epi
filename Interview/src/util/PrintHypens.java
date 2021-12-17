package util;

public class PrintHypens {
    public static String generate() {
        return generate('-', 100);
    }

    public static String generate(char ch, int length) {
        return new String(new char[length]).replace('\0', ch);
    }
}
