package baekjoon.baek20220211;

/*
* 백준 피보나치5 - 10870(브론즈2,구현연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Third {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if(n == 0 || n == 1){
            System.out.println(n);
            return;
        }

        int [] dp = new int[2];
        dp[0] = 0;
        dp[1] = 1;

        for(int i = 2;i <= n ; i++){
            int temp = dp[1];
            dp[1] = dp[0]+dp[1];

            dp[0] = temp;
        }

        System.out.println(dp[1]);
    }
}
