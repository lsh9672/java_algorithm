package baek20220211;

/*
* 백준 약수구하기 - 2501(구현,자바연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;


public class First {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        //몇번째인지 숫자를 셈
        int count = 0;

        //약수 저장
        int result = 0;

        for(int i = 1 ;i <= n;i++){
            if(n%i == 0){
                count += 1;
            }
            //목표 약수에 도달하면 반복문 종료
            if(count == k){
                result = i;
                break;
            }
        }
        System.out.println(result);

    }
}
