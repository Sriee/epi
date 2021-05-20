package design;

public class DLLNode {
    String key;
    int value;

    DLLNode next, prev;

    public DLLNode(String key) {
        this.key = key;
        this.value = 1;
    }

    public DLLNode(String key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("[ %s, %d ]", key, value);
    }
}
