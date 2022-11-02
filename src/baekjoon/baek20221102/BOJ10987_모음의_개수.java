package baekjoon.baek20221102;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : sh Lee
 * @date : 22. 11. 2.
 */
public class BOJ10987_모음의_개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Set<Character> vowel = new HashSet<>();
        vowel.add('a');
        vowel.add('e');
        vowel.add('i');
        vowel.add('o');
        vowel.add('u');


        String inputStr = br.readLine();
        int count = 0;

        for(int i = 0; i < inputStr.length(); i++){
            if(vowel.contains(inputStr.charAt(i))) count++;
        }

        System.out.println(count);

    }
}
