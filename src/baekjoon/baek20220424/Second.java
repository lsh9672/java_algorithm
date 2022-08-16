package baekjoon.baek20220424;

/**
 * 백준 3273번(투포인터, 자바연습)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Second {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Integer[] num_array = new Integer[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++){
            num_array[i] = Integer.parseInt(st.nextToken());
        }

        int x = Integer.parseInt(br.readLine());

        Arrays.sort(num_array);
        int result_count = 0;
        int start = 0;
        int end = n-1;
        int total = 0;

        while(start < end){
            total = num_array[start] + num_array[end];

            if (total < x){
                start++;
            }

            else if(total > x){
                end--;
            }

            else{
                result_count++;
                start++;
            }
        }


        System.out.println(result_count);
    }
}
