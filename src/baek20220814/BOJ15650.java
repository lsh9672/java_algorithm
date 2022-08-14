package baek20220814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15650 {

    static int N;
    static int M;
    static StringBuilder sb;
    static int[] sel;

    static void recursive(int count, int idx){
        if(count == M){
            for(int temp : sel){
                sb.append(temp).append(" ");
            }

            sb.append("\n");
            return;
        }

        for(int i = idx; i <= N; i++){

            sel[count] = i;
            recursive(count+1,i+1);

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sb = new StringBuilder();
        sel = new int[M];

        recursive(0,1);

        sb.setLength(sb.length()-1);
        System.out.println(sb);

    }
}
