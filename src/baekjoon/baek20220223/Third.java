package baekjoon.baek20220223;
/*
* 백준 1977번 완전제곱수(자바 구현연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Third {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int result =0;
        int min = 10001;
        for(int i = 1; i <= 100; i++){
            int temp = i*i;
            if(temp>=n && temp<=m){
                if(min > temp){
                    min = temp;
                }
                result+=temp;
            }
        }

        if(result == 0){
            System.out.println(-1);

        }
        else{
            System.out.println(result);
            System.out.println(min);
        }

    }
}
