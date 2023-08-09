package baekjoon.baek20230809;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * RGB거리, 프로그래머스의 정수삼각형과 굉장히 유사하면서 간단한 dp문제이다.
 * N이 10만개, 즉 모든 경우를 한번씩 완탐하면 시간초과가 난다.
 * 이전에 계산했던 값을 그대로 이용하도록 디피를 사용한다.
 *
 */
public class BOJ2096_내려가기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        //배열 크기
        int N = Integer.parseInt(br.readLine());

        //입력으로 주어지는 값을 저장할 배열
        int[][] numArray = new int[N][3];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < 3; j++){
                numArray[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //최종적으로 답을 구할 변수

        int maxResult = 0;
        int minResult = 0;

        //값을 누적시킬 배열
        int [][] dp = new int[N][3];

        //초기값 저장
        dp[0][0] = numArray[0][0];
        dp[0][1] = numArray[0][1];
        dp[0][2] = numArray[0][2];

        //최대값 구하기
        for(int i = 1; i < N; i++){

            dp[i][0] = numArray[i][0] + Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = numArray[i][1] + Math.max(Math.max(dp[i - 1][0], dp[i - 1][1]), dp[i - 1][2]);
            dp[i][2] = numArray[i][2] + Math.max(dp[i - 1][1], dp[i - 1][2]);
        }

        maxResult = Math.max(Math.max(dp[N - 1][0],dp[N - 1][1]),dp[N - 1][2]);

        //최소값 구하기
        for(int i = 1; i < N; i++){

            dp[i][0] = numArray[i][0] + Math.min(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = numArray[i][1] + Math.min(Math.min(dp[i - 1][0], dp[i - 1][1]), dp[i - 1][2]);
            dp[i][2] = numArray[i][2] + Math.min(dp[i - 1][1], dp[i - 1][2]);

        }

        minResult = Math.min(Math.min(dp[N - 1][0],dp[N - 1][1]), dp[N - 1][2]);

        StringBuilder sb = new StringBuilder();

        sb.append(maxResult).append(" ").append(minResult);
        System.out.println(sb);
    }
}
