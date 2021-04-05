package tree;

import java.util.*;

/**
 * Wrong Answer: The implementation steps are correct. We missed the requirement for ls
 *
 * ls can contain both file path and a directory path and the result should in lexicographic order
 * Using Trie is the correct solution to the problem.
 *
 * TODO: Redo the implementation using Trie.
 */
public class _588_FileSystem {

    private class Node {
        private String dirName = "";
        private Map<String, Node> children = new HashMap<>();
        private Map<String, StringBuilder> files = new HashMap<>();

        public Node(String name) {
            this.dirName = name;
        }

        public void addContentToFile(String fileName, String content) {
            files.putIfAbsent(fileName, new StringBuilder());
            files.get(fileName).append(content);
        }

        public String getContentFromFile(String fileName) {
            return files.get(fileName).toString();
        }
    }

    private Node root;
    public _588_FileSystem() {
        this.root = new Node("/");
    }

    public List<String> ls(String path) {
        Node iter = root;
        for(String dir : path.split("/")) {
            if(dir.equals("")) continue;
            iter = iter.children.get(dir);
        }

        return new ArrayList<>(iter.children.keySet());
    }

    public void mkdir(String path) {
        Node iter = root;
        for(String dir : path.split("/")) {
            if(dir.equals(""))
                continue;

            if(!iter.children.containsKey(dir)) {
                iter.children.put(dir, new Node(dir));
            }

            iter = iter.children.get(dir);
        }
    }

    public void addContentToFile(String filePath, String content) {
        Node iter = root;
        String[] path = filePath.split("/");
        int i = 1;
        for(; i < path.length - 2; i++)
            iter = iter.children.get(path[i]);
        iter.addContentToFile(path[i], content);
    }

    public String readContentFromFile(String filePath) {
        Node iter = root;
        String[] path = filePath.split("/");
        int i = 1;
        for(; i < path.length - 2; i++)
            iter = iter.children.get(path[i]);

        return iter.getContentFromFile(path[i]);
    }

    public static void main(String[] args) {
        _588_FileSystem fs = new _588_FileSystem();

        fs.mkdir("/a/b/c");
        fs.addContentToFile("/a/b/c/d", "hello");
        fs.addContentToFile("/a/b/c/d", " world");
        System.out.println(fs.ls("/"));
        System.out.println(fs.ls("/a/b"));
        fs.addContentToFile("/a/b/f1", "Hi Mate!");
        System.out.println(fs.readContentFromFile("/a/b/f1"));
        System.out.println(fs.readContentFromFile("/a/b/c/d"));
    }
}
