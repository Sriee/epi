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

    /* *
     * This version of the code is not working. Need to figure out the reason for the failures.
     * /
    private List<String> findRepeatedSequences(String s, int k) {
        Set<Long> seen = new HashSet<>();
        Set<String> out = new HashSet<>();
        long prime = 31, pow = 1L, hash = 0L;


        int m = (int) (1e9 + 9);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < k; i++) {
            char ch = s.charAt(i);
            sb.append(ch);
            hash = (hash + (ch - 'A' + 1) * pow) % m;
            pow = (pow * prime) % m;
        }

        seen.add(hash);

        for (int i = k; i < s.length() - k; i++) {
            char ch = s.charAt(i);
            long nextHash = (((hash - sb.charAt(0) * pow) * prime) + (ch - 'A' + 1)) % m;

            if (seen.contains(nextHash)) {
                out.add(new String(sb));
            }

            sb.deleteCharAt(0);
            sb.append(ch);
            seen.add(nextHash);
        }
        List<String> res = new ArrayList<>();

        for (String str : out)
            res.add(str);

        return res;
    }
    */

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
