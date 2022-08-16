package baekjoon.baek20220731;

/**
 * 백준 2440번
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Second {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i =  N; i > 0; i--){
            for(int j = 0; j < i; j++){
                sb.append("*");
            }
            sb.append("\n");
        }
        sb.setLength(sb.length()-1);
        System.out.println(sb);


    }
}
