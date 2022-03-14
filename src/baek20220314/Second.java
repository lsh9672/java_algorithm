package baek20220314;

/*
* 백준 4949번*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;


public class Second {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String temp = br.readLine();
            Stack<Character> bracket = new Stack<>();

            if(temp.equals(".")) break;
            boolean check = true;
            for(int i =0; i < temp.length(); i++){
                char tempChar = temp.charAt(i);
                //단어가 아니라 괄호면,
                if(tempChar == '('){
                    bracket.push(tempChar);
                    continue;
                }
                else if(tempChar == '['){
                    bracket.push(tempChar);
                    continue;
                }
                else if(tempChar == ']'){
                    //스택이 비어있는지 확인
                    //비어있다면 no를 출력하고 반환
                    if(bracket.empty()){
                        System.out.println("no");
                        check = false;
                        break;
                    }
                    //비어있지 않다면 스택 최상단을 확인
                    else{
                        //괄호의 대칭이 맞으면 pop
                        if(bracket.peek() == '['){
                            bracket.pop();
                            continue;
                        }
                        //아니면 끝
                        else{
                            System.out.println("no");
                            check = false;
                            break;
                        }
                    }
                }
                else if(tempChar == ')'){
                    //스택이 비어있는지 확인
                    //비어있다면 no를 출력하고 반환
                    if(bracket.empty()){
                        System.out.println("no");
                        check = false;
                        break;
                    }
                    //비어있지 않다면 스택 최상단을 확인
                    else{
                        //괄호의 대칭이 맞으면 pop
                        if(bracket.peek() == '('){
                            bracket.pop();
                            continue;
                        }
                        //아니면 넣기
                        else{
                            System.out.println("no");
                            check = false;
                            break;
                        }
                    }
                }
                else continue;
            }
            if(check == true){
                //끝까지 탐색했을때, 스택이 비어있다면, 옳바른괄호
                if(bracket.empty()){
                    System.out.println("yes");
                }
                else{
                    System.out.println("no");
                }
            }


        }
    }
}
