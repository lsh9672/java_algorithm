package baekjoon.baek20220912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ6603_로또 {

    static void recursive(int idx, int count, int[] numArray, int[] sel){
        if(count >= 6){
            StringBuilder sb = new StringBuilder();
            for(int t : sel){
                sb.append(t).append(" ");
            }
            sb.setLength(sb.length()-1);
            System.out.println(sb);
            return;
        }

        for(int i = idx; i < numArray.length; i++){
            sel[count] = numArray[i];
            recursive(i+1, count+1,numArray, sel);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = null;

        while(true){
            String temp = br.readLine();


            if(temp.equals("0")) break;

            String[] tempArray = temp.split(" ");

            int num = Integer.parseInt(tempArray[0]);

            int[] numArray = new int[num];

            for(int i = 0; i < num; i++){
                numArray[i] = Integer.parseInt(tempArray[i+1]);
            }

            int[] sel = new int[6];
            recursive(0, 0, numArray, sel);

            System.out.println();

        }
    }
}
