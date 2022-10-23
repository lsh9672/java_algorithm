package baekjoon.baek20221023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 23.
 */
public class BOJ1010_다리놓기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());

        for(int testCase = 0; testCase < T; testCase++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            //서쪽에 N개, 동쪽에 M개의 사이트가 있을떄 다리를 짓을수 있는 경우의 수
            int[][] dp = new int[N+1][M+1];

            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= M; j++){

                    //서쪽과 동쪽의 다리가 같다면 한가지 경우밖에 생기지 않음(서쪽 사이트는 전부 사용해야됨.)
                    if(i == j) dp[i][j] = 1;

                    //서쪽에 사이트가 한개라면 동쪽 사이트 개수만큼 다리건설 가능
                    else if(i == 1) dp[i][j] = j;

                    //서쪽이 더 많은 경우는 불가.
                    //서쪽이 더 적은 경우에만 만들수 있음
                    else if(i < j) dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
                }
            }


            System.out.println(dp[N][M]);
        }
    }
}
