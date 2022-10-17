package programmers.prog20221018;

import java.util.HashMap;
import java.util.Map;


//약 40분, 참조 X, 자료구조 : Map사용, 다른사람 코드: LinkedMap또는 ArrayList를 사용하기도 함.
/**
 * @author : sh Lee
 * @date : 22. 10. 18.
 */
public class 카카오2018공채_캐시 {

    static Map<String,Integer> cacheCheck;


    //LRU 구현 - 캐시 miss인데, 캐시 크기가 최대라면 기존에 사용이 던 된 값을 찾아서 교체해야됨.
    //사용된지 가장 오래된 값은 value값이 가장 작은 값임.
    static void LRUAlgo(String data, int refCount){

        int minValue = Integer.MAX_VALUE;
        String minKey = "";
        //반복문을 돌면서 value가 가장 작은 값을 찾음
        for(String tempKey : cacheCheck.keySet()){
            if(minValue > cacheCheck.get(tempKey)){
                minKey = tempKey;
                minValue = cacheCheck.get(tempKey);
            }
        }

        //최종적으로 구한 교체할 키값을 삭제
        cacheCheck.remove(minKey);

        //새로운 값을 넣음
        cacheCheck.put(data,refCount);

    }

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        //LRU 가장 오랫동안 사용되지 않은 것 선택, - 캐시에 처음 들어오면 value의 숫자가 낮을 수록 교체가 안된것
        cacheCheck = new HashMap<>();

        int refCount = 1; //다음에 들어갈 참조 카운트값


        //반복문을 돌면서 데이터를 확인함.
        //해당 데이터가 캐시에 있는 지 확인하고, 데이터가 없다면 miss이다.
        //miss인 경우는 LRU알고리즘을 동작시켜서 교체를 한다.
        //miss가 아닌 경우에는 참조 카운트를 증가 시킨다.
        for(String city : cities){

            city = city.toUpperCase(); //대소문자를 구분하지 않기 때문에 대문자로 바꿈.

            //캐시 hit라면
            if(cacheCheck.containsKey(city)){
                answer += 1;
                cacheCheck.put(city,refCount);
                refCount++;
            }

            //캐시 miss라면
            else{
                answer += 5;
                //캐시크기가 최대인지 확인

                if(cacheSize == 0) continue;

                //최대라면 교체 알고리즘이 동작해야 됨.
                if(cacheCheck.size() == cacheSize){
                    LRUAlgo(city,refCount);
                }
                //최대가 아니면 그대로 추가해도 됨.
                else{
                    cacheCheck.put(city,refCount);
                }
                refCount++;
            }
        }

        return answer;
    }


    public static void main(String[] args) {

        카카오2018공채_캐시 s = new 카카오2018공채_캐시();

        int cacheSize1 = 3;
        String[] cities1 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        System.out.println(s.solution(cacheSize1,cities1));

        int cacheSize2 = 3;
        String[] cities2 = {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
        System.out.println(s.solution(cacheSize2,cities2));

        int cacheSize3 = 2;
        String[] cities3 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
        System.out.println(s.solution(cacheSize3,cities3));

        int cacheSize4 = 5;
        String[] cities4 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
        System.out.println(s.solution(cacheSize4,cities4));

        int cacheSize5 = 2;
        String[] cities5 = {"Jeju", "Pangyo", "NewYork", "newyork"};
        System.out.println(s.solution(cacheSize5,cities5));

        int cacheSize6 = 0;
        String[] cities6 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        System.out.println(s.solution(cacheSize6,cities6));


    }
}
