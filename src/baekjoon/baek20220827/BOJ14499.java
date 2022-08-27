package baekjoon.baek20220827;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 주사위 굴리기
 */

public class BOJ14499 {

    static int N;
    static int M;

    static Location currentLoc;
    //동서북남 - 0번은 안씀.
    static int[] dx = {0,0,0,-1,1};
    static int[] dy = {0,1,-1,0,0};

    static int[][] diceState = {
            {0,0,0},
            {0,0,0},
            {0,0,0},
            {0,0,0}
    };//처음에는 모든 면에 0이 써있음.

    static int[][] maps;

    static StringBuilder result = new StringBuilder();

    static void diceUpdate(int dir){
        int[][] updateState = new int[4][3];

        //동
        if(dir == 1){
            //바뀌지 않는 세로 줄 먼저 놓기
            for(int i= 0; i < 4; i++){
                updateState[i][1] = diceState[i][1];
            }
            for(int i = 1; i < 3; i++){
                updateState[1][i] = diceState[1][i-1];
            }

            //바닥에 있는 값 왼쪽으로 옮김
            updateState[1][0] = diceState[3][1];

            //오른쪽에 있는 값 바닥으로 옮김
            updateState[3][1] = diceState[1][2];

        }
        //서
        else if(dir == 2){
            //바뀌지 않는 세로 줄 먼저 놓기
            for(int i = 0; i < 4; i++){
                updateState[i][1] = diceState[i][1];
            }
            for(int i = 0; i < 2; i++){
                updateState[1][i] = diceState[1][i+1];
            }

            //바닥에 있는 값 오른쪽으로 옮김
            updateState[1][2] = diceState[3][1];

            //왼쪽에 있는 값 바닥으로 옮김
            updateState[3][1] = diceState[1][0];
        }
        //북
        else if(dir == 3){

            for(int i = 0; i < 3; i++){
                updateState[1][i] = diceState[1][i];
            }

            for(int i = 0; i < 3; i++){
                updateState[i][1] = diceState[i+1][1];
            }

            updateState[3][1] = diceState[0][1];

        }
        //남
        else if(dir == 4){
            for(int i = 0; i < 3; i++){
                updateState[1][i] = diceState[1][i];
            }

            for(int i = 0; i < 3; i++){
                updateState[i+1][1] = diceState[i][1];
            }

            updateState[0][1] = diceState[3][1];
        }

        diceState = updateState;
    }

    static void moveDice(int dir){

        int nextX = currentLoc.x + dx[dir];
        int nextY = currentLoc.y + dy[dir];

        if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M){

            diceUpdate(dir);
            //이동하고 나서 상단의 숫자를 저장.
            result.append(diceState[1][1]).append("\n");

            currentLoc.x = nextX;
            currentLoc.y = nextY;



            //게임판 숫자와 주사위 숫자를 비교해서 업데이트
            int diceDownNum = diceState[3][1]; //주사위 바닥에 있는 숫자.
            int mapNum = maps[currentLoc.x][currentLoc.y];

            //이동한 칸의 숫자가 0이면 바닥의 숫자가 칸에 복사됨.
            if(mapNum == 0){
                maps[currentLoc.x][currentLoc.y] = diceDownNum;
            }
            //칸의 숫자가 바닥으로 복사됨.
            else{
                diceState[3][1] = mapNum;
                maps[currentLoc.x][currentLoc.y] = 0;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        currentLoc = new Location(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));

        int K = Integer.parseInt(st.nextToken());

        maps = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++){
            int command = Integer.parseInt(st.nextToken());
            moveDice(command);
        }

        result.setLength(result.length()-1);
        System.out.println(result);
    }

    static class Location{
        int x,y;

        Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
