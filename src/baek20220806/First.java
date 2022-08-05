package baek20220806;

/*
 * BOJ - 17822번
 * 말을 어렵게 해뒀지만 그냥 원형큐형태
 *
 * 자바에서는 음수 모듈러 연산시에 파이썬과 동일한 결과를 얻고 싶다면 별도의 연산이 필요하다
 * N mod M => N이 음수라면, (-1)N mod M=> 이 연산을 통해서 얻게 된 결과 K,   (-1)k+M을 해주면 된다.
 *  이와같이 연산하면 원하는 파이썬에서의 모듈러 결과를 얻을 수 있다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class First {

    static int N;
    static int M;
    static int T;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] roundPlate;

    //원판을 회전시킬때 직접 회전이 아닌, 시작인덱스 값을 변경하는 식으로 연산을 줄임.
    static int[] startIdx;

    //원판 회전 - 회전이 끝나면 startIdx값에 시작 인덱스 값들이 달라짐.
    //번호가 X의 배수인 원판 선택, d방향으로 회전(0: 시계방향, 1: 반시계 방향), k칸 회전
    static void rotatePlate(int x, int d, int k) {
        //배열에서 각 행을 선택
        for(int i = x; i <= N; i+=x){
            //각 원판 선택시는 i-1을 선택해줘야 됨.
            //방향에 따라서 분기

            //시계 방향
            if(d == 0){
                startIdx[i-1] = (startIdx[i-1] + k) % M;
            }
            //반시계 방향 - 모듈러 연산시 조심
            else{
                int temp = startIdx[i-1] - k;
                if(temp >= 0) startIdx[i-1] = temp % M;
                else startIdx[i-1] = ((temp*(-1)) % M) *(-1) + M;
            }

        }
    }

    //배열을 새로 만들어서 startIdx값에 따라 업데이트  + startIdx배열 초기화.
    static void plateUpdate() {

        int[][] tempPlate = new int[N][M];
        //업데이트 된 시작 배열로 새로운 숫자를 위치시킨 배열을 만듦
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                tempPlate[i][j] = roundPlate[i][(startIdx[i]+j)%M];
            }
        }

        //인접숫자같다면 삭제 =>0으로 표기

        //삭제할 좌표 저장할 배열
        boolean[][] deleteLoc = new boolean[N][M];

        //삭제 할 좌표를 바로 삭제하는 것이 아닌 모아뒀다가 삭제.
        boolean flag = true; // 인접하면서 수가 같은 것이 있는지 없는지
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){

                for(int d = 0; d < 4; d++){
                    int nextX = i + dx[d];
                    int nextY = j + dy[d];
                    //y좌표는 원형큐의 형태임
                    if(nextY < 0) nextY = M-1;
                    else if(nextY >= M) nextY = 0;

                    if(nextX >= 0 && nextX < N){
                        if(tempPlate[i][j] == tempPlate[nextX][nextY]){
                            deleteLoc[i][j] = true;
                            deleteLoc[nextX][nextY] = true;

                            flag = false;
                        }
                    }
                }

            }
        }

        //인접하면서 수가 같은 것이 하나도 없다.=> 전체 더하고 평균 구한후에 각 수랑 비교해서  작은수는 +1, 큰수는 -1씩 해줌.
        if(!flag){
            int notZeroCount = 0;
            int totalValue = 0;
            for(int i = 0; i < N; i++){
                for(int j =0; j< M; j++){

                }
            }


        }
        //인접한 수가 한개라도 있었으면 전부 지운다.
        else{
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(deleteLoc[i][j]){
                        tempPlate[i][j] = 0;
                    }
                }
            }
        }

        //새로 업데이트한 원판으로 업데이트
        roundPlate = tempPlate;
    }

   public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        /** 입력받는 부분**/
        StringTokenizer st = new StringTokenizer(br.readLine());

        //원판수 - 1~N
        N = Integer.parseInt(st.nextToken());

        //원판안의 숫자 개수 1-2-3-...-(M-1)-(M) => 원형 큐 처럼 이어져 있음
        M = Integer.parseInt(st.nextToken());

        //회전시킬 횟수
        T = Integer.parseInt(st.nextToken());


        //각 행은 원판, 열은 원판 안의 수의 위치. - 배열에 넣어두고 삭제시에는 델타 탐색하면됨, 단순한 배열임
        roundPlate = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                roundPlate[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //각 행의 시작 인덱스 배열
        startIdx = new int[N];

        for(int i = 0; i < T; i++) {

            st = new StringTokenizer(br.readLine());
            //x원판의 배수선택
            int x = Integer.parseInt(st.nextToken());
            //방향 0 이면 시계방향, 1이면 반시계방향
            int d = Integer.parseInt(st.nextToken());
            //회전할 칸수.
            int k = Integer.parseInt(st.nextToken());

            rotatePlate(x, d, k);
            plateUpdate();
        }

        //원판 탐색이 끝나고 마지막에 모든 값의 합을 구함.
        int result = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                result += roundPlate[i][j];
            }
        }
//        System.out.println(((3%11)*(-1)) + 11);

        System.out.println(result);
    }

}
