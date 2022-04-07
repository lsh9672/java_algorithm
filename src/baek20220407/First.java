package baek20220407;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*백준 5397번 (자료구조 연습)*/
public class First {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < testCase; i++){
            String temp = br.readLine();

            //스택을 두개써서 해결
            //prev스택에 기본적으로 넣고, 커서이동이 < 으로 들어오면 post스택으로 이동시킴
            Stack<Character> prevStack = new Stack<>();
            Stack<Character> postStack = new Stack<>();

            for(int j = 0; j <temp.length(); j++){
                char tempInput = temp.charAt(j);

                if(tempInput == '<'){
                    //스택이 비어있지 않으면 글자하나 뺴서 post스택으로 이동
                    if(!prevStack.isEmpty()){
                        postStack.push(prevStack.pop());
                    }
                    continue;

                }
                else if(tempInput == '>'){
                    if(!postStack.isEmpty()){
                        prevStack.push(postStack.pop());
                    }
                    continue;
                }
                else if(tempInput == '-'){
                    if(!prevStack.isEmpty()){
                        prevStack.pop();
                    }
                }
                //알파벳이면 그냥 넣음
                else{
                    prevStack.push(tempInput);
                }

            }
            for(int x=0; x < prevStack.size(); x++){
                sb.append(prevStack.elementAt(x));
            }

            for(int y= postStack.size()-1; y >=0; y--){
                sb.append(postStack.elementAt(y));
            }
            sb.append("\n");

        }
        sb.deleteCharAt(sb.lastIndexOf("\n"));
        System.out.println(sb);
    }
}
