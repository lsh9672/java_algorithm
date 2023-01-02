package baekjoon.baek20221129;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : sh Lee
 * @date : 22. 11. 27.
 */

/**
 * 아이디어
 * 1. 배양액을 뿌렸을때 피울수 있는 꽃의 최대개수를 구해야 된다. => 모든 경우를 다 해봐야됨(뿌려야 되는 배양액의 수를 보거나, 그리디하게 생각할 수 없기 때문에 완탐문제)
 * 2. 기본적으로 모든 경우를 다 탐색하는 완탐 문제이다.
 * 3. 배양액을 뿌리고 퍼트리면서 꽃을 피울수 있으면 카운트를 함.
 * 4. 배양액을 뽑는 순서를 바꿔도 결국 뿌리고 퍼트리기 때문에 순서는 상관없기 때문에 조합문제라고 볼 수 있음.
 * 5. 로직 순서
 *      5-1 먼저 배양액을 뿌릴 수 있는 위치를 리스트로 미리 뽑아둠.
 *      5-2 뽑아둔 배양액에서 초록색을 선택하는 경우, 빨간색을 선택하는 경우를 뽑음
 *      5-3 파라미터로 각각의 개수를 따로 두고, 개수가 남앗다면 뽑는 식으로 함.
 *      5-4 뽑은 위치와 색깔은 bfs탐색을 위해 큐에 담음
 *      5-5 모든 개수를 다 뽑았다면, 큐에 초록색 - 빨간색 순으로 담고 bfs로 탐색함. 배열에는 초록색과 꽃만 표시
 *      5-6 만약 번갈아가면서 퍼뜨리지 않고, 초록색 퍼뜨리고 빨간색퍼뜨리는 식으로 하면, 문제에서 꽃을 피우면 더이상 퍼지지 않는다는 조건을 위배함.
 *      5-7 퍼뜨리는 과정에서 꽃이 피어나면 꽃으로 체크를 함.
 *
 */

/*
풀고난 후 문제점
파워셋을 이용해서 빨강을 뽑는 경우, 초록을 뽑는 경우, 뽑지 않는 경우로 나눴는데, 이렇게 하면 빨강 <-> 초록 을 서로 바꾸기만 했을때도 계산함
즉, 계산할 필요 없는 것까지 계산함
초기에 생각했던, 배치 가능한 토양을 뽑고, 거기에서 빨강을 뽑는 경우로 하는 것이 좋음.
 */

public class BOJ18809_Gaaaaaaaaaarden {

    static int N, M, G,R;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int result;

    static int[][] garden;

    static List<Location> incubatePossibleLand;

    static Stack<Node> selectIncubateLocation;

    //선택한 배양액을 퍼뜨리고 만들어지는 꽃을 세는 bfs메서드
    static int bfs(){

        int[][] greenVisited = new int[N][M]; //초록색
        int[][] redVisited = new int[N][M]; //빨간색

        Queue<Node> needVisited = new ArrayDeque<>();
        needVisited.addAll(selectIncubateLocation);


        //초기에 배양된 위치 방문처리 필요
        for(Node node : selectIncubateLocation){
            if(node.color == 3){
                greenVisited[node.x][node.y] = node.time;
            }
            else{
                redVisited[node.x][node.y] = node.time;
            }
        }

        int flowerCount = 0; //생성된 꽃의 수 누적.

        while(!needVisited.isEmpty()){

            Node currentNode = needVisited.poll();

            //큐에서 꺼내서 퍼뜨릴떄, 현재 위치가 꽃이라면, 패스 - 꽃의 경우, 빨간색 방문처리와 초록색 방문처리 모두에 표시해둘 예정.
            //꽃은 다른 것과 겹치지 않기 위해 -1로 표시
            if(greenVisited[currentNode.x][currentNode.y] == -1){
                continue;
            }

            //인접한 노드를 탐색할지 추가.
            for(int dir = 0; dir < 4; dir++){
                int nextX = currentNode.x + dx[dir];
                int nextY = currentNode.y + dy[dir];

                //초록색일때와 빨간색일때를 나눠서 생각.
                if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && garden[nextX][nextY] != 0){

                    //초록색 배양액일때.
                    if(currentNode.color == 3){
                        //방문확인
                        if(greenVisited[nextX][nextY] == 0){

                            //같은 시간에 퍼진 빨간색 배양액이 있다면 꽃으로 만들고 숫자 누적
                            if(redVisited[nextX][nextY] == currentNode.time + 1){
                                greenVisited[nextX][nextY] = -1;
                                flowerCount++;
                            }
                            //해당위치에 꽃을 만들수 없다면 큐에 넣고 퍼뜨리는 행위 반복
                            else{
                                greenVisited[nextX][nextY] = currentNode.time + 1;
                                needVisited.add(new Node(nextX,nextY, currentNode.color, currentNode.time + 1));
                            }
                        }
                    }

                    //빨간색 배양액일때.
                    else if(currentNode.color == 4){
                        if(redVisited[nextX][nextY] == 0){

                            //같은 시간에 퍼진 초록색 배양액이 있다면 꽃으로 만들고 숫자 누적
                            if(greenVisited[nextX][nextY] == currentNode.time + 1){
                                greenVisited[nextX][nextY] = -1;
                                redVisited[nextX][nextY] = -1;
                                flowerCount++;
                            }
                            //해당위치에 꽃을 만들수 없다면 큐에 넣고 퍼뜨리는 행위 반복
                            else{
                                redVisited[nextX][nextY] = currentNode.time + 1;
                                needVisited.add(new Node(nextX,nextY, currentNode.color, currentNode.time + 1));
                            }
                        }
                    }
                }

            }
        }

