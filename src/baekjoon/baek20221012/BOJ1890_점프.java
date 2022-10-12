package baekjoon.baek20221012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 12.
 */
public class BOJ1890_점프 {

    static int N;

    //오른쪽, 왼쪽
    static int[] dx = {1,0};
    static int[] dy = {0,1};

    static int[][] maps;
    static long[][] dp;

    static long dfs(Node currentNode){


        //목적지에 도달하면 1을 리턴
        if(currentNode.x == N-1 && currentNode.y == N-1){
            return 1;
        }

        //목적지가 아닌데 방문한 곳이라면,
        if(dp[currentNode.x][currentNode.y] != -1){
            return dp[currentNode.x][currentNode.y];
        }

        //위에 조건에 걸리지 않았다면 해당 위치 방문처리
        dp[currentNode.x][currentNode.y] = 0;

        for(int i = 0; i < 2; i++){
            int nextX = currentNode.x + dx[i] * maps[currentNode.x][currentNode.y];
            int nextY = currentNode.y + dy[i] * maps[currentNode.x][currentNode.y];

            if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N){
                dp[currentNode.x][currentNode.y] += dfs(new Node(nextX,nextY));
            }

        }

        return dp[currentNode.x][currentNode.y];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        maps = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new long[N][N];
        for(int i = 0; i < N; i++){
            Arrays.fill(dp[i],-1);
        }

        dfs(new Node(0,0));

        System.out.println(dp[0][0]);
    }

    static class Node{
        int x, y;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
