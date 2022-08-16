package baekjoon.baek20220226;
/*
* 백준 2164번 (실버4, 자바 구현연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class Third {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new LinkedList<>();

        for(int i = 1; i <=n; i++){
            queue.offer(i);
        }

        while(queue.size() > 1){

            //맨위에 한장 버림
            queue.poll();

            //맨위에서 한장 뽑아서 맨 아래에 넣음
            queue.offer(queue.poll());

        }

        System.out.println(queue.poll());
    }
}
