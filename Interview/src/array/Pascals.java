package array;

import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

public class Pascals {

    private int pascal(int i, int j) {
        if (j == 0)
            return 1;
        else if (j == i)
            return 1;
        else
            return pascal(i - 1, j - 1) + pascal(i - 1, j);
    }

    private List<List<Integer>> print_pascals_triangle(int num) {
        List<List<Integer>> master = new LinkedList<>();
        for (int i = 0; i < num; i++) {
            List<Integer> list = new LinkedList<>();
            for (int j = 0; j <= i; j++) {
                list.add(this.pascal(i, j));
            }
            master.add(list);
        }
        return master;
    }

    public static void main(String[] args) {
        Pascals p = new Pascals();
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        List<List<Integer>> result = p.print_pascals_triangle(num);
        for (List<Integer> item : result) {
            System.out.println(item.toString());
        }
        scan.close();
    }
}
