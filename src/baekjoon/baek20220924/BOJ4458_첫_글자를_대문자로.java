package baekjoon.baek20220924;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ4458_첫_글자를_대문자로 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++){
            String temp = br.readLine();
            System.out.println(temp.substring(0,1).toUpperCase() + temp.substring(1));
        }

    }
}
