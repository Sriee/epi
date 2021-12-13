package stack;

import java.util.*;

public class NestedInteger {
    List<NestedInteger> list = null;
    int value;

    public NestedInteger() {}

    // Constructor initializes a single value.
    public NestedInteger(int value) {
        this.value = value;
    }

    // @return true if this NestedInteger holds an integer value.
    public boolean isInteger() {
        return this.list == null;
    }

    // @return the single file that this NestedDirectories holds, if it holds a single value
    // Return null if this NestedDirectories holds a nested list
    public int getInteger() {
        return this.value;
    }

    // Set this NestedDirectories to hold a single file.
    public void setInteger(int value) {
        this.list = null;
        this.value = value;
    }

    // Set this NestedDirectories to hold a nested list and adds a nested file to it.
    public void add(NestedInteger ni) {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        this.list.add(ni);
    }

    // @return the nested list that this NestedDirectories holds, if it holds a nested list
    // Return null if this NestedDirectories holds a single file
    public List<NestedInteger> getList() {
        return this.list;
    }
}