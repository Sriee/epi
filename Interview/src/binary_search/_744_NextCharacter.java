package binary_search;

public class _744_NextCharacter {

    // Binary Search Template 2
    public char nextGreatestLetter(char[] letters, char target) {
        if (target >= letters[letters.length - 1])
            return letters[0];

        int left = 0, right = letters.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (letters[mid] <= target)
                left = mid + 1;
            else
                right = mid;
        }

        return letters[left];
    }

    public static void main(String[] args) {
        _744_NextCharacter nc = new _744_NextCharacter();
        char[] letters = {'c', 'f', 'j'};
        char[] nextGrt = {'c', 'e', 'j', 'z', 'a'};

        for (char ch : nextGrt) {
            System.out.println("Next Greater of " + ch + " = " + nc.nextGreatestLetter(letters, ch));
        }
    }
}
