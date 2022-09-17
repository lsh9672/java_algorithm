package baekjoon.baek20220915;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2752_세수정렬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int[] test = new int[3];
        for(int i = 0; i < 3; i++){
            test[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(test);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 3; i++){
            sb.append(test[i]).append(" ");
        }

        System.out.println(sb);
    }
}
