package baek20220815.miniTest;


import java.util.HashMap;

import java.util.Map;

public class Solution2 {

    static int count;
    static Map<Integer,Integer> firstMap;
    static Map<Integer,Integer> secondMap;


    static void updateMap(int[] topping, int index){
        //첫번째 맵에는 추가해주고
        if(firstMap.containsKey(topping[index])){
            firstMap.put(topping[index], firstMap.get(topping[index])+1);
        }
        else{
            firstMap.put(topping[index], 1);
        }
        //두번쨰 맵에는 뺴줌
        //갯수가 1개라면 삭제
        if(secondMap.get(topping[index]) == 1){
            secondMap.remove(topping[index]);
        }
        //2개이상이라면,
        else{
            secondMap.put(topping[index], secondMap.get(topping[index])-1);
        }

        //업데이트 후에 길이 체크해서 count증가
        if(firstMap.size() == secondMap.size()){
            count++;
        }
    }

    public int solution(int[] topping) {

        count = 0;
        firstMap = new HashMap<>();
        secondMap = new HashMap<>();

        //첫 경우에 대한 값들을 세이브 해둠
        firstMap.put(topping[0], 1);
        for(int i = 1; i < topping.length; i++){
            if(secondMap.containsKey(topping[i])){
                secondMap.put(topping[i],secondMap.get(topping[i])+1);
            }
            else{
                secondMap.put(topping[i],1);
            }
        }

        //첫번째 경우에 대해서 계산해줌
        if(firstMap.size() == secondMap.size()) count++;

        //각 경우마다 잘라보기
        for(int i = 1; i < topping.length-1; i++){
            updateMap(topping,i);
        }

        return count;
    }

    public static void main(String[] args) {
        Solution2 sol2 = new Solution2();

        int[] topping1 = {1, 2, 1, 3, 1, 4, 1, 2};
        System.out.println(sol2.solution(topping1));

        int[] topping2 = {1, 2, 3, 1, 4};
        System.out.println(sol2.solution(topping2));

    }
}
