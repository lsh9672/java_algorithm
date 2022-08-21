package ssafy.algorism;

/**
 * 싸피 알고리즘 과목평가 대비 기출 문제 풀이 - 6기 기출 1번
 */

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Solution1 {

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/ssafy/algorism/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        //모음 저장
        Set<Character> vowel = new HashSet<>(){
            {
                add('a');
                add('e');
                add('i');
                add('o');
                add('u');
            }
        };

        for(int testCase = 1; testCase <= T; testCase++){
            StringBuilder result = new StringBuilder();

            String inputValue = br.readLine();

            for(int i = 0; i < inputValue.length(); i++){

                if(vowel.contains(inputValue.charAt(i))){
                    result.append(inputValue.charAt(i));
                    i+=2;
                }
                else{
                    result.append(inputValue.charAt(i));
                }
            }

            System.out.println("#"+ testCase + " " + result);
        }

    }
}
