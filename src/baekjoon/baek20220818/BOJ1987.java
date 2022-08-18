package baekjoon.baek20220818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ1987 {

    static int R;
    static int C;
    static int count;
    static char[][] maps;
    static boolean[] alphaCheck = new boolean[26];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static void dfs(Location startNode,int value) {
        //이미 밟은 알파벳이라면 값 업데이트
        if(alphaCheck[maps[startNode.x][startNode.y] - 'A']){
            count = Math.max(count,value);
            return;
        }
        //밟은 곳이 아니라면 해당 알파벳을 방문처리
        alphaCheck[maps[startNode.x][startNode.y] - 'A'] = true;

        for (int i = 0; i < 4; i++) {

            int nextX = startNode.x + dx[i];
            int nextY = startNode.y + dy[i];

            if(nextX >= 0 && nextX < R && nextY >= 0 && nextY < C){

                dfs(new Location(nextX,nextY),value+1);

            }
        }

        alphaCheck[maps[startNode.x][startNode.y] - 'A'] = false;

    }

    public static void main (String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());


        maps = new char[R][C];

        count = Integer.MIN_VALUE;


        for (int i = 0; i < R; i++) {
            maps[i] = br.readLine().toCharArray();
        }

        //dfs탐색
        dfs(new Location(0, 0),0);


        System.out.println(count);
    }


    static class Location {
        int x;
        int y;

        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
