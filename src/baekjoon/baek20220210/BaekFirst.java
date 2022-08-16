package baekjoon.baek20220210;

/*
* 백준 2445번 별찍기8 구현연습*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class BaekFirst {

    public static void main(String[] args) throws IOException{
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        //첫번째줄 부터 2*n -1 까지 출력
        //별개수 증가 - 한줄에 N*2칸
        for(int i = 1; i<=n ; i++){
            System.out.println("*".repeat(i)+ " ".repeat((n-i)*2) +"*".repeat(i));
        }
        //별개수 감소
        for(int i = n-1; i>=1; i--){
            System.out.println("*".repeat(i) + " ".repeat((n-i)*2) + "*".repeat(i));
        }

    }

}
