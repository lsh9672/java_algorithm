package baekjoon.baek20221026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : sh Lee
 * @date : 22. 10. 26.
 */
public class BOJ13549_숨바꼭질3 {

    static int N,K;

    static int[] dir = {-1,1};

    static int bfs(){
        boolean[] visited = new boolean[100001];
        visited[N] = true;

        PriorityQueue<Node> needVisited = new PriorityQueue<>();
        needVisited.add(new Node(N,0));

        while(!needVisited.isEmpty()){

            Node currentNode = needVisited.poll();

            visited[currentNode.num] = true;

            if(currentNode.num == K) return currentNode.time;

            int nextNode = currentNode.num*2;

            if(nextNode <= 100000 && nextNode >= 0 && !visited[nextNode]){
                needVisited.add(new Node(nextNode,currentNode.time));
            }

            for(int i = 0; i < 2; i++){
                nextNode = currentNode.num + dir[i];

                if(nextNode <= 100000 && nextNode >= 0 && !visited[nextNode]){
                    needVisited.add(new Node(nextNode,currentNode.time + 1));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.println(bfs());
    }

    static class Node implements Comparable<Node>{
        int num, time;

        Node(int num, int time){
            this.num = num;
            this.time = time;
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(this.time, node.time);
        }
    }
}
