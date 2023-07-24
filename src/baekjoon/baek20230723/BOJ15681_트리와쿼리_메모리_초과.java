package baekjoon.baek20230723;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 트리로 만들고 각 정점을 시작점으로 해서 그래프 탐색하듯이 해주면 된다.
 * 단 주의 할점은 문제에서 유향 그래프가 아닌 무향 그래프를 주었기 때문에, 각 노드의 부모를 기록해두었다가,
 * 그래프 탐색시에 인접노드로 부모노드를 선택하는 것을 피해주어야 한다.
 *
 * (메모리 초과 해결)
 * dfs를 이용해서 탐색하면서, 각 노드별로 자식노드의 수(자신도 포함.)가 몇개인지를 저장해두도록 한다.
 * 노드와 개수를 구하는 쿼리의 수가 10만개로 굉장히 많기 때문에, 매 쿼리마다 탐색하는 것은 비효율적이다.
 * 따라서 입력된 값을 가지고 그래프를 구성했고, 부모 노드를 구하기 위해서 탐색을 할때 자식노드의 개수를 세이브하는 것이 좋다,.
 * 기존 bfs대신에 dfs를 이용해서, 리프노드에 도달하면, 노드 수를 리턴한다.
 * 리프노드의 경우에는 1이 될것이고, 상위 노드에 재귀 형식으로 반환되면서 최종적으로 해당 노드를 루트로 하면 몇개의 노드가 나오는 지 알수 있게 된다.
 */

public class BOJ15681_트리와쿼리_메모리_초과 {

    //노드의 수
    private static int N;
    //루트의 번호
    private static int R;

    //노드 개수를 셀 때 부모쪽으로 넘어가지 않기 위해서 부모의 노드를 저장하는 배열
    private static int[] parentArray;

    //그래프를 표시
    private static List<Integer>[] graph;

    //부모를 저장하는 메서드 - 전체 트리의 루트는 주어짐.
    private static void makeParent(){
        boolean[] visited = new boolean[N+1];
        visited[R] = true;

        Queue<Integer> needVisited = new ArrayDeque<>();
        needVisited.add(R);

        while(!needVisited.isEmpty()){

            int currentNode = needVisited.poll();

            for(int nextNode : graph[currentNode]){
                if(!visited[nextNode]){
                    //부모 저장
                    parentArray[nextNode] = currentNode;
                    visited[nextNode] = true;
                    needVisited.add(nextNode);
                }

            }
        }
    }

    //주어진 노드를 루트로 해서 bfs탐색하는 메서드
    private static int bfs(int startNode){

        int count = 1;

        Queue<Integer> needVisited = new ArrayDeque<>();
        needVisited.add(startNode);

        while(!needVisited.isEmpty()){

            int currentNode = needVisited.poll();

            for(int nextNode : graph[currentNode]){

                //부모 노드이면 패스
                if(parentArray[nextNode] == 0 || parentArray[currentNode] == nextNode) continue;

                count++;
                needVisited.add(nextNode);
            }
        }


        return count;
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
        parentArray = new int[N+1];

        //부모노드 저장
        makeParent();

        StringBuilder result = new StringBuilder();

        for(int i = 0; i < Q; i++){

            int startNode = Integer.parseInt(br.readLine());

            result.append(bfs(startNode));

            if(i != Q-1) result.append("\n");
        }

        System.out.println(result);

    }
}
