package baekjoon.baek20220721;

/**
 * 백준 13458번(삼성 역테 대비)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class First {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        //응시자 수
        String[] a = br.readLine().split(" ");

        StringTokenizer st = new StringTokenizer(br.readLine());
        //총 감독관 - 최대 1
        int b = Integer.parseInt(st.nextToken());
        //부 감독관
        int c = Integer.parseInt(st.nextToken());

        long result = 0;
        for(int i = 0; i < a.length; i++){
            int people = Integer.parseInt(a[i]);

            //총감독관 커버 > 부 감독관 커버

            if(people - b <= 0){
                result++;
            }
            else{
                result++;
                if((people - b) % c == 0){
                    result += (int) ((people - b) / c);
                }
                else{
                    result += (int) ((people - b) / c)  + 1;
                }
            }

        }

        System.out.println(result);
    }
}
