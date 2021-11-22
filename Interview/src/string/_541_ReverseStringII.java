package string;

public class _541_ReverseStringII {

    public String reverseStr(String s, int k) {
        char[] strArr = s.toCharArray();
        int n = strArr.length;

        for (int start = 0; start < n; start += 2 * k) {
            if (start + k >= n) {
                reverse(strArr, start, n - 1);
            } else {
                reverse(strArr, start, start + k - 1);
            }
        }

        return new String(strArr);
    }

    private void reverse(char[] strArr, int i, int j) {
        for (; i < j; i++, j--) {
            char temp = strArr[i];
            strArr[i] = strArr[j];
            strArr[j] = temp;
        }
    }

    public static void main(String[] args) {
        _541_ReverseStringII rs = new _541_ReverseStringII();
        String[] inputs = {
          "abcdefg",
          "abc",
          "jgadfityueriopinbmacxgdfhi"
        };
        int[] ks = new int[] {2, 1, 4};

        for (int i = 0; i < inputs.length; i++) {
            System.out.println(rs.reverseStr(inputs[i], ks[i]));
        }
    }
}
