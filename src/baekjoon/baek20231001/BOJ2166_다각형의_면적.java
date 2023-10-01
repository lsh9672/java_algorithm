package baekjoon.baek20231001;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 다각형의 좌표를 알때, 다각형의 넓이를 구하는 "신발끈 공식"을 이용해서 해결할 수 있다.
 */
public class BOJ2166_다각형의_면적 {

    private static class Node{
        private long x, y;

        public Node(long x, long y){
            this.x = x;
            this.y = y;
        }

        public Node createNode(){
            return new Node(this.x, this.y);
        }
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Node[] nodeArray = new Node[N + 1];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            nodeArray[i] = new Node(x,y);
        }

        nodeArray[N] = nodeArray[0].createNode();
        //신발끈 공식
        long value1 = 0;
        long value2 = 0;

        for(int i = 0; i < N; i++){
            Node node1 = nodeArray[i];
            Node node2 = nodeArray[i + 1];

            value1 += node1.x * node2.y;
            value2 += node1.y * node2.x;
        }


        System.out.println(String.format("%.1f", (Math.abs(value1 - value2) / 2.0)));

    }
}
