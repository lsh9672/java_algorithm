package baekjoon.baek20221004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : sh Lee
 * @date : 22. 10. 4.
 */
public class BOJ14502_연구소 {

    static int N;
    static int M;
    static int result;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static List<Node> virusLoc;

    //안전영역 수 구하기.
    static int saveArea(int[][] maps){
        int total = 0;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(maps[i][j] == 0){
                    total++;
                }
            }
        }
        return total;

    }


    //배열 복사
    static int[][] arrayCopy(int[][] maps){
        int[][] returnMap = new int[N][M];

        for(int i = 0; i < N; i++){
            System.arraycopy(maps[i],0,returnMap[i],0,M);
        }

        return returnMap;
    }

    //bfs로 바이러스 퍼트리기.
    static void bfs(int[][] maps){

        Queue<Node> needVisited = new LinkedList<>();

        needVisited.addAll(virusLoc);

        while(!needVisited.isEmpty()){

            Node currentNode = needVisited.poll();


            for(int i = 0; i < 4; i++){
                int nextX = currentNode.x + dx[i];
                int nextY = currentNode.y + dy[i];

                if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && maps[nextX][nextY] == 0){
                    maps[nextX][nextY] = 2;
                    needVisited.add(new Node(nextX,nextY));
                }
            }
        }



    }

    static void recursive(int idx,int count, int[][] maps){

        //벽을 3개 세웠다면 안전구역 확인.
        if(count == 3){

            //바이러스를 확산시킴
            bfs(maps);

            //안전영역수 구하기
            int saveCount = saveArea(maps);

            //최대값 업데이트
            result = Math.max(result,saveCount);

            return;
        }

        if(idx >= N*M){
            return;
        }


        for(int i = idx; i < N*M; i++){
            int x = i / M;
            int y = i % M;

            //빈공간이면 바이러스 배치
            if(maps[x][y] == 0){
                int[][] tempMap = arrayCopy(maps);
                tempMap[x][y] = 1;
                recursive(idx+1,count+1,tempMap);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] maps = new int[N][M];

        virusLoc = new ArrayList<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());

                if(maps[i][j] == 2){
                    virusLoc.add(new Node(i,j));
                }
            }
        }

        result = Integer.MIN_VALUE;

        recursive(0,0,maps);

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
