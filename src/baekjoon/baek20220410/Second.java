package baekjoon.baek20220410;

/*백준 17952번(자바, 자료구조 연습)*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


public class Second {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        //이번 학기 시간
        int n = Integer.parseInt(br.readLine());

        //과제를 담고 있을 스택
        Stack<Integer[]> hwStack = new Stack<>();

        //과제 점수
        int result = 0;

        for(int i = 0; i< n; i++){
            st = new StringTokenizer(br.readLine());

            String temp = st.nextToken();

            if(temp.equals("0")){
                //비어있으면 패스
                if(hwStack.isEmpty()){
                    continue;
                }
                //비어있지않으면, 마지막 원소를 빼서, 시간을 감
                else{
                    Integer[] tempElement = hwStack.pop();
                    //과제 시간 -1을 함
                    tempElement[1] -= 1;

                    //과제를 다했으면 점수를 최종점수에 더해줌
                    if(tempElement[1] == 0){
                        result += tempElement[0];
                    }

                    //과제를 다 못했으면 다시 스택에 넣어둠
                    else{
                        hwStack.push(tempElement);
                    }
                }
            }
            //새로운 과제가 들어왔다면,
            else{
                Integer[] tempTime = new Integer[2];
                tempTime[0] = Integer.parseInt(st.nextToken());
                //새로운 과제가 들어오자마자 바로 과제를 하기 때문에 -1
                tempTime[1] = Integer.parseInt(st.nextToken()) - 1;

                //과제를 다했다면,최종 점수에 추가
                if(tempTime[1] == 0){
                    result += tempTime[0];
                }

                //과제를 다 안했다면, 스택에 넣음
                else{
                    hwStack.push(tempTime);
                }
            }
        }
        System.out.println(result);

    }
}
