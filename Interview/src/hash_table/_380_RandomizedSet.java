package hash_table;

import java.util.*;

/*
 * Approach 2: HashMap + ArrayList
 */
class _380_RandomizedSet {

    Map<Integer, Integer> valToIdx; // Val => List Index Map
    List<Integer> lst;
    Random rand;

    /**
     * Initialize your data structure here.
     */
    public _380_RandomizedSet() {
        valToIdx = new HashMap<>();
        lst = new ArrayList<>();
        rand = new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (valToIdx.containsKey(val))
            return false;

        valToIdx.put(val, lst.size());
        lst.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!valToIdx.containsKey(val))
            return false;

        int lastElement = lst.get(lst.size() - 1);
        int idx = valToIdx.get(val);

        /*
         * Trick to delete an element in a list with O(1) run time.
         *
         * According to the comment in this post: https://leetcode
         * .com/problems/insert-delete-getrandom-o1/discuss/85401/Java-solution-using-a-HashMap-and-an
         * -ArrayList-along-with-a-follow-up.-(131-ms)/192282
         *
         * The implementation of ArrayList.java shows that the run time to delete an element at the end of an array
         * is O(1)
         */
        lst.set(idx, lastElement);
        valToIdx.put(lastElement, idx);

        lst.remove(lst.size() - 1);
        valToIdx.remove(val);

        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return lst.get(rand.nextInt(lst.size()));
    }
}