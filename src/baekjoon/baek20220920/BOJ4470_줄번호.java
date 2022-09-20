package baekjoon.baek20220920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ4470_줄번호 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for(int i = 1; i <= N; i++){

            System.out.println(i+". " + br.readLine());
        }
    }
}
