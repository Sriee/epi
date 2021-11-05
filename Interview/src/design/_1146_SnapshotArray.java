package design;

import java.util.*;

class _1146_SnapshotArray {

    private int snapId = 0, length;
    private TreeMap<Integer, Integer>[] array;

    public _1146_SnapshotArray(int length) {
        this.length = length;
        this.array = new TreeMap[length];
        for (int i = 0; i < length; i++) {
            this.array[i] = new TreeMap<>();
        }
    }

    public void set(int index, int val) {
        this.array[index].put(this.snapId, val);
    }

    public int snap() {
        this.snapId++;
        return this.snapId - 1;
    }

    public int get(int index, int snap_id) {
        TreeMap<Integer, Integer> map = this.array[index];
        Integer val = map.get(snap_id);

        if (val != null)
            return val;
        else if (map.isEmpty())
            return 0;
        else
            return map.lastEntry().getValue();
    }

    public static void main(String[] args) {
        _1146_SnapshotArray sa = new _1146_SnapshotArray(2);
        sa.snap();
        System.out.println(sa.get(1, 0));
        System.out.println(sa.get(0, 0));
    }
}
