package baekjoon.baek20220828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1978 {

    static int N;

    static boolean primeCheck(int num){

        //1은 소수가 아님.
        if(num == 1) return false;

        //에라토스테네스의 체를 이용해서 소수를 구함.
        int m = (int)Math.sqrt(num);

        for(int i = 2; i <= m; i++) {
            if(num%i == 0) return false;
        }
        return true;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int result = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(st.nextToken());
            if(primeCheck(num)) result++;

        }

        System.out.println(result);

    }
}
