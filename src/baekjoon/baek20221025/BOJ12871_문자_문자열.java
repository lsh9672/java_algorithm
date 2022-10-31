package baekjoon.baek20221025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : sh Lee
 * @date : 22. 10. 25.
 */
public class BOJ12871_문자_문자열 {


    //최대공약수  - 최소 공배수를 구하려면 필요
    static int gcd(int a, int b){
        if(b == 0) return a;

        return gcd(b, a%b);
    }

    //최소 공배수
    static int lcm(int a, int b){
        return a*b / gcd(a,b);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder firstStr = new StringBuilder(br.readLine());
        StringBuilder secondStr = new StringBuilder(br.readLine());

        if(firstStr.length() == secondStr.length()){
            if(firstStr.toString().equals(secondStr.toString())){
                System.out.println(1);
            }
            else{
                System.out.println(0);
            }
        }
        //두 문자열의 최소 공배수를 구해서 그 크기까지 이어 붙여봄
        else{

            StringBuilder aStr = new StringBuilder(firstStr.toString());
            StringBuilder bStr = new StringBuilder(secondStr.toString());

            int lcm = lcm(firstStr.length(), secondStr.length());

            for(int i = 0; i < lcm / firstStr.length() - 1; i++){
                aStr.append(firstStr);
            }
            for(int i = 0; i < lcm / secondStr.length() - 1; i++){
                bStr.append(secondStr);
            }

            if(aStr.toString().equals(bStr.toString())){
                System.out.println(1);
            }
            else{
                System.out.println(0);
            }
        }


    }
}
