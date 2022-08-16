package baekjoon.baek20220429;

/**
 * 백준 10773번 (자료구조 연습,실버4)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class First {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());

        Stack<Integer> num_stack = new Stack<>();

        int result = 0;

        for(int i = 0; i<k; i++){
            int temp = Integer.parseInt(br.readLine());

            if(temp != 0){
                num_stack.add(temp);
            }
            else{
                num_stack.pop();
            }
        }

        for (Integer i : num_stack) {
            result += i;
        }
        System.out.println(result);

    }
}
