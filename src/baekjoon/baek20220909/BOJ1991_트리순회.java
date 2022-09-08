package baekjoon.baek20220909;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1991_트리순회 {

    static int N;

    static List<Integer>[] graph;


    //전위순회
    static void preOrder(){

    }

    //중위 순회
    static void inOrder(){

    }

    //후위 순회
    static void postOrder(){

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N];

        for(int i = 0; i < 7; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < 7; i++){
            st = new StringTokenizer(br.readLine());
            int parentNode = Character.getNumericValue(st.nextToken());
        }



    }
}
