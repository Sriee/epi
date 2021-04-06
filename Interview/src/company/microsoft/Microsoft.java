package company.microsoft;
import java.util.*;

/**
 * Online Assessment - May 2021
 * 1. Debugging - Initalize min = arr[0]
 * 2. Given an array of nums, determine the final sign of the product of all numbers in the array.
 * 3. Multiple choice questions - 8 of them. this was quite time consuming and tricky imo.
 * 4. https://leetcode.com/problems/count-good-nodes-in-binary-tree/ - straight from LC.
 *
 * Questions to Review
 * https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/
 * https://leetcode.com/discuss/interview-question/451482/ & https://leetcode.com/problems/jump-game-iii/
 * https://leetcode.com/problems/meeting-rooms-ii/
 *
 *
 * Given an array of 1000 items. What is the highest? (That was the hardest for me)
 * -- Number of unique pairs
 * -- Number of sub arrays
 * -- Number of permutations
 * -- 10e12?
 */
public class Microsoft {

    //Partition array into N subsets with balanced sum
    // TODO: Need to look into the problem
    // https://leetcode.com/discuss/interview-question/430981/
    private List<List<Integer>> part(int[] T, int n) {
        int[] sums = new int[n];
        int c;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> sums[a] - sums[b]);

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(new ArrayList<>());
            pq.add(i);
        }

        for (int i = T.length - 1; i >= 0; i--) {
            c = pq.poll();
            result.get(c).add(T[i]);
            sums[c] += T[i];
            pq.add(c);
        }

        return result;
    }

    // Greedy Approach

    /**
     * Given a string, what is the minimum number of adjacent swaps required to convert a string into a palindrome.
     * If not possible, return -1.
     *
     * Example 1: Input: "mamad" , Output: 3
     * Example 2: Input: "asflkj", Output: -1
     * Example 3: Input: "aabb"  , Output: 2.
     *  Explanation:
     *      1. swap "aabb" -> "abab"
     *      2. swap "abab" -> "abba"
     *
     * Example 4: Input: "ntiin" , Output: 1. Explanation: swap 't' with 'i' => "nitin"
     *
     * Link: https://leetcode.com/discuss/interview-question/351783/
     */
    private int minSwapsPalindrome(String s) {
        char[] chArr = s.toCharArray();

        if(!canMinSwapDone(chArr))
            return -1;

        int totalWays = 0, left = 0, right = chArr.length - 1;

        while(left < right) {
            if(chArr[left] == chArr[right]) {
                left++;
                right--;
            } else {
                int ptr = right;

                while(ptr > left && chArr[left] != chArr[ptr]) ptr--;

                // Pointer has reached left pointers position. chArr[left] must be an odd element.
                if(ptr == left) {
                    swap(chArr, left, left + 1);
                    totalWays++;
                } else {
                    while(ptr < right) {
                        swap(chArr, ptr, ptr + 1);
                        ptr++;
                        totalWays++;
                    }
                }
            }
        }
        return totalWays;
    }

    private void swap(char[] chArr, int p1, int p2) {
        char temp = chArr[p1];
        chArr[p1] = chArr[p2];
        chArr[p2] = temp;
    }

    private boolean canMinSwapDone(char[] chArr) {
        int[] freq = new int[26];
        int oddCount = 0;

        for(char ch : chArr) freq[ch - 'a']++;

        for(int i = 0; i < 26; i++) {
            if(freq[i] % 2 != 0)
                oddCount++;
        }

        return oddCount <= 1;
    }

    /**
     * Given an array like [-2,3,5,-9] return [1, 0, -1] if the product of all elements in the array is positive, 0,
     * or negative respectively.
     *
     * Asked in Microsoft May 2021 OA
     */
    private int finalSign(int[] arr) {
        int count = 0;
        for(int i : arr) {
            if(i == 0)
                return 0;

            if(i < 0)
                count++;
        }

        return count % 2 == 0 ? 1 : -1;
    }

    /**
     * Given a string find the largest character. A largest character is the capital letter in the string. The
     * capital letter should have an equivalent lower case character.
     *
     * Example "abcmdD" though m is largest character it does not have a 'M' so 'D' would be your answer.
     */
    private char largestAlphabetCharacter(String s) {
        if(s == null || s.length() == 0)
            return '0';

        char[] chs = s.toCharArray();
        int[] lowerCase = new int[26];
        int[] upperCase = new int[26];
        int asciiVal, idx;

        for (char ch : chs) {
            asciiVal = ch;

            if (asciiVal >= 65 && asciiVal <= 90)
                upperCase[ch - 'A']++;
            else
                lowerCase[ch - 'a']++;
        }

        for(idx = 25; idx >= 0; idx--) {
            if(upperCase[idx] > 0 && lowerCase[idx] > 0)
                break;
        }

        return (idx < 0) ? '0': (char)(idx + 'a');
    }

    //

    /**
     * Write a function that, given an array A of N integers, returns the lagest integer K > 0 such that both values K
     * and -K exist in array A. If there is no such integer, the function should return 0.
     *
     * Example 1: Input: [3, 2, -2, 5, -3] Output: 3 Max [-K, K] pair = [-3, 3]
     * Example 2: Input: [1, 2, 3, -4] Output: 0 There are no [-K, K] pair
     *
     * Link: https://leetcode.com/discuss/interview-question/406031/
     */
    private int largestK(int[] arr) {
        if(arr == null || arr.length == 0)
            return 0;

        Arrays.sort(arr);

        int left = 0, right = arr.length - 1, labs;

        while(left < right) {
            if(arr[left] > -1 || arr[right] < 1)
                return 0;

            labs = Math.abs(arr[left]);
            if(arr[right] == labs)
                return arr[right];
            else if(arr[right] > labs)
                right--;
            else
                left++;
        }

        return 0;
    }

    /**
     * https://leetcode.com/discuss/interview-question/415307/american-express-online-codility-fraction-addition
     */

    public static void main(String[] args) {
        Microsoft ms = new Microsoft();

        // 1. Final Sign of the product
        int[] arr = {-2, -5, 1, 2, 0};
        System.out.println(ms.finalSign(arr));

        // 2. Find the largest character
        String errMsg = "Expected '%s' vs Got '%s'";
        String[] inp = {"aZAbcz", "DSGHFDSJ", "adhyj", "abcmdD"};
        char[] out = {'z', '0', '0', 'd'};

        for(int i = 0; i < inp.length; i++) {
            char res = ms.largestAlphabetCharacter(inp[i]);
            assert res == out[i]: String.format(errMsg, out[i], res);
        }

        // 3. Minimum Swaps to Palindrome
        String s = "mamad";
        System.out.println(ms.minSwapsPalindrome(s));

        // 4. Largest K & -K in an array
        arr = new int[] {3, 2, -2, 5};
        System.out.println(ms.largestK(arr));
    }
}
