package baekjoon.baek20220917;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ16637_괄호_추가하기 {

    static int N;
    static List<Character> operator;
    static List<Integer> num;

    static int result;

    static int operation(int firstNum, int secondNum, char op) {

        if(op == '+') {
            return firstNum + secondNum;

        }

        else if(op == '-') {
            return firstNum - secondNum;
        }
        else{
            return firstNum * secondNum;
        }
    }


    static void recursive(int idx, int total) {

        if(idx == operator.size()) {
            result = Math.max(result, total);
            return;
        }


        //괄호를 치는 경우
        recursive(idx+1, operation(total, num.get(idx+1), operator.get(idx)));

        // 괄호를 치지 않는 경우
        if(operator.size() >= idx +2) {
            recursive(idx+2, operation(total,operation(num.get(idx+1),num.get(idx+2), operator.get(idx+1)),operator.get(idx)));
        }

    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        result = Integer.MIN_VALUE;

        String temp = br.readLine();

        operator = new ArrayList<>();
        num = new ArrayList<>();

        for(int i = 0 ; i < temp.length(); i++) {
            // 연산자 저장.
            if(i%2 == 1) {
                operator.add(temp.charAt(i));
            }

            // 숫자
            else {
                num.add(Character.getNumericValue(temp.charAt(i)));
            }
        }

        recursive(0,num.get(0));

        System.out.println(result);



    }
}
