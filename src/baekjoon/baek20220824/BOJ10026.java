package baekjoon.baek20220824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ10026 {

    static int N;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static char[][] maps;

    static void bfs(Location startNode, boolean[][] visited, char color,int type) {

        Queue<Location> needVisited = new LinkedList<>();
        needVisited.add(startNode);

        while(!needVisited.isEmpty()) {

            Location currentNode = needVisited.poll();

            for(int i = 0; i < 4; i++) {
                int nextX = currentNode.x + dx[i];
                int nextY = currentNode.y + dy[i];

                if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && !visited[nextX][nextY]) {

                    // 정상일때는 그냥 해당 색깔에 맞는것만 탐색
                    if(type == 1) {
                        if(maps[nextX][nextY] == color) {
                            visited[nextX][nextY] = true;
                            needVisited.add(new Location(nextX,nextY));
                        }

                    }
                    // 적록 색약일때는 나뉨
                    else {
                        //이 두색상의 경우에는 차이 구별 못함.
                        if(color == 'R' || color == 'G') {
                            if(maps[nextX][nextY] == 'R' || maps[nextX][nextY] == 'G') {
                                visited[nextX][nextY] = true;
                                needVisited.add(new Location(nextX,nextY));
                            }
                        }
                        else {
                            if(maps[nextX][nextY] == color) {
                                visited[nextX][nextY] = true;
                                needVisited.add(new Location(nextX,nextY));
                            }
                        }

                    }
                }
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        maps = new char[N][N];

        for(int i = 0 ; i < N; i++) {
            maps[i] = br.readLine().toCharArray();
        }

        // 적록색약x
        boolean[][] normalVisited = new boolean[N][N];

        //적록색약 o
        boolean[][] notNormalVisited = new boolean[N][N];

        // 적록색약 x
        int result1 = 0;
        // 적록색약o
        int result2 = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {

                // 적록 색약 x 처리
                if(!normalVisited[i][j]) {
                    normalVisited[i][j] = true;
                    bfs(new Location(i,j), normalVisited, maps[i][j],1);
                    result1++;
                }
                // 적록 색약 o 처리
                if(!notNormalVisited[i][j]) {
                    notNormalVisited[i][j] = true;
                    bfs(new Location(i,j), notNormalVisited, maps[i][j],2);
                    result2++;
                }
            }
        }

//		System.out.println(result1);
//		System.out.println(result2);

        System.out.println(result1 + " " + result2);



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