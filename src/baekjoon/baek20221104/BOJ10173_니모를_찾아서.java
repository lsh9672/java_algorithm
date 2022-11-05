package baekjoon.baek20221104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : sh Lee
 * @date : 22. 11. 4.
 */
public class BOJ10173_니모를_찾아서 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String temp = "";

        StringBuilder sb = new StringBuilder();
        while(!(temp = br.readLine()).equals("EOI")){
            String s = temp.toUpperCase();
            if(s.contains("NEMO")){
                sb.append("Found").append("\n");
            }
            else{
                sb.append("Missing").append("\n");
            }
        }
        System.out.println(sb);
    }
}
