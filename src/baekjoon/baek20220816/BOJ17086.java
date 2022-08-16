package baekjoon.baek20220816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17086 {

    static int N;
    static int M;
    static int result;

    //상하좌우 대각선
    static int[] dx = {-1,1,0,0,-1,-1,1,1};
    static int[] dy = {0,0,-1,1,-1,1,-1,1};
    static int[][] maps;

    static int bfs(Location startNode){
        boolean[][] visited = new boolean[N][M];
        visited[startNode.x][startNode.y] = true;

        Queue<Location> needVisited = new LinkedList<>();
        needVisited.add(startNode);

        while(!needVisited.isEmpty()){

            Location currentNode = needVisited.poll();

            for(int i = 0; i < 8; i++){
                int nextX = currentNode.x + dx[i];
                int nextY = currentNode.y + dy[i];

                if(nextX >=0 && nextX < N && nextY >= 0 && nextY < M && !visited[nextX][nextY]){
                    if(maps[nextX][nextY] == 1){
                        return currentNode.count+1;
                    }
                    visited[nextX][nextY] = true;
                    needVisited.add(new Location(nextX,nextY, currentNode.count+1));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = Integer.MIN_VALUE;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(maps[i][j] == 0){
//                    System.out.println(bfs(new Location(i,j,0)));
                    result = Math.max(result,bfs(new Location(i,j,0)));
                }
            }
        }

        System.out.println(result);

    }

    static class Location{
        int x;
        int y;
        int count;

        Location(int x, int y,int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
