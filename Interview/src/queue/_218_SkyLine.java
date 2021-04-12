package queue;

import java.util.*;

public class _218_SkyLine {

    private List<List<Integer>> getSkyLine(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        List<int[]> heights = new ArrayList<>();

        // Format: [x, height, 0] -> Starting Edge
        // Format: [y, height, 1] -> Ending Edge
        for (int[] building : buildings) {
            heights.add(new int[]{building[0], building[2], 0});
            heights.add(new int[]{building[1], building[2], 1});
        }

        Collections.sort(heights, (a, b) -> {

            if (a[0] != b[0]) {
                // x-axis is different. Sort them in ascending order
                return a[0] - b[0];
            } else if (a[2] != b[2]) {
                // Starting and ending edge with same x-axis. Need to place starting edge of the second point before
                // the ending edge of the first point.
                // 0 - 1 = -1. Edge case: [[1, 2, 3], [1, 5, 1], [2, 3, 4]]
                return a[2] - b[2];
            } else {
                // Starting edge with same x-axis. Place the tallest edge first. Edge case: [[2, 9, 10], [2, 7, 15]]
                // Ending edge with the same x-axis. Place the shortest edge first. Edge case: [[1, 8, 7], [3, 8, 5]]
                return (a[2] == 0) ? b[1] - a[1] : a[1] - b[1];
            }
        });

        // Height -> Count Tree map. The tree map is sorted in decreasing order of height
        TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
        map.put(0, 1);
        Integer top;

        for (int[] tuple : heights) {
            // Start Point
            if (tuple[2] == 0) {
                top = map.firstKey();
                if (tuple[1] > top) {
                    result.add(Arrays.asList(tuple[0], tuple[1]));
                }

                map.put(tuple[1], map.getOrDefault(tuple[1], 0) + 1);
            } else { // End Point
                Integer count = map.get(tuple[1]);
                count--;
                if (count == 0)
                    map.remove(tuple[1]);
                else
                    map.put(tuple[1], count);

                top = map.firstKey();
                if (top < tuple[1]) {
                    result.add(Arrays.asList(tuple[0], top));
                }
            }
        }

        return result;
    }

    private void printResult(List<List<Integer>> result) {
        for (List<Integer> record : result)
            System.out.print(record + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        _218_SkyLine sky = new _218_SkyLine();
        List<List<Integer>> result;

        int[][] buildings = {
                {2, 9, 10},
                {3, 7, 15},
                {5, 12, 12},
                {15, 20, 10},
                {19, 24, 8}
        };

        result = sky.getSkyLine(buildings);
        sky.printResult(result);

        // 2
        result = sky.getSkyLine(new int[][]{
                {0, 2, 3},
                {2, 5, 3}
        });
        sky.printResult(result);

        // 3
        result = sky.getSkyLine(new int[][]{
                {2, 9, 10},
                {2, 7, 15}
        });
        sky.printResult(result);

        // 4
        result = sky.getSkyLine(new int[][]{
                {1, 8, 7},
                {3, 8, 5}
        });
        sky.printResult(result);
    }
}
