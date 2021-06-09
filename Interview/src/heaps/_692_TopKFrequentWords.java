package heaps;

import java.util.*;

class _692_TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();

        for (String w : words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (a, b) -> a.getValue().equals(b.getValue()) ?
                        b.getKey().compareTo(a.getKey()) :
                        a.getValue().compareTo(b.getValue())
        );

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry);

            if (pq.size() > k)
                pq.poll();
        }

        LinkedList<String> res = new LinkedList<>();
        while (!pq.isEmpty())
            res.addFirst(pq.poll().getKey());

        return res;
    }

    public static void main(String[] args) {
        _692_TopKFrequentWords tkfw = new _692_TopKFrequentWords();
        String[][] inputs = {
                {"i", "love", "leetcode", "i", "love", "coding"},
                {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}
        };
        int[] ks = {2, 4};

        for (int i = 0; i < ks.length; i++) {
            System.out.println(tkfw.topKFrequent(inputs[i], ks[i]));
        }
    }
}