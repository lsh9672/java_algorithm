package baekjoon.baek20221005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 5.
 */
public class BOJ11053_가장_긴_증가하는_부분수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int N = Integer.parseInt(br.readLine());

        int[] numArray = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            numArray[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        Arrays.fill(dp,1);

        for(int i = 0; i < N; i++){
            for(int j = 0; j < i; j++){
                if(numArray[i] > numArray[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }

        int result = Integer.MIN_VALUE;

        for(int temp : dp){
            result = Math.max(result,temp);
        }

        System.out.println(result);
    }
}
