package baekjoon.baek20220628;

/**
 * 백준 15815번 (자료구조, 자바)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class First {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input_string = br.readLine();

        Stack<Integer> number_stack = new Stack<>();

        for(int i = 0; i < input_string.length(); i++){
            char temp = input_string.charAt(i);

            //숫자일때는 스택에 넣음
            if(Character.isDigit(temp)){
                number_stack.add(Character.getNumericValue(temp));
            }
            //숫자가 아닐때(연산자)는 스택에서 두개를 꺼내서 연산을 함.
            else{
                int b = number_stack.pop();
                int a = number_stack.pop();

                if(temp == '+'){
                    number_stack.add(a+b);
                }
                else if(temp == '-'){
                    number_stack.add(a-b);
                }
                else if(temp == '*'){
                    number_stack.add(a*b);
                }
                else{
                    number_stack.add(a/b);
                }

            }

        }

        System.out.println(number_stack.peek());

    }
}
