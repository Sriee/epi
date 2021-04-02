package tree;

public class SegmentTreeNode {
    int start, end, minIndex;
    SegmentTreeNode left, right;

    public SegmentTreeNode(int start, int end) {
        this.start = start;
        this.end = end;

        this.left = null;
        this.right = null;
    }

    @Override
    public String toString() {
        return "SegmentTree(start= " + this.start +" , end= " + this.end + ", minIdx= " + this.minIndex + ")";
    }
}
