package baek20220809;

/*
프로그래머스 실전 모의고사 1회 1번
 */

import java.util.*;

public class First {

    static int StringCount(String str,String tmp){

        int count = 0;
        for(int i = 0; i < tmp.length(); i++){
            if(tmp.charAt(i) == str.charAt(0)){
                count++;
            }
        }
        return count;
    }

    public String solution(String X, String Y) {
        String answer = "";

        List<String> temp = new ArrayList<>();

        Set<String> test1 = new HashSet<>(Arrays.asList(X.split("")));
        Set<String> test2 = new HashSet<>(Arrays.asList(Y.split("")));

        //교집합으로 중복되는 값 가져오기
        test1.retainAll(test2);

        int firstCount = 0;
        int secondCount = 0;

        for(String k : test1){
            firstCount = StringCount(k,X);
            secondCount = StringCount(k,Y);

            //둘중 작은 수만큼 리스트에 추가
            int min = Math.min(firstCount,secondCount);
            for(int i = 0; i < min; i++){
                temp.add(k);
            }
        }

        if(temp.isEmpty()) return "-1";

        temp.sort((v1,v2)->{

            return Integer.parseInt(v2) - Integer.parseInt(v1);
        });

        //정렬했을 때 맨앞에 0이 있으면 뒤에 0이 여러개일 수도 있음
        if(temp.get(0).equals("0")) return "0";

        StringBuilder result = new StringBuilder();

        for(String t : temp){
            result.append(t);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        First f = new First();
        System.out.println(f.solution("100","123450"));
    }
}
