package baekjoon.baek20220210;
/*
* 백준 2446 - 별찍기9 자바 구현연습*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class BaekSecond {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        br.close();

        //단일 스레드 환경에서는 StringBuffer보다 빠르며, 문자열이 계속 바뀌여야 하므로 String보다 효율적이다.
        StringBuilder sb = new StringBuilder("*".repeat(2*n -1));

        System.out.println(sb);
        //별개수 감소
        for(int i = 0 ; i<n-1 ; i++){
            sb.setCharAt(i,' ');
            sb.deleteCharAt((n*2)-2-i);
            System.out.println(sb);
        }
        //별개수 증가
        for(int i = n-2 ; i>=0; i--){
            sb.setCharAt(i,'*');
            sb.append('*');
            System.out.println(sb);

        }

    }
}
