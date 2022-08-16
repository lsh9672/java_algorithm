package baekjoon.baek20220728;

/**
 * 백준 1264번
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class First {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String vowel= "aeiou";

        while(true){
            String temp = br.readLine();

            if(temp.equals("#")){
                break;
            }

            int count = 0;

            for(int i = 0; i < temp.length(); i++){
                if(vowel.contains(String.valueOf(temp.charAt(i)).toLowerCase())){
                    count++;
                }
            }

            System.out.println(count);

        }
    }
}
