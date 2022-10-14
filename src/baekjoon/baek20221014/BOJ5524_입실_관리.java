package baekjoon.baek20221014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : sh Lee
 * @date : 22. 10. 14.
 */
public class BOJ5524_입실_관리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for(int i= 0 ; i < N; i++){
            String temp = br.readLine();

            System.out.println(temp.toLowerCase());
        }
    }
}
