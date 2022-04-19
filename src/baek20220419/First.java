package baek20220419;

/**
 * 백준 10799번(구현 연습)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class First {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input_value = br.readLine();

        Stack<Character> bracket = new Stack<>();

        int result = 0;

        for(int i = 0 ; i < input_value.length(); i++){
            Character temp = input_value.charAt(i);

            //열린괄호이면 스택에 넣는다.
            if(temp == '('){
                bracket.add(temp);
            }
            else{
                //닫는 괄호면 뺌
                bracket.pop();
                //이전에 비교했던 값도 닫는 괄호였으면, 레이저가 아니라 막대기 끝점임
                if(input_value.charAt(i-1) == ')'){
                    //온전한 막대기 1개
                    result++;
                }
                //이전에 비교했던 값이, 여는 괄호였으면, 곧바로 닫는괄호가 나온거이므로, 레이저임
                else{
                    //레이져이면, 남아있는 여는 괄호개수만큼 잘릴것이므로 그만큼 더해줌
                    result += bracket.size();

                }

            }

        }

        System.out.println(result);

    }
}
