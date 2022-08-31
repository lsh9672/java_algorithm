package programmers.prog20220831;


import codingTest.kakaoTechIntern2022.KakaoIntern3;

import java.util.Arrays;

/**
 * problems => alp_req,cop_req, alp_rwd,cop_rwd,cost
 */

public class 코딩테스트공부 {

    public int solution(int alp, int cop, int[][] problems){

        int N = Integer.MIN_VALUE;
        int M = Integer.MIN_VALUE;

        //주어진 problems에서 가장 큰 필요알고력과 필요코딩력을 구해야됨.
        for(int[] temp : problems){

            //최대 필요 알고력 구하기
            if(temp[0] > N){
                N = temp[0];
            }
            //최대 필요 코딩력 구하기.
            if(temp[1] > M){
                M = temp[1];
            }
        }

        int[][] dp = new int[N+1][M+1];
        //각 값의 최소값을 구하는 것이므로 최대값으로 채워넣음
        for(int i = 0; i <= N; i++){
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
        //초기 알고력과, 초기 코딩력을 dp에 넣음 - 초기값의 경우, 실행시간은 0
        dp[alp][cop] = 0;

        //각 경우들로 dp 배열을 채움
        for(int i = alp; i < N; i++){
            for(int j = cop; j < M; j++){
                //알고리즘을 공부하는 경우
                dp[i+1][j] = Integer.min(dp[i+1][j], dp[i][j]+1);
                //코딩을 공부하는 경우
                dp[i][j+1] = Integer.min(dp[i][j+1], dp[i][j]+1);

                //반복문을 돌면서 조건에 맞으면 각 문제를 풀었을때 의 경우를 다 해보고 업데이트
                for(int[] temp : problems){
                    //필요 알고력과 필요 코딩력이 충분하다면 업데이트
                    if(i >= temp[0] && j >= temp[1]){
                        if(i > N - temp[2] && j <= M-temp[3]){
                            dp[N][j+temp[3]] = Integer.min(dp[N][j+temp[3]],dp[i][j] + temp[4]);
                        }
                        else if(i <= N - temp[2] && j > M-temp[3]){
                            dp[i+temp[2]][M] = Integer.min(dp[i+temp[2]][M],dp[i][j] + temp[4]);
                        }
                        else if(i > N - temp[2] && j > M-temp[3]){
                            dp[N][M] = Integer.min(dp[N][M],dp[i][j] + temp[4]);
                        }
//                        else if(i <= N - temp[2] && j <= M-temp[3]){
                        else{
                            dp[i+temp[2]][j+temp[3]] = Integer.min(dp[i+temp[2]][j+temp[3]],dp[i][j] + temp[4]);
                        }

                    }
                }
            }
        }

        return dp[N][M];
    }

    public static void main(String[] args) {
        KakaoIntern3 k = new KakaoIntern3();
        int alp1 = 10;
        int cop1 = 10;
        int[][] problems1 = {{10,15,2,1,2},{20,20,3,3,4}};
        System.out.println(k.solution(alp1,cop1,problems1));

        int alp2 = 0;
        int cop2 = 0;
        int[][] problems2 = {{0,0,2,1,2},{4,5,3,1,2},{4,11,4,0,2},{10,4,0,4,2}};
        System.out.println(k.solution(alp2,cop2,problems2));
    }
}
