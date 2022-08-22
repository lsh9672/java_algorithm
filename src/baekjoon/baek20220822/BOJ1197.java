package baekjoon.baek20220822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * MST 기본유형 - 크루스칼을 이용한 풀이
 */

public class BOJ1197 {

    static int V;
    static int E;

    //부모 노드 정보를 담고 있는 배열
    static int[] parentArray;

    // 대표 부모 찾기
    static int findParent(int currentNode){
        if(parentArray[currentNode] == currentNode){
            return currentNode;
        }

        return parentArray[currentNode] = findParent(parentArray[currentNode]);
    }

    //두 서로소 집합 그래프를 병합 - 대표 노드를 파라미터로 받음
    static void union(int firstNode, int secondNode){

        if(firstNode < secondNode){
            parentArray[secondNode] = firstNode;
        }
        else{
            parentArray[firstNode] = secondNode;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        //인덱스 1번부터 사용.
        parentArray = new int[V+1];
        //초기에는 부모대신 자기 자신을 넣음
        for(int i = 1; i < V; i++){
            parentArray[i] = i;
        }

        //간선 담을 배열
        Edge[] edgeList = new Edge[E];

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            edgeList[i] = new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        //가중치 오름차순으로 정렬
        Arrays.sort(edgeList);

        int totalWeight = 0;

        //간선 갯수누적
        int countEdge = 0;

        for(Edge edge : edgeList){
            int firstNode = findParent(edge.a);
            int secondNode = findParent(edge.b);

            //두 집합의 대표 노드가 다르다면 병합
            if(firstNode != secondNode){
                union(firstNode,secondNode);

                //가중치 누적
                totalWeight += edge.weight;

                countEdge++;
            }

            if(countEdge == V-1) break;
        }

        System.out.println(totalWeight);
    }

    static class Edge implements Comparable<Edge>{
        int a;
        int b;
        int weight;

        Edge(int a, int b, int weight){
            this.a = a;
            this.b = b;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.weight - edge.weight;
        }
    }
}
