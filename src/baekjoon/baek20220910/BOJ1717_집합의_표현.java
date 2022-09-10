package baekjoon.baek20220910;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1717_집합의_표현 {

    static int N;
    static int M;

    static int[] parents;

    static void makeParent(){
        for(int i = 0; i <= N; i++){
            parents[i] = i;
        }
    }

    static int find(int node){
        if(parents[node] == node){
            return node;
        }

        return parents[node] = find(parents[node]);
    }

    static void union(int nodeA, int nodeB){
        nodeA = find(nodeA);
        nodeB = find(nodeB);

        if(nodeA > nodeB){
            parents[nodeA] = nodeB;
        }
        else if(nodeA < nodeB){
            parents[nodeB] = nodeA;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parents = new int[N+1];
        makeParent();

        StringBuilder result = new StringBuilder();

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int operator = Integer.parseInt(st.nextToken());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            if(operator == 0){
                union(nodeA,nodeB);
            }

            else{
                if(find(nodeA) == find(nodeB)) result.append("YES");
                else result.append("NO");

                result.append("\n");
            }
        }

        System.out.println(result);

    }
}
