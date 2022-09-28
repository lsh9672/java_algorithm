package baekjoon.baek20220928;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ24883_자동완성 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String temp = br.readLine();

        if(temp.equals("N") || temp.equals("n")){
            System.out.println("Naver D2");
        }
        else{
            System.out.println("Naver Whale");
        }


    }
}
