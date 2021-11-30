package array._2d;

public class _463_IslandPerimeter {

    /**
     * Simple Counting Approach - Since we are moving from left to right and top to bottom, we add 4 sides to the
     * perimeter when we encounter a land. If the left and top of the current position is also a land, we deduct 2
     * (shared) sides of the land from our perimeter and continue.
     *
     * TC: O(mn)
     * SC: O(1)
     */
    public int islandPerimeter(int[][] grid) {
        int numRow = grid.length, numCol = grid[0].length, perimeter = 0;

        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                if (grid[row][col] == 0)
                    continue;

                perimeter += 4;

                // Left
                if (row > 0 && grid[row - 1][col] == 1)
                    perimeter -= 2;

                // Up
                if (col > 0 && grid[row][col - 1] == 1)
                    perimeter -= 2;
            }
        }

        return perimeter;
    }

    public static void main(String[] args) {
        _463_IslandPerimeter ip = new _463_IslandPerimeter();

        int[][] grid = {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}
        };
        System.out.println(ip.islandPerimeter(grid));
    }
}
