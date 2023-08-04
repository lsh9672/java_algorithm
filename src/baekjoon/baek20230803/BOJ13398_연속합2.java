package baekjoon.baek20230803;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 두가지로 나눠서 해결할 수 있다.
 * 해당 위치까지 숫자를 "안 지운" 경우 => dp[0][i] = max(dp[0][i-1] + arr[i], arr[i]);
 * 해당 위치까지 숫자를 "지운" 경우 =>  dp[1][i] = max(dp[0][i - i], dp[1][i - 1] + arr[i])
 */

public class BOJ13398_연속합2 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] numArray = new int[n];

        int[][] dp = new int[2][n];


        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            numArray[i] = Integer.parseInt(st.nextToken());
        }

        dp[0][0] = numArray[0];

        int maxValue = numArray[0];

        for(int i = 1; i < n; i++){
            dp[0][i] = Math.max(dp[0][i - 1] + numArray[i], numArray[i]);
            dp[1][i] = Math.max(dp[0][i - 1], dp[1][i - 1] + numArray[i]);

            maxValue = Math.max(maxValue, Math.max(dp[0][i], dp[1][i]));
        }



        System.out.println(maxValue);

    }
}
