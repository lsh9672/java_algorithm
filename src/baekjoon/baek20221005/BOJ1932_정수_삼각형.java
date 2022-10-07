package baekjoon.baek20221005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 5.
 */
public class BOJ1932_정수_삼각형 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());

        int[][] triangle = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j <= i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i < 5; i++){
            for(int j = 0; j <= i; j++){

                //처음이면
                if(j == 0){
                    triangle[i][j] += triangle[i-1][j];
                }

                else{
                    triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]);
                }
            }
        }

        int result  = Integer.MIN_VALUE;

        for(int i = 0; i < N; i++){
            result = Math.max(result,triangle[N-1][i]);
        }

        System.out.println(result);

    }
}
