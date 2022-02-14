package baek20220210;

/*
* 백준 2747 - 피보나치 수 자바 구현연습*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class BaekFourth {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[2];

        dp[0] = 0;
        dp[1] = 1;

        for(int i=2;i<= n;i++) {
            int temp = dp[1];
            dp[1] = dp[0] + dp[1];
            dp[0] = temp;


        }
        System.out.println(dp[1]);
    }
}
