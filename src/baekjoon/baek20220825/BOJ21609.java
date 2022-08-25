package baekjoon.baek20220825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 검은색 : -1, 무지개 블록 : 0, 일반블록 : 1~M
 * 상하좌우로 붙어있는 것만 연결된 것으로 봄
 * 블록을 탐색한 후에, 스타트 포인트만 저장하면 됨.
 */

public class BOJ21609 {

    static int N;
    static int M;
    static int result; // 최종 점수
    //상,하,좌,우
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] maps;

    static List<BlockGroupInfo> groupSortList;

    //bfs를 이용해서 블록을 찾음. - type == 0이면 그룹찾는 중, 1이면 탐색하면서 해당 블록들 전부 -2로 변경(빈칸 표시)
    static void bfs(Location startNode, boolean[][] visited, int type){

        visited[startNode.x][startNode.y] = true;

        //기준 값 - 이 값과 같은 값 또는 무지개색만 이동
        int stdNum = maps[startNode.x][startNode.y];

        //블록수 누적
        int totalBlockCount = 0;

        //무지개색 블럭 누적
        int rainbowBlockCount = 0;

        Queue<Location> needVisited = new LinkedList<>();

        needVisited.add(startNode);

        while(!needVisited.isEmpty()){

            Location currentNode = needVisited.poll();
            //만약 타입1이라면, 탐색한 공간들을 다 지워버림
            if(type == 1){
                maps[currentNode.x][currentNode.y] = -2;
            }

            totalBlockCount++;

            for(int i = 0; i < 4; i++){
                int nextX = currentNode.x + dx[i];
                int nextY = currentNode.y + dy[i];

                if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && !visited[nextX][nextY]){
                    //시작점과 동일하거나 0이라면
                    if(maps[nextX][nextY] == stdNum || maps[nextX][nextY] == 0){
                        visited[nextX][nextY] = true;
                        needVisited.add(new Location(nextX,nextY));
                        //무지개 블럭이면 따로 누적해둠
                        if(maps[nextX][nextY] == 0){
                            rainbowBlockCount++;
                        }


                    }
                }
            }

        }

        //탐색 다 끝나고 나서 타입 0이라면 무지개 색 블록은 방문 취소해줌
        if(type == 0){
            //해당 그룹 정보 추가 - 블록 두개 이상일때만 그룹이 됨
            if(totalBlockCount >= 2){
                groupSortList.add(new BlockGroupInfo(startNode.x,startNode.y,totalBlockCount,rainbowBlockCount));
            }

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    //방문처리되었고, 무지개색이라면 취소
                    if(visited[i][j] && maps[i][j] == 0) visited[i][j] = false;
                }
            }
        }
    }

    //지도 회전
    static void rotateMaps(){
       int[][] tempMaps = new int[N][N];

       for(int i = 0; i < N; i++){
           for(int j = 0; j < N; j++){
               tempMaps[N-j-1][i] = maps[i][j];
           }
       }

       maps = tempMaps;

    }

    //점수 업데이트
    static void scoreUpdate(BlockGroupInfo temp){
        result += temp.groupSize * temp.groupSize;
    }

    //중력 적용해서 블록 떨어뜨림 - 검은색(-1) 제외
    static void gravityUpdate(){
        for(int currentX = N-2; currentX >= 0; currentX--){
            for(int currentY = 0; currentY < N; currentY++){

                if(maps[currentX][currentY] != -1 && maps[currentX][currentY] != -2){
                    int count = 1;
                    while(true){
                        int nextX = currentX + count;
                        int nextY = currentY;

                        //게임판을 넘어가거나 해당위치가 빈칸이 아니면 그 이전의 위치에 값을 놓음
                        if(nextX >= N || maps[nextX][nextY] != -2){
                            int temp = maps[currentX][currentY];
                            maps[currentX][currentY] = -2;
                            maps[currentX+ count-1][nextY] = temp;

                            break;
                        }
                        else if(maps[nextX][nextY] == -2){
                            count++;
                        }
                    }
                }

            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][N];

        //맵 입력 받기
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        /**로직**/
        /*
        1. 크기가 가장 큰 그룹 - 무지개 블럭 수  - 기준 블럭의 행이 가장 큰것 - 열이 가장 큰것 순으로 정렬
        2. 1번에서 뽑은 블록 그룹의 모든 블럭을 제거 - 다른 블럭과 안겹치게 빈칸을 -2로 표시
        3. 블록수^2만큼 점수를 얻음.
        4. 중력이 적용해서 검은색을 제외한 모든 블럭이 아래로 떨어짐
        5. 격자가 90도 반시계 방향으로 회전.
        6. 격자에 중력 적용
        */

        while(true){
            /*1번*/
            //각 그룹정보를 저장
            groupSortList = new ArrayList<>();

            //방문배열 - 일반 블럭 (자연수)만 방문처리하고, 무지개 블럭은 다시 방문 취소해줌.
            //일반배열은 같은 숫자면 방문하지만, 무지개는 숫자 상관없이 모든 숫자의 블럭이 방문함. - 따라서 매번 방문후 취소처리해서 다음 탐색에서 방문가능하도록 해줌
            boolean[][] visited = new boolean[N][N];

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    //방문하지 않았고, 일반 블럭일때만 bfs탐색.
                    if(maps[i][j] > 0 && !visited[i][j]){
                        bfs(new Location(i,j),visited,0);
                    }
                }
            }

            //탐색이 끝났는데 조건에 부합하는 그룹이 없다면, 종료
            if(groupSortList.isEmpty()) break;

            //정렬
            Collections.sort(groupSortList);

            //구한 최대 그룹의 정보
            BlockGroupInfo maxBlock = groupSortList.get(0);

            /*2번 - 삭제*/
            visited = new boolean[N][N];
            bfs(new Location(maxBlock.stdX,maxBlock.stdY),visited,1);

            /*3번 - 점수 업데이트*/
            scoreUpdate(maxBlock);

            /*4번 - 중력 적용*/
            gravityUpdate();

            /*5번 - 배열 회전*/
            rotateMaps();
            /*6번 - 중력적용*/
            gravityUpdate();

        }

        System.out.println(result);
    }

    static class Location{
        int x,y;

        Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    //그룹의 기준 블록과 그룹 사이즈, 무지개 블록수를 저장함.
    //조건에 맞는 그룹을 찾기 위해서 Comparable 구현
    static class BlockGroupInfo implements Comparable<BlockGroupInfo>{
        int stdX,stdY, groupSize, rainbowSize;

        BlockGroupInfo(int stdX,int stdY, int groupSize, int rainbowSize){
            this.stdX = stdX;
            this.stdY = stdY;
            this.groupSize = groupSize;
            this.rainbowSize = rainbowSize;
        }

        @Override
        public int compareTo(BlockGroupInfo o) {

            if(this.groupSize == o.groupSize){

                if(this.rainbowSize == o.rainbowSize){

                    if(this.stdX == o.stdX){

                        return o.stdY - this.stdY;
                    }

                    return  o.stdX - this.stdX;
                }

                return o.rainbowSize - this.rainbowSize;
            }

            return o.groupSize - this.groupSize;
        }

    }


}
