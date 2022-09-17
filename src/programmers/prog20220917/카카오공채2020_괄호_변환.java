package programmers.prog20220917;

import java.util.Arrays;
import java.util.Stack;

public class 카카오공채2020_괄호_변환 {


    //균형잡힌 문자열 확인
    static boolean balanceStr(String str){
        int count = 0;

        for(int i = 0; i < str.length(); i++){
            char temp = str.charAt(i);

            if(temp == '(') count++;
            else count--;
        }

        if(count==0) return true;

        return false;
    }

    //옳바른 문자열 확인.
    static boolean correctStr(String str){

        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < str.length(); i++){
            char temp = str.charAt(i);

            if(temp == '(') stack.add(temp);
            else{
                if(stack.isEmpty()) return false;
                else{
                    stack.pop();
                }
            }
        }

        if(stack.isEmpty()) return true;
        else return false;
    }

    //문자열 두개로 분리하기.
    static String[] seperate(String str){

        //0번쨰가 u, 1번째가 v
        String[] returnString = new String[2];

        int count = 0;
        //균형잡힌 괄호로 더이상 분리 불가능해야되기 때문에 하나씩 찾다가 처음으로 균형잡힌 괄호가 되면 리턴
        //중간에 break되지 않았다면 w는 원본 그대로, v는 빈 문자열로 리턴.
        boolean check = true;
        for(int i = 0; i < str.length(); i++){
            char temp = str.charAt(i);

            if(temp == '(') count++;
            else count--;


            if(count == 0) {
                check = false;
                returnString[0] = str.substring(0,i+1);
                returnString[1] = str.substring(i+1);
                break;
            }
        }

        if(check){
            returnString[0] = str;
            returnString[1] = "";
        }

        return returnString;
    }

    static String reverseStr(String str){

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < str.length();i++){
            char temp = str.charAt(i);

            if(temp == '(') sb.append(")");
            else sb.append("(");
        }

        return sb.toString();
    }

    //
    static String logic(String str){
        //1.입력이 빈 문자열인 경우
        if(str.length() == 0) return str;

        //2. 두 문자 분리
        String[] temp = seperate(str);
        String u = temp[0];
        String v = temp[1];

        //3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행
        //3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환
        if(correctStr(u)){
            return u + logic(v);
        }
        //4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행
        else {

            StringBuilder sb = new StringBuilder();
            //4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
            sb.append("(");

            //4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
            sb.append(logic(v));

            //4-3. ')'를 다시 붙입니다.
            sb.append(")");

            //4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
            sb.append(reverseStr(u.substring(1, u.length() - 1)));

            //4-5. 생성된 문자열을 반환합니다.
            return sb.toString();

        }

    }

    public String solution(String p) {

        return logic(p);
    }


    public static void main(String[] args) {
        카카오공채2020_괄호_변환 tt = new 카카오공채2020_괄호_변환();

        String p1 = "(()())()";

        System.out.println(tt.solution(p1));

        String p2 = ")(";
        System.out.println(tt.solution(p2));

        String p3 = "()))((()";
        System.out.println(tt.solution(p3));

    }
}
