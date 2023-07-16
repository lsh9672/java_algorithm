package baekjoon.baek20230716;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 1516번
 *
 * 아이디어
 * 1. 전형적인 위상정렬 문제
 * 2. 각 그래프의 관계와 해당 노드로 들어오는 진입차수의 수를 저장한다.
 * 3. 해당 노드를 탐색하기 위해서는 진입차수가 0이 되어야 한다.
 * 4. 각 건물 즉, 노드를 짓는데 걸리는 시간을 노드의 가중치로 생각
 * 5. 각 건물을 짓기 위해 먼저 지어져야 하는 건물들의 번호를 해당 노드로 들어오는 진입 차수로 count해준다.
 * 6. 먼저 지어져야 하는 건물번호와 해당 건물을 그래프처럼 연결해준다.
 * 7. 연결은 단방향으로 해야된다.
 * 8. 건물번호는 1부터 N 까지로 할 것이므로, 배열로 표현하는 것이 좋다.
 */
public class BOJ1516_게임개발 {

    //노드 수
    private static int N;

    //그래프 표현할 배열. - 인접노드는 리스트로 표현.
    private static List<Integer>[] graph;

    //해당 노드의 가중치를 표현할 배열
    private static int[] weightArray;

    //진입차수 표현할 배열.
    private static int[] inDegree;

    //그래프 탐색시에 방문처리할 배열
    private static int[] visited;

    //그래프 탐색할 메서드
    private static void bfs(){

        //방문 처리한 노드의 가중치값을 저장할 배열 - 시간이므로 해당 노드로 온 가중치중에 가장 큰 값을 구해야됨
        visited = new int[N+1];

        //방문처리 큐
        Queue<Integer> needVisited = new LinkedList<>();

        //진입차수가 0인 노드만 방문처리 큐에 넣기
        for(int i = 1; i <= N; i++){

            //진입차수가 0이면 시작노드로 추가, 방문처리노드에 값 넣기
            if(inDegree[i] == 0){
                needVisited.add(i);
                visited[i] = weightArray[i];
            }
        }

        //반복돌면서 탐색
        while(!needVisited.isEmpty()){

            //노드 하나 꺼내서 확인
            Integer currentNode = needVisited.poll();

            //해당 노드와 연결된 노드의 진입차수 -1
            for(Integer nextNode : graph[currentNode]){

                inDegree[nextNode]--;

                //가중치 추가 - 해당 노드의 가중치와, 진입 노드의 가중치를 더한 값과, 기존의 값을 비교
                visited[nextNode] = Math.max(visited[nextNode], weightArray[nextNode] + visited[currentNode]);

                //진입 차수가 0이면 다음 탐색노드로 추가
                if(inDegree[nextNode] == 0){
                    needVisited.add(nextNode);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        //그래프 배열 초기화
        graph = new List[N+1];
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }

        //가중치 배열
        weightArray = new int[N+1];

        //진입차수 저장
        inDegree = new int[N+1];

        //노드 개수만큼 반복
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());

            int weight = Integer.parseInt(st.nextToken());

            int tempNode = Integer.parseInt(st.nextToken());
            //가중치 저장
            weightArray[i] = weight;

            //-1이 아닐때까지 반복.
            while(tempNode != -1){

                //선행되어야 되는 노드 저장
                graph[tempNode].add(i);
                inDegree[i]++;

                tempNode = Integer.parseInt(st.nextToken());
            }
        }


        bfs();

        StringBuilder result = new StringBuilder();
        for(int i = 1; i <= N; i++){

            result.append(visited[i]);

            if(i != N) result.append("\n");
        }

        System.out.println(result);
    }
}
