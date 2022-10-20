package baekjoon.baek20221018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : sh Lee
 * @date : 22. 10. 18.
 */
public class BOJ10102_개표 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());

        int aCount = 0;

        String temp = br.readLine();
        for(int i = 0; i < V; i++){
            if(temp.charAt(i) == 'A') aCount++;
        }

        int bCount = V - aCount;

        if(aCount > bCount) System.out.println("A");
        else if(aCount < bCount) System.out.println("B");
        else System.out.println("Tie");
    }
}
