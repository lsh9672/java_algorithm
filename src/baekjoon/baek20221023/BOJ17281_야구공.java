package baekjoon.baek20221023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 22.
 */
public class BOJ17281_야구공 {

    static int N;
    static int[][] score;

    static boolean[] check;

    static int result;

    //이동해야되는 칸 정보를 받아서 베이스 상태를 업로드 함.
    static int baseStateUpdate(int[] baseState, int move){

        int total = 0;

        for(int i = 3; i >= 1; i--){
            //베이스에 사람이 서있다면
            if(baseState[i] == 1){
                int moveLoc = i+move;
                baseState[i] = 0;
                //이동한 위치가 베이스를 벗어나지 않았다면 그곳으로 이동.
                if(moveLoc <= 3) {
                    baseState[moveLoc] = 1;
                }
                //이동한 위치가 베이스를 벗어났다면 점수 획득함.
                else{
                    total++;
                }
            }
        }

        //타자 진루
        baseState[move] = 1;

        return total;
    }

    static int baseBallSimulation(int[] order){
//        System.out.println(Arrays.toString(order));

        int idx = 1;

        int totalScore = 0;

        for(int inning = 1; inning <= N; inning++){

            int outCount = 0;
            int[] currentScore = score[inning]; //현재 이닝의 선수들의 행동.
            int[] baseState = new int[4]; //베이스 상태 초기화.
            //아웃카운트가 3이 될때 까지 반복
            while(outCount < 3){
                int behavior = currentScore[order[idx]]; //해당 순서의 선수가 하는 행동.
//                if(order[1] == 2 && order[2] == 3 && order[3] == 4 && order[5] == 5 && order[6] == 6 && order[7] == 7 && order[8] == 8 && order[9] == 9){
//                    System.out.println("test : " + behavior);
//                }

                //안타
                switch (behavior){
                    case 1:
                        totalScore += baseStateUpdate(baseState, 1);
                        break;

                    case 2:
                        totalScore += baseStateUpdate(baseState, 2);
                        break;

                    case 3:
                        totalScore += baseStateUpdate(baseState, 3);
                        break;
                    case 4:
                        totalScore++;
                        for(int i = 1; i < 4; i++){
                            if(baseState[i] == 1){
                                totalScore++;
                            }
                        }
                        Arrays.fill(baseState,0);//베이스 초기화
                        break;
                    default:
                        outCount++;
                }

                idx++;
                if(idx > 9) idx = 1;

            }

        }


        return totalScore;
    }

    //idx: 몇번순번까지 선수 배치했는지.
    static void recursive(int idx, int[] order){

        //4번째 순번은 항상 1번선수가 가져감.
        if(idx == 4){
            idx++;
        }

        //모든 선수를 다 배치했으면 시뮬레이션 돌려봄
        if(idx >= 10){
            //선수 배치상태를 받아서 시뮬레이션 돌리고 점수 가져옴.
            result = Math.max(result, baseBallSimulation(order));
            return;
        }

        //1번은 항상 배치 했기 때문에 2번부터 배치 - 선수 번호.
        //idx번째에 배치함.
        for(int i = 2; i <= 9; i++){
            //i번 선수를 배치하지 않았다면, 배치.
            if(!check[i]){
                check[i] = true;
                order[idx] = i;
                recursive(idx+1,order);
                check[i] = false;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        score = new int[N+1][10]; //각 이닝별 각 선수들이 하는 것
        check = new boolean[10]; //뽑은 선수 고정.
        check[1] = true; //1번선수는 순번이 고정

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 1; j <= 9; j++){
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = Integer.MIN_VALUE;

        //인덱스가 순번, 값이 해당 순번에 들어가는 선수 번호.
        int[] order = new int[10];
        //1번선수는 4번타자.
        order[4] = 1;
        recursive(1,order);

        System.out.println(result);

    }
}
