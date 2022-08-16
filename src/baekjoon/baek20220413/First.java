package baekjoon.baek20220413;

/**
 * 백준 13335번(자바 구현연습)
 * ---접근방법---
 * 1. 입력으로 주어진 트럭을 전부 받아서 큐에 넣는다. - 이것도 큐로 구현
 * 2. 다리를 큐라고 생각하면, 한쪽에서 트럭이 들어오고 먼저 들어온 트럭이 반대쪽에서 나가는 모습이 된다.
 * 3. 매 반복마다 시간을 증가시키고, 현재 다리(큐)에 있는 트럭들의 무게를 계산한다.
 * 4. 계산한 다리위의 트럭 무게와, 다음에 들어갈 트럭의 무게를 더했을떄, 다리의 최대 하중보다 작거나 같으면 트럭이 저장된 대기큐에서 하나를 꺼내서 다리 큐에 넣는다.
 * 5. 만약 다리의 최대 하중보다 크다면, 0을 넣어서 트럭이 이동하는 것 처럼 보이게 한다.
 * 6. 다리 큐의 길이가 최대가 되면 앞에것부터 꺼낸다.
 * 7. 이 과정을 대기큐와, 다리큐에 값이 없을때까지 반복한다.
 */

/*백준 13335번(자바 구현연습)*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class First {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // n:트럭수, w:다리의 길이, l:최대 하중
        int n = 0;
        int w = 0;
        int l = 0;

        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        Queue<Integer> bridge = new LinkedList<>();
        //초기에 다리를 전부 0으로 채움
        for(int i = 0; i<w ; i++){
            bridge.add(0);
        }

        Queue<Integer> truck = new LinkedList<>();

        //트럭 입력
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            truck.add(Integer.parseInt(st.nextToken()));
        }

        int time = 0;
        //다리의 현재 무게를 저장하고 있을 변수
        int bridgeWeight = 0;

        while(!bridge.isEmpty()){
            time += 1;

            //우선 맨앞에 꺼를 뺴야됨
            int temp = bridge.poll();

            //무게 업데이트
            bridgeWeight -= temp;
            //다음 트럭이 있는 지 확인
            //다음 트럭이 있을떄
            if(!truck.isEmpty()){
                //다음트럭이 들어올수 있는지 없는지 확인
                int nextTruckWeight = truck.peek();
                //두 값을 더했을때, 다리의 최대 무게보다 무거운지 확인 - 들어올수 없음
                if(nextTruckWeight+bridgeWeight > l){
                    //무겁다면, 0을 넣음
                    bridge.add(0);
                }
                //다음 트럭이 들어올수 있다면
                else{
                    nextTruckWeight = truck.poll();
                    bridge.add(nextTruckWeight);
                    bridgeWeight += nextTruckWeight;
                }
            }

        }

        System.out.println(time);

    }

}
