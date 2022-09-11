package baekjoon.baek20220911;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2161_카드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Queue<Integer> numQue = new LinkedList<>();

        for(int i = 1; i <= N; i++){
            numQue.add(i);
        }

        StringBuilder result = new StringBuilder();

        for(int i =0; i < N; i++){
            result.append(numQue.poll()).append(" ");

            numQue.add(numQue.poll());
        }

        result.setLength(result.length()-1);
        System.out.println(result);

    }
}
