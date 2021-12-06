package array;

import java.util.*;

public class _315_CountSmallerThanSelf {

    /**
     * This is a variation of merge sort.
     *
     * TC - O(n log n)
     * SC - O(n)
     */
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int[] indices = new int[n], count = new int[n];

        for (int i = 0; i < n; i++)
            indices[i] = i;

        mergeSort(nums, indices, count, 0, n);

        List<Integer> result = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            result.add(count[i]);

        /*
         * Short form for converting int[] to List<Integer>. Not using it here, since it increases the run time by 4ms.
         * return Arrays.stream(count).boxed().toList();
         */
        return result;
    }

    private void mergeSort(int[] nums, int[] indices, int[] count, int start, int end) {
        // I am array Stack overflow for (start >= end) the following condition seems to work
        if (end - start <= 1)
            return;

        int mid = start + (end - start) / 2;
        mergeSort(nums, indices, count, start, mid);

        /*
         * We are not using [start, mid] & [mid + 1, end] because while iterating in merge array it becomes easier
         */
        mergeSort(nums, indices, count, mid, end);
        merge(nums, indices, count, start, mid, end);
    }

    private void merge(int[] nums, int[] indices, int[] count, int start, int mid, int end) {
        int i = start, j = mid, k = 0, len = end - start;
        int[] temp = new int[len]; // To store the indices of the sorted sub array

        while (i < mid && j < end) {
            if (nums[indices[i]] <= nums[indices[j]]) {

                /*
                 * To find the number of elements to the left of this element we calculate j - mid. Why?
                 * if there were elements smaller than num[i], they would be part of the sorted array (temp) and
                 * the right sub array is in [mid, right] range. We start iterating j from mid, so (j - mid) will give
                 * us that number.
                 *
                 * For example:
                 *  [2, 5, 7]   [1, 8]
                 *   i         mid  j
                 *  temp = [1, ]
                 */
                count[indices[i]] += j - mid;
                temp[k++] = indices[i];
                i++;
            } else {
                temp[k++] = indices[j];
                j++;
            }
        }

        while (i < mid) {
            count[indices[i]] += j - mid;
            temp[k++] = indices[i];
            i++;
        }

        while (j < end) {
            temp[k++] = indices[j];
            j++;
        }

        // The indices array need to be updated to hold the correct order of the sorted sub array
        System.arraycopy(temp, 0, indices, start, len);
    }

    public static void main(String[] args) {
        _315_CountSmallerThanSelf cst = new _315_CountSmallerThanSelf();
        System.out.println(cst.countSmaller(new int[] {5, 2, 6 ,1}));
        System.out.println(cst.countSmaller(new int[] {21, 4, 5, 15, 11, 0, 19, 13}));
    }
}
