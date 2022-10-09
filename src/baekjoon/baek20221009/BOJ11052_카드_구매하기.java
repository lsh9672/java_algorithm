package baekjoon.baek20221009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 9.
 */
public class BOJ11052_카드_구매하기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] dp = new int[N+1];

        for(int i = 1; i <= N; i++) {
            dp[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 2; i <= N; i++){
            for(int j = 1; j < i; j++){
                dp[i] = Math.max(dp[i], dp[j] + dp[i-j]);
            }
        }

        System.out.println(dp[N]);
    }
}
