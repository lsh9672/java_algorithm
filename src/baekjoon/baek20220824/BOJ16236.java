package baekjoon.baek20220824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16236 {

    static int N;

    // 상, 좌,우,하
    static int[] dx = {-1,0,0,1};
    static int[] dy = {0,-1,1,0};
    static SharkInfo currentLoc;
    static int[][] maps;

    //현재 상어의 위치에서 먹을 수 있는 물고기를 찾음.
    static int findFish() {
        boolean[][] visited = new boolean[N][N];
        visited[currentLoc.x][currentLoc.y] = true;

        PriorityQueue<Location> needVisited = new PriorityQueue<>();

        needVisited.add(new Location(currentLoc.x,currentLoc.y,0));

        while(!needVisited.isEmpty()) {

            Location currentNode = needVisited.poll();

            // 먹을수 있는 위치에 도달하면 상어 업데이트 하고, 해당위치 빈칸으로 만듦
            if(maps[currentNode.x][currentNode.y] != 0 && maps[currentNode.x][currentNode.y] < currentLoc.size) {

                currentLoc.count++;
                currentLoc.x = currentNode.x;
                currentLoc.y = currentNode.y;

                maps[currentNode.x][currentNode.y] = 0;

                // 먹은개수가 자신의 크기와 동일하면 크기 하나 커지기.
                if(currentLoc.count == currentLoc.size) {
                    currentLoc.size++;
                    currentLoc.count = 0;
                }

                return currentNode.size;
            }

            //우선순위 큐라 꺼냈는데 상어 크기보다 작은 물고기라면 조건에 부합하는 물고기로 바로 먹으면 됨.

            for(int i = 0; i < 4; i++) {
                int nextX = currentNode.x + dx[i];
                int nextY = currentNode.y + dy[i];

                if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && !visited[nextX][nextY] && (maps[nextX][nextY] == 0 || maps[nextX][nextY] <= currentLoc.size)) {
                    visited[nextX][nextY] = true;

                    needVisited.add(new Location(nextX,nextY,currentNode.size+1));
                }
            }
        }

        return -1;
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

        // 상어 초기위치 찾기
        loop:
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(maps[i][j] == 9) {
                    currentLoc = new SharkInfo(i,j,2,0);
                    maps[i][j] = 0;
                    break loop;
                }
            }
        }

        int result = 0;
        while(true) {

            int moveTime = findFish();

            if(moveTime== -1) break;

            result += moveTime;

        }

        System.out.println(result);

    }

    static class SharkInfo{
        int x,y,size,count;

        SharkInfo(int x, int y, int size, int count){
            this.x = x;
            this.y = y;
            this.size = size;
            this.count = count;
        }
    }

    static class Location implements Comparable<Location>{
        int x;
        int y;
        int size;

        Location(int x, int y, int size){
            this.x = x;
            this.y = y;
            this.size = size;
        }

        @Override
        public int compareTo(Location o) {

            if(this.size == o.size) {

                if(this.x == o.x) {
                    return this.y - o.y;
                }

                return this.x - o.x;
            }

            return this.size - o.size;
        }

//		@Override
//		public String toString() {
//			// TODO Auto-generated method stub
//			return "x : " + x + " y : " + y + " size : " + size;
//		}
//

    }

}
