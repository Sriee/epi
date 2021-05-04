package design;

import java.util.*;

/**
 * Design: Using built in functions
 */
public class _1429_FirstUnique {
    Set<Integer> uniq, dups;

    public _1429_FirstUnique(int[] nums) {
        uniq = new LinkedHashSet<>();
        dups = new HashSet<>();
        for (int i : nums)
            add(i);
    }

    public int showFirstUnique() {
        return uniq.isEmpty() ? -1 : uniq.iterator().next();
    }

    public void add(int value) {
        if (uniq.contains(value)) {
            uniq.remove(value);
            dups.add(value);
        } else if (!dups.contains(value)) {
            uniq.add(value);
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 5, 7};
        _1429_FirstUnique fq = new _1429_FirstUnique(nums);

        System.out.println(fq.showFirstUnique());
        fq.add(5);
        System.out.println(fq.showFirstUnique());
        fq.add(3);
        fq.add(7);
        System.out.println(fq.showFirstUnique());
    }
}
