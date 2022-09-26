package baekjoon.baek20220926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BOJ1159_농구_경기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<Character> firstChar = new ArrayList<>();

        for(int i = 0; i < N; i++){
            String temp = br.readLine();

            firstChar.add(temp.charAt(0));

        }

        firstChar.sort(Comparator.naturalOrder());
        firstChar.add('*');
        StringBuilder result = new StringBuilder();


        int count = 0;

        for(int i = 0; i < firstChar.size()-1; i++){
            char currentChar = firstChar.get(i);
            char nextChar = firstChar.get(i+1);

            if(currentChar == nextChar){
                count++;
            }
            else{
                count++;
                if(count >= 5){
                    result.append(currentChar);
                }
                count = 0;
            }

        }

        if(result.length() == 0){
            System.out.println("PREDAJA");
        }
        else{
            System.out.println(result);
        }


    }
}
