package baekjoon.baek20221018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
1. 주어진 문자열을 앞에서부터 스택에 넣는다
2. 폭발문자열의 마지막 문자열과 스택의 맨 위의 문자열이 같다면, 폭발문자열의 길이만큼꺼내서 합쳐본다.
3. 합친 문자열이 폭발문자열이라면, 그대로 삭제, 아니라면 문자열을 다시 다 넣는다.
 */

/**
 * @author : sh Lee
 * @date : 22. 10. 19.
 */
public class BOJ9935_문자열_폭발 {
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

                StringBuilder tempCombineStr = new StringBuilder();
                tempCombineStr.append(tempChar);


                boolean check = false;
                StringBuilder tempSave = new StringBuilder();
                for(int j = 0; j < explosionStr.length() - 1; j++){

                    //스택에서 빼보던 중에 값이 없다면 종료
                    if(strStack.isEmpty()){
                        check = true;
                        break;
                    }

                    char tempPop = strStack.pop();
                    tempCombineStr.append(tempPop);
                    tempSave.append(tempPop);
                }

                //온전하게 문자열을 뺐다면.
                if(!check){

                    tempCombineStr.reverse();
                    //두 문자가 다르면 스택에 다시 넣어야됨.
                    if(!explosionStr.equals(tempCombineStr.toString())){
                        String temp = tempCombineStr.toString();
                        for(int j = 0; j < temp.length(); j++){
                            strStack.add(temp.charAt(j));
                        }
                    }
                }
                else{
                    tempSave.reverse();
                    String temp = tempSave.toString();
                    for(int j = 0; j < tempSave.length(); j++){
                        strStack.add(temp.charAt(j));
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
