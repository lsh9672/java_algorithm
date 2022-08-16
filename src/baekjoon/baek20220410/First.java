package baekjoon.baek20220410;
/*백준 2075번 (자바, 자료구조 연습)*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class First {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> numQue = new PriorityQueue<>(Collections.reverseOrder());


        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                numQue.add(Integer.parseInt(st.nextToken()));

            }
        }

        int result =0;
        for(int i = 0; i < n; i++){
            result = numQue.poll();
        }

        System.out.println(result);

    }
}
