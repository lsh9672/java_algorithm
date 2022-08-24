package baekjoon.baek20220824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7576 {

    static int N;
    static int M;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] maps;

    //토마토 확인
    static int check() {

        int max = -2;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(maps[i][j] == 0) {
                    return 0;
                }
                else if(maps[i][j] != -1) {
                    max = Integer.max(max, maps[i][j]);
                }
            }
        }

        return max;
    }

    static void bfs(Queue<Location> needVisited, boolean[][] visited) {

        while(!needVisited.isEmpty()) {

            Location currentNode = needVisited.poll();

            for(int i = 0; i < 4; i++) {
                int nextX = currentNode.x + dx[i];
                int nextY = currentNode.y + dy[i];

                if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && !visited[nextX][nextY] && maps[nextX][nextY] == 0) {
                    visited[nextX][nextY] = true;
                    needVisited.add(new Location(nextX,nextY));
                    maps[nextX][nextY] = maps[currentNode.x][currentNode.y] + 1;
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        maps = new int[N][M];

        //지도 입력
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //토마토 위치 찾기.
        Queue<Location> needVisited = new LinkedList<>();
        boolean[][] visited =new boolean[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(maps[i][j] == 1) {
                    needVisited.add(new Location(i,j));
                    visited[i][j] = true;
                }
            }
        }

        //탐색전에 이미 모든 토마토가 익어있는지 확인
        int temp  = check();

        //이미 모든 토마토가 익어있다면
        if(temp-1 == 0) {
            System.out.println(0);
        }

        //0이 한개라도 존재하면
        else {
            bfs(needVisited,visited);
            int result = check();
            if(result == 0) {
                System.out.println(-1);
            }
            else {
                System.out.println(result-1);
            }

        }


    }

    static class Location{
        int x;
        int y;

        Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

}
