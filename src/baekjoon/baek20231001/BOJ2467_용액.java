package baekjoon.baek20231001;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 투 포인터를 이용한 문제
 * start는 0, end는 배열의 마지막 인덱스에서 출발하면 해결 할 수 있다.
 */
public class BOJ2467_용액 {

    private static int N;

    private static int[] numArray;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        numArray = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            numArray[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = N - 1;


        int nearZeroValue = Integer.MAX_VALUE;
        int startValue = 0;
        int endValue = 0;


        while(start < end){

            int sumValue = numArray[start] + numArray[end];

            //0으로부터 떨어진 거리가 기존에 저장된 값보다 작다면 저장.
            if(nearZeroValue > Math.abs(sumValue)){
                nearZeroValue = Math.abs(sumValue);
                startValue = numArray[start];
                endValue = numArray[end];
            }

            //두 합이 0보다 작다면,
            if(sumValue < 0){
                start++;
            }
            //두 합이 0보다 크다면,
            else if(sumValue > 0){
                end--;
            }
            //0이면
            else break;
        }

        System.out.println(startValue + " " + endValue);

    }
}