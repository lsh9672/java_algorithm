package baekjoon.baek20220723;

/**
 * 백준 1244번 (월말평가 연습)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Arrays;
import java.util.StringTokenizer;

public class Second {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //스위치의 개수
        int n = Integer.parseInt(br.readLine());

        //스위치의 상태
        String[] switchState = br.readLine().split(" ");

        int numStudent = Integer.parseInt(br.readLine());

        StringTokenizer st = null;
        for(int i = 0; i < numStudent; i++){
            st = new StringTokenizer(br.readLine());

            //성별
            int sex = Integer.parseInt(st.nextToken());

            //받은 수
            int inputNum = Integer.parseInt(st.nextToken());

            //남자일 때
            if(sex == 1){
                for(int x = 1; inputNum*x - 1< n; x++){
                    if(switchState[inputNum*x-1].equals("0")){
                        switchState[inputNum*x-1] = "1";
                    }
                    else{
                        switchState[inputNum*x-1] = "0";
                    }
                }
            }
            //여자일 때
            else{
                int count = 1;
                //받은 값을 바꿔줌
                if (switchState[inputNum-1].equals("0")) switchState[inputNum-1] = "1";
                else switchState[inputNum-1] = "0";

                //반복문을 돌면서 대칭이면 값을 바꿈.
                while(true){
                    //배열 범위를 벗어나지 않고, 대칭이면 값을 뒤집음
                    if(inputNum-1-count >=0 && inputNum-1+count<n && switchState[inputNum-1-count].equals(switchState[inputNum-1+count])){
                        if(switchState[inputNum-1-count].equals("0")){
                            switchState[inputNum-1-count] = "1";
                            switchState[inputNum-1+count] = "1";
                        }
                        else{
                            switchState[inputNum-1-count] = "0";
                            switchState[inputNum-1+count] = "0";
                        }

                    }
                    else break;

                    count++;

                }

            }

//            System.out.println(Arrays.asList(switchState));
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < switchState.length; i++){
            if(i != 0 && i%10 == 0){
                result.setLength(result.length()-1);
                result.append("\n");
            }

            result.append(switchState[i]).append(" ");

        }

        System.out.println(result);
    }
}
