package baekjoon.baek20220212;
/*
* 백준 21918 - 전구(자바, 구현연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Second {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        //n:전구의 개수,m:명령어의 개수
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        //전구 상태
        st = new StringTokenizer(br.readLine());

        //전구 저장
        int[] electricBulbState = new int[n];
        int i = -1;

        while(++i < n ){
            electricBulbState[i] = Integer.parseInt(st.nextToken());
        }

        i = -1;
        while(++i < m ){
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            if(command == 1){
                electricBulbState[first-1] = second;
            }

            else if(command == 2){
                //반복문을 돌면서 상태변경
                for(int k = first-1; k <second; k++ ){
                    if(electricBulbState[k] == 0) electricBulbState[k] = 1;
                    else electricBulbState[k] = 0;
                }
            }
            else if(command == 3){
                for(int k = first-1; k <second; k++ ){
                    electricBulbState[k] = 0;
                }
            }
            else {
                for(int k = first-1; k <second; k++ ){
                    electricBulbState[k] = 1;
                }
            }
        }
        for(int x = 0 ; x < n;x++){
            System.out.print(electricBulbState[x]+" ");
        }

    }
}
