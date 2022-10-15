package baekjoon.baek20221015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 15.
 */
public class BOJ2745_진법_변환 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String temp = st.nextToken();
        int n = Integer.parseInt(st.nextToken());

        int result = 0;

        for(int i = temp.length() -1 ; i >= 0; i--){

            if(temp.charAt(i) >= '0' & temp.charAt(i) <= '9'){
                result += (int) Math.pow(n, temp.length()-1 - i) * (temp.charAt(i) - '0');
            }
            else{
                result += (int) Math.pow(n, temp.length()-1 - i) * (temp.charAt(i) - 'A' + 10);
            }
        }

        System.out.println(result);


    }
}
