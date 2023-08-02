package baekjoon.baek20230802;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 아이디어
 * 참고함 : https://moonsbeen.tistory.com/262
 * str1을 컬럼, str2를 로우로 두고 계산해볼수 있다
 *
 */

public class BOJ5582_공통부분문자열 {
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        int[][] dp = new int[str1.length()+1][str2.length()+1];

        int maxValue = 0;

        for(int i = 1; i <= str1.length(); i++){

            char chr1 = str1.charAt(i - 1);
            for(int j = 1; j <= str2.length(); j++){
                char chr2 = str2.charAt(j - 1);

                if(chr1 == chr2){
                    dp[i][j] += dp[i - 1][j - 1] + 1;
                    maxValue = Math.max(maxValue, dp[i][j]);
                }

            }
        }

        System.out.println(maxValue);

    }
}