        return flowerCount; // 만들어진 꽃의 수를 반환.
    }

    //백트래킹 - 배양액을 선택
    static void backTracking(int idx, int redCount, int greenCount){

        //가능한 배양액수보다 많이 뽑았다면 종료
        if(redCount > R || greenCount > G) return;

        //목표한 배양액을 다 뿌렸다면
        if(redCount == R && greenCount == G){
            int flowerCount = bfs();
            result = Math.max(result, flowerCount);

            return;
        }

        //배양가능한 땅의 인덱스를 넘어선다면, 종료
        if(idx == incubatePossibleLand.size()) return;

        Location currentPossibleLand = incubatePossibleLand.get(idx); //현재 배양가능한 땅.

        //초록색 배양액을 배치하는 경우.
        selectIncubateLocation.add(new Node(currentPossibleLand.x, currentPossibleLand.y, 3,1));
        backTracking(idx+1, redCount, greenCount + 1);
        selectIncubateLocation.pop();

        //빨간색 배양액을 배치하는 경우.
        selectIncubateLocation.add(new Node(currentPossibleLand.x, currentPossibleLand.y, 4,1));
        backTracking(idx+1, redCount+1, greenCount);
        selectIncubateLocation.pop();

        //해당 위치에 배양액을 배치 하지 않는 경우.
        backTracking(idx+1, redCount, greenCount);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*입력*/
        N = Integer.parseInt(st.nextToken()); //행
        M = Integer.parseInt(st.nextToken()); //열
        G = Integer.parseInt(st.nextToken()); //초록색 배양액 수
        R = Integer.parseInt(st.nextToken()); //빨간색 배양액 수

        incubatePossibleLand = new ArrayList<>(); //배양액 배치가 가능한 땅.

        //정원 입력
        garden = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                garden[i][j] = Integer.parseInt(st.nextToken());

                //배양액을 뿌릴수 있는 땅이라면 저장
                if(garden[i][j] == 2){
                    incubatePossibleLand.add(new Location(i,j));
                }
            }
        }


        result = Integer.MIN_VALUE; //꽃의 최대개수를 저장할 변수 - 가장 작은 값으로 초기화.
        selectIncubateLocation = new Stack<>(); //뽑은 배양액을 저장.
        backTracking(0, 0,0);

        System.out.println(result);
    }

    //배양액의 위치, 색깔3: 초록, 4: 빨강 => 기존 정원의 호수,땅과 겹치지 안도록 하기 위해.), 시간을 가지고 있는 클래스
    static class Node{
        int x,y,color, time;

        Node(int x, int y, int color, int time){
            this.x = x;
            this.y = y;
            this.color = color;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", color=" + color +
                    ", time=" + time +
                    '}';
        }
    }

    //배양이 가능한 땅의 좌표 저장을 위한 클래스
    static class Location{
        int x,y;

        Location(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Location{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
