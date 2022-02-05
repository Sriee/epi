package graph;

import javafx.util.*;
import java.util.*;

public class _399_EvaluateDivision {
    Map<String, Pair<String, Double>> groupToWeightMap;

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        groupToWeightMap = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String dividend = equation.get(0), divisor = equation.get(1);

            union(dividend, divisor, values[i]);
        }

        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String dividend = query.get(0), divisor = query.get(1);

            if (!groupToWeightMap.containsKey(dividend) || !groupToWeightMap.containsKey(divisor)) {
                result[i] = -1.0;
            } else if (dividend.equals(divisor)) {
                result[i] = 1.0;
            } else {
                Pair<String, Double> dividendEntry = find(dividend), divisorEntry = find(divisor);

                if (!dividendEntry.getKey().equals(divisorEntry.getKey())) {
                    result[i] = -1.0;
                } else {
                    result[i] = dividendEntry.getValue() / divisorEntry.getValue();
                }
            }
        }

        return result;
    }

    private Pair<String, Double> find(String vertex) {
        if (!groupToWeightMap.containsKey(vertex)) {
            groupToWeightMap.put(vertex, new Pair<String, Double>(vertex, 1.0));
        }

        Pair<String, Double> entry = groupToWeightMap.get(vertex);
        if (!entry.getKey().equals(vertex)) {
            Pair<String, Double> parentEntry = find(entry.getKey());
            groupToWeightMap.put(
                    vertex,
                    new Pair<>(parentEntry.getKey(), entry.getValue() * parentEntry.getValue()));
        }

        return groupToWeightMap.get(vertex);
    }

    private void union(String dividend, String divisor, double weight) {
        Pair<String, Double> dividendEntry = find(dividend), divisorEntry = find(divisor);

        if (!dividendEntry.getKey().equals(divisorEntry.getKey())) {
            groupToWeightMap.put(
                    dividendEntry.getKey(),
                    new Pair<>(
                            divisorEntry.getKey(),
                            divisorEntry.getValue() * weight / dividendEntry.getValue()
                    )
            );
        }
    }

    public static void main(String[] args) {
        _399_EvaluateDivision ed = new _399_EvaluateDivision();

        List<List<String>> equations = List.of(
                Arrays.asList("a", "b"),
                Arrays.asList("b", "c")
        );
        List<List<String>> queries = List.of(
                Arrays.asList("a","c"),
                Arrays.asList("b","a"),
                Arrays.asList("a","e"),
                Arrays.asList("a","a"),
                Arrays.asList("x","x")
        );

        double[] result = ed.calcEquation(equations, new double[] { 2.0, 3.0 }, queries);
        System.out.println(Arrays.toString(result));
    }
}
