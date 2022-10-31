package baekjoon.baek20221026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : sh Lee
 * @date : 22. 10. 26.
 */
public class BOJ2193_이친수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[] dp = new long[91];

        dp[0] = 0;
        dp[1] = 1;

        //n번째에 0이 오면 dp[n-1]개의 경우가 가능, n번째에 1이 오면, n-1번째는0으로 고정, dp[n-2]
        for(int i = 2; i < N+1; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        System.out.println(dp[N]);

    }
}
