package baekjoon.baek20220918;

/**
 * 1. 입력받은 문자와 reverse() 메서드를 이용해서 뒤집은 문자를 별도로 저장한다.
 * 2. 대소문자를 구분하지 않기 때문에 toUpperCase() 메서드를 이용해서 대문자로 만든다.
 * 3. 두 문자열을 equal()를 사용해서 비교하여 같다면 팰린드롬이다.
 */

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Locale;

public class BOJ10174_팰린드롬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++){
            String originStr = br.readLine().toUpperCase();
            String reverseStr = (new StringBuilder(originStr)).reverse().toString();

            if(originStr.equals(reverseStr)){
                System.out.println("Yes");
            }
            else{
                System.out.println("No");
            }
        }
    }
}
