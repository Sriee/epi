package string;

public class _482_LicenseKeyFormatting {
    public String licenseKeyFormatting(String s, int k) {
        int n = s.length();

        char[] dstArr = new char[2 * n];
        char[] srcArr = s.toCharArray();

        int wp = dstArr.length - 1, segments = 0, diff = 'A' - 'a';

        for (int rp = n - 1; rp >= 0; rp--) {
            if (srcArr[rp] == '-') continue;

            dstArr[wp--] = srcArr[rp] < 'a' ? srcArr[rp] : (char) (srcArr[rp] + diff);
            segments++;

            if (segments == k) {
                dstArr[wp--] = '-';
                segments = 0;
            }
        }

        if (dstArr[++wp] == '-') wp ++;

        return new String(dstArr, wp, dstArr.length - wp);
    }

    public static void main(String[] args) {
        _482_LicenseKeyFormatting lkf = new _482_LicenseKeyFormatting();
        String[] inp = {
                "5F3Z-2e-9-w",
                "2-5g-3-J",
                "s-fgh6-749jd-fkj-gb-v-aoisa-d9836-72349-4t47-r-ijbs-f-gjlafgs-adyfuha-o-i-d-uf87we6r923-082-30g-hbna-df-sa"
        };
        int[] k = {4, 2, 3};
        for (int i = 0; i < k.length; i++) {
            System.out.println(lkf.licenseKeyFormatting(inp[i], k[i]));
        }
    }
}
