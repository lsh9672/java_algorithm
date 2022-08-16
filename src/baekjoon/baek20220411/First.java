package baekjoon.baek20220411;
/*백준 15903번(구현연습, 실버2)*/
/**
 * 문제를 보면 그리디라고 생각할 수있다.
 * 주어진 카드중 가장 작은 카드를 뽑아서 더했을때, 값이 가장 작을것이다
 * 가장 작은 값을 구하기 위해서는 매 순간 가장 작은 두값을 꺼내서 조건에 맞게 더하고,
 * 각각의 수를 업데이트하면된다.
 * 이 반복횟수가 모든 카드를 업데이트 안해도되는 작은 수일수도 있기 때문에
 * 매순간 가장 작은 카드를 선택해서 조건에 맞게 업데이트해주면 최소값을 구할 수 있다.*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class First {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());

        Long n = Long.parseLong(st.nextToken());
        Long m = Long.parseLong(st.nextToken());

        //최소 힙 선
        Queue<Long> numQue = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());

        //최소 힙에 값 다 넣기
        for(int i = 0; i < n; i++){
            numQue.add(Long.parseLong(st.nextToken()));
        }

        //m번 만큼 반복
        for(int i = 0; i < m; i++){
            //x와y를 뽑음 - 가장 작은 수 두개를 뽑음
            Long x = numQue.poll();
            Long y = numQue.poll();

            //두 수를 더하고, 각각에 카트에 덮어씌우고 다시 큐에 넣음
            numQue.add(x+y);
            numQue.add(x+y);

        }

        Long result = 0L;
        //반복이 다 끝났으면, 우선순위큐에 값을 전부 꺼내서 더함
        int length = numQue.size();
        for(int i =0; i < length;i++){

            result += numQue.poll();
        }

        System.out.println(result);

    }
}
