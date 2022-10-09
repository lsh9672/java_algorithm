package baekjoon.baek20221009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 9.
 */
public class BOJ14500_테트로미노 {

    static int N,M;
    static int result;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    //ㅗ모양은 dfs로 처리가 안됨
    static int[][] blockDx = {{-1,0,0},{1,0,0},{ 1,1,2},{1,1,2}};
    static int[][] blockDy = {{ 1,1,2},{1,1,2},{-1,0,0},{1,0,0}};

    static int[][] maps;
    static boolean[][] visited;

    static void specialBlock(Node currentNode){

        for(int i = 0; i < 4; i++){
            int total = maps[currentNode.x][currentNode.y];
            boolean check = false;
            for(int j = 0; j < 3; j++){

                int nextX = currentNode.x + blockDx[i][j];
                int nextY = currentNode.y + blockDy[i][j];

                if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M){
                    total += maps[nextX][nextY];
                }
                else{
                    check = true;
                    break;
                }
            }

            if(!check){
                result = Math.max(result, total);
            }
        }
    }

    static void dfs(Node currentNode){

        if(currentNode.depth >= 4){
            result = Math.max(result,currentNode.value);
            return;
        }
        for(int i = 0; i < 4; i++){

            int nextX = currentNode.x + dx[i];
            int nextY = currentNode.y + dy[i];

            if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && !visited[nextX][nextY]){
                visited[nextX][nextY] = true;
                dfs(new Node(nextX,nextY, currentNode.depth+1, currentNode.value + maps[nextX][nextY]));
                visited[nextX][nextY] = false;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = Integer.MIN_VALUE;
        visited = new boolean[N][M];


        //한칸 한칸씩 선택하고 주어진 블록을 놓아봄.
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){

                visited[i][j] = true;
                Node node = new Node(i,j,1,maps[i][j]);
                dfs(node);
                visited[i][j] = false;

                specialBlock(node);

            }
        }

        System.out.println(result);


    }

    static class Node{
        int x,y,depth,value;

        Node(int x, int y, int depth,int value){
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.value = value;
        }

    }
}
