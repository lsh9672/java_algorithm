package programmers.prog20220901;

import java.util.Arrays;

/**
 * problems => alp_req,cop_req, alp_rwd,cop_rwd,cost
 *
 *
 */

public class 코딩테스트공부_카카오인턴_2차수정 {

    public int solution(int alp, int cop, int[][] problems){
        int result = Integer.MAX_VALUE;
        final int INF = Integer.MAX_VALUE;

        int N = Integer.MIN_VALUE; //최대 알고력 저장
        int M = Integer.MIN_VALUE; //최소 알고력 저장.

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

        int[][] dp = new int[801][801];
        //각 값의 최소값을 구하는 것이므로 최대값으로 채워넣음
        for(int i = 0; i < 801; i++){
            Arrays.fill(dp[i],INF);
        }
        //초기 알고력과, 초기 코딩력을 dp에 넣음 - 초기값의 경우, 실행시간은 0
        dp[alp][cop] = 0;

        //각 경우들로 dp 배열을 채움
        for(int i = alp; i < 800; i++){
            for(int j = cop; j < 800; j++){
                //알고리즘을 공부하는 경우
                dp[i+1][j] = Integer.min(dp[i+1][j], dp[i][j]+1);
//                코딩을 공부하는 경우
                dp[i][j+1] = Integer.min(dp[i][j+1], dp[i][j]+1);

                //반복문을 돌면서 조건에 맞으면 각 문제를 풀었을때 의 경우를 다 해보고 업데이트
                for(int[] temp : problems){

                    //alp_req,cop_req, alp_rwd,cop_rwd,cost
                    int alpReq = temp[0];
                    int copReq = temp[1];
                    int alp_rwd = temp[2];
                    int cop_rwd = temp[3];
                    int cost = temp[4];

                    //필요 알고력과 필요 코딩력이 충분하다면 업데이트
                    if(i >= alpReq && j >= copReq){
                        int next_alp_rwd = i + alp_rwd; //해당 문제를 풀었을때 얻게 되는 총 알고력
                        int next_cop_rwd = j + cop_rwd; // 해당 문제를 풀었을 때 얻게되는 총 코딩력

                        //해당 문제를 풀어서 누적된 알고력과 코딩력이 얻어야 되는 최대 알고력과 코딩력을 벗어나면 save
                        if(next_alp_rwd >= N && next_cop_rwd >= M){
                            result = Math.min(result,dp[i][j] + cost);
                            continue;
                        }

                        //둘중에 하나라도 최대알고력과 코딩력을 벗어나면. => 정해진 배열을 벗어났다면.
                        if(next_alp_rwd > 800 || next_cop_rwd > 800){
                            continue;
                        }

                        //그게 아니라면 업데이트
                        dp[next_alp_rwd][next_cop_rwd] = Math.min(dp[next_alp_rwd][next_cop_rwd], dp[i][j] + cost);

                    }
                }
            }
        }

        result = Math.min(result,dp[N][M]);

        return result;
    }

    public static void main(String[] args) {
//        KakaoIntern3 k = new KakaoIntern3();
//        int alp1 = 10;
//        int cop1 = 10;
//        int[][] problems1 = {{10,15,2,1,2},{20,20,3,3,4}};
//        System.out.println(k.solution(alp1,cop1,problems1));
//
//
//
//        KakaoIntern3 k2 = new KakaoIntern3();
//        int alp2 = 0;
//        int cop2 = 0;
//        int[][] problems2 = {{0,0,2,1,2},{4,5,3,1,2},{4,11,4,0,2},{10,4,0,4,2}};
//        System.out.println(k2.solution(alp2,cop2,problems2));

        코딩테스트공부_카카오인턴_2차수정 k3 = new 코딩테스트공부_카카오인턴_2차수정();
        int alp3 = 150;
        int cop3 = 0;
        int[][] problems3 = {{0,0,30,10,2},{150,150,3,2,1}};
        System.out.println(k3.solution(alp3,cop3,problems3));
    }
}
