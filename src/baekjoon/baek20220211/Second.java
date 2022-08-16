package baekjoon.baek20220211;
/*
* 백준 이진수 - 3460(자바, 브론즈3) 구현연습*/
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Second {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //테스트 케이스
        int t = Integer.parseInt(br.readLine());

        for(int i = 0 ; i<t ; i++){
            int n = Integer.parseInt(br.readLine());

            StringBuilder sb = new StringBuilder(Integer.toBinaryString(n));

            String intToBinary = sb.reverse().toString();


            for(int j =0; j < intToBinary.length(); j++){
                if(intToBinary.charAt(j) == '1'){
                    System.out.print(j+" ");
                }
            }
            System.out.println("");
        }

    }
}
