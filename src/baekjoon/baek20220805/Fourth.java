package baekjoon.baek20220805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Fourth {

    static int N;

    // 해당범위까지 소수 다 구해두기
    static boolean findPrime(int num) {

        //1은 소수가 아님.
        if(num == 1) return false;

        //에라토스테네스의 체를 이용해서 소수를 구함.
        int m= (int)Math.sqrt(num);

        for(int i = 2; i <= m; i++) {
            if(num%i == 0) return false;
        }
        return true;
    }

    static void recursive(String str) {

        if(str.length() == N) {
            System.out.println(Integer.parseInt(str));
            return;
        }

        for(int i = 1; i <= 9; i++) {

            // 숫자 한개씩 이어 붙이고 소수인지 판별 - 소수이면 재귀호출
            if(findPrime(Integer.parseInt(str+i)))
                recursive(str+i);
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        recursive("");

    }

}
