package baekjoon.baek20220530;

/**
 * 백준 2346번 (자바, 자료구조) - 덱 구현체로 ArrayDeque를 안쓰면 시간초과가 난다 (처음에는 LinkedList를 사용했음)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Second {

    static class Balloon{
        private int number;
        private int value;

        public int getNumber() {
            return number;
        }

        public int getValue() {
            return value;
        }

        public Balloon(int number, int value) {
            this.number = number;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        Deque<Balloon> numQueue = new ArrayDeque<>();

        StringBuilder result = new StringBuilder();

        for(int i = 1; i < n+1; i++){
            Balloon bl = new Balloon(i,Integer.parseInt(st.nextToken()));
            numQueue.add(bl);
        }

        int popValue = numQueue.pollFirst().getValue();
        result.append(1).append(" ");

        while (numQueue.size() > 0){
            if(popValue > 0){

                for(int i = 0; i < popValue-1; i++){
                    numQueue.addLast(numQueue.pollFirst());
                }

                Balloon temp = numQueue.pollFirst();
                popValue = temp.getValue();
                result.append(temp.getNumber()).append(" ");
            }
            else{
                for(int i = popValue ; i < -1 ; i++){
                    numQueue.addFirst(numQueue.pollLast());
                }

                Balloon temp = numQueue.pollLast();
                popValue = temp.getValue();
                result.append(temp.getNumber()).append(" ");
            }
        }

        result.setLength(result.length()-1);
        System.out.println(result);
    }
}
