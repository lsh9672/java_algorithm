package baekjoon.baek20220329;
/*백준 14425번 (자바 문자열 연습)*/
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;


public class Second {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<String> nValue = new HashSet<>();

        int resultCount = 0;

        for(int i = 0; i < n; i++){
            nValue.add(br.readLine());
        }

        for (int i = 0; i < m; i++){
            String temp = br.readLine();
            if(nValue.contains(temp)){
                resultCount++;
            }
        }

        System.out.println(resultCount);
    }
}
