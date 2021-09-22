package string;

import java.util.*;

public class _187_RepeatedDNASequence {

    public List<String> findRepeatedDnaSequences(String s, int k) {
        int n = s.length();
        if (n < k)
            return new ArrayList<>();

        if (n == k) {
            List<String> res = new ArrayList<String>();
            res.add(s);
            return res;
        }

        int base = 4, pow = 1;
        long prevHash = 0, currHash = 0;
        Set<Long> seen = new HashSet<>();
        Set<String> output = new HashSet<>();

        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 1);
        map.put('C', 2);
        map.put('G', 3);
        map.put('T', 4);

        for (int i = 9; i >= 0; i--) {
            prevHash += (map.get(s.charAt(i)) * pow);
            pow *= base;
        }
        pow /= base;
        seen.add(prevHash);

        for (int i = 1; i < n - 9; i++) {
            currHash = ((prevHash - map.get(s.charAt(i - 1)) * pow) * base) + map.get(s.charAt(i + 9));
            if (seen.contains(currHash)) {
                output.add(s.substring(i, i + 10));
            }

            seen.add(currHash);
            prevHash = currHash;
        }

        return new ArrayList<>(output);
    }

    public static void main(String[] args) {
        _187_RepeatedDNASequence seq = new _187_RepeatedDNASequence();
        String[] inputs = {
                "AAAAACCCCCAAAAACCCCCC",
                "GGGGGGGGGGGGGGGGGGGGGGGGG",
                "TTTTTCCCCCCCTTTTTTCCCCCCCTTTTTTT",
                "AAAAAACCCCCCCAAAAAAAACCCCCCCTG",
                "ATATATATATATATAT",
                "AAAAAAAAAAA"
        };
        int[] ks = new int[]{8, 12, 10, 10, 6, 10};

        for (int i = 0; i < ks.length; i++) {
            List<String> res = seq.findRepeatedDnaSequences(inputs[i], ks[i]);
            for (String str : res) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
    }
}
