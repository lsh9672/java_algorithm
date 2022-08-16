package baekjoon.baek20220209;

/*
* 백준 - 1330(두 수 비교하기, 브론즈4,자바 연습)*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class BaekFourth {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        if (a > b) System.out.println(">");
        else if (a < b) System.out.println("<");
        else System.out.println("==");

    }
}
