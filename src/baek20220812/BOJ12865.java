package baek20220812;

/**
 * BOJ 12865번 - 평범한 배낭(골드5)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ12865 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //물건 개수
        int N = Integer.parseInt(st.nextToken());

        // 최대 무게
        int K = Integer.parseInt(st.nextToken());

        int[] wArrays = new int[N+1];
        int[] vArrays = new int[N+1];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            wArrays[i+1] = Integer.parseInt(st.nextToken());
            vArrays[i+1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int [N+1][K+1];

        //i는 물건을 i번째까지 봤을때의 최대 값
        for(int i = 1; i <=N; i++) {
            //j는 무게를 말함.
            for(int j = 1; j <= K; j++) {

                if(j-wArrays[i] >= 0) {
                    //이전 값과, 새로들어온 i번째 무게를 더한 결과와 비교
                    dp[i][j] = Integer.max(dp[i-1][j], dp[i-1][j-wArrays[i]] + vArrays[i]);
                }

                //물건을 넣을 수 없다면 이전값으로
                else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        System.out.println(dp[N][K]);

    }

}
