package baekjoon.baek20221010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * @author : sh Lee
 * @date : 22. 10. 10.
 */
public class BOJ10826_피보나치수4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if(N == 0){
            System.out.println(N);
        }
        else{

            BigInteger[] dp = new BigInteger[N+1];

            dp[0] = BigInteger.valueOf(0);
            dp[1] = BigInteger.valueOf(1);


            for(int i = 2; i <= N; i++){

                dp[i] = dp[i-1].add(dp[i-2]);
            }

            System.out.println(dp[N]);
        }


    }
}
