package design;

import bst.BinarySearchTree;

class _706_MyHashMap {
    BinarySearchTree[] bucket;

    /**
     * Initialize your data structure here.
     */
    public _706_MyHashMap() {
        bucket = new BinarySearchTree[769];
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        int hash = _hash(key);

        if (bucket[hash] == null) {
            bucket[hash] = new BinarySearchTree();
        }

        bucket[hash].put(key, value);
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int hash = _hash(key);

        if (bucket[hash] != null) {
            return bucket[hash].get(key);
        }

        return -1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int hash = _hash(key);

        if (bucket[hash] != null) {
            bucket[hash].remove(key);
        }
    }

    private int _hash(int key) {
        return key % bucket.length;
    }
}