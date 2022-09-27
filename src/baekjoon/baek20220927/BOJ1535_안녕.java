package baekjoon.baek20220927;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1535_안녕 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());

        int[] lifeArray = new int[N];
        int[] happyArray = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            lifeArray[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            happyArray[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[101];

        for(int i = 0; i < N; i++){
                for(int j = 99; j >= lifeArray[i]; j--){
                    dp[j] = Math.max(dp[j - lifeArray[i]] + happyArray[i],dp[j]);
                }
        }

        System.out.println(dp[99]);
    }
}
