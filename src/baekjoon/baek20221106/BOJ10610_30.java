package baekjoon.baek20221106;

/**
 * @author : sh Lee
 * @date : 22. 11. 6.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 정수론 문제,
 * 30의 배수는 1. 0이 존재해야 하고, 2. 3의 배수의 조건을 만족해야한다.
 * 3의 배수는 각 자리수를 더해도 3의 배수가 되는 경우다(직접 계산해보면 나옴.)
 * 입력받은 문자열에 0이 존재하는지 확인하고, 모든 자리수를 더했을때 3의 배수라면, 9부터 역순으로 문자열을 연결해서 출력한다.
 */
public class BOJ10610_30 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] inputChar = br.readLine().toCharArray();

        //1. 문자열에 0이 존재하는지 확인.
        boolean check = false;
        for(int i = 0; i < inputChar.length; i++){
            if(inputChar[i] == '0'){
                check = true;
                break;
            }
        }

        if(!check){
            System.out.println("-1");
        }
        else{
            int total = 0;
            for(int i = 0; i < inputChar.length; i++){
                total += Character.getNumericValue(inputChar[i]);
            }

            if(total % 3 != 0){
                System.out.println("-1");
            }
            else{
                Arrays.sort(inputChar);

                StringBuilder sb = new StringBuilder();

                for(int i = inputChar.length -1; i >= 0; i--){
                    sb.append(inputChar[i]);
                }
                System.out.println(sb);
            }
        }

    }
}
