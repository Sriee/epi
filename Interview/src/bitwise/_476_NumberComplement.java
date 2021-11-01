package bitwise;

public class _476_NumberComplement {

    /**
     * TC: O(m) where m = length of the binary value
     * SC: O(1)
     */
    public int findComplement(int num) {
        int ans = 0, powerOfTwo = 1;
        if (num == 0) return powerOfTwo;

        for (int n = num; n !=0 ; n >>= 1, powerOfTwo <<= 1) {
            if ((n & 1) == 0) {
                ans += powerOfTwo;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        _476_NumberComplement nc = new _476_NumberComplement();
        int[] numbers = {5, 0, 1, 39, 2147483};
           // results = {2, 1, 0, 24, 2046820}

        for (int num : numbers) {
            System.out.printf("Complement of %d is %d", num, nc.findComplement(num));
        }
    }
}
