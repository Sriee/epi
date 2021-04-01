package array;

import java.util.*;
import java.util.Map.Entry;

public class Smoke {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();

        list.add(7812);
        list.add(62);
        list.add(613);
        list.add(178);

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (Integer i : list) {
            System.out.print(i + " ");
        }
        System.out.println();

        map.put(1, 101);
        map.put(2, 201);
        map.put(3, 301);
        map.put(4, 401);
        map.put(5, 501);

        List<Map.Entry<Integer, Integer>> keySet = new LinkedList<Entry<Integer, Integer>>(map.entrySet());

        System.out.println(keySet.toString());
        Collections.sort(keySet, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> m1, Map.Entry<Integer, Integer> m2) {
                return m2.getValue() - m1.getValue();
            }
        });

        // Iterating through Hash Map - Method 1
        System.out.println("\nIterating through Hash Map (Method 1).");
        for (Map.Entry<Integer, Integer> item : keySet) {
            System.out.println(item.getKey() + " " + item.getValue());
        }

        // Iterating through Hash Map - Method 2
        System.out.println("\nIterating through Hash Map (Method 2).");
        Iterator<Entry<Integer, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> entry = it.next();
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        // Iterating through Hash Map - Method 3
        System.out.println("\nIterating through Hash Map (Method 3).");
        Set<Integer> keys = map.keySet();
        for (Integer key : keys) {
            System.out.println(key + " " + map.get(key));
        }

        // Set operations
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();

        s1.addAll(Arrays.asList(1, 2, 3, 4, 5));
        s2.addAll(Arrays.asList(2, 4, 18, -83));

        Set<Integer> union = new HashSet<>(), intersection = new HashSet<>(), difference = new HashSet<>();
        union.addAll(s1);
        intersection.addAll(s1);
        difference.addAll(s1);

        // Union
        union.addAll(s2);
        System.out.println(union.toString());

        // Intersection
        intersection.retainAll(s2);
        System.out.println(intersection.toString());

        // Asymmentric difference
        difference.removeAll(s2);
        System.out.println(difference.toString());
    }

}
