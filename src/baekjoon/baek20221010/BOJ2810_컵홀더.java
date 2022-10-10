package baekjoon.baek20221010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : sh Lee
 * @date : 22. 10. 10.
 */
public class BOJ2810_컵홀더 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String temp = br.readLine();

        //총 컵홀더 수
        int count = 0;
        for(int i = 0; i < N; i++){
            char people = temp.charAt(i);

            if(people == 'S'){
                count++;
            }
            else{
                i++;
                count++;
            }
        }

        count++; //맨끝에 컵홀더를 놓을 수 있어서

        // 인원수보다 컵홀더 수가 많으면
        if(count > N) System.out.println(N);
        else System.out.println(count);

    }
}
