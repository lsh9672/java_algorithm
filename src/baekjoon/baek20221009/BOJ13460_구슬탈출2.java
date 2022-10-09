package baekjoon.baek20221009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 9.
 */
public class BOJ13460_구슬탈출2 {

    static int N, M;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static char[][] maps;

    //방향과 현재위치를 입력받아서 해당 방향으로 이동했을때의 다음 노드를 리턴함. - type이
    static void nextNode(Node currentNode,Node nextNode, int dir, int type){

        int count = 1;
        while(true){
            if(type == 0){
                int nextX = currentNode.redX + dx[dir] * count;
                int nextY = currentNode.redY + dy[dir] * count;

                //벽이면 종료
                if(maps[nextX][nextY] == '#'){
                    nextNode.redX = currentNode.redX + dx[dir] * (count-1);
                    nextNode.redY = currentNode.redY + dy[dir] * (count-1);
                    return;
                }

                //가다가 구멍이면 빠짐.
                else if(maps[nextX][nextY] == 'O'){
                    nextNode.redX = nextX;
                    nextNode.redY = nextY;
                    return;

                }
            }
            else{

                int nextX = currentNode.blueX + dx[dir] * count;
                int nextY = currentNode.blueY + dy[dir] * count;


                //벽이면 종료
                if(maps[nextX][nextY] == '#'){
                    nextNode.blueX = currentNode.blueX + dx[dir] * (count-1);
                    nextNode.blueY = currentNode.blueY + dy[dir] * (count-1);
                    return;
                }

                //가다가 구멍이면 빠짐.
                else if(maps[nextX][nextY] == 'O'){
                    nextNode.blueX = nextX;
                    nextNode.blueY = nextY;
                    return;

                }
            }

            count++;

        }

    }

    static int bfs(Node startNode){

        boolean[][][][] visited = new boolean[N][M][N][M];
        visited[startNode.redX][startNode.redY][startNode.blueX][startNode.blueY] = true;

        Queue<Node> needVisited = new LinkedList<>();
        needVisited.add(startNode);

        while(!needVisited.isEmpty()){

            Node currentNode = needVisited.poll();

            if(maps[currentNode.redX][currentNode.redY] == 'O' && currentNode.count <= 10){
                return currentNode.count;
            }

            for(int i = 0; i < 4; i++){

                //다음 노드
                Node nextNode = new Node(-1,-1,-1,-1, -1);

                nextNode(currentNode, nextNode , i, 0); //빨간색 이동
                nextNode(currentNode, nextNode , i, 1); //파란색 이동

                //두 구슬의 위치가 같다면 초기 구슬위치(파-빨 등)를 확인해서 재배치
                if(nextNode.redX == nextNode.blueX && nextNode.redY == nextNode.blueY){
                    /*각 방향에 따라 빨간색과 파란색 위치를 확인해서 재배치*/

                    //둘다 동시에 구멍에 빠졌다면 패스
                    if(maps[nextNode.redX][nextNode.redY] == 'O') continue;
                    //상
                    if(i == 0){

                        //빨간색이 더 위
                        if(currentNode.redX < currentNode.blueX){
                            nextNode.blueX++;
                        }
                        else{
                            nextNode.redX++;
                        }
                    }
                    //하
                    else if(i == 1){

                        if(currentNode.redX < currentNode.blueX){
                            nextNode.redX--;
                        }
                        else{
                            nextNode.blueX--;
                        }
                    }
                    //좌
                    else if(i == 2){

                        if(currentNode.redY < currentNode.blueY){
                            nextNode.blueY++;
                        }
                        else{
                            nextNode.redY++;
                        }
                    }
                    //우
                    else{
                        if(currentNode.redY < currentNode.blueY){
                            nextNode.redY--;
                        }
                        else{
                            nextNode.blueY--;
                        }
                    }

                }
                //두 위치가 같지 않으면 파란구슬이 구멍에 빠졌는지 확인.
                else if(maps[nextNode.blueX][nextNode.blueY] == 'O'){
                    //다음으로 넘어가기
                    continue;
                }

                //다음 노드를 완성했으면 이전에 탐색한 곳이 아닌지 확인.
                if(!visited[nextNode.redX][nextNode.redY][nextNode.blueX][nextNode.blueY]){
                    visited[nextNode.redX][nextNode.redY][nextNode.blueX][nextNode.blueY] = true;
                    nextNode.count = currentNode.count+1;
                    needVisited.add(nextNode);
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new char[N][M];

        Node currentLocation = new Node(-1,-1,-1,-1,0);

        for(int i = 0; i < N; i++){
            maps[i] = br.readLine().toCharArray();

            for(int j = 0; j < M; j++){
                //빨간공과 파란공의 초기위치 잡기.
                if(maps[i][j] == 'R'){

                    currentLocation.redX = i;
                    currentLocation.redY = j;
                    maps[i][j] = '.';
                }
                if(maps[i][j] == 'B'){
                    currentLocation.blueX = i;
                    currentLocation.blueY = j;
                    maps[i][j] = '.';
                }
            }
        }


        System.out.println(bfs(currentLocation));


    }

    static class Node{
        int redX,redY,blueX,blueY, count;

        Node(int redX,int redY, int blueX,int blueY, int count){
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
            this.count = count;
        }

        @Override
        public String toString() {
            return "redX : " + redX + " redY : " + redY +" blueX : " + blueX + " blueY : " + blueY + " count : " + count;
        }
    }
}
