package baekjoon.baek202208029;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ1427_소트인사이드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] num = br.readLine().toCharArray();

        Arrays.sort(num);

        StringBuilder sb = new StringBuilder();
        for(int i = num.length-1; i >= 0 ; i--){
            sb.append(num[i]);
        }

        System.out.println(sb);
    }
}
