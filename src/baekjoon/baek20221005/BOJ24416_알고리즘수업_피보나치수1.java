package baekjoon.baek20221005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : sh Lee
 * @date : 22. 10. 5.
 */
public class BOJ24416_알고리즘수업_피보나치수1 {

    static int n1;
    static int n2;
    static int[] f;

    static int fib(int n){

        if(n == 1 || n == 2) return 1;

        else
        {
            n1++;
            return (fib(n-1) + fib(n-2));
        }
    }

    static int fibonacci(int n){
        f[1] = 1;
        f[2] = 1;

        for(int i = 3; i <= n; i++){
            n2++;
            f[i] = f[i-1] + f[i-2];
        }

        return f[n];

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        f = new int[N+1];
        fib(N);
        fibonacci(N);

        System.out.println((n1+1) + " " + n2);

    }
}
