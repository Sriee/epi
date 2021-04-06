package backtrack;

import java.util.*;

/**
 * Asked in Microsoft OA 2019
 */
public class _1239_MaxConcat {

    int max = 0;

    public int maxLength(List<String> arr) {
        int[] freq = new int[26];
        dfs(arr, freq, 0, 0);
        return max;
    }

    private void dfs(List<String> arr, int[] freq, int idx, int lenSoFar) {
        max = Math.max(max, lenSoFar);

        if(idx >= arr.size())
            return;

        for(int i = idx; i < arr.size(); i++) {
            String current = arr.get(i);
            if(isUnique(current, freq)) {
                dfs(arr, freq, i + 1, lenSoFar + current.length());
            }
            reset(current, freq);
        }
    }

    // Check if a string has unique characters
    private boolean isUnique(String s, int[] freq) {
        boolean res = true;
        for(int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            freq[idx]++;

            if(freq[idx] > 1) res = false;
        }

        return res;
    }

    // Reset frequency counter for the given string
    private void reset(String s, int[] freq) {
        for(int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            freq[idx]--;
        }
    }

    public static void main(String[] args) {
        _1239_MaxConcat mc = new _1239_MaxConcat();

        String[] s = {"un", "iq", "ue"};
        List<String> arr = new ArrayList<>(Arrays.asList(s));
        System.out.println(mc.maxLength(arr));
    }
}
