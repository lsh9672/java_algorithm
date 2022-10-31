package baekjoon.baek20221026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : sh Lee
 * @date : 22. 10. 26.
 */
public class BOJ5789_한다_안한다 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++){
            String temp = br.readLine();

            int firstIdx = (temp.length() / 2) - 1;
            int secondIdx = temp.length() / 2;

            if(temp.charAt(firstIdx) == temp.charAt(secondIdx)){
                System.out.println("Do-it");
            }
            else{
                System.out.println("Do-it-Not");
            }
        }
    }
}
