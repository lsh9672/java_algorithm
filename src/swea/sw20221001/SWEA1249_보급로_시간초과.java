package swea.sw20221001;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class SWEA1249_보급로_시간초과 {


    static int N;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int[][] maps;
    static int[][] dp;

    static void dfs(Node currentNode) {

        if(currentNode.x == N-1 && currentNode.y == N-1){
            return;
        }

        for(int i = 0; i < 4; i++) {
            int nextX = currentNode.x + dx[i];
            int nextY = currentNode.y + dy[i];

            if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) {

                if(dp[nextX][nextY] > dp[currentNode.x][currentNode.y] + maps[nextX][nextY]){
                    dp[nextX][nextY] = dp[currentNode.x][currentNode.y] + maps[nextX][nextY];
                    dfs(new Node(nextX,nextY));
                }

            }
        }

    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new FileInputStream("/home/leesh/lsh/my_study/java_study/algorism_java/src/swea/sw20221001/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int testCase = 1; testCase <= T; testCase++) {
            N = Integer.parseInt(br.readLine());
            maps = new int[N][N];
            dp = new int[N][N];
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    dp[i][j] = 999999;
                }
            }

            for(int i = 0; i < N; i++) {
                String temp = br.readLine();
                for(int j = 0; j < N; j++) {
                    maps[i][j] = Integer.parseInt(temp.charAt(j)+"");
                }
            }
            dp[0][0] = 0;
            dfs(new Node(0,0));

            System.out.println("#"+testCase + " "+dp[N-1][N-1]);


        }

    }

    static class Node{
        int x, y;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

}