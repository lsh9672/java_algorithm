package baekjoon.baek20220821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ23288 {

    static int N;
    static int M;
    static int K;

    static int value; //획득할 점수 누적.

    //방향 - 우하좌상
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    static int dir; //주사위의 방향

    static Location currentLoc; //현재 위치

    static int[][] diceStatus = {{0,2,0},{4,1,3},{0,5,0},{0,6,0}}; //주사위 상태를 2차원 배열로 표시 - 다루기편함
    static int[][] maps;

    static void bfs(Location startNode){
        int tempValue = maps[startNode.x][startNode.y];

        int tempCount = 0;

        boolean[][] visited = new boolean[N][M];
        visited[startNode.x][startNode.y] = true;

        Queue<Location> needVisited = new LinkedList<>();
        needVisited.add(startNode);

        while(!needVisited.isEmpty()){

            Location currentNode = needVisited.poll();
            tempCount++;

            for(int i = 0; i < 4; i++){
                int nextX = currentNode.x + dx[i];
                int nextY = currentNode.y + dy[i];

                if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && !visited[nextX][nextY] && maps[nextX][nextY] == tempValue){
                    visited[nextX][nextY] = true;
                    needVisited.add(new Location(nextX,nextY));
                }
            }

        }

        value += tempCount*tempValue;
    }

    //현재위치 업데이트
    static void locationUpdate(){
        int nextX = currentLoc.x + dx[dir];
        int nextY = currentLoc.y + dy[dir];

        //이동할 칸이 맵을 벗어나면 반대로 돌림
        if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M){
            nextX = currentLoc.x + dx[(dir+2)%4];
            nextY = currentLoc.y + dy[(dir+2)%4];
            //방향도 뒤집어야 됨.
            dir = (dir+2) % 4;
        }

        currentLoc.x = nextX;
        currentLoc.y = nextY;

    }

    //현재방향에 따른 주사위 업데이트
    static void diceUpdate(){
        int[][] updateDiceStatus = new int[4][3];

        switch (dir){
            case 0:
                //동쪽으로 회전
                for(int i = 0; i < 4; i++){
                    updateDiceStatus[i][1] = diceStatus[i][1];
                }

                //오른쪽에 있는거 하단으로 옮김
                updateDiceStatus[3][1] = diceStatus[1][2];

                for(int i = 0; i < 2; i++){
                    updateDiceStatus[1][i+1] = diceStatus[1][i];
                }

                //기존에 아래에 있던것 옮기기
                updateDiceStatus[1][0] = diceStatus[3][1];
                break;

            case 1:
                //남쪽으로 회전
                for(int i = 0; i < 3; i++){
                    updateDiceStatus[1][i] = diceStatus[1][i];
                }

                updateDiceStatus[0][1] = diceStatus[3][1];

                for(int i = 0; i < 3; i++){
                    updateDiceStatus[i+1][1] = diceStatus[i][1];
                }
                break;

            case 2:
                //서쪽으로 회전
                for(int i = 0; i < 4; i++){
                    updateDiceStatus[i][1] = diceStatus[i][1];
                }

                updateDiceStatus[3][1] = diceStatus[1][0];

                for(int i = 0; i < 2; i++){
                    updateDiceStatus[1][i] = diceStatus[1][i+1];
                }

                updateDiceStatus[1][2] = diceStatus[3][1];
                break;

            default:
                //북쪽으로 회전
                for(int i = 0; i < 3; i++){
                    updateDiceStatus[1][i] = diceStatus[1][i];
                }

                updateDiceStatus[3][1] = diceStatus[0][1];

                for(int i = 0; i < 3; i++){
                    updateDiceStatus[i][1] = diceStatus[i+1][1];
                }

        }

        diceStatus = updateDiceStatus;
    }


    //이동방향 변화.
    static void dirUpdate(){

        //업데이트 된 주사위의 맨 아래 값과, 현재위치 값을 비교
        //아랫면은 3,1
        int downValue = diceStatus[3][1]; // 맨밑 값
        int mapValue = maps[currentLoc.x][currentLoc.y];

        //시계방향회전
        if(downValue > mapValue){
            dir++;
            if(dir == 4) dir = 0;
        }
        //반시계방향 회전
        else if(downValue < mapValue){
            dir--;
            if(dir < 0) dir = 3;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        /**지도 입력받기.**/

        maps = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //방향
        dir = 0;

        value = 0;

        //현재위치 설정
        currentLoc = new Location(0,0);

        for(int i = 0; i < K; i++){

            //1. 이동방향으로 굴러간다.(이동방향을 반대로 한 다음에 한칸 굴러간다.)
            locationUpdate();

            //2. 이동한 방향에 맞게 주사위를 업데이트 한다.
            diceUpdate();

            //3. 이동방향 업데이트
            dirUpdate();

            //bfs로 탐색하면서 점수 구하기
            bfs(currentLoc);

        }

        //현재 방향을 시작으로 bfs탐색
        System.out.println(value);

    }

    static class Location{
        int x;
        int y;

        Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
