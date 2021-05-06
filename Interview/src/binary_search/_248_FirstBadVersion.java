package binary_search;

/**
 * Binary Search Template 2
 */
public class _248_FirstBadVersion {
    private int bad;

    private boolean isBadVersion(int n) {
        return n >= bad;
    }

    public int firstBadVersion(int n) {
        int left = 1, right = n;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (isBadVersion(mid))
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }

    public static void main(String[] args) {
        _248_FirstBadVersion bv = new _248_FirstBadVersion();
        int[] bad = {508, 49, 4, 1};
        int[] n = {23005, 1538, 5, 1};

        for (int i = 0; i < n.length; i++) {
            bv.bad = bad[i];
            System.out.println(bv.firstBadVersion(n[i]));
        }

    }
}
