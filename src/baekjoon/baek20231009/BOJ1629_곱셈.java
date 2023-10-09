package baekjoon.baek20231009;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1629_곱셈 {


    private static long pow(long num, long exp, long C){

        if(exp == 1) return num % C;

        long tempValue = pow(num, exp / 2, C);

        //홀수면 한번 곱해줘야 함 - 모듈려 연산 이용 =>  (A*B)%C = ((A % C)*(B & C)) % C
        if(exp % 2 == 1) return ((tempValue * tempValue) % C) * num % C;

        return (tempValue * tempValue) % C;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());


        System.out.println(pow(A,B,C));

    }
}
