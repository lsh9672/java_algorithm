package baekjoon.baek20220901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ10282_해킹 {

    static final int INF= Integer.MAX_VALUE;

    static int[] dijkstra(int startNode, int nodeNum, List<Node>[] graph){

        int[] distances = new int[nodeNum+1];
        Arrays.fill(distances, INF);

        distances[startNode] = 0;

        PriorityQueue<Node> needVisited = new PriorityQueue<>();
        needVisited.add(new Node(startNode,0));

        while(!needVisited.isEmpty()){

            Node currentNode = needVisited.poll();

            if(distances[currentNode.node] < currentNode.weight) continue;

            for(Node nextNode : graph[currentNode.node]){

                int nextDistance = distances[currentNode.node] + nextNode.weight;

                if(distances[nextNode.node] > nextDistance){
                    distances[nextNode.node] = nextDistance;
                    needVisited.add(new Node(nextNode.node, nextDistance));
                }
            }
        }

        return distances;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int testCase = 1; testCase <= T; testCase++){

            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            List<Node>[] graph = new ArrayList[n+1];
            for(int i = 1; i <= n; i++){
                graph[i] = new ArrayList<>();
            }

            for(int i = 0; i < d; i++){
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                graph[b].add(new Node(a,s));
            }

            int[] visited = dijkstra(c,n,graph);


            int computerNum = 0;
            int time = Integer.MIN_VALUE;

            for(int i = 1; i <= n; i++){
                if(visited[i] != INF){
                    computerNum++;
                    time = Math.max(time,visited[i]);
                }
            }

            sb.append(computerNum).append(" ").append(time);
            sb.append("\n");
        }

        sb.setLength(sb.length()-1);
        System.out.println(sb);
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
