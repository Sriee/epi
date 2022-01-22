package trie;

public class TNode {

    public String word;
    public TNode[] children = null;

    public TNode() {
        this.children = new TNode[26];
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < 26; i++) {
            if (this.children[i] != null) {
                sb.append((char) ('a' + i));
                sb.append(", ");
            }
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return "TNode(" + word + ", " + sb + ")";
    }
}
