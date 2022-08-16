package baekjoon.baek20220226;
/*
* 백준 1158번(실버5 - 구현연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class First {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        //큐에서 조건에 맞게 꺼낸것 저장
        Integer[] result = new Integer[n];

        //큐 만들기
        Queue<Integer> queue = new LinkedList<>();
        //큐에 값넣기
        for(int i = 1; i <= n ; i++){
            //add 대신에 offer를 써야, 공간이 부족해도 exception이 터지지 않음
            queue.offer(i);
        }


        //큐가 빌때까지 반복
        for(int j =0; j < n; j++ ){

            //k-1개만큼 poll해서 다시 큐에 넣음
            for(int m = 0; m < k-1; m++){
                queue.offer(queue.poll());
            }

            result[j] = queue.poll();
        }

        StringBuilder sb = new StringBuilder("<");
        sb.append(result[0]);

        for(int x = 1; x< result.length; x++){
            sb.append(", ");
            sb.append(result[x]);
        }
        sb.append(">");

        System.out.println(sb);

    }
}
