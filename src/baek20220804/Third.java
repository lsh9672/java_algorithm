package baek20220804;

/**
 * Swea 1218번
 */

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Third {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/baek20220804/input (2).txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = 10;

        for(int testCase = 1; testCase <= T; testCase++){

            int result = 0;
            Stack<Character> bracket = new Stack<>();

            int N = Integer.parseInt(br.readLine());

            String inputString = br.readLine();

            //중간에 유호하지 않음을 확인하면 break할껀데 그때 break 되었는지 확인.
            boolean flag = false;
            for(int i = 0; i < N; i++){
                Character temp = inputString.charAt(i);

                //여는 괄호면 스택에 넣기
                if(temp == '(' || temp == '{' || temp == '[' || temp == '<'){
                    bracket.add(temp);
                }
                //닫는 괄호의 경우에는 스택의 마지막 값을 확인함.
                else if(temp == ')'){
                    if(bracket.isEmpty()){
                        flag = true;
                        break;
                    }
                    else if(bracket.peek() == '('){
                        bracket.pop();
                    }
                    else{
                        flag = true;
                        break;
                    }
                }
                else if(temp == '}'){
                    if(bracket.isEmpty()){
                        flag = true;
                        break;
                    }
                    else if(bracket.peek() == '{'){
                        bracket.pop();
                    }
                    else{
                        flag = true;
                        break;
                    }
                }
                else if(temp == ']'){
                    if(bracket.isEmpty()){
                        flag = true;
                        break;
                    }
                    else if(bracket.peek() == '['){
                        bracket.pop();
                    }
                    else{
                        flag = true;
                        break;
                    }
                }
                else if(temp == '>'){
                    if(bracket.isEmpty()){
                        flag = true;
                        break;
                    }
                    else if(bracket.peek() == '<'){
                        bracket.pop();
                    }
                    else{
                        flag = true;
                        break;
                    }
                }

            }

            //탐색이 끝났을떄 스택이 비어있으면 유효함
            if(!flag && bracket.isEmpty()){
                result = 1;
            }

            System.out.println("#" + testCase +" " + result);
        }
    }
}
