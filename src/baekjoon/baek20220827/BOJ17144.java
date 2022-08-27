package baekjoon.baek20220827;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 미세먼지 안녕 복습 - 약 1시간 20분 소모
 */

public class BOJ17144 {

    static int R;
    static int C;
    static int T;

    //공기청정기 위치 - 위,아래 순
    static Location[] airCleanerLoc = new Location[2];

    //0번쨰는 위에 있는 공기청정기의 업데이트 방향, 1번째는 아래에 있는 공기청정기의 업데이트 방향.
    static int[][] airDx = {{-1,0,1,0},{1,0,-1,0}}; //상우하좌, 하우상좌,
    static int[][] airDy = {{0,1,0,-1},{0,1,0,-1}};//상우하좌, 하우상좌,

    //먼지 확산 체크를 위한 사방탐색.
    static int[] dustDx = {-1,1,0,0};
    static int[] dustDy = {0,0,-1,1};
    static int[][] maps;


    //미세먼지 양 구하기.
    static int dustMount(){
        int total = 0;

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(maps[i][j] != -1){
                    total += maps[i][j];
                }
            }
        }
        return total;
    }

    //확산은 동시에 일어나야 되기 때문에 확산될 칸의 미세먼지 좌표를 리스트에 담아서 넘기기. - 확산 위치 구하는 메서드
    static List<Location> spreadDustList(){

        List<Location> returnList = new ArrayList<>();

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                //먼지가 있는 곳이면 4방향 탐색
                if(maps[i][j] > 0){

                    int spreadValue = maps[i][j]/5; //확산되는 값.
                    int spreadCount = 0;//확산되는 위치 수

                    for(int dir = 0 ; dir < 4; dir++){
                        int nextX = i + dustDx[dir];
                        int nextY = j + dustDy[dir];

                        //맵을 벗어나지 않고 공기청정기가 아니라면 확산
                        if(nextX >= 0 && nextX < R && nextY >= 0 && nextY < C && maps[nextX][nextY] != -1){
                            spreadCount++; //확산 개수 누적
                            returnList.add(new Location(nextX,nextY, spreadValue)); //좌표를 리스트에 추가.
                        }
                    }
                    maps[i][j] -= spreadValue * spreadCount; //확산좌표를 다 구했으면 현재 좌표 업데이트
                }
            }
        }

        return returnList;
    }

    //미세먼지 확산 시키기 - 확산시키는 기능만 있음
    static void dustUpdate(){

        //확산 시킬 좌표 받아오기.
        List<Location> updateList = spreadDustList();

        //확산될 미세먼지값 저장.
        for(Location updateLoc : updateList){
            maps[updateLoc.x][updateLoc.y] += updateLoc.value;
        }
    }

    //공기 청정기 위, 아래 순으로 실행시켜서 순환시키기.
    static void rotateDust(){
        //위 :0 아래: 1
        for(int num = 0; num < 2; num++){

            //회전할 시작위치 구하기
            int currentX = 0;
            int currentY = 0;
            int dir = 0;
            if(num == 0){
                currentX = airCleanerLoc[num].x-1;
            }
            else{
                currentX = airCleanerLoc[num].x+1;
            }

            //다음위치를 확인할 변수 초기화.
            int nextX = 0;
            int nextY = 0;
            //회전
            while(true){

                nextX = currentX + airDx[num][dir];
                nextY = currentY + airDy[num][dir];

                if(num == 0){
                    //맵을 벗어나지 않았다면 값 업데이트
                    if(nextX >= 0 && nextX < R && nextY >= 0 && nextY < C && nextX <= airCleanerLoc[num].x){
                        //공기 청정기가 아니라면 다음값으로 업데이트
                        if(maps[nextX][nextY] != -1){
                            maps[currentX][currentY] = maps[nextX][nextY];
                            currentX = nextX;
                            currentY = nextY;
                        }

                        //공기청정기라면 0으로 업데이트
                        else{
                            maps[currentX][currentY] = 0;
                            break;
                        }
                    }

                    //맵을 벗어났다면 방향 전환.
                    else{
                        dir++;
                    }
                }

                else{
                    //맵을 벗어나지 않았다면 값 업데이트
                    if(nextX >= 0 && nextX < R && nextY >= 0 && nextY < C && nextX >= airCleanerLoc[num].x){
                        //공기 청정기가 아니라면 다음값으로 업데이트
                        if(maps[nextX][nextY] != -1){
                            maps[currentX][currentY] = maps[nextX][nextY];
                            currentX = nextX;
                            currentY = nextY;
                        }

                        //공기청정기라면 0으로 업데이트
                        else{
                            maps[currentX][currentY] = 0;
                            break;
                        }
                    }

                    //맵을 벗어났다면 방향 전환.
                    else{
                        dir++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        maps = new int[R][C];

        //지도 입력 받기, 각 칸에는 미세먼지 수치가 들어있음.
        int tempIdx = 0;
        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());

                //공기청정기 위치 저장.
                if(maps[i][j] == -1){
                    airCleanerLoc[tempIdx] = new Location(i,j);
                    tempIdx++;
                }
            }
        }

        for(int time = 0; time < T; time++){
            dustUpdate();
            rotateDust();
        }

        System.out.println(dustMount());
    }

    static class Location{
        int x, y,value;

        Location(int x, int y){
            this.x = x;
            this.y = y;
            value = -1;
        }

        //확산 시킬 좌표를 저장할때 사용하는 생성자 - 미세먼지 값도 필요함.
        Location(int x, int y, int value){
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
}
