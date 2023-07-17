package baekjoon.baek20230717;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 중간이 될 수 없으려면 특정 수 k보다 작은 수가 전체 개수를 N이라고 했을 때,
 * N/2 보다 많거나, k보다 큰 수가 N/2개보다 많으면 된다.
 * 결국, 각 노드마다 해당노드보다 무게가 작은 수, 해당 노드보다 무게가 무거운 개수를 저장해주면 된다.
 * a -> b가 주어졌을때, b가 더 무거운 무게라면 1, 더 가벼운 무게라면 -1로 표시해서 값을 구해준다.
 */

public class BOJ2617_구슬찾기 {

    private static int N;
    private static int M;

    private static int[][] graph;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N+1][N+1];

        for(int i = 0; i < M; i++){

            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //무거운쪽으로 가면 1, 가벼운쪽으로 향하면 -1
            graph[a][b] = -1;
            graph[b][a] = 1;
        }

        //플로이드 워셜을 이용해서 모든 경우를 다 탐색

        //k가 거쳐가는 곳.
        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){

                    //출발지에서 거쳐가는 곳이 0이면 연결이 되어있지 않음.
                    //또한 출발지에서 거쳐가는 곳 까지의 값과, 거쳐가는 곳에서 도착지의 값이 같을ㄷ 때만 누적함
                    // a -> b -> c (a < b < c) 라고 하면 a < c가 성립
                    //하지만 a -> b는 1이지만 b -> c -1이라고 한다면 a < b, b > c 이므로, a와 c의 대소관계를 비교할 수 없음.
                    if(graph[i][k] == 0 || (graph[i][k] != graph[k][j])) continue;

                    //위의 조건이 아니라면, 대소비교가 가능함.
                    graph[i][j] = graph[i][k];
                }
            }
        }

        //각 노드별로, 노드보다 큰것의 수, 작은것의 수를 따로 세어줌
        int[] biggerCount = new int[N+1];
        int[] smallerCount = new int[N+1];

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){

                //자기자신으로 가는 것은 패스
                if(i == j) continue;

                //1이면 자신보다 크다는 뜻
                if(graph[i][j] == 1){
                    biggerCount[i]++;
                }
                //자신보다 작은것이라는 뜻.
                else if(graph[i][j] == -1){
                    smallerCount[i]++;
                }
            }
        }


        //결과를 반환할 변수
        int result = 0;

        //각 노드별로, 해당 노드보다 큰것, 작은 것의 수가 N/2 수보다 크다면 중간 불가.
        for(int i = 1; i <= N; i++){

            if(biggerCount[i] > (N/2) || smallerCount[i] > (N/2)){
                result++;
            }

        }

        System.out.println(result);

    }
}
