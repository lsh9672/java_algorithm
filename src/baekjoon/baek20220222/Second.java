package baekjoon.baek20220222;
/*
* 백준 1357번 뒤집힌 덧셈(자바 구현연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Second {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");

        int result = 0;
        StringBuffer sb = null;
        for(int i=0;i<2;i++){
            sb = new StringBuffer(temp[i]);
            sb.reverse();
            result += Integer.parseInt(sb.toString());

        }

        //뒤집어서 더한걸 다시 뒤집기
        sb = new StringBuffer(String.valueOf(result));
        sb.reverse();

        System.out.println(Integer.parseInt(sb.toString()));
    }
}
