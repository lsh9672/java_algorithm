package baekjoon.baek20230728;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * 아이디어
 * mst를 활용하는 문제
 * 모든 별자리는 연결될 수 있는데, 모든 별자리가 연결되는 최소 비용을 구하는 것이므로 최소신장트리로 볼수 있다.
 */

public class BOJ4386_별자리_만들기 {

    //노드 정보 저장할 클래스
    private static class Node{
        double x, y;

        public Node(double x, double y){
            this.x = x;
            this.y = y;
        }
    }

    //간선 정보
    private static class Edge implements Comparable<Edge>{
        int x, y;
        double weight;

        public Edge(int x, int y, double weight){
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {


            return Double.compare(this.weight, edge.weight);
        }
    }

    private static int N;//노드 수
    private static Node[] nodeList;//각 노드 정보를 저장하고 있을 배열.

    //간선을 선택했을때, 사이클이 생성되는지 확인하기 위해, 최대 부모를 저장하는 배열
    private static int[] parents;

    //엣지를 저장하는 리스트.
    private static List<Edge> edgeList;

    //엣지 추가
    private static void addEdge(){

        for(int i = 1; i <= N; i++){

            Node aNode = nodeList[i];

            for(int j = 1; j <= N; j++){

                if(i == j) continue;

                Node bNode = nodeList[j];

                edgeList.add(new Edge(i,j,distance(aNode,bNode)));

            }
        }
    }

    //두 좌표 사이 거리 구하기
    private static double distance(Node aNodeInfo, Node bNodeInfo){

        //직선거리이므로 맨해튼이 아닌 피타고라스를 이용해서 구해준다.
        return Math.sqrt(Math.pow(aNodeInfo.x - bNodeInfo.x,2) + Math.pow(aNodeInfo.y - bNodeInfo.y,2));
    }

    //부모 저장하는 배열 자기 자신으로 초기화
    private static void initParent(){

        for(int i = 1; i <= N; i++){

            parents[i] = i;

        }

    }


    //최대 부모 찾는 메서드
    private static int findParent(int currentNode){

        //자기 자신이면 최대 부모
        if(parents[currentNode] == currentNode) return currentNode;

        return parents[currentNode] = findParent(parents[currentNode]);

    }

    //두간선을 선택하면 두 노드를 합치는 메서드
    private static boolean union(int aNode, int bNode){
        //부모노드 찾기
        int aParentNode = findParent(aNode);
        int bParentNode = findParent(bNode);


        //수가 작은 노드쪽을 부모로 함.
        if(aParentNode < bParentNode){
            parents[bParentNode] = aParentNode;
            return true;
        }
        else if(aParentNode > bParentNode){
            parents[aParentNode] = bParentNode;
            return true;
        }

        //두 수가 같으면 합칠 수 없음. 사이클 발생함.
        return false;

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nodeList = new Node[N+1]; //노드 번호는 1번부터 사용.

        // 좌표 입력받기
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());

            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            //노드만들고 저장.
            nodeList[i] = new Node(x,y);

        }
        //엣지 저장 리스트
        edgeList = new ArrayList<>();

        parents = new int[N+1];
        initParent(); //부모 저장 노드 초기화.


        //엣지 추가
        addEdge();

        //크루스칼이므로 엣지 가중치 작은 순으로 정렬
        Collections.sort(edgeList);

        //연결한 엣지 개수 저장할 변수
        int edgeCount = 0;

        //엣지 가중치 저장 할 변수.
        double weightTotalValue = 0.0;

        //반복은 모든 노드가 연결되어야 되기 때문에 엣지개수가 노드개수 - 1 될때까지 반복.
        //엣지 하나씩 꺼내서 확인
        for(Edge edge : edgeList){

            int aNode = edge.x;
            int bNode = edge.y;

            //합칠수 있는 지 확인.
            if(union(aNode,bNode)){

                edgeCount++;
                weightTotalValue += edge.weight;

            }
        }

        //소수점 두자리로 출력.

        int temp = (int) (weightTotalValue * 100.0);

        System.out.println(temp / 100.0);

    }
}
