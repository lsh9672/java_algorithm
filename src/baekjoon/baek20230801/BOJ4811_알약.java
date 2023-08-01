package baekjoon.baek20230801;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 아이디어
 * 가지수를 구하는 디피 문제
 * 1. 온전한 알약의 수와 반개짜리 알약의 수를 계속해서 계산해야된다.
 * 2. 1번 상황에 따라서 N일차에 선택할 수 있는 가지수가 결정 된다.
 *
 *
 * 참고함 : https://steady-coding.tistory.com/187
 * 작은 것을 먹는 걸 dp[0]으로 생각하고 나머지를 계산한다.
 * 첫번째는 무조건 큰것(온전한 1개짜리)부터 먹고 시작한다.
 *
 *
 * N = 1 큰 - 작 => 1가지의 경우의 수
 *
 * 맨 앞은 제외
 * N = 2 작 - 큰 - 작 => dp[0] * dp[1]
 *       큰 - 작 - 작 => dp[1] * dp[0]
 *
 * N = 3 작 - (큰 - 작 - 큰 - 작) => dp[0] * dp[2] => 앞에서 큰 - 작을 먹으면 괄호친부분은 큰알약 2개인 경우로 dp[2]와 같음.
 *       큰 - 작 - 큰 - 작 - 작 => dp[1] * dp[1]
 *       큰 - 큰 - 작 - 작 - 작 => dp[2] * dp[0]
 */

public class BOJ4811_알약 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //경우의 수를 담을 배열.
        long dp[] = new long[61];

        dp[0] = 1; //작은 것을 뽑는 것은 경우의 수에 영향을 안줌 - 먹으면 끝이므로.
        dp[1] = 1; //디폴트로 큰것을 먹고, 다음 경우에 작은 것을 마저 먹는 경우 하나임.
        dp[2] = 2; // 작은 것을 먼저 뽑으면 그 뒤의 경우는 N = 1 과 동일, 큰것을 먼저 뽑으면 거기서 부터 N = 1과 동일.
        for(int i = 3; i <= 60; i++){

            for(int j = 0; j < i; j++){

                dp[i] += dp[j] * dp[i - j - 1];
            }
        }

        StringBuilder sb = new StringBuilder();

        int n;

        while((n = Integer.parseInt(br.readLine())) != 0){

            sb.append(dp[n]).append("\n");
        }

        System.out.println(sb);

    }
}
