package baek20220710;

/**
 * 백준 1260번 (그래프 연습, 실버2)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Second {

    static StringBuilder bfs(Integer[][] graph,int n,int startNode){

        StringBuilder result = new StringBuilder();

        Integer[] visited = new Integer[n+1];
        Arrays.fill(visited,0);

        visited[startNode] = 1;

        Queue<Integer> needVisited = new LinkedList<>();
        needVisited.add(startNode);

        while(needVisited.size() != 0){

            int currentNode = needVisited.poll();

            result.append(currentNode).append(" ");

            for(int nextNode = 1; nextNode < n+1; nextNode++){
                if(graph[currentNode][nextNode] == 1 && visited[nextNode] == 0){
                    needVisited.add(nextNode);
                    visited[nextNode] = 1;
                }

            }

        }

        result.setLength(result.length() -1);

        return result;
    }

    static StringBuilder dfs(Integer[][] graph,int n, int startNode){
        StringBuilder result = new StringBuilder();

        Integer[] visited = new Integer[n+1];
        Arrays.fill(visited,0);

        Stack<Integer> needVisited = new Stack<>();
        needVisited.add(startNode);

        while(needVisited.size() != 0){
            int currentNode = needVisited.pop();

            if(visited[currentNode] == 0) {
                result.append(currentNode).append(" ");
                visited[currentNode] = 1;
                for (int nextNode = n; nextNode > 0; nextNode--) {
                    if (graph[currentNode][nextNode] == 1) {
                        needVisited.add(nextNode);
                    }

                }
            }

        }

        result.setLength(result.length() -1);
        return result;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int startNode = Integer.parseInt(st.nextToken());

        Integer[][] graph = new Integer[n+1][n+1];
        for (Integer[] integers : graph) {
            Arrays.fill(integers,0);
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = 1;
            graph[b][a] = 1;
        }

//        bfs(graph,n,startNode);
        System.out.println(dfs(graph,n,startNode));
        System.out.println(bfs(graph,n,startNode));
    }
}
