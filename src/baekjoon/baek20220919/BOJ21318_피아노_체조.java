package baekjoon.baek20220919;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ21318_피아노_체조 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());

        int[] numArray = new int[N+1];
        st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++){
            numArray[i] = Integer.parseInt(st.nextToken());
        }

        /*로직*/
        // 특정 위치까지 실수횟수를 누적시키는 누적합을 구하고 거기서 값을 뽑아냄

        //실수횟수를 누적하는 배열
        int[] mistakeCounts = new int[N+1];
        for(int i = 1; i <= N-1; i++){
            //i+1번째가 더 어렵다면 체크
            if(numArray[i] > numArray[i+1]){
                mistakeCounts[i+1]++;
            }

        }

        //누적합 만들기
        for(int i = 1; i <= N; i++){
            mistakeCounts[i] += mistakeCounts[i-1];
        }


        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            sb.append(mistakeCounts[y] - mistakeCounts[x]).append("\n");

        }

        sb.setLength(sb.length()-1);
        System.out.println(sb);


    }

}
