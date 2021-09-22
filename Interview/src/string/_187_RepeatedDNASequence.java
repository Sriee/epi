package string;

import java.util.*;

public class RepeatedDNASequence {

    public static List<String> findRepeatedSequences(String s, int k) {
        int len = s.length();
        if (len <= k)
            return new ArrayList<>();

        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 1); map.put('C', 2); map.put('G', 3); map.put('T', 4);

        Set<Integer> seen = new HashSet<>();
        Set<String> output = new HashSet<>();
        int base = 4, prevHash = 0, currHash = 0, pow = 1;

        // Calculate initial hash
        for (int i = k - 1; i >= 0; i--) {
            prevHash += (map.get(s.charAt(i)) * pow);
            pow *= base;
        }
        pow /= base;
        seen.add(prevHash);

        for (int start = 1; start <= len - k; start++) {
            currHash = (prevHash - map.get(s.charAt(start - 1)) * pow) * base + map.get(s.charAt(start + k - 1));

            if (seen.contains(currHash))
                output.add(s.substring(start, start + k));

            seen.add(currHash);

            // Reset prevHash & currHash
            prevHash = currHash;
        }

        return new ArrayList<>(output);
    }

    public static void main(String[] args) {
        RepeatedDNASequence seq = new RepeatedDNASequence();
        String[] inputs = {
                "AAAAACCCCCAAAAACCCCCC",
                "GGGGGGGGGGGGGGGGGGGGGGGGG",
                "TTTTTCCCCCCCTTTTTTCCCCCCCTTTTTTT",
                "AAAAAACCCCCCCAAAAAAAACCCCCCCTG",
                "ATATATATATATATAT",
        };

        int[] ks = new int[] {8, 12, 10, 10, 6};
        int i = 0;

        // for (int i = 0; i < ks.length; i++) {
            List<String> res = seq.findRepeatedSequences(inputs[i], ks[i]);
//            List<String> res = seq.findRepeatedSequences("AGACCTAGAC", 3);
            for (String str : res) {
                System.out.print(str + " ");
            }
            System.out.println();
        //}
    }
}
