package baekjoon.baek20220711;

/**
 * 백준 1240번 (그래프, 골드5)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Second {

    static int bfs(int startNode,int endNode, int[][] graph, int n){
        int[] visited = new int[n+1];

        Queue<Integer[]> needVisited = new LinkedList<>();

        Integer[] node = {startNode,0};
        needVisited.add(node);

        while(!needVisited.isEmpty()){

            Integer[] currentNode = needVisited.poll();

            if(currentNode[0] == endNode){
                return currentNode[1];
            }

            for(int i = 1; i < n+1; i++){
                if(graph[currentNode[0]][i] != 0 && visited[i] == 0){
                    Integer[] nextNode = {i,currentNode[1]+ graph[currentNode[0]][i]};
                    visited[i] = 1;
                    needVisited.add(nextNode);

                }
            }
        }

        return -1;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] graph = new int[n+1][n+1];

        for(int i = 0; i < n-1; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[a][b] = weight;
            graph[b][a] = weight;

        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int firstNode = Integer.parseInt(st.nextToken());
            int secondNode = Integer.parseInt(st.nextToken());

            int result = bfs(firstNode,secondNode,graph,n);
            if(result != -1){
                System.out.println(result);
            }

        }

    }
}
