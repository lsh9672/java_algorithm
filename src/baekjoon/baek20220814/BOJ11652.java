package baekjoon.baek20220814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ11652 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<Long,Integer> numCheck = new HashMap<>();

        int N = Integer.parseInt(br.readLine());

        for(int i =0 ; i < N; i++){
            Long temp = Long.parseLong(br.readLine());

            //키가 포함되어있으면 +1
            if(numCheck.containsKey(temp)){
                numCheck.put(temp,numCheck.get(temp)+1);
            }
            else{
                numCheck.put(temp, 1);
            }
        }

        List<Map.Entry<Long, Integer>> sortList = new ArrayList<Map.Entry<Long,Integer>>(numCheck.entrySet());

        sortList.sort((o1,o2) ->{
            if(o1.getValue().equals(o2.getValue())){
                return o1.getKey().compareTo(o2.getKey());
            }

            return o2.getValue() - o1.getValue();
        } );

        System.out.println(sortList.get(0).getKey());

    }
}
