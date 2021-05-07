package binary_search;

import java.util.*;

public class _658_KClosest {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0, right = arr.length - k;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (x - arr[mid] > arr[mid + k] - x)
                left = mid + 1;
            else
                right = mid;
        }

        List<Integer> result = new ArrayList<>();
        while (k-- > 0)
            result.add(arr[left++]);

        return result;
    }

    public static void main(String[] args) {
        _658_KClosest kc = new _658_KClosest();
        int[] arr;
        List<Integer> result;

        // 1
        arr = new int[]{0, 1, 1, 1, 2, 3, 6, 7, 8, 9};
        result = kc.findClosestElements(arr, 4, -10);
        System.out.println(result);

        // 2
        arr = new int[]{1, 1, 1, 100, 100, 100};
        result = kc.findClosestElements(arr, 2, 9);
        System.out.println(result);

        // 3
        arr = new int[]{1, 1, 2, 2, 2, 2, 2, 3, 3};
        result = kc.findClosestElements(arr, 3, 3);
        System.out.println(result);
    }
}
