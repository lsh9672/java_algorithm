package baekjoon.baek20221022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author : sh Lee
 * @date : 22. 10. 22.
 */
public class BOJ2156_포도주_시식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] numArray = new int[n+1];

        for(int i = 1; i <= n; i++){
            numArray[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[10001];
        dp[1] = numArray[1];
        dp[2] = numArray[1] + numArray[2];

        for(int i = 3; i <= n; i++){
            dp[i] = Math.max(dp[i-1],Math.max(numArray[i] + numArray[i-1] + dp[i-3], numArray[i] + dp[i-2]));
        }

        System.out.println(dp[n]);
    }
}
