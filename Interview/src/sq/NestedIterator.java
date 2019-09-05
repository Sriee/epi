package sq;

import java.util.List;
import java.util.Stack;

import sq.NestedInteger;

import java.util.Iterator;

/**
 * Leet code. Solution -> Accepted
 * 
 * Runtime: 9ms
 * 
 * The NestedIterator object will be instantiated and called as such:
 * 		
 * {@code
 * 		NestedIterator i = new NestedIterator(nestedList);
 * 		while (i.hasNext()) {
 * 			v[f()] = i.next();
 * 		}
 * }		
 * 
 * @author sriee
 */
public class NestedIterator implements Iterator<Integer>{
	Stack<NestedInteger> stack = new Stack<>();
    
    public NestedIterator(List<NestedInteger> nestedList) {
        int length = nestedList.size() - 1;
        while(length >= 0){
            this.stack.push(nestedList.get(length));
            length--;
        }
    }

    @Override
    public Integer next() {
        return this.stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!this.stack.isEmpty()){
            if(this.stack.peek().isInteger()){
                return true;
            }
            List<NestedInteger> list = this.stack.pop().getList();
            int len = list.size() - 1;
            while(len >= 0){
                this.stack.push(list.get(len));
                len--;
            }   
        }
        return false;
    }
}

