package baek20220816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ 2839 그리디 연습
 */

public class BOJ2839 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int result = 0;

        while(N > 0){
            if(N%5 == 0){
                result += N/5;
                break;
            }

            N -= 3;
            result++;
        }

        if(N <0){
            System.out.println(-1);
        }
        else{
            System.out.println(result);
        }

    }
}
