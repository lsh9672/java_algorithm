package baekjoon.baek20220815.miniTest;

/**
 * 프로그래머스 모의고사 2회
 */

public class Solution1 {

    static int count;

    static void recursive(int[] number, int idx, int check, int value){
        if(check >= 3){
            if(value == 0){
                count++;
            }
            return;
        }

        for(int i = idx; i < number.length; i++){
            recursive(number, i+1, check+1,value + number[i]);
        }

    }

    public int solution(int[] number) {


        count = 0;
        recursive(number, 0,0,0);

        return count;
    }

    public static void main(String[] args) {
        Solution1 sol = new Solution1();
        int[] number = {-2, 3, 0, 2, -5};
        System.out.println(sol.solution(number));

        int[] number1 = {-3, -2, -1, 0, 1, 2, 3};
        System.out.println(sol.solution(number1));

        int[] number2 = {-1, 1, -1, 1};
        System.out.println(sol.solution(number2));


    }
}
