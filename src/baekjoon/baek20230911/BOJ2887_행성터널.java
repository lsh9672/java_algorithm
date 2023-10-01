package baekjoon.baek20230911;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * mst문제이다
 * 좌표만 3차원이 되어서 두 좌표간 거리 계산식만 달라졌을뿐이다.
 * 크루스칼과 경로압축을 이용하면 무난하게 해결이 된다.
 *
 * (추가) 잘 생각해보면 10만개의 노드로 연결 될수 있는 좌표를 모두 구하면 터진다.
 * 문제를 잘 보면 3차원 좌표간 거리를 구하는 공식이 아닌, 각 좌표별로 뺀 값의 절대값중 가장 작은 값이다.
 * 따라서 노드를 각각 정렬후에 바로 인접한 노드끼리 빼주는 식으로 구하면 된다
 * 이 작업을 x,y,z 각각 해주면 된다.
 * a,b,c가 오름차순으로 정렬되어 있을 때, a - b가 무조건 a-c보다 작을 것이므로 모든 경우를 하는 것이 아닌, 정렬후에 인접한 노드와 빼기를 진행하면 된다.
 */
public class BOJ2887_행성터널 {


    //노드 정보를 저장할 객체.
    private static class Node{
        private int x, y, z, num;

        public Node(int x, int y, int z, int num){
            this.x = x;
            this.y = y;
            this.z = z;
            this.num = num;
        }

        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }

        public int getZ() {
            return z;
        }

        public int getNum() {
            return num;
        }
    }
    //엣지 정보를 저장할 객체
    private static class Edge{
        private int aNode, bNode, weight;

        public Edge(int aNode, int bNode, int weight){
            this.aNode = aNode;
            this.bNode = bNode;
            this.weight = weight;
        }

        public int getaNode() {
            return aNode;
        }

        public int getbNode() {
            return bNode;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "aNode=" + aNode +
                    ", bNode=" + bNode +
                    ", weight=" + weight +
                    '}';
        }
    }

    //노드 수를 저장할 변수
    private static int N;

    //부모 정보를 가지고 있을 배열.
    private static int[] parentArray;

    //노드 정보를 가지고 있을 배열 - 정렬용
    private static Node[] nodeArray;

    //엣지 리스트
    private static List<Edge> edgeList;

    //부모를 찾는 메서드 - 경로압축 도입
    private static int findParent(int currentNode){

        if(parentArray[currentNode] == currentNode) return currentNode;

        return parentArray[currentNode] = findParent(parentArray[currentNode]);
    }

    //두 노드를 합치는 작업을 하는 메서드
    private static boolean union(int a, int b){

        int aNode = findParent(a);
        int bNode = findParent(b);

        if(aNode == bNode) return false;

        if(aNode < bNode){
            parentArray[bNode] = aNode;
        }
        else if(aNode > bNode){
            parentArray[aNode] = bNode;
        }

        return true;

    }

    //초기화 작업 - 부모 저장배열 만들기
    private static void initParentArray(){
        parentArray = new int[N];

        for(int i = 0; i < N; i++){
            parentArray[i] = i;
        }

    }

    //엣지 만드는 메서드
    private static void initEdge(){
        edgeList = new ArrayList<>();

        //x를 기준으로 정렬
        Arrays.sort(nodeArray, (node1, node2) -> {
            return node1.getX() - node2.getX();
        });
        for(int i = 0; i < N - 1; i++){
            edgeList.add(new Edge(nodeArray[i].num, nodeArray[i + 1].num, Math.abs(nodeArray[i].getX() - nodeArray[i + 1].getX())));
        }

        //y를 기준으로 정렬
        Arrays.sort(nodeArray, (node1, node2) -> {
            return node1.getY() - node2.getY();
        });
        for(int i = 0; i < N - 1; i++){
            edgeList.add(new Edge(nodeArray[i].num, nodeArray[i + 1].num, Math.abs(nodeArray[i].getY() - nodeArray[i + 1].getY())));
        }

        //z를 기준으로 정렬.
        Arrays.sort(nodeArray, (node1, node2) -> {
            return node1.getZ() - node2.getZ();
        });
        for(int i = 0; i < N - 1; i++){
            edgeList.add(new Edge(nodeArray[i].num, nodeArray[i + 1].num, Math.abs(nodeArray[i].getZ() - nodeArray[i + 1].getZ())));
        }

        Collections.sort(edgeList, (edge1,edge2) -> {
            return edge1.getWeight() - edge2.getWeight();
        });

    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nodeArray = new Node[N];
        initParentArray(); //부모 저장 배열 초기화

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            nodeArray[i] = new Node(x,y,z, i);
        }

        initEdge(); //엣지 만들기.

        int edgeCount = 0;
        long edgeWeight = 0;

        //엣지 하나씩 반복문 돌면서 처리
        for(Edge edge : edgeList) {

            //두 노드를 합쳐봄
            if (union(edge.getaNode(), edge.getbNode())) {
                edgeCount++;
                edgeWeight += edge.getWeight();
            }

            if (edgeCount == N - 1) break;
        }

        System.out.println(edgeWeight);


    }
}
