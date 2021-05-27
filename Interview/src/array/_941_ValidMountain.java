package array;

class _941_ValidMountain {
    public boolean validMountainArray(int[] arr) {
        if (arr.length < 3)
            return false;

        int i, j, n = arr.length;

        for (i = 0; i + 1 < n; i++) {
            if (arr[i] == arr[i + 1])
                return false;
            else if (arr[i] > arr[i + 1])
                break;
        }

        // Strictly increasing case
        if (i == 0 || i == n - 1)
            return false;

        for (j = n - 1; j - 1 >= 0; j--) {
            if (arr[j] == arr[j - 1])
                return false;
            else if (arr[j] > arr[j - 1])
                break;
        }

        // Strictly decreasing case
        if (j == 0 || j == n - 1)
            return false;


        return i == j;
    }

    public static void main(String[] args) {
        _941_ValidMountain vm = new _941_ValidMountain();
        int[][] inputs = {
                {2, 1},
                {3, 5, 5},
                {0, 3, 2, 1},
                {33, 44, 11, 9, 5, 1}
        };

        for (int[] input : inputs) {
            System.out.println(vm.validMountainArray(input));
        }
    }
}