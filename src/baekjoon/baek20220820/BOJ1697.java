package baekjoon.baek20220820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1697 {

    static int N;
    static int K;

    static int bfs(Location startNode, int endNode){
        Set<Integer> visited = new HashSet<>();

        Queue<Location> needVisited = new LinkedList<>();
        needVisited.add(startNode);

        visited.add(startNode.node);

        while(!needVisited.isEmpty()){

            Location currentNode = needVisited.poll();

            if(currentNode.node == endNode) return currentNode.count;;

            if(!visited.contains(currentNode.node-1) && currentNode.node-1 <= 100_000){
                visited.add(currentNode.node-1);
                needVisited.add(new Location(currentNode.node-1, currentNode.count+1));
            }
            if(!visited.contains(currentNode.node+1) && currentNode.node+1 <= 100_000){
                visited.add(currentNode.node+1);
                needVisited.add(new Location(currentNode.node+1, currentNode.count+1));
            }
            if(!visited.contains(2*currentNode.node) && 2*currentNode.node <= 100_000){
                visited.add(2*currentNode.node);
                needVisited.add(new Location(2*currentNode.node, currentNode.count+1));
            }

        }

        return -1;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.println(bfs(new Location(N,0),K));
    }

    static class Location{
        int node;
        int count;

        Location(int node, int count){
            this.node = node;
            this.count = count;
        }
    }
}
