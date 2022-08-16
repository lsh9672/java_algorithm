package baekjoon.baek20220814;

/**
 * BOJ15649
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15649 {

    static int N;
    static int M;
    static StringBuilder result;
    static boolean[] check;

    static void recursive(int idx, StringBuilder sb){
        if(idx == M){

            //마지막에 들어간 공백 빼주기
            sb.setLength(sb.length()-1);
            //개행 붙여주기.
            sb.append("\n");
            //결과 출력
            result.append(sb.toString());
//            System.out.println(sb);
            return;
        }

        for(int i = 1; i <= N; i++){
            if(!check[i]){
                check[i] = true;
                sb.append(i).append(" ");
                recursive(idx+1,sb);
                sb.setLength(sb.length()-2);
                check[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        result = new StringBuilder();
        check = new boolean[N+1];

        recursive(0,sb);

        System.out.println(result);


    }
}
