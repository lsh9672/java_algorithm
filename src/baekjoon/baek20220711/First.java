package baekjoon.baek20220711;

/**
 * 백준 2841번 (자료구조, 실버1)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class First {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        Stack<Integer>[] stacks = new Stack[7];

        for(int i = 0; i < 7; i++){
            stacks[i] = new Stack<>();
        }

        int result = 0;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int lineNum = Integer.parseInt(st.nextToken());
            int fretNum = Integer.parseInt(st.nextToken());

            //해당 줄에 누르고 있는 프렛이 존재하고, 그 프랫보다 새로 들어온 프랫이 작다면, 기존의 프랫에서 손을 떼야 해당음 연주 가능
            while(!stacks[lineNum].isEmpty() && stacks[lineNum].peek() > fretNum){
                stacks[lineNum].pop();
                result++;
            }
            //해당 줄에 있는 프랫스택이 비었거나, 비어있지는 않지만 기존의 프랫중 제일 큰값과 비교했을때, 새로운 프랫이 더 크면 그냥 새로운 손가락으로 누르면 됨.
            if(stacks[lineNum].isEmpty() || (!stacks[lineNum].isEmpty() && stacks[lineNum].peek() < fretNum)){
                stacks[lineNum].add(fretNum);
                result++;
            }
        }

        System.out.println(result);
    }

}
