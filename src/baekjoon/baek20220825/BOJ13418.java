package baekjoon.baek20220825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ13418 {

    static int N;
    static int M;
    static int[] parents;


    //피로도 구하기.
    static int fatigueUpdate(List<Node> edgeList){

        make();

        int edgeCount = 0;
        int fatigueCount = 0;

        for(Node node : edgeList){

            if(union(node.startNode, node.endNode)){
                edgeCount++;
                if(node.weight == 0){
                    fatigueCount++;
                }
            }

            if(edgeCount == N) break;
        }

        return fatigueCount;
    }

    static void make(){

        parents = new int[N+1];
        for(int i = 0; i <= N; i++){
            parents[i] = i;
        }
    }

    static int find(int node){
        if(parents[node] == node) return node;

        return parents[node] = find(parents[node]);
    }

    static boolean union(int firstNode, int secondNode){
        firstNode = find(firstNode);
        secondNode = find(secondNode);

        if(firstNode < secondNode){
            parents[secondNode] = firstNode;
            return true;
        }

        else if(firstNode > secondNode){
            parents[firstNode] = secondNode;
            return true;
        }
        else return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<Node> edgeList = new ArrayList<>();

        for(int i = 0; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            edgeList.add(new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        //피로도 최적
        edgeList.sort(Comparator.reverseOrder());
        int minWeight = fatigueUpdate(edgeList);

        //최악
        edgeList.sort(Comparator.naturalOrder());
        int maxWeight = fatigueUpdate(edgeList);



        System.out.println(maxWeight*maxWeight - minWeight*minWeight);
    }

    static class Node implements Comparable<Node>{

        int startNode;
        int endNode;
        int weight;

        Node(int startNode,int endNode, int weight){
            this.startNode = startNode;
            this.endNode = endNode;
            this.weight = weight;
        }


        @Override
        public int compareTo(Node node) {
            return this.weight - node.weight;
        }

    }
}
