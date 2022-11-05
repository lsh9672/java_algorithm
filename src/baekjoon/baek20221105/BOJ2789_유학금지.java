package baekjoon.baek20221105;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : sh Lee
 * @date : 22. 11. 5.
 */
public class BOJ2789_유학금지 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String temp = "CAMBRIDGE";

        Set<Character> checkChar = new HashSet<>();
        for(int i = 0; i < temp.length(); i++){
            checkChar.add(temp.charAt(i));
        }

        char[] inputChar = br.readLine().toCharArray();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < inputChar.length; i++){
            if(!checkChar.contains(inputChar[i])){
                sb.append(inputChar[i]);
            }
        }

        System.out.println(sb);

    }
}
