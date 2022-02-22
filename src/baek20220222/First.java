package baek20220222;
/*
* 백준 17608번 막대기(자바 구현연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class First {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //막대기 개수
        int n = Integer.parseInt(br.readLine());

        //막대기 정보를 배열에 넣기
        int[] stick = new int[n];

        for(int i = 0; i<n ;i++){
            stick[i] = Integer.parseInt(br.readLine());
        }
        //맨마지막값은 시작부터 포함
        int count=1;

        int lastStick = stick[n-1];

        for(int j = n-2 ; j>=0 ; j--){
            if(lastStick < stick[j]){
                lastStick = stick[j];
                count++;
            }
        }
        System.out.println(count);
    }
}
