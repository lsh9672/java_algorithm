package swea.sw20221006;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 6.
 */
public class SWEA3307_최장_증가_부분_수열 {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/home/leesh/lsh/my_study/java_study/algorism_java/src/swea/sw20221006/sample_input (18).txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        for(int testCase = 1; testCase <= T; testCase++){
            int N = Integer.parseInt(br.readLine());

            int[] numArray = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                numArray[i] = Integer.parseInt(st.nextToken());
            }
            int[] dp = new int[N];
            Arrays.fill(dp,1);

            for(int i = 0; i < N; i++){
                for(int j = 0; j < i; j++){

                    if(numArray[i] > numArray[j]){

                        dp[i] = Math.max(dp[i], dp[j]+1);
                    }
                }
            }
            int result = Integer.MIN_VALUE;
            for(int temp : dp){
                result = Math.max(temp,result);
            }

            System.out.println("#" + testCase+" " + result);
        }


    }
}
