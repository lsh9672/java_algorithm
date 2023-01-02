package baekjoon.baek20221106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : sh Lee
 * @date : 22. 11. 6.
 */
public class BOJ1543_문서검색 {

    //문자열 비교.
    static boolean strCheck(String compareStr, String subString){

        if(compareStr.equals(subString)) return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String originStr = br.readLine();

        String compareStr = br.readLine();

        int start = 0;
        int end = start + compareStr.length();

        int total = 0;

        while(end <= originStr.length()){

            if(strCheck(compareStr,originStr.substring(start,end))){
                total++;
                start+= compareStr.length();
            }
            else{
                start++;
            }


            end = start + compareStr.length();
        }

        System.out.println(total);

    }
}
