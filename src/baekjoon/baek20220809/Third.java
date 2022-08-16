package baekjoon.baek20220809;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Third {

    public int solution(int[] order) {
        int answer = 0;


        Queue<Integer> mainContain = new LinkedList<>();
        Stack<Integer> subContain = new Stack<>();

        for(int i =1; i <= order.length; i++){
            mainContain.add(i);
        }

        loop:
        for(int i = 0; i < order.length; i++){
            int wantNum = order[i];

            //보조 컨테이너가 비어있지 않고, 최상단 값이 원하는 값이라면 추가하고 넘어가기
            if(!subContain.empty() && subContain.peek() == wantNum){
                subContain.pop();
                answer++;
                continue;
            }
            //그게 아니라면 원하는 것을 찾을때까지 메인 컨테이너에서 하나씩 내림
            while(true){

                if(mainContain.isEmpty()) break loop;

                int temp = mainContain.poll();
                if(temp == wantNum){
                    answer++;
                    break;
                }
                else{
                    subContain.add(temp);
                }

            }

//            System.out.println(subContain.toString());
//            System.out.println(wantNum);

        }

        return answer;
    }

    public static void main(String[] args) {
        Third t = new Third();
        int[] order = {4,3,1,2,5};
        System.out.println(t.solution(order));
    }
}
