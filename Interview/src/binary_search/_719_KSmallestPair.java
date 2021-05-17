package binary_search;

import java.util.*;

public class _719_KSmallestPair {
    private final Random rand = new Random();

    /*
     * Refer this video - https://www.youtube.com/watch?v=HiSvEhLIaTI to see why we are using a count array
     */
    public int smallestDistancePair(int[] arr, int k) {
        int result = -1, op = rand.nextInt(3) + 1;

        switch (op) {
            case 1:
                System.out.print("Tree Map count Approach: ");
                result = smallestDistancePair1(arr, k);
                break;
            case 2:
                System.out.print("Count Array Approach: ");
                result = smallestDistancePair2(arr, k);
                break;
            case 3:
                System.out.print("Binary Search + Sliding Window Approach: ");
                result = smallestDistancePair3(arr, k);
                break;
            case 4:
                System.out.print("Optimized Binary Search + Sliding Window Approach: ");
                result = smallestDistancePair4(arr, k);
                break;
        }

        return result;
    }

    /**
     * Modified TreeMap approach
     * <p>
     * TC: O(n log n) + O(n ^ 2) + O(log m) + O(m)
     * SC: O(m)
     * where m = m = arr[arr.length - 1] + 1
     * <p>
     * Solution TLE As expected.
     */
    public int smallestDistancePair1(int[] arr, int k) {
        // O(n log n)
        Arrays.sort(arr);

        // We don't need to store the pairs. It was just for our trial purposes. Replacing it with a simple counter.
        Map<Integer, Integer> countMap = new TreeMap<>();

        // O(n^2)
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int key = arr[j] - arr[i];

                // O (log m) where m = arr[arr.length - 1] + 1
                countMap.put(key, countMap.getOrDefault(key, 0) + 1);
            }
        }

        int num = 0;

        // Worst case: O(m)
        for (int count : countMap.keySet()) {
            num += countMap.get(count);
            if (num >= k)
                return count;
        }

        return -1;
    }

    /**
     * Count Array approach
     * <p>
     * TC: O(n log n) + O(n ^ 2) + O(m)
     * SC: O(m)
     * where m = m = arr[arr.length - 1] + 1
     * <p>
     * Solution accepted but the time complexity is huge.
     */
    public int smallestDistancePair2(int[] arr, int k) {
        // O(n log n)
        Arrays.sort(arr);
        int n = arr.length;

        // Range of our count = max(arr) - min(arr)
        // +1 to handle num[i] == num[j]  { 0 } or
        //              max(arr) - 0 { max(arr) }
        int[] count = new int[arr[n - 1] + 1];

        // O(n^2)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                count[arr[j] - arr[i]]++;
            }
        }

        int num = 0;

        // Worst case: O(m)
        for (int i = 0; i < count.length; i++) {
            num += count[i];
            if (num >= k)
                return i;
        }

        return -1;
    }

    /**
     * Binary Search + Sliding Window Approach.
     * <p>
     * TC: O(n log w) + O(n log n)
     * SC: O(1)
     */
    public int smallestDistancePair3(int[] arr, int k) {
        // O(n log n)
        Arrays.sort(arr);
        int lo = 0, hi = arr[arr.length - 1];

        /*
          Binary Search = O(log w) where w = [0, max(arr)]
          since we do sliding window for each log w operation total TC = O(n log w)
         */
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            // O(n) - sliding window
            int numPairs = findNumPairs(arr, mid);

            if (numPairs >= k)
                hi = mid;
            else
                lo = mid + 1;
        }

        return lo;
    }

    private int findNumPairs(int[] arr, int target) {
        int countPairs = 0, end = 1;

        for (int start = 0; start < arr.length; start++) {
            while (end < arr.length && arr[end] - arr[start] <= target)
                end++;

            countPairs += end - start - 1;
        }

        return countPairs;
    }

    /**
     * Optimized Binary Search + Sliding Window Approach.
     * <p>
     * TC: O(n log w) + O(n log n)
     * SC: O(1)
     */
    public int smallestDistancePair4(int[] arr, int k) {
        Arrays.sort(arr);
        int lo, hi, n = arr.length, count = 0, start = 0;

        // A neat trick to initialize variables. This avoids multiple int declaration inside the loop.
        for (lo = 0, hi = n - 1; lo < hi; count = 0, start = 0) {
            int mid = lo + (hi - lo) / 2;

            // The run time in OJ seems to be fast when we increment the start pointer instead of the end pointer
            for (int end = 0; end < n; end++) {
                while (arr[end] - arr[start] > mid)
                    start++;
                count += end - start;
            }

            if (count >= k)
                hi = mid;
            else
                lo = mid + 1;
        }

        return lo;
    }

    private void visualizeCountArray(int[] arr) {
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        Map<Integer, List<int[]>> countMap = new TreeMap<>();

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int key = arr[j] - arr[i];
                List<int[]> lst = countMap.getOrDefault(key, new ArrayList<>());
                lst.add(new int[]{arr[i], arr[j]});
                countMap.put(key, lst);
            }
        }

        for (int count : countMap.keySet()) {
            System.out.println(count + " " + Arrays.deepToString(countMap.get(count).toArray()));
        }
    }

    public void runner(int[] arr, int[] ks) {
        if (arr.length < 5)
            visualizeCountArray(arr);

        for (int k : ks) {
            System.out.println(smallestDistancePair(arr, k));
        }
    }

    public static void main(String[] args) {
        _719_KSmallestPair sp = new _719_KSmallestPair();
        int[] arr, ks;

        // 1
        arr = new int[]{39, 5, 6, 0, 8, 15, 20};
        ks = new int[]{0, 2, 11, 19};
        sp.runner(arr, ks);

        // 2
        arr = new int[]{3, 4, 1, 2, 5, 3, 7, 5};
        ks = new int[]{4, 15, 13};
        sp.runner(arr, ks);

        // 3
        arr = new int[]{1, 6, 1};
        ks = new int[]{2, 0};
        sp.runner(arr, ks);
    }
}
