package baekjoon.baek20221018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 19.
 */
public class BOJ2636_치즈 {

    static int N,M;
    static int remainCheeze;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int[][] maps;

    static int bfs(){
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;

        Queue<Node> needVisited = new LinkedList<>();
        needVisited.add(new Node(0,0));

        int meltCheeze = 0;

        while(!needVisited.isEmpty()){

            Node currentNode = needVisited.poll();

            for(int i = 0; i < 4; i++){

                int nextX = currentNode.x + dx[i];
                int nextY = currentNode.y + dy[i];

                if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && !visited[nextX][nextY]){

                    //0 - 공기면 그냥 추가
                    if(maps[nextX][nextY] == 0){
                        visited[nextX][nextY] = true;
                        needVisited.add(new Node(nextX,nextY));

                    }

                    //1 - 치즈이면 녹임
                    else if(maps[nextX][nextY] == 1){
                        maps[nextX][nextY] = 0; //치즈를 녹이되, 큐에 추가해서 계속 탐색하게 하면 안됨. - 이렇게 하면 전부다 녹여버림
                        meltCheeze++;
                        visited[nextX][nextY] = true;
                    }
                }
            }
        }

        return meltCheeze;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][M];
        remainCheeze = 0; //남은 치즈

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());

                if(maps[i][j] == 1) remainCheeze++;
            }
        }

        int resultTime = 0;
        int resultCheeze = 0;

        //시작하자마자 치즈가 없다면
        if(remainCheeze != 0){
            int time = 1;

            while(true){
                int meltingCheeze = bfs();

                remainCheeze -= meltingCheeze;

                if(remainCheeze <= 0){
                    resultTime = time;
                    resultCheeze = remainCheeze + meltingCheeze;
                    break;
                }

                time++;
            }
        }

        System.out.println(resultTime);
        System.out.println(resultCheeze);
    }

    static class Node{
        int x, y;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
