package baekjoon.baek20221011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : sh Lee
 * @date : 22. 10. 11.
 */
public class BOJ12605_단어순서_뒤집기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for(int i = 1; i <= N; i++){
            String[] temp = br.readLine().split(" ");
            StringBuilder sb = new StringBuilder();

            for(int j = temp.length -1 ; j >= 0; j--){
                sb.append(temp[j]).append(" ");
            }
            System.out.println("Case #" + i +": " +sb);
        }
    }
}
