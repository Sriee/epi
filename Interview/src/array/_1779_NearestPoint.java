package array;

public class _1779_NearestPoint {

    public int nearestValidPoint(int x, int y, int[][] points) {
        int idx = -1, minDist = Integer.MAX_VALUE;

        for (int i = 0; i < points.length; i++) {
            if (points[i][0] == x || points[i][1] == y) {
                int dist = calculateDistance(x, y, points[i]);
                if (dist < minDist) {
                    minDist = dist;
                    idx = i;
                }
            }
        }

        return idx;
    }

    private int calculateDistance(int x, int y, int[] point) {
        return Math.abs(x - point[0]) + Math.abs(y - point[1]);
    }

    public static void main(String[] args) {
        _1779_NearestPoint np = new _1779_NearestPoint();
        int[][] points = {{1, 2}, {3, 1}, {2, 4}, {2, 3}, {4, 4}};
        System.out.println(np.nearestValidPoint(3, 4, points));

        points = new int[][]{{2, 3}};
        System.out.println(np.nearestValidPoint(3, 4, points));
    }
}
