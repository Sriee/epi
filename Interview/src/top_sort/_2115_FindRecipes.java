package top_sort;

import java.util.*;

public class _2115_FindRecipes {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Set<String> supplySet = new HashSet<>();
        Map<String, Integer> inDegree = new HashMap<>();
        Map<String, List<String>> graph = new HashMap<>();
        Collections.addAll(supplySet, supplies);

        for (String recipe : recipes) {
            inDegree.put(recipe, 0);
        }

        for (int i = 0; i < recipes.length; i++) {
            for (String ing : ingredients.get(i)) {
                if (supplySet.contains(ing))
                    continue;

                graph.putIfAbsent(ing, new ArrayList<>());
                graph.get(ing).add(recipes[i]);
                inDegree.put(recipes[i], inDegree.get(recipes[i]) + 1);
            }
        }

        Deque<String> queue = new ArrayDeque<>();
        for (String recipe : inDegree.keySet()) {
            if (inDegree.get(recipe) == 0) {
                queue.offer(recipe);
            }
        }

        List<String> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            String recipe = queue.poll();
            res.add(recipe);

            if (!graph.containsKey(recipe))
                continue;

            for (String ing : graph.get(recipe)) {
                inDegree.put(ing, inDegree.get(ing) - 1);
                if (inDegree.get(ing) == 0)
                    queue.offer(ing);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        _2115_FindRecipes fr = new _2115_FindRecipes();

        String[][] recipes = {
                {"bread", "sandwich"},
                {"fries", "burger", "sandwich"}
        };

        List<List<List<String>>> ingredients = Arrays.asList(
                Arrays.asList(
                        Arrays.asList("yeast", "flour"),
                        Arrays.asList("bread", "meat")
                ),
                Arrays.asList(
                        Arrays.asList("potatoes", "oil"),
                        Arrays.asList("sauce", "onion", "fries", "pepper", "bun", "meat"),
                        Arrays.asList("cheese", "fish", "bread", "sauce")
                )
        );

        String[][] supplies = {
                {"yeast", "flour", "meat"},
                {"sauce", "fish", "onion", "pepper", "bun", "bread", "meat", "cheese", "potatoes"}
        };

        for (int i = 0; i < recipes.length; i++) {
            List<String> res = fr.findAllRecipes(recipes[i], ingredients.get(i), supplies[i]);
            System.out.println(res);
        }
    }
}
