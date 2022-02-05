package graph;

import javafx.util.Pair;

import java.util.*;

public class _399_EvaluateDivision {

    /* ===========================================================================================
     * Approach 1: Union Find
     *
     * Prefer to use DFS approach for this problem.
     * ===========================================================================================
     */
    Map<String, Pair<String, Double>> groupToWeightMap;

    public double[] calcEquationUF(List<List<String>> equations, double[] values, List<List<String>> queries) {
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

    /* ===========================================================================================
     * Approach 2: Graph DFS
     * ===========================================================================================
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            List<String> equation = equations.get(i);
            String dividend = equation.get(0), divisor = equation.get(1);

            graph.putIfAbsent(dividend, new HashMap<>());
            graph.putIfAbsent(divisor, new HashMap<>());

            graph.get(dividend).put(divisor, values[i]);
            graph.get(divisor).put(dividend, 1 / values[i]);
        }

        double[] result = new double[queries.size()];
        int idx = 0;
        for (List<String> query : queries) {
            String dividend = query.get(0), divisor = query.get(1);

            if (!graph.containsKey(dividend) || !graph.containsKey(divisor)) {
                result[idx] = -1.0;
            } else if (dividend.equals(divisor)) {
                result[idx] = 1.0;
            } else {
                result[idx] = dfs(dividend, divisor, 1.0, graph, new HashSet<>());
            }
            idx++;
        }

        return result;
    }

    private double dfs(String currentNode, String targetNode, double prod, Map<String, Map<String, Double>> graph, Set<String> visited) {
        visited.add(currentNode);
        double res = -1;

        Map<String, Double> neighbors = graph.get(currentNode);
        if (neighbors.containsKey(targetNode)) {
            res = prod * neighbors.get(targetNode);
        } else {
            for (String neighbor : neighbors.keySet()) {
                if (visited.contains(neighbor))
                    continue;

                res = dfs(neighbor, targetNode, prod * neighbors.get(neighbor), graph, visited);
                if (res != -1)
                    break;
            }
        }

        visited.remove(currentNode);
        return res;
    }

    public static void main(String[] args) {
        _399_EvaluateDivision ed = new _399_EvaluateDivision();

        List<List<String>> equations = List.of(
                Arrays.asList("a", "b"),
                Arrays.asList("b", "c")
        );
        List<List<String>> queries = List.of(
                Arrays.asList("a", "c"),
                Arrays.asList("b", "a"),
                Arrays.asList("a", "e"),
                Arrays.asList("a", "a"),
                Arrays.asList("x", "x")
        );

        double[] result = ed.calcEquationUF(equations, new double[]{2.0, 3.0}, queries);
        System.out.println(Arrays.toString(result));

        // Test case 2
        equations = List.of(
                Arrays.asList("a", "b"),
                Arrays.asList("b", "c"),
                Arrays.asList("bc", "cd")
        );
        queries = List.of(
                Arrays.asList("a", "c"),
                Arrays.asList("c", "b"),
                Arrays.asList("bc", "cd"),
                Arrays.asList("cd", "bc")
        );
        result = ed.calcEquation(equations, new double[]{1.5,2.5,5.0}, queries);
        System.out.println(Arrays.toString(result));
    }
}
