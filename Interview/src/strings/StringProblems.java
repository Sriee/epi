package strings;

import java.util.*;

public class StringProblems {

	/**
	 * Leet code. Solution -> Wrong Answer
	 * 
	 * Perform C languages atoi. This problem has lot of downvotes due to peculiar edge
	 * cases. The approach is correct. Settling on it.
	 * 
	 * @param str
	 * @return
	 */
	public int atoi(String str) {
		boolean isNeg = false;
		int result = 0, i = 0;
		char ch = '\0';

		if (str == null || str.length() == 0)
			return result;

		for (; i < str.length(); i++) {
			ch = str.charAt(i);
			if (ch != ' ')
				break;
		}

		// Handle all case
		if (i == str.length())
			return result;

		// Handle +/-
		if (ch == '-' || ch == '+') {
			isNeg = ch == '-';
			i++;
		}

		for (; i < str.length(); i++) {
			ch = str.charAt(i);
			int cp = ch - '0';

			if (cp < 0 || cp > 9)
				break;

			result *= 10;
			// Failing for 2147483647. Should handle overflow properly
			if (result > Integer.MAX_VALUE / 10 || result == Integer.MAX_VALUE && cp > 7)
				return (isNeg) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

			result += cp;
		}

		return (isNeg) ? -result : result;
	}

	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 4 ms. Optimal run time
	 * 
	 * Given a string s1 and s2, check if s2 contains the permutation of s1. The
	 * string consists of only lower case letter.
	 * 
	 * @param s1
	 * @param s2
	 * @return true if s2 contains permutation of s1, false otherwise
	 */
	public boolean checkInclusion(String s1, String s2) {
		int[] table = new int[256];
		int begin = 0, end = 0, counter = s1.length(), k = s1.length();

		for (char ch : s1.toCharArray())
			table[ch]++;

		while (end < s2.length()) {
			if (table[s2.charAt(end)]-- > 0)
				counter--;
			end++;

			while (counter == 0) {
				if (end - begin == k)
					return true;

				if (table[s2.charAt(begin)]++ == 0)
					counter++;

				begin++;
			}
		}
		return false;
	}

	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 6 ms. Optimal run time
	 * 
	 * Given a string s and p, find the indices of all anagrams of p in s. Both the
	 * string consists of only lower case letter.
	 * 
	 * @param s given string
	 * @param p substring whose anagrams to be matched
	 * @return all the indices of anagrams of p in s
	 */
	public List<Integer> findAnagrams(String s, String p) {
		// Use ArrayList instead of LinkedList
		List<Integer> result = new ArrayList<>();

		// Trick of using 256 int array instead of 26 int array
		int[] table = new int[256];
		int counter = 0, length = 0, begin = 0;

		for (char ch : p.toCharArray()) {
			table[ch]++;
			counter++;

			// Using length instead of p.length() lookup reduces the run time by 3 ms
			length++;
		}

		char[] inp = s.toCharArray();
		for (int end = 0; end < s.length(); end++) {
			if (table[inp[end]]-- > 0)
				counter--;

			while (counter == 0) {
				if (end - begin + 1 == length)
					result.add(begin);

				if (table[inp[begin++]]++ == 0)
					counter++;
			}
		}

		return result;
	}

	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 2 ms. Optimal run time.
	 * 
	 * Given a string, find the length of the longest substring without repeating
	 * characters.
	 * 
	 * @param s Given string
	 * @return length of the longest substring
	 */
	public int lengthOfLongestSubstring(String s) {
		int len = 0, begin = 0, end = 0;
		int[] table = new int[256];

		while (end < s.length()) {
			char ch = s.charAt(end);
			table[ch]++;

			if (table[ch] > 1 && end - begin > len) {
				len = end - begin;
			}

			while (table[ch] > 1) {
				table[s.charAt(begin++)]--;
			}

			end++;
		}

		if (end - begin > len)
			len = end - begin;

		return len;
	}

