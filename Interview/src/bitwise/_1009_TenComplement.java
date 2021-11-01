package bitwise;

public class _1009_TenComplement {

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

    /**
     * TC: O(1)
     * SC: O(1)
     */
    public int findComplementXor(int num) {
        if (num == 0) return 1;

        int bitCount = (int) Math.floor((int)(Math.log(num) / Math.log(2))) + 1;
        int allBitsSet = (int) Math.pow(2, bitCount) - 1;
        return num ^ allBitsSet;
    }

    public static void main(String[] args) {
        _1009_TenComplement nc = new _1009_TenComplement();
        int[] numbers = {5, 0, 1, 38, 2147483};
           // results = {2, 1, 0, 25, 2046820}

        int ans = -1;
        for (int num : numbers) {
            ans = (num & 2) == 0 ? nc.findComplement(num) : nc.findComplementXor(num);
            System.out.printf("Complement of %d is %d\n", num, ans);
        }
    }
}
