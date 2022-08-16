package baekjoon.baek20220809;

/*
 * BOJ 2563
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2563 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int result = 0;

        int N = Integer.parseInt(br.readLine());
        int[][] maps = new int[101][101];
        StringTokenizer st = null;
        for(int k= 0; k < N; k++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            for(int i = 100-y; i > 100-y-10; i--) {
                for(int j = x; j < x+10; j++) {
                    maps[i][j] = 1;
                }
            }
        }

        for(int i = 0; i < 101; i++) {
            for(int j = 0; j< 101; j++) {
                result+= maps[i][j];
            }
        }

        System.out.println(result);

    }

}

