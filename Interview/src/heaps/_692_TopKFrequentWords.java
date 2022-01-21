package heaps;

import java.util.*;

class _692_TopKFrequentWords {

    /* ========================================================================
     * Approach 1: Min Heap
     * ========================================================================
     *
     * TC: O (n) - To construct the frequency map
     *   + O(n log k) - To add unique words to the min-heap
     *   + O(k log k) - Comparator sorting when words are polled out of min-heap
     *   since k << n && (n log k) > O(n), the total TC = O(n log k)
     *
     * SC: O(n) - result list
     *   + O(k) - heap size
     *   since k << n, the total SC = O(n)
     */
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

    /* ===========================================================================================
     * Approach 2: Quick Select
     * ===========================================================================================
     * The number of steps in quick select is considerably larger and need additional creation of
     * arrays. Prefer the bucket sort + trie approach for this problem.
     *
     * TC: O (n) - To construct the frequency map
     *   + O(n) - To form unique String[]
     *   + O(n) - Quick select
     *   + O(2n) - Arrays.copyOfRange
     *   + O(n log n) - Sort unique array
     *   TC = O(n log n).
     *
     * SC: O(n + 2n + n + k) - freqMap, unique array, sort, and result
     *   Total SC = O(n)
     */
    Map<String, Integer> freqMap;

    public List<String> topKFrequentQS(String[] words, int k) {
        freqMap = new HashMap<>();

        for (String w : words)
            freqMap.put(w, freqMap.getOrDefault(w, 0) + 1);

        int n = freqMap.size(), j = 0;
        String[] unique = new String[n];
        for (String w : freqMap.keySet())
            unique[j++] = w;

        quickSelect(unique, 0, n - 1, n - k);

        j = n - k;
        while (j >= 0 && freqMap.get(unique[j]).equals(freqMap.get(unique[n - k]))) {
            j--;
        }
        unique = Arrays.copyOfRange(unique, j + 1, n);

        Arrays.sort(unique, (a, b) -> {
            int aF = freqMap.get(a), bF = freqMap.get(b);
            if (aF == bF)
                return a.compareTo(b);
            return Integer.compare(bF, aF);
        });

        return new ArrayList<>(Arrays.asList(Arrays.copyOfRange(unique, 0, k)));
    }

    private void quickSelect(String[] words, int left, int right, int nk) {
        while (left < right) {
            int partitionPos = partition(words, left, right);

            if (partitionPos == nk)
                return;
            else if (partitionPos < nk)
                left = partitionPos + 1;
            else
                right = partitionPos - 1;
        }
    }

    private int partition(String[] words, int left, int right) {
        int pivotIdx = left + (right - left) / 2, j = left;
        int pivotFreq = freqMap.get(words[pivotIdx]);

        swap(words, pivotIdx, right);

        for (int i = left; i <= right; i++) {
            if (freqMap.get(words[i]) < pivotFreq) {
                swap(words, i, j);
                j++;
            }
        }

        swap(words, right, j);
        return j;
    }

    private void swap(String[] words, int first, int second) {
        if (first == second)
            return;

        String temp = words[first];
        words[first] = words[second];
        words[second] = temp;
    }

    public static void main(String[] args) {
        _692_TopKFrequentWords tkfw = new _692_TopKFrequentWords();
        String[][] inputs = {
                {"i", "love", "leetcode", "i", "love", "coding"},
                {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}
        };
        int[] ks = {2, 4};
        System.out.println(tkfw.topKFrequent(inputs[0], ks[0]));
        System.out.println(tkfw.topKFrequentQS(inputs[1], ks[1]));
    }
}