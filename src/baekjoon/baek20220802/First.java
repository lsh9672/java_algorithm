package baekjoon.baek20220802;

/**
 * 백준 2442번
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class First {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= N; i++){
            for(int j = i; j < N; j++){
                sb.append(" ");
            }

            for(int x= 0; x< i*2-1; x++){
                sb.append("*");
            }

            sb.append("\n");
        }

        sb.setLength(sb.length()-1);
        System.out.println(sb);

    }
}
