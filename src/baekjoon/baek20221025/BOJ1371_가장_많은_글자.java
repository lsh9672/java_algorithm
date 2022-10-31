package baekjoon.baek20221025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : sh Lee
 * @date : 22. 10. 25.
 */
public class BOJ1371_가장_많은_글자 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] alphaCount = new int[26];

        String temp = "";
        while((temp = br.readLine()) != null){

            for(int i = 0; i < temp.length(); i++){
                if(temp.charAt(i) != ' '){
                    alphaCount[temp.charAt(i) - 'a']++;
                }
            }
        }

        int maxValue = Integer.MIN_VALUE;

        for(int i= 0; i < 26; i++){
            maxValue = Math.max(maxValue,alphaCount[i]);
        }

        StringBuilder sb = new StringBuilder();
        for(int i= 0 ; i < 26; i++){
            if(alphaCount[i] == maxValue){
                sb.append((char)(i+'a'));
            }
        }

        System.out.println(sb);
    }

}
