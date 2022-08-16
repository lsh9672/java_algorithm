package baekjoon.baek20220808;

/**
 * BOJ 16713
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class fourth {

    static int N;
    static int[][] maps;
    static int[] dx = {1,0};
    static int[] dy = {0,1};

    static boolean bfs(){
        boolean[][] visited = new boolean[N][N];
        visited[0][0] = true;

        Queue<Location> needVisted = new LinkedList<>();
        needVisted.add(new Location(0,0));

        while(!needVisted.isEmpty()){

            Location currentLoc = needVisted.poll();

            if(maps[currentLoc.x][currentLoc.y] == 0) continue;

            for(int i = 0; i < 2; i++){
                int nextX = currentLoc.x + dx[i] * maps[currentLoc.x][currentLoc.y];
                int nextY = currentLoc.y + dy[i] * maps[currentLoc.x][currentLoc.y];

                if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && visited[nextX][nextY] == false){
                    if(maps[nextX][nextY] == -1) return true;

                    visited[nextX][nextY] = true;
                    needVisted.add(new Location(nextX,nextY));

                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        maps = new int[N][N];
        for(int i= 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if(bfs()) System.out.println("HaruHaru");
        else System.out.println("Hing");


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
