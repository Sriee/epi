package array;

import java.util.*;

class _119_PascalTriangleII {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> prev = new ArrayList<>(), temp, curr = new ArrayList<>();
        prev.add(1);

        while (rowIndex-- > 0) {
            curr.add(1);
            for (int i = 0; i < prev.size() - 1; i++)
                curr.add(prev.get(i) + prev.get(i + 1));

            curr.add(1);
            temp = prev;
            prev = curr;
            curr = temp;
            curr.clear();
        }

        return prev;
    }

    public static void main(String[] args) {
        _119_PascalTriangleII pt2 = new _119_PascalTriangleII();

        for (int i : new int[]{3, 5, 9}) {
            List<Integer> res = pt2.getRow(i);
            System.out.println(res);
        }
    }
}