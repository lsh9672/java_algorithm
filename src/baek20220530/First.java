package baek20220530;

/**
 * 백준 1935번 (자바, 자료구조)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class First {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String notation = br.readLine().strip();

        Map<Character,Double> numberArray = new HashMap<>();

        for(int i = 0; i < n; i++){
            numberArray.put((char)(i+65),Double.parseDouble(br.readLine().strip()));
        }

        int index = 0;
        Stack<Double> result = new Stack<>();

        for(int i = 0; i< notation.length(); i++){
            //알파벳이면 숫자로 변환해서 스택에 넣기
            if(Character.isAlphabetic(notation.charAt(i))){
                result.add(numberArray.get(notation.charAt(i)));
            }
            //알파벳이 아닌 연산기호이면 두개꺼내서 연산.
            else{
                double b = result.pop();
                double a = result.pop();

                //+일때
                if(notation.charAt(i) == '+'){
                    result.add(a+b);
                }
                // - 일때
                else if(notation.charAt(i) == '-'){
                    result.add(a-b);
                }
                // * 일때
                else if(notation.charAt(i) == '*'){
                    result.add(a*b);
                }

                // / 일때
                else if(notation.charAt(i) == '/'){
                    result.add(a/b);
                }
            }
        }

        System.out.printf("%.2f", result.pop());

    }
}
