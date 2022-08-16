package baekjoon.baek20220806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ 19238번
 */

public class Second {

    static int N;
    static int M;
    static int fuel;
    static int[][] maps;
    static Map<Integer,PassengerLoc> passengerInfo;

    //승객- 목적지 까지의 연료를 잠시 저장해둠.
    static int tempFuel;

    //택시의 시작 위치.
    static Location taxiStart;

    //행 -열 번호 순서로 우선순위를 가지기 때문에 상,하,좌,우 순으로 탐색
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    //bfs탐색 - endNode의 x,y값이 -1,-1이면 최단거리의 승객을 찾아가는 중임
    //반면 -1,-1이 아니라면 목적지로 가는 것임.
    static int bfs(Location startNode, Location endNode){
        boolean[][] visited = new boolean[N][N];

        //시작노드 방문 처리.
        visited[startNode.x][startNode.y] = true;

        PriorityQueue<Location> needVisited = new PriorityQueue<>();

        needVisited.add(startNode);

        while(!needVisited.isEmpty()){

            Location currentNode = needVisited.poll();

            //이 경우 승객을 찾는 중임.
            if(endNode.x == -1 && endNode.y == -1){

                //승객을 찾으러 가던 도중 연료가 떨어지면 -1 리턴
                if(currentNode.count >=fuel) return -1;

                //만약 승객이라면
                if(maps[currentNode.x][currentNode.y] >= 2){
                    //소모된 연료를 전체 연료에서 뻄
                    fuel -= currentNode.count;
                    int tempNum = maps[currentNode.x][currentNode.y];
                    //손님위치를 다시 0으로 만듦
                    maps[currentNode.x][currentNode.y] = 0;
                    //택시위치 업데이트
                    taxiStart.x = currentNode.x;
                    taxiStart.y = currentNode.y;

                    //손님 번호를 리턴
                    return tempNum;
                }
                //연료가 떨어지지 않았다면, 택시의 현재 위치를 업데이트 하고 승객의 번호를 리턴
                for(int i = 0; i< 4; i++){
                    int nextX = currentNode.x + dx[i];
                    int nextY = currentNode.y + dy[i];

                    if(nextX >=0 && nextX < N && nextY >= 0 && nextY < N && maps[nextX][nextY] != 1 && !visited[nextX][nextY]){
                        visited[nextX][nextY] = true;
                        needVisited.add(new Location(nextX,nextY,currentNode.count+1));
                    }
                }
            }

            //-1,-1 아니라면 목적지로 가는 중임
            else{
                //목적지에 가던 도중 연료가 떨어지면 -1, 도착과 동시에 떨어지면 가능함.
                if(currentNode.count > fuel) return -1;

                //목적지에 도달했으면, 사용한 연료만큼 누적 - 목적지에 도달하면 쓴것의 두배만큼 연료가 충전되지만, 목적지 도달까지 쓴 연료를 빼면, 쓴만큼만 더해주면 됨.
                if(currentNode.x == endNode.x && currentNode.y == endNode.y){
                    fuel += currentNode.count;
                    //택시의 현재 위치 업데이트
                    taxiStart.x = endNode.x;
                    taxiStart.y = endNode.y;

                    //다음 이동을 위해 0을 리턴
                    return 0;
                }

                for(int i = 0; i <4; i++){
                    int nextX = currentNode.x + dx[i];
                    int nextY = currentNode.y + dy[i];
                    if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && maps[nextX][nextY] != 1 && !visited[nextX][nextY]){
                        visited[nextX][nextY] = true;
                        needVisited.add(new Location(nextX,nextY,currentNode.count+1));
                    }
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
        fuel = Integer.parseInt(st.nextToken());

        //지도 입력
        maps = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j< N; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(st.nextToken()) - 1;
        int startY = Integer.parseInt(st.nextToken()) - 1;
        taxiStart = new Location(startX,startY,0);

        //승객의 출발지와 목적지, 기존의 값과 겹치지 않도록 승객은 2이상으로 표시한다.
        //Map을 만들어서 매핑을 해둔다.
        passengerInfo = new HashMap<>();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int tempStartX = Integer.parseInt(st.nextToken());
            int tempStartY = Integer.parseInt(st.nextToken());
            int tempEndX = Integer.parseInt(st.nextToken());
            int tempEndY = Integer.parseInt(st.nextToken());

            maps[tempStartX-1][tempStartY-1] = i+2;

            passengerInfo.put(i+2,new PassengerLoc(tempEndX-1,tempEndY-1));
        }


//        System.out.println("fuel start : " + fuel);
        //승객의 수 만큼 탐색을 반복 - 최단거리의 승객은 bfs탐색을 하면서 먼저 나오는 승객을 선택
        for(int i = 0 ; i < M; i++){

            //승객 태우러감.
            int passenagerNum = bfs(taxiStart,new Location(-1,-1,0));

            //승객을 태우러갔는데 -1이 리턴되면 연료가 떨어져서 못태웠다는 뜻
            if (passenagerNum == -1){
                fuel = -1;
                break;
            }
//            System.out.println("passenager num : " + passenagerNum);
//            System.out.println("fuel:" + fuel);

            //-1이 아닌 숫자가 나오면 승객을 태웠다는 의미 - 목적지까지 가야됨.

            PassengerLoc temp = passengerInfo.get(passenagerNum);
            int check_Num = bfs(taxiStart,new Location(temp.endX,temp.endY,0));
            if(check_Num == -1){
                fuel = -1;
                break;
            }
//            System.out.println("fuel:" + fuel);
        }

        System.out.println(fuel);
    }

    static class Location implements  Comparable<Location>{
        int x;
        int y;
        int count;

        Location(int x, int y, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Location o) {
            if(this.count == o.count){
                if(this.x == o.x){
                    return this.y - o.y;
                }
                return this.x - o.x;
            }
            return (this.count - o.count);
        }
    }
    //승객의 위치와 목적지 정보를 담고 있음.
    static class PassengerLoc{

        int endX;
        int endY;

        PassengerLoc(int endX, int endY){

            this.endX = endX;
            this.endY = endY;
        }
    }

}