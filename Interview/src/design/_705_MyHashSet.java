package design;

import java.util.*;

import bst.BSTNode;
import bst.BinarySearchTree;

/**
 * Things to keep in mind while asked to desing a HashSet/HashMap
 * 1. You don't need to write a function to get a prime number for the bucket size. Just choose a 3 digit prime.
 * 2. Don't need to use Horner's rule to compute the hash. A simple key % table size is enough
 * 3. If you are choosing Linked List as the bucket type, use java's LinkedList. You don't need to implement Linked List
 * operation as part of this question.
 * 4. Rehasing logic is not required
 */
class _705_MyHashSet {
    BinarySearchTree[] bucket;
    private final double LOAD = 0.8;
    private int size = 0;

    /**
     * Initialize your data structure here.
     */
    public _705_MyHashSet() {
        bucket = new BinarySearchTree[887];
    }

    public void add(int key) {
        int hash = _hash(key);

        if (bucket[hash] == null) {
            bucket[hash] = new BinarySearchTree();
        }

        bucket[hash].put(key, -1);
        size++;

        if (size > (int) (LOAD * bucket.length))
            rehash();
    }

    public void remove(int key) {
        int hash = _hash(key);

        if (bucket[hash] != null) {
            bucket[hash].remove(key);
        }

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
        BinarySearchTree[] oldBucket = bucket;
        bucket = new BinarySearchTree[2 * oldBucket.length];

        for (int i = 0; i < oldBucket.length; i++) {
            if (oldBucket[i] == null) continue;

            List<BSTNode> allNodes = oldBucket[i].getNodes();
            for (BSTNode node : allNodes)
                add(node.key);
        }
    }
}