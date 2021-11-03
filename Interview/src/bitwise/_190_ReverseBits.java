package bitwise;

public class _190_ReverseBits {
    public int reverseBits(int n) {
        if (n == 0) return n;

        int result = 0;
        for (int i = 0; i < 32; i++, n >>= 1) {
            result <<= 1;
            result |= (n & 1);
        }

        return result;
    }

    public static void main(String[] args) {
        _190_ReverseBits rb = new _190_ReverseBits();
        int[] numbers = {80, 0, -3, 41596};
           // results = {167772160, 0, -1073741825, 1044709376}

        int ans = -1;
        for (int num : numbers) {
            ans = rb.reverseBits(num);
            System.out.printf("Reverse Bits of %d is %d\n", num, ans);
        }
    }
}
