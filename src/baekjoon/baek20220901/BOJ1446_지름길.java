package baekjoon.baek20220901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1446_지름길 {

    static int N;
    static int D;
    static List<Node>[] graph;


    static int dijkstra(){
        int[] distances = new int[D+1];
        Arrays.fill(distances, Integer.MAX_VALUE);

        distances[0] = 0;

        PriorityQueue<Node> needVisited = new PriorityQueue<>();
        needVisited.add(new Node(1,1));

        while(!needVisited.isEmpty()){

            Node currentNode = needVisited.poll();

            if(distances[currentNode.endNode] < currentNode.weight) continue;

            for(int i = 0)
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        graph = new ArrayList[D+1];

        for(int i = 0; i < D+1; i++){
            graph[i] = new ArrayList<>();

            graph[i].add(new Node(i+1,1));
        }

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int startNode = Integer.parseInt(st.nextToken());
            int endNode = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            if(startNode > endNode) continue;

            if(endNode - startNode <= weight) continue;

            graph[startNode].add(new Node(endNode,weight));
        }




    }

    static class Node{
        int endNode, weight;

        Node(int endNode, int weight){
            this.endNode = endNode;
            this.weight = weight;
        }
    }
}
