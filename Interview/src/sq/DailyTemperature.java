package sq;

public class DailyTemperature {

    /**
     * Leet code problem. Solution -> Accepted Given a list of daily temperatures,
     * produce a list that, for each day in the input, tells you how many days you
     * would have to wait until a warmer temperature. If there is no future day for
     * which this is possible, put 0 instead. For example, given the list
     * temperatures = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1,
     * 4, 2, 1, 1, 0, 0].
     *
     * @param temperatures temperature list
     * @return list of number of days to warmer temperature
     */
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0)
            return temperatures;

        int[] stack = new int[temperatures.length];
        int[] result = new int[temperatures.length];
        int top = -1;
        for (int i = 0; i < temperatures.length; i++) {
            while (top > -1 && temperatures[i] > temperatures[stack[top]]) {
                int index = stack[top];
                result[index] = i - index;
                top--;
            }
            stack[++top] = i;
        }

        while (top != -1)
            result[stack[top--]] = 0;

        return result;
    }
}
