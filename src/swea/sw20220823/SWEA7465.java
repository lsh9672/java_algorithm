package swea.sw20220823;

import java.io.*;
import java.util.StringTokenizer;

public class SWEA7465 {

    static int N;
    static int M;
    static int[] parents;


    static int find(int a){
        if(parents[a] == a) return a;

        return parents[a] = find(parents[a]);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);

        if(a < b){
            parents[b] = a;
        }
        else if(a > b){
            parents[a] = b;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/swea/sw20220823/s_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        for(int testCase = 1; testCase <= T; testCase++){

            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            parents = new int[N+1];
            for(int i = 1; i <= N; i++){
                parents[i] = i;
            }

            for(int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                union(a,b);
            }

            int count = 0;
            for(int i = 1; i <= N; i++){
                if(parents[i] == i) count++;
            }

            System.out.println("#" + testCase + " " + count);

        }
    }

}
