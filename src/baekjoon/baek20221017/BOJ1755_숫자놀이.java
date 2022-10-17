package baekjoon.baek20221017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : sh Lee
 * @date : 22. 10. 17.
 */
public class BOJ1755_숫자놀이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String[] numToAlpha = {"zero","one","two","three","four","five","six","seven","eight","nine"};

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        List<Node> nodeList = new ArrayList<>();

        for(int i = M; i <= N; i++){

            //한자리 수 일때.
            if(i < 10){
                nodeList.add(new Node(i,numToAlpha[i]));
            }
            else{
                int firstNum = i / 10;
                int secondNum = i % 10;

                nodeList.add(new Node(i,numToAlpha[firstNum] + " " + numToAlpha[secondNum]));
            }
        }

        nodeList.sort(Comparator.naturalOrder());

        StringBuilder result = new StringBuilder();
        for(int i = 0; i < nodeList.size(); i++){

            if(i != 0 && i%10 == 0) result.append("\n");

            result.append(nodeList.get(i).num).append(" ");
        }

        System.out.println(result);
    }

    static class Node implements Comparable<Node>{
        int num;
        String numStr;

        Node(int num, String numStr){
            this.num = num;
            this.numStr = numStr;
        }

        @Override
        public int compareTo(Node o) {
            return this.numStr.compareTo(o.numStr);
        }
    }
}