	/**
	 * Leet code. Problem is locked
	 * 
	 * Given a string, find the length of the longest substring with atmost 2
	 * distinct character.
	 * 
	 * Variation: atmost k distict character
	 * 
	 * Example: "aaaabcdd" -> 5
	 * 
	 * @param s Given string
	 * @return length of the longest substring with 2 distinct characters
	 */
	public int lengthOfLongestSubstringTwoDistinct(String s) {
		int len = 0, end = 0, begin = 0, counter = 0;
		int[] table = new int[256];

		while (end < s.length()) {
			char ech = s.charAt(end);
			table[ech]++;

			if (table[ech] == 1)
				counter++;

			end++;

			while (counter > 2) {
				char bch = s.charAt(begin);

				table[bch]--;
				if (table[bch] == 0)
					counter--;
				begin++;
			}

			if (end - begin > len)
				len = end - begin;
		}

		return len;
	}

	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 19 ms. Below average run time. Bulk of the timing is going for
	 * accessing the map collection
	 * 
	 * Given a string 's' and a substring 't' find the minimum window of 't' in 's'.
	 * 't' can be in any combination in s. The string only contains letters (both
	 * upper & lower case)
	 * 
	 * Refer Reference/Sliding_Window.pdf for explanation.
	 * 
	 * @param s given string
	 * @param t substring
	 * @return substring with minium window
	 */
	public String minWindow(String s, String t) {
		Map<Character, Integer> mem = new HashMap<>();
		String result = "";

		if (s == null || s.length() == 0)
			return result;

		// Initialize the frequency table
		for (char ch : t.toCharArray())
			mem.put(ch, mem.getOrDefault(ch, 0) + 1);

		int begin = 0, end = 0, length = Integer.MAX_VALUE, counter = mem.size();

		while (end < s.length()) {
			char ch = s.charAt(end);

			// Decrement the count for the character
			if (mem.containsKey(ch)) {
				mem.put(ch, mem.get(ch) - 1);
				if (mem.get(ch) == 0)
					counter--;
			}

			end++;

			// When counter reaches zero => t has every character of s
			while (counter == 0) {

				// Logic to store the result
				if (end - begin < length) {
					length = end - begin;
					result = s.substring(begin, end);
				}

				// Trick: incrementing the table value as we slide right
				// This trick avoids intializing counters for a window again !
				char bc = s.charAt(begin);
				if (mem.containsKey(bc)) {
					mem.put(bc, mem.get(bc) + 1);
					if (mem.get(bc) > 0)
						counter++;
				}

				begin++;
			}
		}

		return result;
	}

	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 3 ms. Above average solution. Calling character class is causing
	 * extra run time
	 * 
	 * Given a string, find whether it is a palindrome or not. String may contain
	 * other characters as well. Ignore special characters, space and upper case
	 * letters.
	 * 
	 * @param s Given string
	 * @return true if the
	 */
	public boolean isPalindrome(String s) {
		char[] ch = s.toCharArray();
		int i = 0, j = s.length() - 1;

		while (i < j) {
			if (!Character.isLetterOrDigit(ch[i]))
				i++;
			else if (!Character.isLetterOrDigit(ch[j]))
				j--;
			else if (Character.toLowerCase(ch[i++]) != Character.toLowerCase(ch[j--]))
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		StringProblems sp = new StringProblems();
		String ans = sp.minWindow("fgiowblkjadgfladfbmnyuhijo", "ibn");
		sp.findAnagrams("abaacbabc", "abc");
		System.out.println(ans);

		System.out.println(sp.lengthOfLongestSubstring("pwwkew"));

		System.out.println(sp.checkInclusion("ab", "eidaoboooba"));
		System.out.println(sp.checkInclusion("atuietyeui", "adfkjliutetyuqripojadgflkalvnbalcteoiyuhdjfasdf"));
		System.out.println(sp.checkInclusion("hello", "ooolleoooleh"));
		
		sp.atoi("2147483648");
	}
}
