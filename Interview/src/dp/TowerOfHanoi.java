package dp;

import java.util.Arrays;

public class TowerOfHanoi {

    public void towerOfHanoi(char source, char destination, char extra, int n) {
        if (n <= 0) return;

        towerOfHanoi(source, extra, destination, n - 1);
        System.out.printf("Move Disc-%d from %c to %c\n", n, source, destination);
        towerOfHanoi(extra, destination, source, n - 1);
    }

    public void bubbleSort(int[] arr, int n) {
        if (n <= 1)
            return;

        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1])
                swap(arr, i, i + 1);
        }

        bubbleSort(arr, n - 1);
    }

    public void quickSort(int[] nums) {
        sortRange(nums, 0, nums.length - 1);
    }

    private void sortRange(int[] nums, int first, int last) {
        if (first < last) {
            int position = partition(nums, first, last);
            sortRange(nums, first, position - 1);
            sortRange(nums, position + 1, last);
        }
    }

    private int partition(int[] nums, int l, int r) {
        while (l < r) {
            if (nums[l] < nums[r]) {
                r--;
            } else if (nums[l + 1] <= nums[l]) {
                swap(nums, l + 1, l);
                l++;
            } else {
                swap(nums, l + 1, r);
            }
        }
        return l;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        TowerOfHanoi toh = new TowerOfHanoi();
        toh.towerOfHanoi('s', 'd', 'e', 2);

        // Bubble Sort
        int[] nums = {9, 6, 2, 12, 11, 11, 9, 3, 7};
//        toh.bubbleSort(nums, nums.length);
        toh.quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
