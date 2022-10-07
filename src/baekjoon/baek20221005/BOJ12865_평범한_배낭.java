package baekjoon.baek20221005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 5.
 */
public class BOJ12865_평범한_배낭 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken()); //무게

        int[] weightArray = new int[N+1];// 각 물건의 무게
        int[] valueArray = new int[N+1]; //각 물건의 가치

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());

            weightArray[i] = Integer.parseInt(st.nextToken());
            valueArray[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N+1][K+1];

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= K; j++){

                if(j - weightArray[i] >= 0){
                    //이전 값에서 그대로 가져오는 경우, j - weightArray[i]무게에 있는 값 가져오기
                    dp[i][j] = Integer.max(dp[i-1][j], dp[i-1][j-weightArray[i]] + valueArray[i]);
                }
                //넣을수 없는 경우는 이전 값을 그대로 가져옴.
                else{
                    dp[i][j] = dp[i-1][j];
                }

            }
        }

        System.out.println(dp[N][K]);

    }
}
