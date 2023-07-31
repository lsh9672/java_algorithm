package baekjoon.baek20230731;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 아이디어 MST를 이용한 방법.
 * 이전의 mst와의 차이점은, 이미 연결된 간선이 있다는 점.
 * 이 부분은 미리 연결된것들 사이에서 최대 부모를 찾아둠.
 */

public class BOJ1774_우주신과의교감 {

    //노드 객체
    private static class Node{
        long x, y;

        public Node(long x, long y){
            this.x = x;
            this.y = y;

        }
    }

    //간선 객체
    private static class Edge implements Comparable<Edge>{
        int aNode, bNode;
        double weight;

        public Edge(int aNode, int bNode, double weight){
            this.aNode = aNode;
            this.bNode = bNode;
            this.weight = weight;
        }

        public int compareTo(Edge edge){

            return Double.compare(this.weight, edge.weight);
        }
    }

    //노드의 수
    private static int N;

    //노드를 저장하고 있을 배열 - 노드는 배열의 인덱스 번호로 표현함
    private static Node[] nodeArray;

    //간선을 저장하고 정렬하기 위한 리스트
    private static List<Edge> edgeList;

    //각 노드의 부모를 저장할 배열.
    private static int[] parents;

    //두 좌표간의 거리를 계산하는 메서드 - 피타고라스 이용.
    private static double distance(Node aNode, Node bNode){

        return Math.sqrt(Math.pow(aNode.x - bNode.x, 2) + Math.pow(aNode.y - bNode.y,2));
    }

    //각 노드 부모 배열을 자기 자신으로 초기화 하는 메서드
    private static void initParents(){

        for(int i = 1; i <= N; i++){
            parents[i] = i;
        }

    }

    //부모 찾기 및 경로 압축하는 메서드
    private static int findParent(int currentNode){

        if(parents[currentNode] == currentNode) return currentNode;

        return parents[currentNode] = findParent(parents[currentNode]);

    }

    //두 노드를 합칠수 있으면 합치고 아니면 false리턴하는 메서드
    private static boolean union(int aNode, int bNode){

        int aParentNode = findParent(aNode);
        int bParentNode = findParent(bNode);

        if(aParentNode < bParentNode){

            parents[bParentNode] = aParentNode;

            return true;
        }
        else if(aParentNode > bParentNode){

            parents[aParentNode] = bParentNode;

            return true;
        }

        return false;

    }

    //2중반복문을 돌면서 간선 추가
    private static void addEdge(){

        for(int i = 1; i <= N; i++){
            for(int j = i + 1; j <= N; j++){

                edgeList.add(new Edge(i,j,distance(nodeArray[i],nodeArray[j])));
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        nodeArray = new Node[N + 1];
        edgeList = new ArrayList<>();
        parents = new int[N + 1];

        initParents();


        //황선자를 포함한 우주신의 좌표 - 황선자인지 우주신인지는 중요하지 않음
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            nodeArray[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int edgeCount = 0;//엣지 수 세기
        double totalWeight= 0.0;//가중치 세기

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(union(a,b)){
                edgeCount++;
            }
        }

        //엣지 추가.
        addEdge();

        //정렬
        Collections.sort(edgeList);

        //반복문 돌면서 처리
        for(Edge edge : edgeList){

            int aNode = edge.aNode;
            int bNode = edge.bNode;

            if(union(aNode,bNode)){

                edgeCount++;
                totalWeight += edge.weight;


            }

            if(edgeCount == N - 1) break;
        }


        System.out.printf("%.2f", (double) Math.round(totalWeight * 100.0)/ 100.0);

    }
}
