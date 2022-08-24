package baekjoon.baek20220824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17070 {

    static int N;
    static int count = 0; //최종적으로 출력할 값.(경우의 수 누적)
    static int[][] maps;


    // 재귀로 하나씩 돌면서 끝점이 목적지에 도달하면 갯수 증가.
    static void dfs(Location currentNode) {
        // 목적지에 도달하면 경우의 수 증가시키고 종료.
        if(currentNode.x == N-1 && currentNode.y == N-1) {
            count++;
            return;
        }

        //현 방향이 가로일때
        if(currentNode.dir == 0) {

            // 맵을 벗어나지 않았다면 - 재귀 호출
            if(currentNode.y + 1 < N && maps[currentNode.x][currentNode.y+1] == 0) {
                dfs(new Location(currentNode.x,currentNode.y+1,0));
            }
        }

        //현 방향이 세로일때
        else if(currentNode.dir == 1) {

            if(currentNode.x + 1 < N && maps[currentNode.x+1][currentNode.y] == 0) {
                dfs(new Location(currentNode.x + 1, currentNode.y , 1));
            }

        }

        //현 방향이 대각선일때.
        else if(currentNode.dir == 2) {

            // 가로 방향으로 이동
            if(currentNode.y+1 < N && maps[currentNode.x][currentNode.y+1] == 0) {
                dfs(new Location(currentNode.x,currentNode.y+1, 0));
            }
            //세로방향으로 이동
            if(currentNode.x+1 < N && maps[currentNode.x+1][currentNode.y] == 0) {
                dfs(new Location(currentNode.x+1, currentNode.y, 1));

            }

        }

        //방향에 상관없이 대각선 이동은 동일하기 때문에 한번에 처리.
        //대각선의 경우, 우,하, 대각선, 전부다 갈수 있어야 됨.
        if(currentNode.x + 1 < N && currentNode.y + 1 < N && maps[currentNode.x+1][currentNode.y+1] == 0 && maps[currentNode.x][currentNode.y+1] == 0 && maps[currentNode.x+1][currentNode.y] == 0 ) {
            dfs(new Location(currentNode.x+1, currentNode.y+1, 2));
        }

    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        maps = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(new Location(0,1,0));

        System.out.println(count);

    }

    //좌표는 파이프의 시작점이 아닌 끝점을 가리키고 있고, 방향은 가로: 0, 세로 : 1, 대각선: 2로 구성한다.
    static class Location{
        int x;
        int y;
        int dir;

        Location(int x, int y, int dir){
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

    }
}
