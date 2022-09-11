package baekjoon.baek20220911;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1325_효율적인_해킹_최적화 {

    static int N;
    static int M;

    static int maxValue = Integer.MIN_VALUE;

    static List<Value> saveNode;

    static List<Integer>[] graph;
    static boolean[] startCheck;

    static int bfs(int startNode){

        boolean[] visited = new boolean[N+1];
        visited[startNode] = true;

        Queue<Integer> needVisited = new LinkedList<>();
        needVisited.add(startNode);

        int count = 1;

        while(!needVisited.isEmpty()){

            Integer currentNode = needVisited.poll();

            for(Integer nextNode : graph[currentNode]){
                if(!visited[nextNode]){
                    count++;
                    visited[nextNode] = true;
                    needVisited.add(nextNode);
                    startCheck[nextNode] = true;
                }
            }
        }

        maxValue = Math.max(maxValue, count);
        return count;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        startCheck = new boolean[N+1];

        graph = new ArrayList[N+1];
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[b].add(a);
        }

        saveNode = new ArrayList<>();

        for(int i = 1; i <= N; i++){
            if(!startCheck[i]){
                System.out.println("test");
                startCheck[i] = true;
                int temp = bfs(i);
                saveNode.add(new Value(i,temp));
            }
        }

        StringBuilder sb = new StringBuilder();
        for(Value temp : saveNode){
            if(temp.value == maxValue){
                sb.append(temp.node).append(" ");
            }
        }

        sb.setLength(sb.length()-1);
        System.out.println(sb);

    }

    static class Value{
        int node, value;

        Value(int node, int value){
            this.node = node;
            this.value = value;
        }
    }
}
