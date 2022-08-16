package baekjoon.baek20220808;

/**
 * SWEA 9229
 */
import java.io.*;
import java.util.StringTokenizer;

public class Third {
    static int N;
    static int M;
    static int result;
    static int[] snackWeight;

    static void recursive(int idx, int count, int value){

        //M 초과하면 종료
        if(value > M){
            return;
        }
        //배낭에 두개 넣었으면 종료
        if(count == 2){
            result = Math.max(result,value);
            return;
        }

        for(int i = idx; i < N; i++){
            recursive(i+1,count+1,value + snackWeight[i]);
        }

    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/baekjoon.baek20220808/sample_input (15).txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for(int testCase = 1; testCase <= T; testCase++){

            result = Integer.MIN_VALUE;

            st = new StringTokenizer(br.readLine());
            //과자봉지의 개수.
            N = Integer.parseInt(st.nextToken());
            //무게 제한.
            M = Integer.parseInt(st.nextToken());


            st = new StringTokenizer(br.readLine());
            //과자 무게
            snackWeight = new int[N];
            for(int i = 0; i < N; i++){
                snackWeight[i] = Integer.parseInt(st.nextToken());
            }

            recursive(0,0,0);
            if(result == Integer.MIN_VALUE) result = -1;
            System.out.println("#" + testCase + " " + result);

        }
    }
}
