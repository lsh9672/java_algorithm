package baekjoon.baek20230724;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 1 -> 2, 2 -> 3이 주어지면 1 -> 3이라는 것을 알 수 있음을 이용한 문제
 * 플로이드 워셜로 해결하는 대표적인 유형이다.
 */
public class BOJ10159_저울 {

    private static int N; //물건의 수
    private static int[][] graph; //플로이드 워셜 결과를 저장할 배열.
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        graph = new int[N][N];

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            // a < b 이면 a -> b는 1로 표시, 반대의 경우는 -1로 표시.
            graph[a][b] = 1;
            graph[b][a] = -1; //무거운쪽에서 가벼운쪽으로 가는 것은 -1;
        }

        //플로이드 워셜을 이용해서 모든 배열 채우기
        //k는 거쳐가는 노드, i가 출발지, j가 목적지.
        for(int k = 0; k < N; k++){
            for(int i = 0; i < N; i++){

                for(int j = 0; j < N; j++){

                    if(graph[i][k] == 0 || (graph[i][k] != graph[k][j])) continue;

                    graph[i][j] = graph[i][k];
                }
            }
        }



        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){

            int count = 0;

            for(int j : graph[i]){
                if(j != 0) continue;

                count++;
            }

            //0인것들은 알수 없는 것이지만, 자기자신도 포함되어있기 때문에 빼주어야 됨.
            sb.append(count - 1);
            if(i < N - 1){
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }
}
