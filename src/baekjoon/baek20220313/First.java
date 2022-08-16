package baekjoon.baek20220313;
/*
* 백준 9012번(자료구조 연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class First {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        List<String> result = new ArrayList<>();

        String[] bracketArray = null;
        Stack<String> bracketStack = null;
        for(int i = 0; i < testCase; i++){
            bracketArray = br.readLine().strip().split("");
            bracketStack = new Stack<>();

            boolean check = true;
            for (String s : bracketArray) {
                if(bracketStack.empty()){
                    //스택이 비어있는데, ) 이걸 넣으면 더 옳바른 괄호가 아님.
                    if(s.equals(")")){
                        result.add("NO");
                        check = false;
                        break;
                    }
                    //옳바른 괄호가 들어오면 넣고 다음 진행
                    else {
                        bracketStack.push(s);
                        continue;
                    }
                }
                //스택이 비어있지 않다면, 최상단값과, 넣으려고 하는 값을 비교
                else{
                    String topValue = bracketStack.peek();
                    StringBuilder sb = new StringBuilder();
                    //최상단에서 꺼낸값과, 넣을값을 합쳐봤을떄, 옳바론 괄호면, 스택최상단값을 pop함.
                    sb.append(topValue).append(s);
                    if(sb.toString().equals("()")){
                        bracketStack.pop();
                        continue;
                    }
                    else{
                        bracketStack.push(s);
                        continue;
                    }
                }
            }
            if(check== true){
                //스택에 값이 남아있지 않으면 옳바른 괄호임
                if(bracketStack.empty()){
                    result.add("YES");
                }
                else{
                    result.add("NO");
                }
            }

        }

        for (String s : result) {
            System.out.println(s);
        }
    }
}
