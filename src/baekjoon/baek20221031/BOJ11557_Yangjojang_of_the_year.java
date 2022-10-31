package baekjoon.baek20221031;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 31.
 */
public class BOJ11557_Yangjojang_of_the_year {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();

        for(int testCase = 1; testCase <= T; testCase++){
            int N = Integer.parseInt(br.readLine());

            Node[] nodeArray = new Node[N];

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                nodeArray[i] = new Node(st.nextToken(), Integer.parseInt(st.nextToken()));
            }

            Arrays.sort(nodeArray);
            result.append(nodeArray[0].name).append("\n");
        }

        System.out.println(result);
    }

    static class Node implements Comparable<Node>{
        String name;
        int alcohol;

        Node(String name, int alcohol){
            this.name = name;
            this.alcohol = alcohol;
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(node.alcohol, this.alcohol);
        }
    }
}
