package baekjoon.baek20220914;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11728_배열_합치기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] numArrays = new int[N+M];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            numArrays[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = N; i < M+N; i++){
            numArrays[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numArrays);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N+M; i++){
            sb.append(numArrays[i]).append(" ");
        }

        sb.setLength(sb.length()-1);
        System.out.println(sb);
    }
}
