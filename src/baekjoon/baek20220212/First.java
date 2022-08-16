package baekjoon.baek20220212;

/*
* 백준 20053 최소,최대2 (자바 브론즈 3)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class First {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //테스트 케이스
        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0 ;i <testCase;i++){

             int n = Integer.parseInt(br.readLine());

             int max = -1000001, min = 1000001;
             StringTokenizer st = new StringTokenizer(br.readLine());
             int temp = 0;
             for(int j =0; j < n ;j++){
                 temp = Integer.parseInt(st.nextToken());
                 max = Math.max(max,temp);
                 min = Math.min(min,temp);
             }

            System.out.println(min+" "+max);
        }
    }
}
