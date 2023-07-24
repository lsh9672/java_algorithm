package baekjoon.baek20230723;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * bfs로 탐색하면 쉽게 풀수 있는 문제
 * 기존의 bfs처럼 다음 노드로 이동할때마다 count를 증가시키고, 가장 먼저 목적지에 도달 하면 그게 최소값이다.
 * 이 문제에서는 기존 bfs와는 약간 다르게, 다음 탐색할 노드가, 1~3칸 이동, 방향 전환이 된다.
 * 이전에 풀던 bfs에서 다음 탐색할 노드를 4방향 탐색할 위치로 구했던것에서 약간 확장한 것이다.
 * 탐색에 사용할 노드 객체를 만든다.
 * 노드 객체에는 위치(x,y), 방향, 명령횟수를 저장한다.
 *
 * 주의할 점은, 칸 이동을 할때 해당 칸으로의 이동여부이다
 * 예를 들면, 바라보는 방향으로 3칸 이동하려고 하는데, 도착지에 있는것은 +3 해서 쉽게 찾을수 있다.
 * 하지만, 가는 경로상에 1이 있어서 도착지가 0이라 단순하게 +3해봤을떄는 도달이 되지만, 경로를 따라가보면 막혀있을수도 있다.
 * 이러한 것을 조심 해야된다.
 *
 * 만들어야되는 것들.
 * 노드객체
 * bfs탐색
 * 2차원 배열크기를 나타낼 M,N
 * 그래프를 표시할 2차원 배열.
 * 해당 칸(1~3)만큼 움직였을 때 문제가 없는지 확인하는 메서드.
 * 90도 왼쪽또는 오른쪽으로 회전했을때의 방향을 나타내는 메서드
 * 방향에 따른, 이동 좌표 배열(dx,dy) - 동남서북으로 해야, 오른쪽이면 인덱스+1, 왼쪽이면 인덱스 - 1로 표기가 가능해진다.
 * 좌우방향전환시, dir에 더할 수(-1,1)
 * 문제에서 주어지는 방향에 따른 숫자를 90도 회전처리 하기 쉽게 바꾼, 수로 변경하는 메서드
 */

public class BOJ1726_로봇 {

    private static class Node{
        int x, y, dir, count;

        public Node(int x, int y, int dir, int count){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y && dir == node.dir;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, dir);
        }
    }

    private static int M; //세로
    private static int N; // 가로
    private static int[][] graph;//격자형 그래프
    //동남서북
    private static int[] dx = {0,1,0,-1};
    private static int[] dy = {1,0,-1,0};
    //좌 우
    private static int[] dTurn = {-1,1};

    //현재 칸에서 k만큼 이동이 가능한지 확인.
    private static boolean moveCheck(int startX,int startY, int dir, int k){

        for(int i = 1; i <= k; i++){

            int nextX = startX + dx[dir] * i;
            int nextY = startY + dy[dir] * i;

            //배열 범위를 벗어나거나, 1이면 이동 불가.
            if(nextX < 0 || nextX >= M || nextY < 0 || nextY >= N || graph[nextX][nextY] == 1) return false;

        }

        return true;
    }

    //동서남북(1,2,3,4) -> 동남서북(0,1,2,3)으로 변경하는 메서드.
    private static int dirUpdate(int dir){

        switch(dir){
            case 1:
                return 0;
            case 2:
                return 2;
            case 3:
                return 1;
            default:
                return 3;
        }
    }


    //그래프 탐색
    private static int bfs(Node startNode, Node endNode){

        //방문 처리 - 방향과 항상 동등하게 한칸씩 이동하는 것이 아니므로 3차원 배열로 나타내야됨.
        boolean[][][] visited = new boolean[M][N][4];
        visited[startNode.x][startNode.y][startNode.dir] = true;

        Queue<Node> needVisited = new LinkedList<>();
        needVisited.add(startNode);

        while(!needVisited.isEmpty()){

            Node currentNode = needVisited.poll();

            if(currentNode.equals(endNode)){
                return currentNode.count;
            }

            //1~3칸 움직이는 경우.
            for(int k = 1; k <= 3 ; k++){

                int nextX = currentNode.x + dx[currentNode.dir] * k;
                int nextY = currentNode.y + dy[currentNode.dir] * k;
                //해당칸으로 이동할 수 있으면, 노드추가
                if(moveCheck(currentNode.x, currentNode.y, currentNode.dir,k) && !visited[nextX][nextY][currentNode.dir]){
                    visited[nextX][nextY][currentNode.dir] = true;
                    needVisited.add(new Node(nextX,nextY, currentNode.dir, currentNode.count + 1));
                }
            }
            //방향전환 하는 경우 - 3차원 배열로 처리.
            for(int turn = 0; turn < 2; turn++){

                int nextDir = (currentNode.dir + dTurn[turn] + 4) % 4;

                if(!visited[currentNode.x][currentNode.y][nextDir]){
                    visited[currentNode.x][currentNode.y][nextDir] = true;
                    needVisited.add(new Node(currentNode.x, currentNode.y, nextDir, currentNode.count + 1));
                }

            }

        }

        //도착 못하는 경우는 없지만 리턴값이 필요하기 때문에 -1로 표기.
        return -1;

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        //그래프 초기화
        graph = new int[M][N];

        for(int i = 0; i < M ; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //출발 노드
        st = new StringTokenizer(br.readLine());
        int tempX = Integer.parseInt(st.nextToken()) - 1;
        int tempY = Integer.parseInt(st.nextToken()) - 1;
        int tempDir = Integer.parseInt(st.nextToken());
        Node startNode = new Node(tempX,tempY,dirUpdate(tempDir),0);

        //도착 노드
        st = new StringTokenizer(br.readLine());
        tempX = Integer.parseInt(st.nextToken()) - 1;
        tempY = Integer.parseInt(st.nextToken()) - 1;
        tempDir = Integer.parseInt(st.nextToken());

        Node endNode = new Node(tempX,tempY,dirUpdate(tempDir),0);

        System.out.println(bfs(startNode,endNode));

    }
}
