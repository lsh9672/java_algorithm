package baekjoon.baek20221003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ9465_스티커 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < T; i++){
            int n = Integer.parseInt(br.readLine());
//            int[][] dp = new int[2][n+1];

            int[][] numArray = new int[2][n+1];
            for(int x = 0; x < 2; x++){
                st = new StringTokenizer(br.readLine());
                for(int y = 1; y <= n; y++){
                    numArray[x][y] = Integer.parseInt(st.nextToken());
                }
            }

            //점화식
//            dp[0][1] = numArray[0][1];
//            dp[1][1] = numArray[1][1];
//
//            for(int k = 2; k <=n; k++){
//                dp[0][k] = Math.max(dp[1][k-1] + numArray[0][k], dp[1][k-2] + numArray[0][k]);
//                dp[1][k] = Math.max(dp[0][k-1] + numArray[1][k] , dp[0][k-2] + numArray[1][k]);
//            }

            for(int k = 2; k <=n; k++){
                numArray[0][k] += Math.max(numArray[1][k-1] , numArray[1][k-2] );
                numArray[1][k] += Math.max(numArray[0][k-1] , numArray[0][k-2]);
            }


            sb.append(Math.max(numArray[0][n],numArray[1][n])).append("\n");
        }

//        sb.setLength(sb.length()-1);
        System.out.println(sb);

    }
}
