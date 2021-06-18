package heaps;

import java.util.Arrays;

public class _973_KClosest {

    // Quick Select Template
    public int[][] kClosest(int[][] points, int k) {
        quickSelect(points, 0, points.length - 1, k);
        int[][] res = new int[k][2];

        System.arraycopy(points, 0, res, 0, k);
        return res;
    }

    private void quickSelect(int[][] points, int left, int right, int k) {
        while (left < right) {
            int partitionPos = partition(points, left, right);

            if (partitionPos == k - 1)
                return;
            else if (partitionPos < k - 1)
                left = partitionPos + 1;
            else
                right = partitionPos - 1;
        }
    }

    private int partition(int[][] points, int left, int right) {
        int pivotIdx = left + (right - left) / 2, j = left;
        int refDistance = calculateDistance(points[pivotIdx]);
        swap(points, pivotIdx, right);

        for (int i = left; i <= right; i++) {
            if (calculateDistance(points[i]) < refDistance) {
                swap(points, i, j);
                j++;
            }
        }

        swap(points, right, j);
        return j;
    }

    private int calculateDistance(int[] point) {
        return (point[0] * point[0]) + (point[1] * point[1]);
    }

    private void swap(int[][] points, int first, int second) {
        if (first == second)
            return;

        int[] temp = points[first];
        points[first] = points[second];
        points[second] = temp;
    }

    public static void main(String[] args) {
        _973_KClosest kc = new _973_KClosest();
        int[][] points = {{1, 3}, {-2, 2}};
        System.out.println(Arrays.deepToString(kc.kClosest(points, 1)));

        points = new int[][]{{3, 3}, {5, -1}, {-2, 4}};
        System.out.println(Arrays.deepToString(kc.kClosest(points, 2)));
    }
}
