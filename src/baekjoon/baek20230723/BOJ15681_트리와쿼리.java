package baekjoon.baek20230723;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * (메모리 초과 해결)
 * dfs를 이용해서 탐색하면서, 각 노드별로 자식노드의 수(자신도 포함.)가 몇개인지를 저장해두도록 한다.
 * 노드와 개수를 구하는 쿼리의 수가 10만개로 굉장히 많기 때문에, 매 쿼리마다 탐색하는 것은 비효율적이다.
 * 따라서 입력된 값을 가지고 그래프를 구성했고, 부모 노드를 구하기 위해서 탐색을 할때 자식노드의 개수를 세이브하는 것이 좋다,.
 * 기존 bfs대신에 dfs를 이용해서, 리프노드에 도달하면, 노드 수를 리턴한다.
 * 리프노드의 경우에는 1이 될것이고, 상위 노드에 재귀 형식으로 반환되면서 최종적으로 해당 노드를 루트로 하면 몇개의 노드가 나오는 지 알수 있게 된다.
 */

public class BOJ15681_트리와쿼리 {

    //노드의 수
    private static int N;
    //루트의 번호
    private static int R;

    //노드 개수를 셀 때 부모쪽으로 넘어가지 않기 위해서 부모의 노드를 저장하는 배열
    private static int[] parentArray;

    //각 노드 별로 하위에 몇개의 노드가 있는지
    private static int[] subNodeCount;

    //그래프를 표시
    private static List<Integer>[] graph;

    //주어진 노드를 루트로 해서 bfs탐색하는 메서드
    //방문 처리는 따로 필요 없음 - 부모노드 저장 배열을 통해서 탐색한 노드는 탐색 못하게 막음.
    private static int dfs(int currentNode){

        //자기자신을 저장.
        subNodeCount[currentNode] = 1;

        for(int nextNode : graph[currentNode]){

            //다음 노드가 부모 노드라면 패스
            if(parentArray[currentNode] == nextNode) continue;

            //배열에 부모노드 저장.
            parentArray[nextNode] = currentNode;

            //노드갯수 누적.
            subNodeCount[currentNode] += dfs(nextNode);
        }

        return subNodeCount[currentNode];
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
        //최상위 루트의 부모는 -1로 표시
        parentArray[R] = -1;

        //하위노드 저장 배열.
        subNodeCount = new int[N + 1];

        //dfs로 각 노드를 루트로 했을떄 몇개의 서브 트리가 나오는 지 확인.
        //시작노드는 문제에서 주어진 노드.
        dfs(R);

        StringBuilder result = new StringBuilder();

        for(int i = 0; i < Q; i++){

            int startNode = Integer.parseInt(br.readLine());

            result.append(subNodeCount[startNode]);

            if(i != Q-1) result.append("\n");
        }

        System.out.println(result);

    }
}
