package baekjoon.baek20220913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1405_미친_로봇 {

    static int N; //이동 횟수
    static double result; //총 퍼센트 저장.

    //동서남북
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static double[] dirPercentage;

    static boolean[][] visited;

    static void dfs(Location currentNode, int depth, double percentage){

        if(depth >= N){
            result += percentage;
            return;
        }

        for(int i = 0; i < 4; i++){
            int nextX = currentNode.x + dx[i];
            int nextY = currentNode.y + dy[i];

            if(nextX >= 0 && nextX < N*2+1 && nextY >= 0 && nextY < N*2+1 && !visited[nextX][nextY]){
                visited[currentNode.x][currentNode.y] = true;
                dfs(new Location(nextX,nextY),depth+1, percentage * dirPercentage[i]);
                visited[currentNode.x][currentNode.y] = false;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        dirPercentage = new double[4];
        for(int i = 0; i < 4; i++){
            dirPercentage[i] = Integer.parseInt(st.nextToken()) * 0.01;
        }

        visited = new boolean[N*2+1][N*2+1];

        Location startNode = new Location(N,N);
        visited[N][N] = true;

        dfs(startNode,0,1.0);

        System.out.println(result);

    }

    static class Location{
        int x,y;
        Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
