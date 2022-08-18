package baekjoon.baek20220817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1074 {

    static int N;
    static int r;
    static int c;
    static int count;

    static void recursive(int x, int y, int size) {

        if(size == 1) {
            return;
        }

        int half = size /2;


        //1사분면
        if(x < half && y < half) {
            recursive(x,y,half);
        }
        //2사분면
        else if(x < half && half <= y) {
            count += half*half;
            recursive(x,y - half,half);

        }
        //3사분면
        else if(half <= x && y < half) {
            count += half*half * 2;
            recursive(x - half,y,half);
        }
        //4사분면
        else {
            count += half*half * 3;
            recursive(x- half,y-half,half);

        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        count = 0;

        recursive(r,c,(int)Math.pow(2,N));

        System.out.println(count);

    }

}