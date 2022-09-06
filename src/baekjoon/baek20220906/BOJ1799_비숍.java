package baekjoon.baek20220906;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 이와 같이 1이 검은색이라고 했을때, 비숍은 같은 색깔인곳만 이동가능
1 0 1
0 1 0
1 0 1
*/

public class BOJ1799_비숍 {

    static int N;
    static int blackCount = Integer.MIN_VALUE;
    static int whiteCount = Integer.MIN_VALUE; // 비숍의 최대 개수 저장.

    static int[] dx = {-1,-1,1,1};
    static int[] dy = {-1,1,-1,1};

    static int[][] maps;
    static boolean[][] visited; //비숍을 놓았는지 확인
    static boolean[][] colorCheck; //true 면 검은색, false면 흰색

    //놓을 수 있는지 없는지 확인.
    static boolean checkBishop(int x, int y){

        //비숍을 놨으면 다른 비숍들의 위치와 비교 - 4방향의 대각선을 확인.
        for(int dir = 0; dir < 4; dir++){

            int count = 1;
            while(true){
                int nextX = x + dx[dir] * count;
                int nextY = y + dy[dir] * count;

                if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N){
                    //비숍이 놓아져 있으면 false 리턴
                    if(visited[nextX][nextY]){
                        return false;
                    }
                    //아니면 다음 탐색.
                    else{
                        count++;
                    }
                }
                //맵을 벗어나면 break
                else{
                    break;
                }
            }
        }

        return true;
    }

    //재귀호출하면서 백트래킹해서 비숍 놓기.
    static void recursive(int idx,int bishopCount, boolean type){

        //검은 색
        if(type) blackCount = Math.max(blackCount,bishopCount);

        //흰색
        else whiteCount = Math.max(whiteCount,bishopCount);


        //칸의 개수만큼 탐색
        for(int i = idx; i < N*N; i++){
            int currentX = i/N;
            int currentY = i%N;

            //현재 탐색중인 색깔이 아니고 놓을 수 있는 위치가 아니거나 대각선에 비숍존재하면 다음 칸 탐색.
            if(colorCheck[currentX][currentY] != type || maps[currentX][currentY] != 1 || !checkBishop(currentX,currentY)) continue;

            //비숍을 놓을수 있다면 놓고, 재귀호출
            visited[currentX][currentY] = true;
            recursive(i+1,bishopCount+1,type);
            visited[currentX][currentY] = false;
        }


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        maps = new int[N][N];

        visited = new boolean[N][N];
        colorCheck = new boolean[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());

                //체스판 색깔 두개로 나누기 - 비숍은 자기와 같은 색깔인 부분만 이동 가능.
                if((i%2 == 0 && j%2 == 0) || (i%2==1 && j%2==1)) colorCheck[i][j] = true;
            }
        }

        //비숍은 체스판에서 자기 색깔 있는 곳만 보면 됨.
        recursive(0,0,true); //체스판에서 검은색
        recursive(0,0,false); //체스판에서 흰색

        //두 최대합 합치기
        System.out.println(blackCount + whiteCount);
    }

}
