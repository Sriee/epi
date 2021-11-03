package bitwise;

import java.util.*;
import java.util.stream.Collectors;

public class _271_EncodeDecodeStrings {
    public static String lengthToBytes(String x) {
        int length = x.length();
        StringBuilder bytes = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            bytes.append((char) (length >> (i * 8)));
        }

        return bytes.reverse().toString();
    }

    public static int bytesToLength(String bytesString) {
        int result = 0;

        for (char c : bytesString.toCharArray()) {
            result = result * 256 + c;
        }

        return result;
    }

    /**
     * TC: O(m) where m is the number of characters in the strings
     * SC: O(m)
     */
    public static String encode(List<String> strings) {
        StringBuilder encodedString = new StringBuilder();

        for (String x : strings) {
            encodedString.append(lengthToBytes(x)).append(x);
        }

        return encodedString.toString();
    }

    /**
     * TC: O(m) where m is the number of characters in the strings
     * SC: O(m)
     */
    public static List<String> decode(String str) {
        int i = 0;
        List<String> decodedString = new ArrayList<>();

        while (i < str.length()) {
            int length = bytesToLength(str.substring(i, i + 4));
            i += 4;
            decodedString.add(str.substring(i, i + length));
            i += length;
        }

        return decodedString;
    }

    public static void printEncoded(String str) {
        StringBuilder finalStr = new StringBuilder();
        int i = 0;

        while (i < str.length()) {
            for (char c : str.substring(i, i + 4).toCharArray()) {
                finalStr.append((int) c);
            }
            int length = bytesToLength(str.substring(i, i + 4));
            i += 4;
            finalStr.append(str.substring(i, i + length));
            i += length;
        }

        System.out.println(finalStr.toString());
    }

    public static void main(String[] args) {
        List<List<String>> input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList("I", "love", "educative")));
        input.add(new ArrayList<>(Arrays.asList("6^Hello_5", "5_World^6")));
        input.add(new ArrayList<>(Arrays.asList("I", "love", "programming")));
        input.add(new ArrayList<>(Arrays.asList("a", "b", "c", "d")));
        input.add(new ArrayList<>(Arrays.asList("*_*EDUCATIVE*_")));

        for (int i = 0; i < input.size(); i++) {
            String encoded = encode(input.get(i));
            System.out.println((i + 1) + ".\tInput = " + input.get(i).stream().map(s -> "\"" + s + "\"").collect(Collectors.joining(", ", "[", "]")));
            System.out.print("\tEncoded string = ");
            printEncoded(encoded);
            System.out.println("\tOutput = " + decode(encoded).stream().map(s -> "\"" + s + "\"").collect(Collectors.joining(", ", "[", "]")));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}