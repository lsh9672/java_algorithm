package programmers.prog20220912;

/*
 1. S -> A , S -> B 합승을 하지 않는 경우.
 2. 합승을 하는 경우
    2-1. 합승을 하는 경우에는
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class 카카오공채2021_합승_택시_요금 {


    static final int INF = Integer.MAX_VALUE;
    static List<Node>[] graph;

    static int[] dijkstra(int n, int startNode){

        int[] distances = new int[n+1];
        Arrays.fill(distances,INF);
        distances[startNode] = 0;

        PriorityQueue<Node> needVisited = new PriorityQueue<>();
        needVisited.add(new Node(startNode,0));

        while(!needVisited.isEmpty()){

            Node currentNode = needVisited.poll();

            if(currentNode.weight > distances[currentNode.node]) continue;

            for(Node nextNode : graph[currentNode.node]){
                int nextDistance = distances[currentNode.node] + nextNode.weight;

                if(nextDistance < distances[nextNode.node]){
                    distances[nextNode.node] = nextDistance;
                    needVisited.add(new Node(nextNode.node, nextDistance));
                }
            }
        }

        return distances;

    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] fare : fares){
            graph[fare[0]].add(new Node(fare[1],fare[2]));
            graph[fare[1]].add(new Node(fare[0],fare[2]));
        }

        //따로 따로 a,b지점으로 가는 경우.
        int[] firstDistances = dijkstra(n,s);
        answer = Math.min(answer, firstDistances[a] + firstDistances[b]);

        //s가 아닌 각 지점에서 가는 모든 배열
        for(int startNode = 1; startNode <= n; startNode++){
            if(startNode != s){
                int[] tempDistances = dijkstra(n,startNode);
                answer = Math.min(answer, tempDistances[a] + tempDistances[b] + firstDistances[startNode]);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        카카오공채2021_합승_택시_요금 tt = new 카카오공채2021_합승_택시_요금();
        int n1 = 6;
        int s1 = 4;
        int a1 = 6;
        int b1 = 2;
        int[][] fares1 = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
        System.out.println(tt.solution(n1,s1,a1,b1,fares1));

        int n2 = 7;
        int s2 = 3;
        int a2 = 4;
        int b2 = 1;
        int[][] fares2 = {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}};
        System.out.println(tt.solution(n2,s2,a2,b2,fares2));


        int n3 = 6;
        int s3 = 4;
        int a3 = 5;
        int b3 = 6;
        int[][] fares3 = {{2,6,6}, {6,3,7}, {4,6,7}, {6,5,11}, {2,5,12}, {5,3,20}, {2,4,8}, {4,3,9}};
        System.out.println(tt.solution(n3,s3,a3,b3,fares3));
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
