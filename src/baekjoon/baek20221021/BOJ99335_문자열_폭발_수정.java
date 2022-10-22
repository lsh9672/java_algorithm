package baekjoon.baek20221021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * @author : sh Lee
 * @date : 22. 10. 21.
 */
public class BOJ99335_문자열_폭발_수정 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String originStr = br.readLine().trim(); //원래 문자열
        String explosionStr = br.readLine().trim(); //폭발 문자열.

        char explosionLastChar = explosionStr.charAt(explosionStr.length()-1);

        Stack<Character> strStack = new Stack<>();


        for(int i = 0; i < originStr.length(); i++){

            char tempChar = originStr.charAt(i);

            //스택에 넣으려고 하는 문자가 폭발문자열의 마지막 문자라면 이전에 들어간 것을 꺼내서 합쳐봄
            if(tempChar == explosionLastChar){

                //스택에 들어간 글자 크기가 폭발문자열 길이보다 작다면 패스
                if(strStack.size() - (explosionStr.length()-1) < 0) {
                    strStack.add(tempChar);
                    continue;
                }

                boolean check = true;
                for(int j = 0; j < explosionStr.length() - 1; j++){

                    if(explosionStr.charAt(j) != strStack.get(j + (strStack.size() - (explosionStr.length()-1)))){
                        check = false;
                        break;
                    }

                }

                //문자열 삭제조건에 맞지 않으면 추가
                if(!check) strStack.add(tempChar);

                else{
                    //터지는 글자 빼기
                    for(int j = 0; j < explosionStr.length()-1; j++){
                        strStack.pop();
                    }
                }

            }
            else{
                strStack.add(tempChar);
            }
        }

        StringBuilder result = new StringBuilder();
        if(strStack.isEmpty()) System.out.println("FRULA");
        else{
            while(!strStack.isEmpty()){
                result.append(strStack.pop());
            }

            result.reverse();
            System.out.println(result);
        }



    }
}
