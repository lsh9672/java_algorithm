package baekjoon.baek20221103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : sh Lee
 * @date : 22. 11. 3.
 */

/**
 * 1.지도를 2차원 배열로 만든다
 * 2. 주어진 좌표로 2차원 배열에 1로 범위를 표시한다.
 * 3. 남은 구간(0)에 대해 bfs탐색을 해서 크기를 구한다.
 * 4. 구한 크기를 리스트에 넣고 오름차순 정렬한다.
 * 5. 정렬된 리스트를 출력결과에 맞게 출력한다.
 * 소요시간 : 20분
 */
public class BOJ2583_영역_구하기 {

    static int M,N,K;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int[][] maps;

    static void rectangleCheck(Rectangle rectangle){

        //행
        for(int i = M - rectangle.endY; i < M - rectangle.startY; i++){
            //열
            for(int j = rectangle.startX; j < rectangle.endX; j++){
                maps[i][j] = 1;
            }
        }
    }

    static int bfs(Node node){
        int count = 1;

        maps[node.x][node.y] = 1;
        Queue<Node> needVisited = new LinkedList<>();
        needVisited.add(node);

        while(!needVisited.isEmpty()){

            Node currentNode = needVisited.poll();

            for(int i = 0; i < 4; i++){
                int nextX = currentNode.x + dx[i];
                int nextY = currentNode.y + dy[i];

                if(nextX >= 0 && nextX < M & nextY >= 0 && nextY < N && maps[nextX][nextY] == 0){
                    maps[nextX][nextY] = 1;
                    count++;
                    needVisited.add(new Node(nextX,nextY));
                }
            }
        }

        return count;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        maps = new int[M][N];

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());

            Rectangle rectangle = new Rectangle(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );

            rectangleCheck(rectangle); //직사각형 표시
        }

        //직사각형이 놓이지 않은 영역의 크기를 저장
        List<Integer> emptyArea = new ArrayList<>();

        //반복문 돌면서 0인 부분 찾기
        //bfs를 돌리고, 방문 노드는 1로 표시해버림.
        int resultCount = 0;
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if(maps[i][j] == 0){
                    resultCount++;
                    emptyArea.add(bfs(new Node(i,j)));
                }
            }
        }

        emptyArea.sort(Comparator.naturalOrder());
        StringBuilder result = new StringBuilder();
        for(int temp : emptyArea){
            result.append(temp).append(" ");
        }

        System.out.println(resultCount);
        System.out.println(result);
    }

    static class Rectangle{
        int startX,startY, endX,endY;

        Rectangle(int startX,int startY, int endX, int endY){
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }
    }

    static class Node{
        int x,y;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
