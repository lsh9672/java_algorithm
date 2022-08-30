package baekjoon.baek20220830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11404_플로이드 {

    static int N;
    static int M;
    static int[][] graph;
    static int INF = 999999999;

    static void floydWarshall(){


        for(int i  = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                for(int k = 1; k <= N; k++){
                    if(graph[j][k] > graph[j][i] + graph[i][k]){
                        graph[j][k] = graph[j][i] + graph[i][k];
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new int[N+1][N+1];
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(i != j){
                    graph[i][j] = INF;
                }
            }
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a][b] = Math.min(graph[a][b],c);
        }



        floydWarshall();

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < N+1; i++){
            for(int j = 1; j < N+1; j++){
                if(graph[i][j] == INF){
                    sb.append(0).append(" ");
                }
                else{
                    sb.append(graph[i][j]).append(" ");
                }

            }
            sb.setLength(sb.length()-1);
            sb.append("\n");
        }

        sb.setLength(sb.length()-1);
        System.out.println(sb);

    }
}
