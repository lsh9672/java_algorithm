package baekjoon.baek20221023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author : sh Lee
 * @date : 22. 10. 23.
 */
/* 아이디어
1. 콜론을 기준으로 8개 구간이 생겨야 된다.
2. 1번에 맞게 :: 으로 이루어져 있으면 8개 구간에 맞게 복구시킨다
3. 안의 값들은 전부 0으로 채운다.
4. 각 자리 앞에 0이 붙으면 생략이 가능하기 때문에 4자리에 맞춰준다(부족한 자리수는 0으로 채운다) ex) 436 -> 0436

 */

public class BOJ3107_IPv6 {

    static String makeZero(int num){

        StringBuilder sb =new StringBuilder();

        for(int i= 0; i < num; i++){
            sb.append("0");
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //1. 콜론을 기준으로 각 구간의 ip 주소를 넣을수 있도록 길이 8의 배열을 만든다.
        String[] ipArray = new String[8];
        Arrays.fill(ipArray,"0");

        //2. "::"을 기준으로 문자열을 나눈다.
        String inputStr = br.readLine();
        String[] tempArray = inputStr.split("::");

        System.out.println(Arrays.toString(tempArray));

        if(tempArray.length >= 2){
            // 0번째 인덱스인 왼쪽부분, 1번째 인덱스인 오른쪽 부분을 각각 :으로 분리하고 개수를 센다.
            String[] leftIp = null;
            String[] rightIp = null;

            leftIp = tempArray[0].split(":");
            rightIp = tempArray[1].split(":");


            for(int i = 0; i < leftIp.length; i++){
                ipArray[i] = leftIp[i];
            }

            int idx = 7;
            for(int j = rightIp.length - 1; j >= 0; j--){
                ipArray[idx] = rightIp[j];
                idx--;
            }
        }

        else if(tempArray.length == 1){
            String[] temp = tempArray[0].split(":");
            for(int i = 0; i < temp.length; i++){
                ipArray[i] = temp[i];
            }
        }


        //각 자리수를 4자리로 맞춤
        for(int i = 0; i < 8; i++){
            ipArray[i] = makeZero(4 - ipArray[i].length()) + ipArray[i];
        }

        StringBuilder sb = new StringBuilder();

        for(int i= 0; i < 8; i++){

            sb.append(ipArray[i]);

            if(i !=7){
                sb.append(":");
            }
        }

        System.out.println(sb);


    }
}
