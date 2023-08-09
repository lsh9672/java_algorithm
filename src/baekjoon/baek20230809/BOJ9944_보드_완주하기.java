package baekjoon.baek20230809;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 2차원 배열에 주어지는 백트래킹
 * 모든 경우를 다하다가 할 수 없는 경우의 수라면 제외하면 된다.
 * 문제의 관건은, 진행하다가 더이상 진행이 불가능할 때, 방향을 선택하고, 그 방향으로 계속해서 나아가는 것이다.
 *
 * (수정)
 * 현재 직진인 경우에도 한칸 이동시마다 재귀호출을 하도록 했는데, 재귀호출할 필요가 없음.
 * 갈수 있는 곳 까지 반복문을 이용해서 한번에 이동하고 더이상 이동이 불가능 할때만 재귀호출을 하면 된다.
 *
 * (수정2)
 * 직진시에는 방향을 정하기 때문에 바라보는 방향만이 아닌 나머지 방향으로도 전진해봐야 한다.
 */

public class BOJ9944_보드_완주하기 {

    //좌표를 나타낼 객체
    private static class Node{
        private int x, y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX(){
            return this.x;
        }
        public int getY(){
            return this.y;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    //직진 이동후 정보 저장
    private static class Info{
        private int x,y,move;

        public Info(int x, int y, int move){
            this.x = x;
            this.y = y;
            this.move = move;
        }

        public int getX() {
            return this.x;
        }
        public int getY(){
            return this.y;
        }
        public int getMove(){
            return this.move;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "x=" + x +
                    ", y=" + y +
                    ", move=" + move +
                    '}';
        }
    }

    //격자형 그래프 가로세로
    private static int N;
    private static int M;

    //이동해야되는 최대 칸수
    private static int moveCount;

    //최소 이동횟수 저장.
    private static int minMoveCount;

    //격자형 그래프 저장할 배열
    private static char[][] maps;

    //방향을 나타낼 배열
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    //로깅용
    private static void print(boolean[][] visited){
        for(int i = 0; i < N; i++){
            System.out.println(Arrays.toString(visited[i]));
        }

        System.out.println("+++++++++++++++++++");
    }

    //방문배열 복사
    private static boolean[][] copyVisited(boolean[][] visited){
        boolean[][] returnVisited = new boolean[N][M];

        for(int i = 0; i < N ;i++){
            System.arraycopy(visited[i],0,returnVisited[i],0, M);
        }


        return returnVisited;
    }


    //직진구간 이동하는 메서드 - 이동후에 몇칸 이동했고, 이동후 위치가 어디인지 리턴.
    private static Info straight(Node currentNode, int dir, boolean[][] visited){

        int count = 1;
        Info returnInfo = null;

        int currentX = currentNode.getX();
        int currentY = currentNode.getY();

        while(true){

            int nextX = currentX + dx[dir];
            int nextY = currentY + dy[dir];

            //이동가능하면 한칸 증가.
            if(check(nextX,nextY, visited)){
                visited[nextX][nextY] = true;
                currentX = nextX;
                currentY = nextY;
                count++;
            }
            //이동 불가능 하면 이전 위치 리턴
            else{
                returnInfo = new Info(
                        currentX,
                        currentY,
                        count - 1);

                break;
            }
        }

        return returnInfo;

    }

    //이동가능한지 판단하는 메서드
    private static boolean check(int nextX, int nextY, boolean[][] visited){

        return nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && !visited[nextX][nextY] && maps[nextX][nextY] == '.';

    }

    //재귀돌리면서 갯수세는 메서드 - dircount => 4를 넘어가면 하나의 위치에서 4방향을 다 돌았음.
    //currentMove는 회전할때마다 하나씩.
    //count는 칸을 하나씩 점유할때마다 증가.
    private static void recursive(Node currentNode, int count, int currentMove,int currentDir, int dirCount, boolean[][] visited){

        //이동횟수(currentMove)가 기존에 저장된 값을 넘거나 같다면.
        if(currentMove >= minMoveCount) return;

        //count값이 모든 칸(moveCount)과 같다면, 이동횟수(currnetMove) 중 더 작은 값으로 업데이트 하고 종료.
        if(count == moveCount){

            minMoveCount = Math.min(minMoveCount, currentMove);
            return;
        }

        for(int i = 0; i < 4; i++){
            int nextX = currentNode.getX() + dx[(currentDir + i) % 4];
            int nextY = currentNode.getY() + dy[(currentDir + i) % 4];

            //이동할 수 있으면 이동.
            if(check(nextX,nextY,visited)){

                boolean[][] tempVisited = copyVisited(visited);

                Info info = straight(currentNode, (currentDir + i) % 4, tempVisited);//해당 방향으로 이동.

                recursive(new Node(info.getX(),info.getY()),
                        count + info.getMove(),
                        currentMove + 1,
                        (currentDir + i) % 4,
                        dirCount + 1,
                        tempVisited);
            }
        }

    }

    //방문해야되는 칸 수를 구하는 메서드
    private static int visitedCount(){

        int count = 0;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(maps[i][j] != '.') continue;

                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        StringBuilder sb = new StringBuilder();

        int caseCount = 1;

        String temp = "";

        while((temp = br.readLine()) != null && !temp.isEmpty()){

            st = new StringTokenizer(temp);
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            maps = new char[N][M];
            boolean[][] visited = new boolean[N][M];

            minMoveCount = Integer.MAX_VALUE;

            for(int i = 0; i < N; i++){
                maps[i] = br.readLine().toCharArray();
            }

            //이동해야되는 최대 칸수
            moveCount = visitedCount();

            //반복문 돌면서 공을 둘수 있으면 공두기.
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(maps[i][j] == '*') continue;

                    visited[i][j] = true;
                    recursive(new Node(i,j),1, 0, 0,1, visited);
                    visited[i][j] = false;

//                    System.out.println(i + ", " + j);
                }
            }

//            System.out.println("++++++++++++++++");

            //최종적으로 모든 칸 방문이 불가능 하면 - 1
            if(minMoveCount == Integer.MAX_VALUE){
                minMoveCount = -1;
            }

            sb.append("Case " + caseCount + ": ").append(minMoveCount).append("\n");

            caseCount++;

        }

        System.out.println(sb);

    }
}
