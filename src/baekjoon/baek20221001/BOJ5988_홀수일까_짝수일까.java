package baekjoon.baek20221001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5988_홀수일까_짝수일까 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++){
            String temp = br.readLine();

            if(Integer.parseInt(temp.charAt(temp.length()-1)+"")%2 == 0){
                System.out.println("even");
            }
            else{
                System.out.println("odd");
            }
        }
    }


}
