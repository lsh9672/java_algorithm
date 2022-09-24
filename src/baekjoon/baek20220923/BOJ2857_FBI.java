package baekjoon.baek20220923;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2857_FBI {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            String temp = br.readLine();
            if(temp.contains("FBI")){
                result.add(i+1);
            }
        }
        if(result.size() == 0){
            System.out.println("HE GOT AWAY!");
        }
        else{
            result.sort(Comparator.naturalOrder());
            for(int i : result){
                System.out.println(i);
            }
        }
    }
}
