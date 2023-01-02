package swea.sw20221113;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 11. 13.
 */
public class SWEA1486_장훈이의_높은_선반 {

    static int N,B;

    static int[] peopleArray;

    static int result;

    static void recursive(int idx, int selectTotalValue){

        if(selectTotalValue >= B){
            result = Math.min(result,selectTotalValue);
            return;
        }

        if(idx > N) return;

        for(int i = idx; i < N; i++){
            recursive(i+1, selectTotalValue + peopleArray[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/home/leesh/lsh/my_study/java_study/algorism_java/src/swea/sw20221113/input (2).txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        for(int testCase = 1; testCase <= T; testCase++){
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            peopleArray = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                peopleArray[i] = Integer.parseInt(st.nextToken());
            }

            result = Integer.MAX_VALUE;
            recursive(0,0);

            System.out.println("#"+testCase + " " + (result - B));

        }
    }
}
