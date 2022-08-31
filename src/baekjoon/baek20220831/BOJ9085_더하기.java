package baekjoon.baek20220831;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9085_더하기 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++){
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int total = 0;
            for(int j = 0; j < N; j++){
                total += Integer.parseInt(st.nextToken());
            }

            System.out.println(total);
        }
    }
}
