package baekjoon.baek20220824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3055 {

    static int R;
    static int C;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static char[][] maps;

    static int bfs(Queue<Location> needVisited, boolean[][] visited) {

        while(!needVisited.isEmpty()) {

            Location currentNode = needVisited.poll();

            for(int i = 0; i < 4; i++) {
                int nextX = currentNode.x + dx[i];
                int nextY = currentNode.y + dy[i];

                if(nextX >= 0 && nextX < R && nextY >= 0 && nextY < C ){
                    //고슴도치 일때.
                    if(currentNode.type == 1) {

                        if(maps[nextX][nextY] == 'D') {
                            return currentNode.count + 1;
                        }

                        else if(maps[nextX][nextY] == '.' && !visited[nextX][nextY]) {
                            visited[nextX][nextY] = true;
                            needVisited.add(new Location(nextX,nextY,1,currentNode.count+1));
                        }

                    }
                    //물일때.
                    else {

                        if(maps[nextX][nextY] == '.') {
                            maps[nextX][nextY] = '*';
                            needVisited.add(new Location(nextX,nextY,2,currentNode.count+1));
                        }


                    }
                }
            }
        }

        return -1;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        maps = new char[R][C];

        for(int i = 0; i < R; i++) {
            maps[i] = br.readLine().toCharArray();
        }

        //비버와 물의 위치를 구해서 물 - 비버 순으로 큐에 넣음
        Queue<Location> needVisited = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        Location hedgehogLoc = null;

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(maps[i][j] == '*') {
                    needVisited.add(new Location(i,j,2,0));
                }

                if(maps[i][j] == 'S') {
                    visited[i][j] = true;
                    hedgehogLoc = new Location(i,j,1,0);
                }
            }
        }

        // 마지막에 고슴도치 위치 넣음.
        needVisited.add(hedgehogLoc);

        int result = bfs(needVisited,visited);

        if(result == -1) {
            System.out.println("KAKTUS");
        }
        else {
            System.out.println(result);
        }



    }

    static class Location{
        int x;
        int y;
        int type;
        int count;

        Location(int x, int y, int type,int count){
            this.x = x;
            this.y = y;
            this.type = type;
            this.count = count;
        }
    }

}
