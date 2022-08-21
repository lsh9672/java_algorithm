package baekjoon.baek20220821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 캐슬 디펜스
 */

public class BOJ17135 {

    static int N;
    static int M;
    static int D;

    //좌 상 우 순으로
    static int[] dx = {0,-1,0};
    static int[] dy = {-1,0,1};

    static int maxKill;
    static List<Integer[]> maps;


    static void gameStart(int[] achorColumn) {

//        System.out.println(Arrays.toString(achorColumn));

        //맵을 복사해서 사용
        List<Integer[]> tempMaps = copyMaps(maps);
        int killNum = 0;

        // 총 N번전진하면 끝
        int fowardCount = 0;

        while(fowardCount < N) {


            //1. 궁수가 사격을 한다 - (가장 가까운적 - 좌 - 상- 우)
            //2. 궁수가 죽인 적들을 저장했다가 한번에 업데이트한다.
            //3. 업데이트 후에 맨밑줄을 확인해서 적이 남아있으면 반복을 종료한다.


            // 방문큐를 넘겨서 bfs탐색하고 리턴 값으로 죽일 위치를 받음.
            // 죽인 사람 명단.
            List<Location> killList = new ArrayList<>();

            for(int loc : achorColumn) {
                Location bfsCheck = bfs(loc,tempMaps);
                if(bfsCheck != null) {
                    killList.add(bfsCheck);
                }
            }

            //적을 죽임
            for(Location killLoc : killList) {
                if(tempMaps.get(killLoc.x)[killLoc.y] == 1) {
                    killNum++;
                    tempMaps.get(killLoc.x)[killLoc.y] = 0;
                }

            }

            //맵을 업데이트 해준다.
            mapsUpdate(tempMaps);

            fowardCount++;
        }

//        System.out.println(killNum);

        maxKill = Integer.max(maxKill, killNum);
    }

    // 시작노드들(궁수의 위치)과 게임판정보를 입력받아서 bfs 탐색을 돌면서 죽일수 있는 적의 수를 확인.
    // 최대로 죽인 적의 수를 리턴해줌.
    static Location bfs(int loc,List<Integer[]> tempMaps) {


        boolean[][] visited = new boolean[N][M];

        Queue<Location> needVisited = new LinkedList<>();

        needVisited.add(new Location(N-1,loc,1));

        visited[N-1][loc] = true;

        while(!needVisited.isEmpty()) {

            Location currentNode = needVisited.poll();

            // 현재위치에 병사가 있으면
            if(tempMaps.get(currentNode.x)[currentNode.y] == 1) {
                //죽일 리스트에 추가함.
                return currentNode;
            }

            for(int i = 0; i < 3; i++) {
                int nextX = currentNode.x + dx[i];
                int nextY = currentNode.y + dy[i];

                if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && !visited[nextX][nextY]) {
                    int tempDistance = currentNode.count + 1;

                    // 목표 사거리 이내면 다음 탐색 노드로 넣어줌
                    if(tempDistance <= D) {
                        needVisited.add(new Location(nextX,nextY,tempDistance));
                        visited[nextX][nextY] = true;
                    }
                }
            }

        }

        return null;

    }

    //게임판을 복사함.
    static List<Integer[]> copyMaps(List<Integer[]> origin){
        List<Integer[]> returnMaps = new ArrayList<>();
        for(Integer[] ttt : origin) {
            Integer[] temp = new Integer[M];
            System.arraycopy(ttt, 0, temp, 0, M);

            returnMaps.add(temp);
        }


        return returnMaps;
    }

    //게임판 업데이트, 적들이 한칸씩 밑으로 내려옴.
    static void mapsUpdate(List<Integer[]> tempMaps) {


        // 마지막줄 삭제하고 맨앞에 0으로 채운 배열넣음
        tempMaps.remove(tempMaps.size()-1);
        Integer[] temp = new Integer[M];
        Arrays.fill(temp, 0);
        tempMaps.add(0,temp);


    }

    // 배열에는 행번호
    static void recursive(int idx,int[] achorColumn,int loc) {

        if(idx > M) {
            return;
        }

        //3개의 병사를 배치했으면 게임 시작
        if(loc >= 3) {
//			System.out.println(Arrays.toString(achorColumn));
            // 이 함수에서 죽일수 있는 적의 수 전부 구함.
            gameStart(achorColumn);
            return;
        }

        achorColumn[loc] = idx;
        recursive(idx+1,achorColumn,loc+1);
        recursive(idx+1,achorColumn,loc);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        // 지도를 deque로 만들어서 병사들이 이동하는 것 처럼 보이게 함.
        maps = new ArrayList<>();

        for(int i= 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Integer[] tmp = new Integer[M];
            for(int j = 0; j< M; j++) {
                tmp[j] = Integer.parseInt(st.nextToken());
            }

            maps.add(tmp);
        }

        maxKill = 0;


        recursive(0,new int[3],0);

        System.out.println(maxKill);

    }

    static class Location{
        int x;
        int y;
        int count;

        Location(int x, int y, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }

    }

}
