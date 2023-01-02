package baekjoon.baek20221106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : sh Lee
 * @date : 22. 11. 6.
 */

/**
 * Map을 이용해서 책을 키값으로, 책의 수를 value로 저장한 후, keyset()을 이용해서 탐색하여 가장 많이 팔린 책을 찾는다.
 */
public class BOJ1302_베스트셀러 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> bookMap = new HashMap<>();

        int N = Integer.parseInt(br.readLine());

        int maxValue = 1; //책의 최대 개수 저장해두기.

        for(int i= 0; i < N; i++){
            String bookName = br.readLine();

            if(bookMap.containsKey(bookName)){

                int tempValue = bookMap.get(bookName) + 1;

                bookMap.put(bookName, tempValue);

                maxValue = Math.max(maxValue, tempValue);
            }

            else{

                bookMap.put(bookName, 1);
            }
        }
        List<String> bestSeller = new ArrayList<>();
        for(String name : bookMap.keySet()){

            if(bookMap.get(name) == maxValue){
                bestSeller.add(name);
            }
        }

        bestSeller.sort(Comparator.naturalOrder());
        System.out.println(bestSeller.get(0));

    }
}
