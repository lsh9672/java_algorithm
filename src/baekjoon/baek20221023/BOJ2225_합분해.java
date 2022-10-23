package baekjoon.baek20221023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 23.
 */
public class BOJ2225_합분해 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[K+1][N+1];

        //1개를 선택해서 N을 만드는 경우는 한가지 밖에 없음
        for(int i = 0; i <= N; i++){
            dp[1][i] = 1;
        }

        //K개를 사용해서 0을 만드는 경우는 몇개를 사용하더라도 단 1개밖에 없음.
        for(int i= 1; i <= K; i++){
            dp[i][0] = 1;
        }

        // K개를 사용해서 N을 만드는 경우는
        // (K-1개로 N을 만드는 경우 - 이 경우의 수에서 +0을 하면 K개로 N을 만드는 경우가 되기 때문에 경우의수가 동일함.) +
        // (K개로 N-1을 만드는 경우 - 이 경우들에서 숫자 하나만 N으로 만들수 있게 1큰수로 바꿔주면 됨.)
        for(int i = 2; i <= K; i++){
            for(int j = 1; j <= N; j++){
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1_000_000_000;
            }
        }

        System.out.println(dp[K][N]);

    }
}
