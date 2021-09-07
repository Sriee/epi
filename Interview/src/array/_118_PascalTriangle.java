package array;

import java.util.*;

class _118_PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> prevRow = new ArrayList<>();
        prevRow.add(1);
        res.add(prevRow);

        while (--numRows > 0) {
            List<Integer> thisRow = new ArrayList<>();
            thisRow.add(1);
            for (int i = 1; i < prevRow.size(); i++) {
                thisRow.add(prevRow.get(i) + prevRow.get(i - 1));
            }

            thisRow.add(1);
            res.add(thisRow);
            prevRow = thisRow;
        }

        return res;
    }

    public static void main(String[] args) {
        _118_PascalTriangle pt = new _118_PascalTriangle();
        int numRows = 6;
        List<List<Integer>> res = pt.generate(numRows);
        int spaces = numRows - 1;
        for (int i = 0; i < numRows; i++) {
            int j = spaces;
            String s = "";
            while (j > 0) {
                s += " ";
                j--;
            }
            System.out.println(s + res.get(i));
            spaces--;
        }
    }
}