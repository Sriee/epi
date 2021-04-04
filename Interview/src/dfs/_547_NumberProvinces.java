package dfs;

public class _547_NumberProvinces {

    /**
     * Another problem that follows Connect Component pattern. The only difference
     * is how the connectivity between cities are defined.
     *
     * Optimum Solution.
     */
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length, count = 0;
        boolean[] visited = new boolean[n];

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                count++;
                dfs(isConnected, visited, i);
            }
        }
        return count;
    }

    private void dfs(int[][] graph, boolean[] visited, int vertex) {
        visited[vertex] = true;

        for(int city = 0; city < graph[0].length; city++) {
            if(city == vertex || graph[vertex][city] == 0 || visited[city])
                continue;
            dfs(graph, visited, city);
        }
    }

    public static void main(String[] args) {
        _547_NumberProvinces np = new _547_NumberProvinces();
        int[][] graph = {{1,1,0},{1,1,0},{0,0,1}};
        System.out.println(np.findCircleNum(graph));
    }
}
