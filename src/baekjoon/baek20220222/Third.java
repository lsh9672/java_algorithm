package baekjoon.baek20220222;
/*
* 백준 9093번 단어 뒤집기(자바 구현연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Third {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for(int i=0;i<testCase;i++){

            String[] sentence = br.readLine().split(" ");
            for(int j = 0; j<sentence.length; j++){
                StringBuffer sb = new StringBuffer(sentence[j]);
                sb.reverse();
                System.out.print(sb + " ");
            }
        }
    }
}
