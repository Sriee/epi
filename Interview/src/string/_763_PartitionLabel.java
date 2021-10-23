package string;

import java.util.*;

public class _763_PartitionLabel {

    /**
     * Improve time complexity by removing the usage of tree map
     */
    public List<Integer> partitionLabels(String s) {
        int partition = 0, n = s.length(), prev = -1, max = 0;
        int[] table = new int[26], map = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';

            if (table[idx] == 0) {
                table[idx] = ++partition;
            } else if (table[idx] < partition){
                partition = table[idx];
                for (int j = 0; j < 26; j++) {
                    if (table[j] <= partition)
                        continue;
                    map[table[j]] = 0;
                    table[j] = partition;
                }
            }
            map[partition] = i;
        }

        // find max partition
        for (int j = 1; j <= n; j++) {
            if (map[j] != 0)
                max = j;
        }

        // Calculate result
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= max; i++) {
            res.add(map[i] - prev);
            prev = map[i];
        }

        return res;
    }

    public static void main(String[] args) {
        _763_PartitionLabel pl = new _763_PartitionLabel();
        String[] input = {
            "ababcbacadefegdehijhklij", // [9,7,8]
            "jybmxfgseq",               // [1,1,1,1,1,1,1,1,1,1]
            "eccbbbbdec"                // [10]
        };

        for (String s : input) {
            List<Integer> lst = pl.partitionLabels(s);
            System.out.println(lst);
        }
    }
}
