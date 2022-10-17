package baekjoon.baek20221017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author : sh Lee
 * @date : 22. 10. 17.
 */
public class BOJ10820_문자열_분석 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] count = new int[4];

        int n = 1;
        StringBuilder sb = new StringBuilder();
        String inputStr = "";
        while((inputStr = br.readLine()) != null){
            Arrays.fill(count,0);

            for(int i = 0; i < inputStr.length(); i++){
                char temp = inputStr.charAt(i);

                //소문자
                if(Character.isLowerCase(temp)){
                    count[0]++;
                }
                //대문자
                else if(Character.isUpperCase(temp)){
                    count[1]++;
                }
                //숫자
                else if(Character.isDigit(temp)){
                    count[2]++;
                }
                //공백
                else{
                    count[3]++;
                }
            }

            for (int i = 0; i < 4; i++){
                sb.append(count[i]).append(" ");
            }

            sb.append("\n");

        }


        sb.setLength(sb.length()-1);
        System.out.println(sb);
    }
}
