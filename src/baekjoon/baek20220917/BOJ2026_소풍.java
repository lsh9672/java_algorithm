package baekjoon.baek20220917;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2026_소풍 {

    static int K;
    static int N;
    static int F;

    static Set<Integer>[] graph;
    static boolean[] visited;

    //새로 방문하려고 하는 노드와 이미 방문한 노드들이 친구인지 확인
    static boolean friendsCheck(int node){

        for(int i = 1; i <= N; i++){
            //이미 방문한 노드이고 새로운 노드와 연결되어있지 않다면 false
            if(visited[i] && !graph[i].contains(node)) return false;
        }

        return true;

    }

    static boolean dfs(int node, int count){

        //k명의 친구를 구했으면 종료
        if(count >= K){
            return true;
        }

        for(int nextNode = 1; nextNode <= N; nextNode++){
            if(graph[node].contains(nextNode)){
                if(!visited[nextNode] && friendsCheck(nextNode)){
                    visited[nextNode] = true;
                    if(dfs(nextNode,count+1)) return true;
                    visited[nextNode] = false;
                }
            }
        }

        return false;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        graph = new HashSet[N+1];

        for(int i = 1; i <= N; i++){
            graph[i] = new HashSet<>();
        }

        for(int i = 0; i < F; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        boolean check = false;
        for(int i = 1; i <= N; i++){

            visited = new boolean[N+1];
            //인접한 노드수가 총 친구 수-1보다 작으면 볼필요 없음
            if(graph[i].size() >= K-1){
                visited[i] = true;
                if(dfs(i,1)){
                    check = true;
                    break;
                }

            }
        }


        if(check){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i <= N; i++){
                if(visited[i]){
                    sb.append(i).append("\n");
                }
            }
            sb.setLength(sb.length()-1);
            System.out.println(sb);
        }
        else{
            System.out.println(-1);
        }



    }
}
