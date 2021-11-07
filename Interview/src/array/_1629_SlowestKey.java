package array;

public class _1629_SlowestKey {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int longestDuration = releaseTimes[0];
        char[] keysPressedArr = keysPressed.toCharArray();
        char longestKeyPressed = keysPressedArr[0];

        for (int i = 1; i < releaseTimes.length; i++) {
            int durationDiff = releaseTimes[i] - releaseTimes[i - 1];
            if (durationDiff > longestDuration ||
                (durationDiff == longestDuration && keysPressedArr[i] > longestKeyPressed)) {
                longestDuration = durationDiff;
                longestKeyPressed = keysPressedArr[i];
            }
        }

        return longestKeyPressed;
    }

    public static void main(String[] args) {
        _1629_SlowestKey sk = new _1629_SlowestKey();
        System.out.println(sk.slowestKey(new int[] {9, 29, 49, 50}, "cbcd"));
        System.out.println(sk.slowestKey(new int[] {12, 23, 36, 46, 62}, "spuda"));
    }
}
