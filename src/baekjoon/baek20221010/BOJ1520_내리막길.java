package baekjoon.baek20221010;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * @author : sh Lee
 * @date : 22. 10. 10.
 */
public class BOJ1520_내리막길 {

    static int N,M;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int[][] maps;

    static int[][] visited;


    static int dfs(Node currentNode){

        //목적지에 도달하면 경로가 하나 존재한다는 뜻이므로 1을 리턴해서 visited에 누적
        if(currentNode.x == N-1 && currentNode.y == M-1){
            return 1;
        }

        //이전에 방문한 곳이라면 저장된 경로 수를 리턴함.
        if(visited[currentNode.x][currentNode.y] != -1){
            return visited[currentNode.x][currentNode.y];
        }


        //위의 조건에 걸리지 않았다면 해당 노드 방문처리
        visited[currentNode.x][currentNode.y] = 0;

        for(int i = 0; i < 4; i++){
            int nextX = currentNode.x + dx[i];
            int nextY = currentNode.y + dy[i];

            //이동이 가능한지 확인
            if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && (maps[currentNode.x][currentNode.y] > maps[nextX][nextY])){
                visited[currentNode.x][currentNode.y] += dfs(new Node(nextX,nextY));
            }

        }

        return visited[currentNode.x][currentNode.y];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new int[N][M];
        for(int i = 0; i < N; i++){
            Arrays.fill(visited[i], -1);
        }
        dfs(new Node(0,0));

        System.out.println(visited[0][0]);


    }

    static class Node{
        int x,y;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
