package baekjoon.baek20220825;

import java.io.*;
import java.util.*;

public class BOJ1753 {

    static int V;
    static int E;
    static final int INF = Integer.MAX_VALUE;

    static List<Node>[] graph;

    static int[] dijkstra(int startNode){

        //거리 저장할 배열 - 초기에는 최대값으로 채워 넣음
        int[] distance = new int[V+1];
        Arrays.fill(distance,INF);

        distance[startNode] = 0;

        //pq를 이용해서 시간 단축
        PriorityQueue<Node> needVisited = new PriorityQueue<>();

        needVisited.add(new Node(startNode, 0));

        while(!needVisited.isEmpty()){

            Node currentNode = needVisited.poll();

            if(distance[currentNode.node] < currentNode.weight) continue;

            for(Node nextNode : graph[currentNode.node]){

                int nextDistance = distance[currentNode.node] + nextNode.weight;
                //기존에 저장된 값보다 새로 구한 값이 더 작다면 업데이트
                if(distance[nextNode.node] > nextDistance){
                    distance[nextNode.node] = nextDistance;
                    needVisited.add(new Node(nextNode.node,nextDistance));
                }
            }
        }
        return distance;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //정점
        V = Integer.parseInt(st.nextToken());
        //간선
        E = Integer.parseInt(st.nextToken());

        //시작 노드 번호
        int K = Integer.parseInt(br.readLine());

        graph = new LinkedList[V+1];

        for(int i = 1; i <= V; i++){
            graph[i] = new LinkedList<>();
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int tempStart = Integer.parseInt(st.nextToken());
            int tempEnd = Integer.parseInt(st.nextToken());
            int tempWeight = Integer.parseInt(st.nextToken());

            graph[tempStart].add(new Node(tempEnd,tempWeight));
        }

//        StringBuilder sb = new StringBuilder();
        int[] resultDistance = dijkstra(K);
        for(int i = 1; i <= V; i++){
            if(resultDistance[i] == INF){
//                sb.append("INF").append("\n");
                wr.write("INF\n");

            }
            else{
//                sb.append(resultDistance[i]).append("\n");
                wr.write(resultDistance[i]+ "\n");
            }

        }

//        sb.setLength(sb.length()-1);
//        wr.write(sb.toString());
        wr.flush();
//        System.out.println(sb);

    }

    static class Node implements Comparable<Node>{
        int node, weight;

        Node(int node, int weight){
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            return this.weight - node.weight;
        }
    }
}
