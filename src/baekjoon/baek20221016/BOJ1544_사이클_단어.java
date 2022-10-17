package baekjoon.baek20221016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : sh Lee
 * @date : 22. 10. 16.
 */
public class BOJ1544_사이클_단어 {

    static String cycleString(int startIdx, String temp){
        StringBuilder sb = new StringBuilder();

        for(int i = startIdx; i < startIdx + temp.length(); i++){
            sb.append(temp.charAt(i%temp.length()));
        }


        return sb.toString();

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Set<String> wordSet = new HashSet<>();

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++){

            String temp = br.readLine();


            boolean flag = false;
            for(int j = 1; j < temp.length(); j++){
                String newStr = cycleString(j,temp);

                //
                if(wordSet.contains(newStr)){
                    flag = true;
                }
            }

            if(!flag){
                wordSet.add(temp);
            }
        }

        System.out.println(wordSet.size());
    }
}
