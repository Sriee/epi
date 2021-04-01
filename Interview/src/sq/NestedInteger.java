package sq;

import java.util.List;

public interface NestedInteger {

    /**
     * Helper function
     * 
     * @return true if this NestedInteger holds a single integer, rather than a
     *         nested list. false otherwise
     */
    public boolean isInteger();

    /**
     * Helper function
     * 
     * @return the single integer that this NestedInteger holds, if it holds a
     *         single integer null if this NestedInteger holds a nested list
     */
    public Integer getInteger();

    /**
     * Helper function
     * 
     * @return the nested list that this NestedInteger holds, if it holds a nested
     *         list null if this NestedInteger holds a single integer
     */
    public List<NestedInteger> getList();
}
