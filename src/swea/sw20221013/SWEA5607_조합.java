package swea.sw20221013;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 13.
 */
public class SWEA5607_조합 {

    static long div(long a, long b){
        if(b == 1) return a;

        long temp  = div(a,b/2);

        if(b%2 == 1) return temp*temp%1234567891*a%1234567891;
        else return temp*temp%1234567891;

    }


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/home/leesh/lsh/my_study/java_study/algorism_java/src/swea/sw20221013/sample_input (21).txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        for(int testCase = 1; testCase <= T; testCase++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());


            long minValue = Math.min(N,N-R);

            long a = 1,b = 1;

            for(int i = 0; i < minValue; i++){
                a = a*(N - i) % 1234567891;
                b = b*(minValue - i) % 1234567891;
            }

            long result = (a%1234567891 * div(b,1234567891-2)%1234567891)%1234567891;

            System.out.println("#"+ testCase+" "+ result);
        }

    }


}
