package baekjoon.baek20221020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : sh Lee
 * @date : 22. 10. 20.
 */
public class BOJ9933_민균이의_비밀번호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Set<String> password = new HashSet<>();

        int result = 0;
        char resultChar = ' ';

        for(int i = 0; i < N; i++){
            String originStr = br.readLine();
            StringBuilder temp = new StringBuilder(originStr);
            temp.reverse();
            String reverseStr = temp.toString();

            String test = "";

            //뒤집은 단어가 존재한다면 출력.
            if(originStr.equals(reverseStr) || password.contains(reverseStr)){
                result = temp.toString().length();
                resultChar = temp.charAt(temp.length()/2);
            }
            else{

                password.add(originStr);
            }
        }

        System.out.println(result + " " + resultChar);
    }
}
