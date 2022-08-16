package baekjoon.baek20220308;
/*
* 백준 2609번 (구현연습, - 유클리드호제법이용)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Third {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int first = Integer.parseInt(st.nextToken());
        int second = Integer.parseInt(st.nextToken());

        int a = Math.min(first,second);
        int b = Math.max(first,second);


        int lcm = 0;
        int gcd = 0;

        // b mod a
        while(true){
            int remainder = b%a;

            if(remainder == 0){
                gcd = a;
                break;
            }

            b = a;
            a = remainder;


        }

        lcm = (first*second)/gcd;

        System.out.println(gcd);
        System.out.println(lcm);
    }
}
