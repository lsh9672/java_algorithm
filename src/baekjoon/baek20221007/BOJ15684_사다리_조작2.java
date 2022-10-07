package baekjoon.baek20221007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 7.
 */
public class BOJ15684_사다리_조작2 {

    static int N,M,H;
    static int result;

    static int[][] ladderMap;

    static List<Node> emptyLadder;

    static boolean check(){

        for(int i = 0; i < N; i++){
            int currentY = i;

            for(int j = 0; j < H; j++){

                currentY += ladderMap[j][currentY];
            }

            if(currentY != i) return false;
        }

        return true;
    }

    static void recursive(int idx, int count){

        if(count > 3) return;

        if(result <= count) return;

        if(check()){
            result = Math.min(result,count);
            return;
        }

        for(int i = idx; i < emptyLadder.size(); i++){

            Node currentNode = emptyLadder.get(i);
            if(ladderMap[currentNode.x][currentNode.y] == 0 && ladderMap[currentNode.x][currentNode.y+1] == 0){
                ladderMap[currentNode.x][currentNode.y] = 1;
                ladderMap[currentNode.x][currentNode.y+1] = -1;

                recursive(i+1,count+1);

                ladderMap[currentNode.x][currentNode.y] = ladderMap[currentNode.x][currentNode.y+1] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());


        ladderMap = new int[H][N];

        //왼쪽으로 가면 -1, 오른쪽이면 1, 사다리가 연결되어있지 않으면 0;
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            ladderMap[a][b] = 1;
            ladderMap[a][b+1] = -1;
        }

        emptyLadder = new ArrayList<>();

        for(int i = 0; i < H; i++){
            for(int j = 0; j < N-1; j++){
                if(ladderMap[i][j] == 0 && ladderMap[i][j+1] == 0){
                    emptyLadder.add(new Node(i,j));
                }
            }
        }

        final int INF = Integer.MAX_VALUE;

        result = INF;

        recursive(0, 0);

        if(result == INF){
            result = -1;
        }

        System.out.println(result);
    }

    static class Node{
        int x, y;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

}
