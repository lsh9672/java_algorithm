package baek20220811;

/**
 * BOJ 3040 - 백설공주와 일곱 난쟁이 (브론즈2)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ3040 {


    static int[] numArray;
    static boolean[] check;


    static void recursive(int idx, int count,int total){
        if(idx == 9){
            if(count==7){
                if(total == 100){
                    for(int i = 0; i < 9; i++){
                        if(check[i]){
                            System.out.println(numArray[i]);
                        }
                    }
                }
            }
            return;
        }

        check[idx] = false;
        recursive(idx+1,count, total);
        check[idx] = true;
        recursive(idx+1,count+1, total +numArray[idx]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        numArray = new int[9];
        for(int i = 0; i < 9; i++){
            numArray[i] = Integer.parseInt(br.readLine());
        }

        check = new boolean[9];

        recursive(0,0,0);


    }
}
