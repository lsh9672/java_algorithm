package baek20220806;

/**
 * Boj 19236
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Third {

    //최댓값.
    static int result;

    //위 - 왼위 - 왼 - 왼위 아래 - 아래 - 오른쪽아래 - 오른쪽 - 오른쪽위 => 인덱스 1부터 사용.
    static int[] dx = {-1,-1, 0, 1,1,1,0,-1};
    static int[] dy = {0,-1,-1,-1,0,1,1, 1};


    static void fishMoveCheck(int x, int y,Location[][] tempMaps,int count){
        //이동할수 있는 곳이라면 이동하고 종료
        int nextX = x + dx[tempMaps[x][y].dir];
        int nextY = y + dy[tempMaps[x][y].dir];

        if(count >= 8) return;

        if(nextX >= 0 && nextX < 4 && nextY >= 0 && nextY < 4 && tempMaps[nextX][nextY].value != 99){
            //조건에 맞으면 이동하고 리턴
            Location temp = tempMaps[nextX][nextY];
            tempMaps[nextX][nextY] = tempMaps[x][y];
            tempMaps[x][y] = temp;

            return;
        }

        tempMaps[x][y].dir = (tempMaps[x][y].dir+1)%8;
        fishMoveCheck(x,y,tempMaps,count+1);
    }

    static void fishMove(Location[][] tempMaps){
        //물고기를 찾을때 마다 매번 번호를 반복문을 돌려서 찾음
        for(int num = 1; num <= 16; num++){
            loop:
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    if(tempMaps[i][j].value == num){
                        //해당번호의 물고기를 찾았으면 재귀로 움직이면서 자리를 업데이트 하는 함수 출력.
                        fishMoveCheck(i,j,tempMaps,0);
                        break loop;
                    }
                }
            }
        }

    }
    //상어가 움직임 - 백트래킹 해야됨, 여러 경우의 수가 있음.
    static void sharkMove(int x, int y, int totalNum,Location[][] maps){

        //해당 좌표값이 이동이 가능한 곳인지 확인.
        //이동이 불가능한 곳이라면 종료
        if(x < 0 || x >= 4 || y < 0 || y >= 4 || maps[x][y].value == 0){
            result = Math.max(result,totalNum);
            return;
        }

        //이동이 가능한 곳이라면, 배열을 복사하고, 상어를 이동시킨 후에, 물고기를 이동 시킨다.
        //먼저 기존의 리스트를 복사함.
        Location[][] tempMaps = new Location[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                tempMaps[i][j] = new Location(maps[i][j].value,maps[i][j].dir);
            }
        }

        //이동할 곳의 물고기의 숫자를 누적해둠
        totalNum += tempMaps[x][y].value;

        //해당 위치를 상어로 바꿈.
        tempMaps[x][y].value = 99;
        //물고기를 이동시킴
        fishMove(tempMaps);

        /* 재귀로직*/
        //상어가 이동할수 있는 경우.
        //최대 이동가능한 칸은 3칸임.
        for(int i = 1; i <= 3; i++){
            int nextX = x + dx[tempMaps[x][y].dir] * i;
            int nextY = y + dy[tempMaps[x][y].dir] * i;

            tempMaps[x][y].value = 0;
            sharkMove(nextX,nextY,totalNum,tempMaps);
            tempMaps[x][y].value = 99;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        Location[][] maps = new Location[4][4];
        for(int i = 0; i < 4; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++){

                int tempValue = Integer.parseInt(st.nextToken());
                int tempDir = Integer.parseInt(st.nextToken())-1;

                maps[i][j] = new Location(tempValue,tempDir);

            }
        }

        result = Integer.MIN_VALUE;

        //초기에 상어가 0,0으로 들어감. - 상어가 물고기를 먹으면 value가 99 됨.
        sharkMove(0,0, 0,maps);

        System.out.println(result);
    }

    static class Location{
        // 1~16: 물고기, 99: 상어, 0:빈공간
        int value;
        int dir;

        Location(int value, int dir){
            this.value = value;
            this.dir = dir;

        }

    }
}
