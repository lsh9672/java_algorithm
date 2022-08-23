package baekjoon.baek20220822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2441 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++){

            for(int x = 0; x < i; x++){
                sb.append(" ");
            }
            for(int y = N - i; y > 0; y--){
                sb.append("*");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }
}
