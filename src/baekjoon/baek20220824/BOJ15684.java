package baekjoon.baek20220824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15684 {

    static int N;
    static int M;
    static int H;
    static int count;
    static int[][] maps;

    //매번 i번쨰 사다리가 i번째에 도착하는지 확인.
    static boolean ladderCheck(){

        //세로줄 하나씩 선택
        for(int column = 1; column <= N; column++){
            int currentY = column;

            for(int row = 1; row <= H; row++){

                //오른쪽으로 꺽어야됨.
                if(maps[row][currentY] == 1){
                    currentY++;
                }
                //왼쪽으로 꺽어야 됨.
                else if(maps[row][currentY] == 2){
                    currentY--;
                }
            }
            //탐색이 다 끝났으면 시작사다리와 현재 위치 비교
            if(currentY != column) return false;
        }

        return true;
    }

    static void recursive(int startX, int value){

        if(value > 3){
            return;
        }

        //사다리가 조건에 맞다면 최대값 업데이트 하고 종료
        if(ladderCheck()){
            count = Integer.min(count,value);
            return;
        }

        for(int i = startX; i <= H; i++){
            for(int j = 1; j < N; j++){
                if(maps[i][j] == 0 && maps[i][j+1] == 0) {
                    maps[i][j] = 1;
                    maps[i][j + 1] = 2;
                    //현재 가로위치에서 세로만 이동.
                    recursive(i, value + 1);
                    maps[i][j] = maps[i][j + 1] = 0;
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        //사다리 정보를 2차원 배열로 만듦
        // 0: 아래로, 1: 오른쪽, 2: 왼쪽;

        maps = new int[H+1][N+1];

        //초기 사다리 셋
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            maps[a][b] = 1; //오른쪽
            maps[a][b+1] = 2; //옆 사다리에서는 왼쪽이 됨
        }

        count = Integer.MAX_VALUE;
        recursive(1, 0);

        if(count == Integer.MAX_VALUE){
            System.out.println(-1);
        }
        else{
            System.out.println(count);
        }
    }
}
