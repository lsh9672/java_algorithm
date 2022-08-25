package ssafy.algorism.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DijkstraTest {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());

        int[][] maps = new int[V][V];
        for(int i= 0; i < V; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < V; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int start = 0;
        int end = V-1;

        int[] D = new int[V];
        boolean[] visited = new boolean[V];

        Arrays.fill(D,Integer.MAX_VALUE);

        D[start] = 0;

        int min, minVertex;

        for(int i = 0; i < V; i++){
            min = Integer.MAX_VALUE;
            minVertex = -1;
            for(int j = 0; j < V; j++){
                if(!visited[j] && min>D[j]){
                    min = D[j];
                    minVertex = j;
                }
            }

            //방문처리
            visited[minVertex] = true;

            //선택된 정점을 경유지로 해서 미방문 정점들로 가는 비용을 따져보고 기존 최적해보다 유리하면 갱신
            for(int j = 0; j < V; j++){
                if(!visited[j] && maps[minVertex][j] > 0 && D[j] > D[minVertex] + maps[minVertex][j]){
                    D[j] = D[minVertex] + maps[minVertex][j];
                }
            }
        }


        System.out.println(D[end]);


    }
}
