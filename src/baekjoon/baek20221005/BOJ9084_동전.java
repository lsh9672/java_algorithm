package baekjoon.baek20221005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 5.
 */
public class BOJ9084_동전 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());

        for(int testCase = 0; testCase < T; testCase++){

            int N = Integer.parseInt(br.readLine()); //동전의 가지수

            int[] coinArray = new int[N]; //동전 종류
            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < N; i++){
                coinArray[i] = Integer.parseInt(st.nextToken());
            }

            int M = Integer.parseInt(br.readLine());//동전으로 만들어야 될 금액

            int[] dp = new int[M+1]; //dp안의 값은 i원을 만드는데 필요한 경우의 수
            dp[0] = 1; //0원을 만드는데 필요한 경우의 수는 1개.

            //j원을 만들수 있는 경우.
            for(int i = 0; i < N; i++){
                for(int j = coinArray[i]; j <= M; j++){
                    dp[j] += dp[j - coinArray[i]]; //j원을 만드는데 필요한 경우의 수는 j-coinArray[i]에서 cointArray[i]를 추가하는 경우.
                }
            }
            System.out.println(dp[M]);
        }

    }
}