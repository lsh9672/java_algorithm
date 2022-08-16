package baekjoon.baek20220804;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Fourth {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Queue<Integer> cards = new ArrayDeque<>();

        for(int i = 1; i <= N; i++){
            cards.add(i);
        }

        while(cards.size() >= 2){
            cards.poll();

            cards.add(cards.poll());

        }

        System.out.println(cards.peek());

    }
}
