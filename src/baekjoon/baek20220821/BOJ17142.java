package baekjoon.baek20220821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 연구소3
 */

public class BOJ17142 {

    static int N;
    static int M;
    static int minResult = Integer.MAX_VALUE;
    static int[][] maps;

    static List<Location> virusLoc;
    // 상하좌우
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};


    static int bfs(Location[] startNode) {
        boolean[][] visited = new boolean[N][N]; // 방문배열

        int[][] tempMaps = new int[N][N];

        int maxValue = Integer.MIN_VALUE;

        // 해당 바이러스 방문처리
        for(Location temp : startNode) {
            visited[temp.x][temp.y] = true;
        }

        Queue<Location> needVisited = new LinkedList<>(Arrays.asList(startNode));

        for(Location temp : startNode){
            needVisited.add(temp);
        }

        while(!needVisited.isEmpty()) {

            Location currentNode = needVisited.poll();

            for(int i = 0; i < 4; i++){
                int nextX = currentNode.x + dx[i];
                int nextY = currentNode.y + dy[i];

                if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && !visited[nextX][nextY] && maps[nextX][nextY] != 1){

                    visited[nextX][nextY] = true;
//                    if(maps[nextX][nextY] == 2)  tempMaps[nextX][nextY] = tempMaps[currentNode.x][currentNode.y];
                    tempMaps[nextX][nextY] = tempMaps[currentNode.x][currentNode.y] + 1;
                    needVisited.add(new Location(nextX,nextY));
                }
            }

        }

        //최대값 찾기
        boolean flag = true;

        loop:
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(maps[i][j] != 2 && maxValue < tempMaps[i][j]){
                    maxValue = tempMaps[i][j];
                }
                if(tempMaps[i][j] == 0 && maps[i][j] == 0){
                    flag = false;
                    break loop;

                }
            }
        }

        if(flag){
            return maxValue;
        }
        else{
            return -1;
        }

    }


    static void recursive(int idx, Location[] tempLoc, int count) {
        if(idx > virusLoc.size()){
            return;
        }

        if(count == M){
            int tempValue = bfs(tempLoc);
            if(tempValue != -1){
                minResult = Integer.min(minResult,tempValue);
            }

            return;
        }

        for(int i = idx; i < virusLoc.size(); i++){
            tempLoc[count] = virusLoc.get(i);
            recursive(i+1,tempLoc,count+1);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        virusLoc = new ArrayList<>();

        //바이러스를 놓을 수 있는 위치를 찾아서 배열에 담기.
        for(int i = 0; i <N; i++){
            for(int j = 0; j < N; j++){
                if(maps[i][j] == 2){
                    virusLoc.add(new Location(i,j));
                }
            }
        }


        recursive(0, new Location[M],0);

        if(minResult == Integer.MAX_VALUE){
            System.out.println(-1);
        }
        else{
            System.out.println(minResult);
        }



    }

    static class Location{
        int x;
        int y;
        int count;

        Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

}


