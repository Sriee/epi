package string;

import java.util.*;

public class _763_PartitionLabel {
    public List<Integer> partitionLabels(String s) {
        Map<Integer, Integer> map = new TreeMap<>();
        int[] table = new int[26];
        int partition = 0;

        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';

            if (table[idx] == 0) {
                table[idx] = ++partition;
            } else if (table[idx] < partition){
                partition = table[idx];
                for (int j = 0; j < 26; j++) {
                    if (table[j] <= partition)
                        continue;
                    map.remove(table[j]);
                    table[j] = partition;
                }
            }
            map.put(partition, i);
        }

        List<Integer> res = new ArrayList<>();
        int prev = -1;
        for (Integer key : map.keySet()) {
            int val = map.get(key);
            res.add(val - prev);
            prev = val;
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
