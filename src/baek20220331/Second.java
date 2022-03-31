package baek20220331;

/* 백준 - 1874번(자바, 자료구조 연습)*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class Second {

    static Stack<Integer> intStack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int start_value = 0;

        for(int i = 0; i < n; i++){
            int value = Integer.parseInt(br.readLine());

            if(value > start_value){
                for(int j = start_value+1; j <= value; j++){
                    intStack.push(j);
                    sb.append("+").append("\n");
                }

                start_value = value;
            }
            else if(intStack.peek() != value){
                System.out.println("NO");
                return;
            }
            intStack.pop();
            sb.append("-").append("\n");

        }

        System.out.println(sb);
    }
}
