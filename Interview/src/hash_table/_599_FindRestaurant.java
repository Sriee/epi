package hash_table;

import java.util.*;

class _599_FindRestaurant {
    public String[] findRestaurant(String[] list1, String[] list2) {
        if (list2.length < list1.length)
            return findRestaurant(list2, list1);

        Map<String, Integer> common = new HashMap<>();
        Map<String, Integer> map = new HashMap<>();
        int i, min = Integer.MAX_VALUE;

        for (i = 0; i < list1.length; i++)
            map.put(list1[i], i);

        for (i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                int idxSum = map.get(list2[i]) + i;
                min = Math.min(min, idxSum);

                if (idxSum <= min) {
                    common.put(list2[i], idxSum);
                }
            }
        }

        // Remove entries greater than min Idx
        for (Map.Entry<String, Integer> entry : common.entrySet()) {
            if (common.get(entry.getKey()) > min) {
                common.remove(entry.getKey());
            }
        }

        // Populate result
        String[] res = new String[common.size()];
        i = 0;

        for (String s : common.keySet()) res[i++] = s;

        return res;
    }

    public static void main(String[] args) {
        _599_FindRestaurant fr = new _599_FindRestaurant();
        String[] list1 = new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] list2 = new String[]{"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};
        System.out.println(Arrays.toString(fr.findRestaurant(list1, list2)));
    }
}