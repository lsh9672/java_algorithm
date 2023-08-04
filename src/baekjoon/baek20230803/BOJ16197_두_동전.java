package baekjoon.baek20230803;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 전형적인 완탐문제.
 * 최소 횟수이므로 중간에 종료할 수 있기 때문에 백트래킹으로 볼수 있음
 * 일반적인 경우와 약간 다르게 동전이 두개임
 * 하나의 객체에 좌표 두개를 넣고, equals를 재 정의해주면 됨.
 */

public class BOJ16197_두_동전 {

    private static class Node{

        int x1,y1,x2,y2;

        public Node(){}
        public Node(int x1, int y1, int x2, int y2){
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public void setFirstCoin(int x1, int y1){
            this.x1 = x1;
            this.y1 = y1;
        }
        public void setSecondCoin(int x2, int y2){
            this.x2 = x2;
            this.y2 = y2;

        }

    }


    private static int N;
    private static int M;
    private static int minValue;//최소 버튼 횟수 저장
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};

    private static char[][] maps;
    private static boolean[][][][] visited;

    //밖으로 나갔는지 확인.
    private static boolean check(int x1, int y1, int x2, int y2){

        //칸을 벗어나지 않음.
        if(x1 >= 0 && x1 < N && y1 >= 0 && y1 < M && x2 >= 0 && x2 < N && y2 >= 0 && y2 < M){
            return true;
        }
        return false;
    }

    //해당위치로 이동이 가능한지 확인
    private static boolean moveCheck(int x, int y){
        if(maps[x][y] != '#'){
            return true;
        }
        return false;
    }

    //두 코인중 하나만 벗어났는지 둘다 벗어났는지 확인.
    private static boolean outOneCheck(Node node){

        int x1 = node.x1;
        int y1 = node.y1;
        int x2 = node.x2;
        int y2 = node.y2;

        //1번 코인만 벗어났을때
        if((x1 < 0 || x1 >= N || y1 < 0 || y1 >= M) && (x2 >= 0 && x2 < N && y2 >= 0 && y2 < M)){
            return true;
        }
        //2번 코인만 벗어났을때
        else if((x2 < 0 || x2 >= N || y2 < 0 || y2 >= M) && (x1 >= 0 && x1 < N && y1 >= 0 && y1 < M)){

            return true;
        }

        return false;

    }

    //재귀돌면서 모든 경우를 확인하는 메서드 - outCheck는 맵을 벗어난 좌표라는 뜻, 이때는 확인해봐야됨 -
    private static void dfs(Node currentNode, int currentCount, boolean outCheck){

        //10번 넘어가면 볼 필요 없음.
        if(currentCount > 10) return;

        //밖으로 벗어났다면, 하나만 벗어 났는지 확인.
        if(outCheck){
            if(outOneCheck(currentNode)){
                minValue = Math.min(minValue, currentCount);
            }
            return;
        }

        for(int i = 0; i < 4; i++){

            int nextX1 = currentNode.x1 + dx[i];
            int nextY1 = currentNode.y1 + dy[i];
            int nextX2 = currentNode.x2 + dx[i];
            int nextY2 = currentNode.y2 + dy[i];

            //칸을 벗어나지 않는다면
            if(check(nextX1,nextY1,nextX2,nextY2)){

                //동전 1이 갈수 없다면 좌표 그대로
                if(!moveCheck(nextX1,nextY1)){
                    nextX1 = currentNode.x1;
                    nextY1 = currentNode.y1;
                }
                if(!moveCheck(nextX2, nextY2)){
                    nextX2 = currentNode.x2;
                    nextY2 = currentNode.y2;
                }

                //좌표를 구했으면 방문했던 노드인지 확인
                if(visited[nextX1][nextY1][nextX2][nextY2]) continue;
                visited[nextX1][nextY1][nextX2][nextY2] = true;
                dfs(new Node(nextX1,nextY1,nextX2,nextY2),currentCount + 1, false);
                visited[nextX1][nextY1][nextX2][nextY2] = false;
            }

            //칸을 벗어났다면 방문 처리 필요 없이 재귀호출, 메서드 도입 부분에서 벗
            else{
                dfs(new Node(nextX1,nextY1,nextX2,nextY2), currentCount + 1, true);
            }
        }

    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        minValue = Integer.MAX_VALUE;
        maps = new char[N][M];

        for(int i = 0; i < N; i++){
            maps[i] = br.readLine().toCharArray();
        }

        //초기 동전위치 찾기
        Node startNode = null;
        boolean firstCheck = false;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){

                if(maps[i][j] == 'o'){
                    if(!firstCheck){
                        startNode = new Node();
                        startNode.setFirstCoin(i,j);
                        firstCheck = true;
                    }
                    else{
                        startNode.setSecondCoin(i,j);
                    }
                }
            }
        }

        visited = new boolean[N][M][N][M];

        dfs(startNode,0,false);

        if(minValue == Integer.MAX_VALUE){
            minValue = -1;
        }

        System.out.println(minValue);

    }
}
