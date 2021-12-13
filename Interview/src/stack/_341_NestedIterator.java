package stack;

import java.util.*;

public class _341_NestedIterator implements Iterator<Integer> {
    Deque<NestedInteger> stack;

    public _341_NestedIterator(List<NestedInteger> nestedList) {
        stack = new ArrayDeque<>(nestedList);
        load();
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        load();
        return !stack.isEmpty();
    }

    private void load() {
        while (!stack.isEmpty() && !stack.peek().isInteger()) {
            List<NestedInteger> lst = stack.pop().getList();
            ListIterator<NestedInteger> iter = lst.listIterator(lst.size());
            while (iter.hasPrevious()) {
                stack.push(iter.previous());
            }
        }
    }

    public static void main(String[] args) {
        List<String> inputs = Arrays.asList(
                "[1, [2, 3], 4]",
                "[3, [2, 3, 4], 4, [2, 3]]",
                "[[2, 3], 3, [2, 3], 4, [2, 3, 4, 5]]",
                "[1, [3, [4, [5, 6], 7], 8], 9]",
                "[[2, 3, [2, 3]]]",
                "[[],[3]]"
                );
        List<_341_NestedIterator> itr = new ArrayList<>();

        // Test Case 1
        List<NestedInteger> nestedList = new ArrayList<>();
        NestedInteger l1 = new NestedInteger();
        nestedList.add(new NestedInteger(1));
        l1.add(new NestedInteger(2));
        l1.add(new NestedInteger(3));
        nestedList.add(l1);
        nestedList.add(new NestedInteger(4));
        itr.add(new _341_NestedIterator(nestedList));

        // Test Case 2
        List<NestedInteger> nestedList1 = new ArrayList<>();
        NestedInteger l2 = new NestedInteger();
        nestedList1.add(new NestedInteger(3));
        l2.add(new NestedInteger(2));
        l2.add(new NestedInteger(3));
        l2.add(new NestedInteger(4));
        nestedList1.add(l2);
        nestedList1.add(new NestedInteger(4));
        nestedList1.add(l1);
        itr.add(new _341_NestedIterator(nestedList1));

        // Test Case 3
        List<NestedInteger> nestedList2 = new ArrayList<>();
        NestedInteger l3 = new NestedInteger();
        nestedList2.add(l1);
        nestedList2.add(new NestedInteger(3));
        l3.add(new NestedInteger(2));
        l3.add(new NestedInteger(3));
        l3.add(new NestedInteger(4));
        l3.add(new NestedInteger(5));
        nestedList2.add(l1);
        nestedList2.add(new NestedInteger(4));
        nestedList2.add(l3);
        itr.add(new _341_NestedIterator(nestedList2));

        // Test Case 4
        List<NestedInteger> nestedList3 = new ArrayList<>();
        nestedList3.add(new NestedInteger(1));
        NestedInteger l4 = new NestedInteger();
        l4.add(new NestedInteger(5));
        l4.add(new NestedInteger(6));
        NestedInteger l5 = new NestedInteger();
        l5.add(new NestedInteger(4));
        l5.add(l4);
        l5.add(new NestedInteger(7));
        NestedInteger l6 = new NestedInteger();
        l6.add(new NestedInteger(3));
        l6.add(l5);
        l6.add(new NestedInteger(8));
        nestedList3.add(l6);
        nestedList3.add(new NestedInteger(9));
        itr.add(new _341_NestedIterator(nestedList3));

        // Test Case 5
        List<NestedInteger> nestedList4 = new ArrayList<>();
        NestedInteger l7 = new NestedInteger();
        l7.add(new NestedInteger(2));
        l7.add(new NestedInteger(3));
        NestedInteger l8 = new NestedInteger();
        l8.add(new NestedInteger(2));
        l8.add(new NestedInteger(3));
        l8.add(l7);
        nestedList4.add(l8);
        itr.add(new _341_NestedIterator(nestedList4));

        // Test Case 6
        List<NestedInteger> nestedList6 = new ArrayList<>();
        nestedList6.add(new NestedInteger());
        nestedList6.add(new NestedInteger(3));
        itr.add(new _341_NestedIterator(nestedList6));

        for (int i = 0; i < itr.size(); i++) {
            System.out.println((i + 1) + ".\tOriginal structure: " + inputs.get(i));
            System.out.println("\n\tOutput:\n");
            while (itr.get(i).hasNext()) {
                System.out.print("\titr.Next(): ");
                System.out.println(itr.get(i).next());
            }
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
