package baek20220813;

/**
 * BOJ 1194번 - 달이 차오른다, 가자(골드1)
 * 핵심 : 벽부수기와 유사하다고 생각됨. 각 노드마다 가지고 있는 열쇠에 따라 방문처리를 해야됨
 * 열쇠 상태로 구분하기 위해서 비트마스킹을 이용해야됨
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1194 {

    static int N;
    static int M;
    static String[][] maps;
    //각 열쇠정보를 비트마스킹 하기 위해, 각 열쇠마다 몇 비트를 밀어야 되는지 저장.
    static Map<String,Integer> keys;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    //키정보 매핑하는 메서드 - 메인함수에서 쓰면 지저분해서 메서드로 묶음
    static void keyMapping(){
        keys.put("a",0);
        keys.put("b",1);
        keys.put("c",2);
        keys.put("d",3);
        keys.put("e",4);
        keys.put("f",5);

    }

    //출구로 가는 최단거리를 찾는 bfs함수
    static int bfs(Location startNode){
        //각 좌표별로 키의 상태에 따라서 64가지 경우가 발생 - 0은 키가 없을 때 63은 전부 가졌을때.
        //문과 가지고 있는 키를 or연산했을때,값이 0이 아니라면 문을 열수 있다는 뜻
        boolean[][][] visited = new boolean[N][M][64];
        visited[startNode.x][startNode.y][0] = true;

        //탐색을 위해 큐를 선언
        Queue<Location> needVisited = new LinkedList<>();

        needVisited.add(startNode);

        while(!needVisited.isEmpty()){

            Location currentNode = needVisited.poll();


            //인접노드를 추가하는데, 열쇠상태에 따라 추가함.
            for(int i = 0; i < 4; i++){
                int nextX = currentNode.x + dx[i];
                int nextY = currentNode.y + dy[i];


                //이동할 위치가 격자판을 벗어나지 않는지 확인.
                if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && !visited[nextX][nextY][currentNode.keyInfo]){
                    //빈칸일 경우 - 그냥 이동
                    if(maps[nextX][nextY].equals(".")){
                        visited[nextX][nextY][currentNode.keyInfo] = true;
                        needVisited.add(new Location(nextX,nextY, currentNode.count+1, currentNode.keyInfo));
                    }
                    //열쇠일 경우 - 현재 열쇠 상태에 or연산을 통해서 합쳐줌
                    else if(Character.isLowerCase(maps[nextX][nextY].charAt(0))){
                        //현재 상태에 열쇠를 추가하고, 빈공간으로 바꾼후에 다음 탐색노드로 탐색.
                        visited[nextX][nextY][currentNode.keyInfo] = true;
                        int newKey = currentNode.keyInfo | (1<<keys.get(maps[nextX][nextY]));
                        needVisited.add(new Location(nextX,nextY, currentNode.count+1,newKey));

                    }
                    //문일경우 - and연산을 통해서 0인지 아닌지에 따라서 통과 가능한지 아닌지 확인.
                    else if(Character.isUpperCase(maps[nextX][nextY].charAt(0))){
//                        System.out.println(Integer.toBinaryString(currentNode.keyInfo));
                        //현재 보유한 열쇠로 열수 있는지 확인
                        int tempCheck = currentNode.keyInfo & (1<< (keys.get(Character.toLowerCase(maps[nextX][nextY].charAt(0))+"")));
                        //문을 열수 있다면 방문처리하고 다음 노드로 추가
                        if(tempCheck != 0){
                            visited[nextX][nextY][currentNode.keyInfo] = true;
                            needVisited.add(new Location(nextX,nextY, currentNode.count+1, currentNode.keyInfo));
                            //문을 열었으면 평지로 만듦 - 어차피 큐에 넣어서 탐색하기 때문에 이 이후에 탐색하는 노드들은 전부 문을 열수 있음.
                        }
                    }
                    //도착지점이면, 그대로 리턴
                    else if(maps[nextX][nextY].equals("1")){
                        return currentNode.count+1;
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

        maps = new String[N][M];

        //지도 입력받기.
        for(int i = 0; i < N; i++){
            maps[i] = br.readLine().split("");
        }

        Location startNode = null;

        //반복문 돌면서 bfs탐색시에 초기위치로 사용할 민식이의 위치(0)을 찾아서 저장.
        loop:
        for(int i = 0; i< N; i++){
            for(int j = 0; j< M; j ++){
                if(maps[i][j].equals("0")){
                    startNode = new Location(i,j,0,0);
                    //탐색조건 주기 편하게 민식이 위치를 빈공간을 만듦
                     maps[i][j] = ".";
                     break loop;
                }
            }
        }

        //키 정보 매핑 해두기.
        keys = new HashMap<>();
        keyMapping();

        System.out.println(bfs(startNode));
    }

    //x,y좌표와 이동 거리를 저장할 클래스
    static class Location{
        int x;
        int y;
        int count;
        int keyInfo;

        Location(int x, int y, int count, int keyInfo){
            this.x = x;
            this.y = y;
            this.count = count;
            this.keyInfo = keyInfo;
        }
    }
}
