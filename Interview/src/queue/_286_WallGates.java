package queue;

import java.util.*;

public class _286_WallGates {

    public void wallsAndGates(int[][] rooms) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    int step = 0;
                    queue.offer(new int[]{i, j});

                    while (!queue.isEmpty()) {
                        int size = queue.size();
                        step++;

                        for (int k = 0; k < size; k++) {
                            int[] curr = queue.poll();

                            for (int[] d : direction) {
                                int x = curr[0] + d[0];
                                int y = curr[1] + d[1];

                                if (x < 0 || x >= rooms.length || y < 0 || y >= rooms[0].length || rooms[x][y] == -1 || rooms[x][y] <= step) {
                                    continue;
                                }

                                rooms[x][y] = step;
                                queue.offer(new int[]{x, y});
                            }
                        }
                    }
                }
            }
        }
    }

    private void print(int[][] rooms) {
        for (int[] room : rooms) {
            for (int j = 0; j < rooms[0].length; j++) {
                System.out.print(room[j] + " ");
            }
            System.out.println();
        }

        System.out.println("=================\n");
    }

    public static void main(String[] args) {
        _286_WallGates wg = new _286_WallGates();
        int[][] rooms;

        // 1
        rooms = new int[][]{{2147483647, -1, 0, 2147483647}, {2147483647, 2147483647, 2147483647, -1},
                {2147483647, -1, 2147483647, -1}, {0, -1, 2147483647, 2147483647}};
        wg.wallsAndGates(rooms);
        wg.print(rooms);

        // 2
        rooms = new int[][]{{0, -1, 2147483647}, {-1, 2147483647, 2147483647}, {2147483647, 2147483647, 0}};
        wg.wallsAndGates(rooms);
        wg.print(rooms);

        // 3
        rooms = new int[][]{{0, -1, -1}, {-1, -1, 2147483647}, {2147483647, -1, 0}};
        wg.wallsAndGates(rooms);
        wg.print(rooms);

        // 4
        rooms = new int[][]{{-1, 2147483647}};
        wg.wallsAndGates(rooms);
        wg.print(rooms);
    }
}
