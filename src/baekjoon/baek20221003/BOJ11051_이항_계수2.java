package baekjoon.baek20221003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11051_이항_계수2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K= Integer.parseInt(st.nextToken());

        long[][] dp = new long[N+1][N+1];

        for(int i = 0; i <= N; i++){
            for(int j = 0; j <= i; j++){

                if(j == 0) dp[i][j] = 1;
                else dp[i][j] = (dp[i-1][j] + dp[i-1][j-1]) %10007;

            }
        }

        System.out.println(dp[N][K]);

    }
}
