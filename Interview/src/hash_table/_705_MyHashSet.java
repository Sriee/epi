package hash_table;

import java.util.LinkedList;

/*
 * Separate Chaining
 *  - Using LinkedList
 *      a. Rehashing = 30 ms
 *      a. W/o Rehashing = 19 ms. Though the run time is reduced, compared to the hashing method, we should use the
 * rehashing approach in interviews.
 */
class _705_MyHashSet {
    LinkedList<Integer>[] bucket;
    private final double LOAD = 0.8;
    private int size = 0;

    /**
     * Initialize your data structure here.
     */
    public _705_MyHashSet() {
        bucket = new LinkedList[887];
    }

    public void add(int key) {
        int hash = _hash(key);

        if (bucket[hash] == null) {
            bucket[hash] = new LinkedList<>();
            bucket[hash].addFirst(key);
        } else {
            if (!bucket[hash].contains(key))
                bucket[hash].addFirst(key);
        }

        size++;
        if (size > (int) (LOAD * bucket.length))
            rehash();
    }

    public void remove(int key) {
        int hash = _hash(key);

        if (bucket[hash] == null) return;

        int idx = bucket[hash].indexOf(key);
        if (idx != -1)
            bucket[hash].remove(idx);

        size--;
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        int hash = _hash(key);

        if (bucket[hash] != null) {
            return bucket[hash].contains(key);
        }

        return false;
    }

    private int _hash(int key) {
        return key % bucket.length;
    }

    private void rehash() {
        LinkedList<Integer>[] oldBucket = bucket;
        bucket = new LinkedList[2 * oldBucket.length];

        for (int i = 0; i < oldBucket.length; i++) {
            if (oldBucket[i] == null) continue;

            for (Integer key : oldBucket[i])
                add(key);
        }
    }

    public static void main(String[] args) {
        _705_MyHashSet hs = new _705_MyHashSet();

        hs.add(1);
        hs.add(2);
        System.out.println(hs.contains(3) + " " + hs.contains(2));
        hs.remove(2);
        System.out.println(hs.contains(1) + " " + hs.contains(2));
    }
}
