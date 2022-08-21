package baekjoon.baek20220820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17144 {

    static int R;
    static int C;
    static int T;
    static Location[] airCleanerLoc; //공기 청정기의 위치 - 위 , 아래
    static int[][] maps; // 방의 정보.

    //미세먼지 확산에 사용할 4방향 정의
    static int[] dustDx = {-1,1,0,0};
    static int[] dustDy = {0,0,-1,1};

    //공기청정기의 이동방향 - 각각 위 아래 (상우하좌, 하우상좌)
    static int[][] airCleanerDx = {{-1,0,1,0},{1,0,-1,0}};
    static int[][] airCleanerDy = {{0,1,0,-1},{0,1,0,-1}};

    //1. 미세먼지를 확산시키는 메서드
    static void spreadDust(){

        //1. 반복문을 돌면서 먼지위치를 찾음
        //2. 먼지를 찾으면 4방향 탐색을 하면서 확산위치를 찾음.
        //3. 확산할 위치와 양을 리스트에 저장해둠(먼지가 동시에 확산되야 되기 떄문에. - 단, 초기 먼지 위치는 업데이트 함.)
        //4. 탐색이 다 끝났으면 리스트에서 하나씩 꺼내서 해당 위치에 더해줌.

        List<Location> tempSpreadDust = new ArrayList<>(); //퍼트릴 먼지의 위치 저장.

        //먼지를 찾음
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                //공기청정기(-1)가 아니고, 빈칸(0)이 아니라면
                if(maps[i][j] != -1 && maps[i][j] != 0){
                    currentDustUpdate(tempSpreadDust,i,j); //현재위치를 업데이트하고 확산위치를 저장하는 메서드 실행.
                }
            }
        }

        //리스트에서 원소를 하나씩 꺼내서 미세먼지를 퍼트림.
        for(Location temp : tempSpreadDust){
            maps[temp.x][temp.y] += temp.mount;
        }


    }

    //1-1 미세먼지의 위치를 받아서 4방 탐색을 하고, 리스트에 확산좌표 추가후 현재좌표 업데이트
    static void currentDustUpdate(List<Location> tempSpreadDust, int currentX, int currentY){

        int count = 0; //몇 군데에 확산이 가능한지 저장 - 현재 위치업데이트를 위해서
        int updateValue = maps[currentX][currentY] / 5; //확산시 퍼지는 양을 미리 구해둠.

        for(int i = 0; i < 4; i++){
            int nextX = currentX + dustDx[i];
            int nextY = currentY + dustDy[i];

            //해당 위치가 방을 벗어나지 않고, 공기청정기가 아니면 확산
            if(nextX >= 0  && nextX < R && nextY >= 0 && nextY < C && maps[nextX][nextY] != -1) {
                count++;
                tempSpreadDust.add(new Location(nextX, nextY, updateValue));
            }
        }

        //현재위치를 업데이트함.
        maps[currentX][currentY] -= updateValue * count;
    }

    //2. 공기 청정기 작동 메서드
    static void airCleanerStart(){

        //위 아래를 순서대로 동작시킴 - 0 : 위, 1: 아래
        for(int loc = 0; loc < 2; loc++){
            int dir = 0;

            //공기 청정기 위치.
            int currentX = airCleanerLoc[loc].x;
            int currentY = airCleanerLoc[loc].y;

            //초기에 한칸은 이동함. - 한칸 이동하고 시작해야지 먼지값이 공기청정기를 덮어쓰지 않음.
            currentX = currentX + airCleanerDx[loc][dir];
            currentY = currentY + airCleanerDy[loc][dir];

            while(true){
                //다음위치를 확인함.
                int nextX = currentX + airCleanerDx[loc][dir];
                int nextY = currentY + airCleanerDy[loc][dir];

                //유효한 값이라면 이 값으로 현재위치를 업데이트 하고 현재위치 업데이트
                if(nextX >= 0 && nextX < R && nextY >= 0 && nextY < C){

                    //공기청청기의 영역을 벗어나면, 방향 회전.
                    if(loc == 0){
                        //위에 있는 공기청정기
                        if(nextX > airCleanerLoc[0].x){
                            dir++;
                            continue;
                        }

                    }
                    else if(loc == 1){
                        //아래에 있는 공기 청정기
                        if(nextX < airCleanerLoc[1].x){
                            dir++;
                            continue;
                        }
                    }

                    if(maps[nextX][nextY] != -1){
                        maps[currentX][currentY] = maps[nextX][nextY];
                        currentX = nextX;
                        currentY = nextY;
                    }
                    //공기청정기라면 현재위치에 0을 넣고 종료
                    else{
                        maps[currentX][currentY] = 0;
                        break;
                    }

                }

                //맵을 벗어난거라면 방향을 바꿈.
                else{
                    dir++;
                }
            }
        }
    }

    //전체 미세먼지 양을 구하는 메서드
    static int allDustMount(){
        //미세먼지양 저장
        int totalDustMount = 0;

        //반복문을 돌면서 전체 미세먼지의 양을 구함.
        for(int i = 0; i < R; i++){
            for(int j = 0; j< C; j++){
                if(maps[i][j] != -1 && maps[i][j] != 0){
                    totalDustMount += maps[i][j];
                }
            }
        }

        return totalDustMount;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        maps = new int[R][C];

        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        airCleanerLoc = new Location[2];
        //공기청정기의 위치 저장.
        for(int i = 0; i < R; i++){
            if(maps[i][0] == -1){
                airCleanerLoc[0] = new Location(i,0,0);
                airCleanerLoc[1] = new Location(i+1,0,0);
                break;
            }
        }



        //T초간 각 동작을 반복
        for(int i = 0; i < T; i++){

            spreadDust(); //1. 미세먼지 확산
            airCleanerStart(); // 2. 공기 청정기 작동

        }

        //남아있는 미세먼지의 양을 출력.
        System.out.println(allDustMount());
    }

    //미세먼지를 동시에 확산시켜야되기 때문에 객체로 만들어서 저장하고 있음.
    static class Location{
        int x;
        int y;
        int mount; // 해당 칸으로 확산되는 미세먼지의 양.

        Location(int x, int y, int mount){
            this.x = x;
            this.y = y;
            this.mount = mount;
        }
    }
}
