package baekjoon.baek20220825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ13418_prim {
    static int N;
    static int M;
    static List<Node>[] maps;

    static int prim(int type){

        int fatigue = 0;

        boolean[] visited = new boolean[N+1];
        visited[0] = true;

        PriorityQueue<Node> needVisited;

        //최적
        if(type == 1){
            needVisited = new PriorityQueue<>(Comparator.reverseOrder());
        }
        //최악
        else{
            needVisited = new PriorityQueue<>(Comparator.naturalOrder());
        }

        needVisited.addAll(maps[0]);

        int edgeCount = 0;

        while(!needVisited.isEmpty()){

            Node currentNode = needVisited.poll();

            if(!visited[currentNode.node]){
                //오르막길이면 누적
                if(currentNode.weight == 0){
                    fatigue++;
                }
                edgeCount++;
                if(edgeCount == N) break;

                visited[currentNode.node] = true;

                needVisited.addAll(maps[currentNode.node]);
            }

        }

        return fatigue;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new ArrayList[N+1];
        for(int i = 0; i <= N; i++){
            maps[i] = new ArrayList<>();
        }

        for(int i = 0; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int firstNode = Integer.parseInt(st.nextToken());
            int secondNode = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            maps[firstNode].add(new Node(secondNode,weight));
            maps[secondNode].add(new Node(firstNode,weight));
        }

        //피로도 최악
        int maxWeight = prim(0);
//        System.out.println(maxWeight);

        //최적
        int minWeight = prim(1);
//        System.out.println(minWeight);

        System.out.println(maxWeight*maxWeight - minWeight*minWeight);
    }

    static class Node implements Comparable<Node>{

        int node;
        int weight;

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
