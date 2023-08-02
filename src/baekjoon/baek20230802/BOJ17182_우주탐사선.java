package baekjoon.baek20230802;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 전형적인 백트래킹
 * 1. 시작위치에서부터 갈수 있는 위치로 간다
 * 2. 자기 자신을 다시 호출하고, 이동 거리의 최소값을 누적한다.
 * 3. 갔던곳을 다시 갈수 있기 때문에 반드시, 이전에 누적된 최소값을 넘어서면 종료해주는 조건을 넣어준다.
 * 4. 이와 같이 재귀를 이용해서 모든 경우를 다 처리해주면 최소값을 구하게 된다.
 *
 *
 * ---
 * 중복해서 노드를 방문할 수 있기 떄문에 그냥 재귀돌리면 터진다
 * 따라서 플로이드 워셜을 이용해서 각 노드에서 다른 노드로 가는 최단거리를 먼저 구해주고 dfs 처리를 해야된다.
 */
public class BOJ17182_우주탐사선 {

    private static int N;//행성의 개수
    private static int K;//행성의 위치
    private static int[][] graph;//각 행성으로 가는 가중치를 저장할 2차원 배열

    private static int minTime;//최소 시간을 저장할 변수

    //재귀 호출을 하면서 모든 경우를 다 해볼 메서드
    //파라미터는 순서대로 현재 노드, 현재까지 누적된 시간, 몇개의 노드를 방문했는지 체크한 변수, 방문한 노드를 방문처리한 배열.
    private static void backTracking(int currentNode, int currentTime, int checkCount, boolean[] visited){

        //저장 된 시간보다 크거나 같으면 종료
        if(minTime <= currentTime) return;

        //모든 노드를 방문했다면(checkCount == N 이라면), 누적된 시간을 저장함.
        if(checkCount == N){
            minTime = Math.min(minTime, currentTime);
            return;
        }


        //반복문을 돌면서 해당 노드와 연결된 모든 노드에 방문함.
        for(int nextNode = 0; nextNode < N; nextNode++){
            if(nextNode == currentNode || visited[nextNode]) continue; //자기 자신이거나 방문했으면 패스

            //해당 노드를 방문하지 않았다면

            visited[nextNode] = true;
            backTracking(nextNode, currentTime + graph[currentNode][nextNode], checkCount + 1 , visited);
            visited[nextNode] = false;

        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //중복해서 갈 수 있기 때문에 플로이드 워셜로 각 지점으로 가는 최단거리를 업데이트 해준다.
        for(int z = 0; z < N; z++){
            for(int x = 0; x < N; x++){
                if(z == x) continue;
                for(int y = 0; y < N; y++){
                    if(z == y) continue;

                    graph[x][y] = Math.min(graph[x][y], graph[x][z] + graph[z][y]);
                }
            }
        }

        minTime = Integer.MAX_VALUE;

        boolean[] visited = new boolean[N];
        visited[K] = true;

        backTracking(K, 0, 1, visited);

        System.out.println(minTime);
    }
}
