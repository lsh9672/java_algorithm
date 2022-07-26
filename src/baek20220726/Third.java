package baek20220726;

/**
 * 백준 17413번 (A형 대비문제)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Third {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputStr = br.readLine();

        StringBuilder result = new StringBuilder();

        Stack<Character> strStack = new Stack<>();

        //여는 괄호 시작을 알림.
        Boolean flag = false;
        for(int i = 0; i < inputStr.length(); i++){
            if(inputStr.charAt(i) == '<'){
                flag = true;

                while(!strStack.isEmpty()){
                    result.append(strStack.pop());
                }
                result.append("<");
            }

            else if(inputStr.charAt(i) == '>'){
                flag = false;
                result.append(">");
            }

            else if(flag){
                result.append(inputStr.charAt(i));
            }

            else if(!flag){
                if(inputStr.charAt(i) == ' '){
                    while(!strStack.isEmpty()){
                        result.append(strStack.pop());
                    }

                    result.append(inputStr.charAt(i));
                }

                else{
                    strStack.push(inputStr.charAt(i));
                }
            }

        }
        while(!strStack.isEmpty()){
            result.append(strStack.pop());
        }

        System.out.println(result);
    }
}
