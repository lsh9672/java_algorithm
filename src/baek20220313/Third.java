package baek20220313;
/*
* 백준 1244번(스위치, 구현연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Third {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int switchNum = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        Integer[] switchState = new Integer[switchNum];

        for(int i = 0; i < switchNum; i++){
            switchState[i] = Integer.parseInt(st.nextToken());
        }


        int studentNum = Integer.parseInt(br.readLine());
        for(int i = 0; i< studentNum; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine());

            //1이면 남자, 2면 여자
            int man_woman = Integer.parseInt(st2.nextToken());
            int receiveNum = Integer.parseInt(st2.nextToken());

            //로직이 들어가야됨.

            //남자의 경우
            if(man_woman == 1){
                //받은 수에 배수인 스위치를 전부 끔
                for(int j = receiveNum; j<=switchNum;j+=receiveNum){
                    switchState[j-1] = switchState[j-1]==0?1:0;
                }

            }


            //여자의 경우
            else{
                //받은 값은 먼저 상태를 바꿈
                switchState[receiveNum-1] = switchState[receiveNum-1]==0?1:0;
                for(int j = 1; j <=switchNum/2; j++){
                    //해당 값이 스위치 배열을 안넘어가는지 확인해야됨.
                    if(receiveNum-1+j >= switchNum || receiveNum-1-j < 0) break;

                    //두 값이 같으면 스위치 상태 바꿈
                    if(switchState[receiveNum-1-j] == switchState[receiveNum-1+j]){
                        switchState[receiveNum-1-j] = switchState[receiveNum-1-j]==0?1:0;
                        switchState[receiveNum-1+j] = switchState[receiveNum-1+j]==0?1:0;
                    }
                    //같지 않으면 반복문을 끝
                    else break;
                }

            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<switchNum ;i++){
            sb.append(switchState[i]).append(" ");

            //한줄에 20개씩출력
            if((i+1)%20 == 0){
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}
