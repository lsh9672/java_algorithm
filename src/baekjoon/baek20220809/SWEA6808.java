package baekjoon.baek20220809;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA6808 {

    static int inCount;
    static int kyuCount;


    //중복을 막기 위해 사용한 숫자나열
    static boolean[] check;
    static int[] kyuArray;


    static void recursive(int idx, int kyuTotal, int inTotal) {

        if(idx == 9) {
            // 인영이가 이기는 경우.
            if(inTotal > kyuTotal) {
                inCount++;
            }
            //규영이가 이기는 경우
            else if(inTotal < kyuTotal) {
                kyuCount++;
            }
            return;
        }

        //숫자 뽑기.
        for(int i = 0; i < 18; i++) {
            // 해당 숫자를 뽑지 않았다면.
            if(!check[i+1]) {
                check[i+1] = true;
                //규영가 이겼을때
                if(kyuArray[idx] > i+1) {
                    recursive(idx+1,kyuTotal + (kyuArray[idx] + i+1),inTotal);
                }
                //인영이가 이겼을때.
                else {
                    recursive(idx+1,kyuTotal, inTotal + (kyuArray[idx] + i+1));
                }

                check[i+1] = false;
            }
        }

    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringTokenizer st = null;
        for(int testCase = 1; testCase <= T; testCase++) {
            inCount = 0;
            kyuCount = 0;
            st = new StringTokenizer(br.readLine());
            check = new boolean[19];

            //인영이가 내는 경우
            kyuArray = new int[9];
            for(int i = 0; i < 9; i++) {
                int staticNum = Integer.parseInt(st.nextToken());
                kyuArray[i] = staticNum;
                check[staticNum] = true;

            }

            recursive(0, 0, 0);

            System.out.println("#"+testCase + " " + kyuCount + " " + inCount);
        }


    }

}

