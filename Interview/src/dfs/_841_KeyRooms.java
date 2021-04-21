package dfs;

import java.util.*;

public class _841_KeyRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> visited = new HashSet<>();

        List<Integer> roomZero = rooms.get(0);
        visited.add(0);

        for (int key : roomZero) {
            if (!visited.contains(key)) {
                dfs(rooms, visited, key);
            }
        }
        return visited.size() == rooms.size();
    }

    private void dfs(List<List<Integer>> rooms, Set<Integer> visited, int curr) {
        visited.add(curr);

        for (int key : rooms.get(curr)) {
            if (!visited.contains(key)) {
                dfs(rooms, visited, key);
            }
        }
    }

    public static void main(String[] args) {
        _841_KeyRooms kr = new _841_KeyRooms();

        // 1. [[1],[2],[3],[]]
        List<List<Integer>> rooms = new ArrayList<>();
        int[][] input = {
                {1}, {2}, {3}, {0}
        };

        for (int[] row : input) {
            List<Integer> r = new ArrayList<>();

            for (int i : row) r.add(i);
            rooms.add(r);
        }
        System.out.println(kr.canVisitAllRooms(rooms));

        // 2. [[1,3],[3,0,1],[2],[0]]
        rooms = new ArrayList<>();
        input = new int[][]{
                {1, 3}, {3, 0, 1}, {2}, {0}
        };

        for (int[] row : input) {
            List<Integer> r = new ArrayList<>();

            for (int i : row) r.add(i);
            rooms.add(r);
        }
        System.out.println(kr.canVisitAllRooms(rooms));
    }
}
