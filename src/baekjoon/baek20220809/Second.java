package baekjoon.baek20220809;

import java.util.HashMap;
import java.util.Map;

public class Second {
    //물품목록 개수 매핑
    static Map<String,Integer> wantedMap;

    //매일매일 10일간의 할인 품목 누적
   static Map<String,Integer> foodCount;

    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        //각 식료품
        foodCount = new HashMap<>();
        for(String temp : want){
            if(!foodCount.containsKey(temp)){
                foodCount.put(temp,0);
            }

        }

        //원하는 개수 매핑
        wantedMap = new HashMap<>();
        for(int i= 0; i < want.length; i++){
            wantedMap.put(want[i], number[i]);
        }


        int startIdx = 0;
        int endIdx = 10;

        //초기 10일간의 데이터 업데이트
        for(int i = startIdx; i < endIdx; i++){
            if(foodCount.containsKey(discount[i])){
                foodCount.put(discount[i], foodCount.get(discount[i])+1);
            }
        }


        while(true){

            //처음에 누적된 값과 가지고 있는 값을 확인.
            boolean flag = true;
            for(String test : want){
                if(foodCount.get(test) < wantedMap.get(test)){
                    flag = false;
                    break;
                }
            }
            //중간에 브레이크 되지 않았다면.
            if(flag) answer++;

            if(endIdx == discount.length) break;
            //다음 구간
            startIdx++;
            endIdx++;

            //다음 구간의 식료품 갯수 업데이트
            if(foodCount.containsKey(discount[startIdx-1])){
                foodCount.put(discount[startIdx-1],foodCount.get(discount[startIdx-1])-1);
            }
            if(foodCount.containsKey(discount[endIdx-1])){
                foodCount.put(discount[endIdx-1],foodCount.get(discount[endIdx-1])+1);
            }

        }


        return answer;
    }


    public static void main(String[] args) {
        Second s = new Second();
        String[] want = {"banana", "apple", "rice", "pork", "pot"};
        int[] number = {3, 2, 2, 2, 1};
        String[] discount = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};

        System.out.println(s.solution(want,number,discount));
    }
}
