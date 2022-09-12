package programmers.prog20220912;

import java.util.*;

public class 카카오공채2021_메뉴_리뉴얼 {

    static Map<String,Integer> courseCount;
    static int maxValue;

    //코스요리 메뉴의 조합을 구해서 맵에 저장.
    static void recursive(char[] menu,int idx, int count, int menuLength, char[] sel){

        //목표하는 메뉴 길이에 도달하면,맵에 저장.
        if(count >= menuLength){
            String temp = new String(sel);

            //키가 존재하면 개수만 추가.
            if(courseCount.containsKey(temp)){
                courseCount.put(temp, courseCount.get(temp) + 1);
                maxValue = Math.max(maxValue, courseCount.get(temp));
            }
            else{
                courseCount.put(temp,1);
            }

            return;

        }

        if(idx >= menu.length){
            return;
        }

        for(int i = idx; i < menu.length; i++){
            sel[count] = menu[i];
            recursive(menu,i+1,count+1,menuLength,sel);
        }
    }

    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};

        List<String> tempAnswer = new ArrayList<>();

        for(int menuLength : course){
            courseCount = new HashMap<>();
            maxValue = Integer.MIN_VALUE;
            //메뉴를 2
            for(String order : orders){

                char[] menuChar = order.toCharArray();
                Arrays.sort(menuChar);

                recursive(menuChar,0,0,menuLength, new char[menuLength]);
            }

            for(Map.Entry<String,Integer> courseInfo : courseCount.entrySet()){
                if(courseInfo.getValue() == maxValue){
                    tempAnswer.add(courseInfo.getKey());
                }
            }
        }

        answer = tempAnswer.toArray(new String[tempAnswer.size()]);
        Arrays.sort(answer);

        return answer;
    }

    public static void main(String[] args) {
        카카오공채2021_메뉴_리뉴얼 tt = new 카카오공채2021_메뉴_리뉴얼();
        String[] orders1 = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course1 = {2,3,4};
        System.out.println(Arrays.toString(tt.solution(orders1,course1)));


        String[] orders2 = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        int[] course2 = {2,3,5};
        System.out.println(Arrays.toString(tt.solution(orders2,course2)));

        String[] orders3 = {"XYZ", "XWY", "WXA"};
        int[] course3 = {2,3,4};
        System.out.println(Arrays.toString(tt.solution(orders3,course3)));

    }
}
