package baek20220727;

/**
 * 백준 2798번 (역테 준비)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Second {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] cards = new int[N];

        for(int i = 0; i < N; i++){
            cards[i] = Integer.parseInt(st.nextToken());
        }
        int result = Integer.MIN_VALUE;

        int temp = 0;

        loop:
        for(int x = 0; x < N-2; x++){
            for(int y = x+1; y < N-1; y++){
                for(int z = y+1; z < N; z++){
                    temp = cards[x] + cards[y] + cards[z];

                    if(temp == M){
                        result = M;
                        break loop;
                    }
                    else if(temp <= M){
                        result = Math.max(result, temp);
                    }
                }
            }
        }

        System.out.println(result);

    }
}
