package baekjoon.baek20220428;


/**
 * 백준 1699번 (dp 연습,실버3)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class First {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Integer[] dp = new Integer[n+1];
        dp[0] = 0;

        for(int i =1 ; i<= n; i++){
            dp[i] = i;

            for(int j = (int) Math.floor(Math.sqrt(i)); j>=1; j--){
//                System.out.println("test: "+(i - (j*j)));
                int temp = dp[i - (j*j)]+1;
//                System.out.println("test");
                if(dp[i]>temp){
                    dp[i] = temp;
                }
            }
        }
        System.out.println(dp[n]);

    }
}
