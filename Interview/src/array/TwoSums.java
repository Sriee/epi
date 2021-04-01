package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSums {
    public static void main(String[] args) {
        int[] array = { 2, 7, 11, 15 };
        int target = 22;
        int[] result = null;
        Map<Integer, Integer> table = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            if (table.isEmpty()) {
                table.put(array[i], i);
            } else if (table.containsKey(target - array[i])) {
                result = (i < table.get(target - array[i])) ? new int[] { i, table.get(target - array[i]) }
                        : new int[] { table.get(target - array[i]), i };
                break;
            } else {
                table.put(array[i], i);
            }
        }
        System.out.println(Arrays.toString(result));
    }
}
