package baekjoon.baek20220907;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1388_바닥장식 {

    static int N;
    static int M;
    static int result = 0;

    static int[][] dx = {{-1,1},{0,0}};
    static int[][] dy = {{0,0},{-1,1}};

    static char[][] maps;
    static boolean[][] visited;

    static void bfs(int currentX, int currentY, char type){
        Queue<Location> needVisited = new LinkedList<>();
        needVisited.add(new Location(currentX,currentY));

        visited[currentX][currentY] = true;

        while(!needVisited.isEmpty()){

            Location currentNode = needVisited.poll();


            for(int i = 0; i < 2; i++){
                int dir = 0;
                if(type == '-') dir = 1;
                else dir = 0;

                int nextX = currentNode.x + dx[dir][i];
                int nextY = currentNode.y + dy[dir][i];


                if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && !visited[nextX][nextY] && maps[nextX][nextY] == type){
                    needVisited.add(new Location(nextX,nextY));
                    visited[nextX][nextY] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new char[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++){
            maps[i] = br.readLine().toCharArray();
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(!visited[i][j]){
                    bfs(i,j,maps[i][j]);
                    result++;
                }
            }
        }

        System.out.println(result);

    }

    static class Location{
        int x, y;

        Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
