package baekjoon.baek20220904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ18430_무기공학 {

    static int N;
    static int M;
    static int result = Integer.MIN_VALUE;

    //부메랑의 4가지 모양
    static int[][] boomerangsDx = {{0,1},{0,-1},{-1,0},{1,0}};
    static int[][] boomerangsDy = {{-1,0},{-1,0},{0,1},{0,1}};

    static int[][] maps;

    //방문 처리 배열 복사
    static boolean[][] arrayCopy(boolean[][] visited){

        boolean[][] returnArray = new boolean[N][M];

        for(int i = 0; i < N; i++){
            System.arraycopy(visited[i],0,returnArray[i],0, M);
        }

        return returnArray;
    }

    //해당위치에 해당 모양의 부메랑을 놓을 수 있는 지 확인.
    static boolean check(int currentX, int currentY, int type, boolean[][] visited){

        if(visited[currentX][currentY]) return false;

        for(int i = 0; i < 2; i++){
            int nextX = currentX + boomerangsDx[type][i];
            int nextY = currentY + boomerangsDy[type][i];

            if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M || visited[nextX][nextY]){
                return false;
            }
        }

        return true;
    }

    //해당 위치의 부메랑 값 구하기.
    static int setBoomerang(int currentX, int currentY, int type,boolean[][] visited){
        int total = maps[currentX][currentY]*2;
        visited[currentX][currentY] = true;

        for(int i = 0; i < 2; i++){
            int nextX = currentX + boomerangsDx[type][i];
            int nextY = currentY + boomerangsDy[type][i];

            total += maps[nextX][nextY];
            visited[nextX][nextY] = true;
        }

        return total;
    }

    //idx는 열
    static void recursive(int x,int y, int value, boolean[][] visited){

        //끝까지 다 탐색함.
        if(x >= N) {
            result = Math.max(result, value);
            return;
        }

        //y값이 M을 벗어나지 않았다면.
        if(y < M){
            //부메랑을 해당위치에 놓지않고 그냥 넘어가는 경우
            recursive(x,y+1,value, visited);

            //반복문을 돌면서 복사된 배열에 부메랑을 하나씩 놓아봄.
            for(int type = 0; type < 4; type++){

                //부메랑을 놓을수 있으면 놓음
                if(check(x,y,type,visited)){
                    boolean[][] copyVisited = arrayCopy(visited); //배열 복사
                    copyVisited[x][y] = true;
                    int tempValue = setBoomerang(x, y, type, copyVisited); //부메랑 놓고 값 가져옴.
                    recursive(x,y+1,value+tempValue,copyVisited);//재귀 호출
                }
            }
        }
        else{
            recursive(x+1,0,value, visited);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recursive(0,0,0, new boolean[N][M]);
        if(result == Integer.MIN_VALUE){
            System.out.println(0);
        }

        else{
            System.out.println(result);
        }

    }
}
