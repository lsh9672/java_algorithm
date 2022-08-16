package baekjoon.baek20220627;

/**
 * 백준 11279번 (자바, 자료구조 연습)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class First {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        /**
         * Collections.reverseOrder()를 이용해서 최대힙을 구현한거랑, 힙에 넣을때 -1을 곱해서 최대힙처럼 동작하게 한것 두개의 속도차이가 거의 없음.
         */
//        Queue<Integer> max_heap = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Integer> max_heap = new PriorityQueue<>();

        StringBuilder result = new StringBuilder();

        for(int i = 0; i < n; i++){
            int temp = Integer.parseInt(br.readLine());

            if(temp == 0){
                if(max_heap.size() == 0){
                    result.append(0);
                }
                else{
                    result.append((-1)*max_heap.poll());
                }
                result.append("\n");
            }

            else{
                max_heap.add((-1) * temp);
            }
        }

        result.setLength(result.length() - 1);
        System.out.println(result);
    }
}
