package baekjoon.baek20221026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : sh Lee
 * @date : 22. 10. 26.
 */
public class BOJ2292_벌집 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        //각 층별로 (1*6-6)개 -> (2*6-6)개 -> (3*6-6)개 ...
        int count = 1;

        int prev = 0;
        int current = 1;

        while(true){

            if(prev < N  && N <= current){
                break;
            }

            count++;

            prev = current;
            current = prev + ((count)*6 - 6);
        }

        System.out.println(count);

    }
}
