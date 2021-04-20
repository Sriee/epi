package bfs;

import java.util.*;

public class _752_OpenLock {

    private int openLock(String[] deadends, String target) {
        int steps = 0, size, nextVal;
        Set<String> visited = new HashSet<>(), dends = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        int[] direction = {-1, 1};
        StringBuilder sb;

        queue.add("0000");
        visited.add("0000");
        Collections.addAll(dends, deadends);

        while (!queue.isEmpty()) {
            size = queue.size();
            for (int k = 0; k < size; k++) {
                String curr = queue.remove();

                if (curr.equals(target)) {
                    return steps;
                } else if (!dends.contains(curr)) {
                    sb = new StringBuilder(curr);
                    for (int i = 0; i < 4; i++) {
                        for (int d : direction) {
                            nextVal = (sb.charAt(i) - '0' + d + 10) % 10;
                            String neighbor =
                                    curr.substring(0, i) + nextVal + curr.substring(i + 1);

                            if (!visited.contains(neighbor)) {
                                visited.add(neighbor);
                                queue.offer(neighbor);
                            }
                        }
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    public static void main(String[] args) {
        _752_OpenLock ol = new _752_OpenLock();
        System.out.println(ol.openLock(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202"));
        System.out.println(ol.openLock(new String[]{"8888"}, "0009"));
    }
}
