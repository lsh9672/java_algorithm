package baekjoon.baek20220904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ14938_서강그라운드 {

    static int N;
    static int M;
    static int R;

    static final int INF = Integer.MAX_VALUE;
    static int result = Integer.MIN_VALUE; // 획득가능한 최대 아이템 수 저장.

    static int[] nodeValue; //각 노드가 가지고 있는 아이템 수.
    static List<Node>[] graph;

    static void maxValueUpdate(int[] distances, int startNode){
        int tempTotal = 0;

        //거리가 M이하면 누적.
        for(int i = 1; i <= N; i++){
            if(distances[i] <= M){
                tempTotal += nodeValue[i];
            }
        }

        result = Math.max(result,tempTotal);
    }

    static int[] dijkstra(int startNode){
        int[] distances = new int[N+1];
        Arrays.fill(distances,INF);
        distances[startNode] = 0;

        PriorityQueue<Node> needVisited = new PriorityQueue<>();
        needVisited.add(new Node(startNode,0));

        while(!needVisited.isEmpty()){

            Node currentNode = needVisited.poll();

            if(distances[currentNode.node] < currentNode.weight) continue;

            for(Node nextNode : graph[currentNode.node]){
                int nextDistance = distances[currentNode.node] + nextNode.weight;

                if(nextDistance < distances[nextNode.node]){
                    distances[nextNode.node] = nextDistance;
                    needVisited.add(new Node(nextNode.node,nextDistance));
                }
            }
        }



        return distances;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());


        /*그래프 입력*/
        graph = new ArrayList[N+1];

        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }

        /*각 노드별 아이템 개수 셋팅*/
        nodeValue = new int[N+1];
        st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++){
            nodeValue[i] = Integer.parseInt(st.nextToken());
        }

        /*간선과 가중치*/
        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b,l));
            graph[b].add(new Node(a,l));
        }

        //각 노드를 시작노드로 해서 다익스트라 실행
        for(int i = 1; i <= N; i++){
            int[] tempDistance = dijkstra(i);

            //구한 거리를 이용해서 최대값 업데이트
            maxValueUpdate(tempDistance,i);
        }

        System.out.println(result);
    }

    static class Node implements Comparable<Node>{
        int node, weight;

        Node(int node, int weight){
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}
