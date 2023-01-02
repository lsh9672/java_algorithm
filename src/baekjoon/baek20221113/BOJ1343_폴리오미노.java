package baekjoon.baek20221113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : sh Lee
 * @date : 22. 11. 13.
 */
public class BOJ1343_폴리오미노 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputStr = br.readLine();

        int count = 0;
        StringBuilder result = new StringBuilder();
        boolean check = false;
        for(int i = 0; i < inputStr.length(); i++){

            if(inputStr.charAt(i) == 'X'){
                count++;
                if(count == 4){
                    result.append("AAAA");
                    count = 0;
                }
            }
            else{
                if(count == 2){
                    result.append("BB");
                }
                else if(count != 0){
                    check = true;
                    break;
                }

                count = 0;
                result.append(".");
            }
        }

        if(count == 2){
            result.append("BB");
        }
        else if(count != 0) check = true;


        if(check){
            System.out.println(-1);
        }
        else{
            System.out.println(result);
        }
    }
}
