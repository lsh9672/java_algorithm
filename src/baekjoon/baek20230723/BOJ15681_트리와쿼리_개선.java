package baekjoon.baek20230723;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 서브 트리를 구성했을때의 노드개수를 저장하는 배열과, 부모 노드를 저장하는 배열을 통합하여 연산수를 줄여보려고 함.
 *
 * 결과는 실패 - 메모리는 더 잡아먹고 시간은 더 느려짐.
 *
 */

public class BOJ15681_트리와쿼리_개선 {

    //노드의 수
    private static int N;
    //루트의 번호
    private static int R;

    //노드 개수를 셀 때 부모쪽으로 넘어가지 않기 위해서 부모의 노드를 저장하는 배열
    private static int[] parentArray;


    //그래프를 표시
    private static List<Integer>[] graph;

    //주어진 노드를 루트로 해서 bfs탐색하는 메서드
    //방문 처리는 따로 필요 없음 - 부모노드 저장 배열을 통해서 탐색한 노드는 탐색 못하게 막음.
    private static int dfs(int currentNode){

        //자기자신을 저장.
        parentArray[currentNode] = 1;

        for(int nextNode : graph[currentNode]){

            //다음 노드가 부모 노드라면 패스
            if(parentArray[nextNode] != 0) continue;

            //노드갯수 누적.
            parentArray[currentNode] += dfs(nextNode);
        }

        return parentArray[currentNode];
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        //그래프 초기화
        graph = new List[N+1];
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }

        //그래프 셋팅
        for(int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        //부모노드 저장배열
        parentArray = new int[N + 1];


        //dfs로 각 노드를 루트로 했을떄 몇개의 서브 트리가 나오는 지 확인.
        //시작노드는 문제에서 주어진 노드.
        dfs(R);

        StringBuilder result = new StringBuilder();

        for(int i = 0; i < Q; i++){

            int startNode = Integer.parseInt(br.readLine());

            result.append(parentArray[startNode]);

            if(i != Q-1) result.append("\n");
        }

        System.out.println(result);

    }
}
