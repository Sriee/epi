package bfs;

import java.util.*;

public class _733_FloodFill {

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor)
            return image;

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Deque<int[]> queue = new ArrayDeque<>();
        int oldColor = image[sr][sc];

        image[sr][sc] = newColor;
        queue.offer(new int[]{sr, sc});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            for (int[] d : directions) {
                int x = curr[0] + d[0];
                int y = curr[1] + d[1];

                if (x < 0 || x >= image.length || y < 0 || y >= image[0].length || image[x][y] != oldColor)
                    continue;

                image[x][y] = newColor;
                queue.offer(new int[]{x, y});
            }
        }
        return image;
    }

    private void print(int[][] image) {
        for (int[] ints : image) {
            for (int j = 0; j < image[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        _733_FloodFill ff = new _733_FloodFill();
        int[][] image = {
                {1, 1, 0},
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        // 1. [1][1] 0's to 3
        image = ff.floodFill(image, 1, 1, 3);
        ff.print(image);
        System.out.println("=====================\n");

        // 2. [2][2] 1's to 4
        image = ff.floodFill(image, 2, 2, 4);
        ff.print(image);
        System.out.println("=====================\n");

        // 3. [0][0] 1's to 59
        image = ff.floodFill(image, 0, 0, 59);
        ff.print(image);
        System.out.println("=====================");
    }
}
