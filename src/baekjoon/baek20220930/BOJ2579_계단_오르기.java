package baekjoon.baek20220930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2579_계단_오르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] stairArray = new int[301];

        for(int i = 1; i <= N; i++){
            stairArray[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[301];
        dp[1] = stairArray[1];
        dp[2] = stairArray[1] + stairArray[2];
        dp[3] = Math.max(stairArray[2] + stairArray[3], stairArray[1] + stairArray[3]);

        for(int i = 4; i <= N; i++){
            dp[i] = Math.max(stairArray[i] + dp[i-2], stairArray[i] + stairArray[i-1] + dp[i-3]);
        }

        System.out.println(dp[N]);


    }
}
