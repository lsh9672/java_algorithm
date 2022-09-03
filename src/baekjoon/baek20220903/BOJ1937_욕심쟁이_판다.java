package baekjoon.baek20220903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1. 이중 반복문으로 각 배열의 칸을 시작점으로 잡는다.
 * 2. dfs를 돌면서 칸이동할때마다 칸수를 증가시키고 별도의 배열에 저장해둔다.
 * 3. 방문하려고 하는 칸에 저장된 값이 현재의 가중치ㄷ보다 크면 갈필요가 없다.
 *
 */

public class BOJ1937_욕심쟁이_판다 {

    static int N;
    static int result = Integer.MIN_VALUE;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int[][] maps;
    static int[][] valueSave; //이동할수 있는 최대거리 저장.

    static int dfs(Location currentNode){
        System.out.println("------ x : "+ currentNode.x +" y : "+ currentNode.y +"---------");
        for(int[] ttt : valueSave){
            System.out.println(Arrays.toString(ttt));
        }

        //이미 저장된 값이 있으면 그 값을 리턴.
        if(valueSave[currentNode.x][currentNode.y] != 0) return valueSave[currentNode.x][currentNode.y];

        //간적이 없어서 0이라면
        valueSave[currentNode.x][currentNode.y] = 1;

        for(int i = 0; i < 4; i++){
            int nextX = currentNode.x + dx[i];
            int nextY = currentNode.y + dy[i];

            //지도를 벗어나지 않는 지 확인
            if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N){
                //다음 이동할 지역이 전 지역보다 대나무양이 많은지 확인.
                if(maps[currentNode.x][currentNode.y] < maps[nextX][nextY]){
                    valueSave[currentNode.x][currentNode.y] = Math.max(valueSave[currentNode.x][currentNode.y],dfs(new Location(nextX, nextY))+1);
                }
            }
        }

        return valueSave[currentNode.x][currentNode.y];

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        maps = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        valueSave = new int[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                result = Math.max(result,dfs(new Location(i,j))); //해당 좌표에서 bfs 탐색후 최대 거리 리턴


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
