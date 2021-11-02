package bitwise;

import java.util.*;

public class _832_FlippingImage {

    /**
     * TC: O(n ^ 2)
     * SC: O(1)
     *
     * The naive approach of solving this problem is to create separate functions for flipping the matrix horizontally
     * and then inverting each individual element of the matrix.
     *
     * The time complexity for flipping the matrix is O(n^2) and for inverting each element is O(n^2). By using Bitwise
     * Manipulation approach we have reduced 1 O(n^2) iteration over the entire matrix.
     */
    public int[][] flipAndInvertImage(int[][] image) {
        int n = image.length, mid = image.length / 2;
        boolean invertMid = (n & 1) == 1;

        for (int row = 0; row < n; row++) {
            for (int i = 0, j = n - 1; i < j; i++, j--) {
                int temp = image[row][i];
                image[row][i] = image[row][j] ^ 1;
                image[row][j] = temp ^ 1;
            }


            if (invertMid) {
                // Trick: Replacement for image[row][mid] = (image[row][mid] & 1) == 0 ? 1 : 0;
                image[row][mid] ^= 1;
            }
        }

        return image;
    }

    private void print(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            System.out.println(Arrays.toString(mat[i]));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        _832_FlippingImage fi = new _832_FlippingImage();

        List<int[][]> inputs = new ArrayList<>();
        inputs.add(new int[][] {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}});
        inputs.add(new int[][] {{1, 1, 0, 0}, {1, 0, 0, 1}, {0, 1, 1, 1}, {1, 0, 1, 0}});
        inputs.add(new int[][] {{1}});

        for (int[][] image: inputs) {
            int[][] flippedImage = fi.flipAndInvertImage(image);
            fi.print(flippedImage);
        }
    }
}
