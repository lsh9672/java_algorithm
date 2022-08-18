package baekjoon.baek20220817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20057 {

    static int N;
    static int[][] maps;

    //토네이도가 이동할 좌표 - 좌, 하, 우,상
    static int[] dx = {0,1,0,-1};
    static int[] dy = {-1,0,1,0};

    //각 방향별 모래 위치
    //방향은 좌, 하,우,상 순서,
    static int[][] sandLocDx = {{ 0,-1, 1,-2,-1,1,2,1,-1},{2, 1,1, 0, 0,0,0,-1,-1},{0,-1,1,-2,-1,1,2,-1, 1},{-2,-1,-1, 0, 0,0,0, 1,1}};
    static int[][] sandLocDy = {{-2,-1,-1, 0, 0,0,0,1, 1},{0,-1,1,-2,-1,1,2,-1, 1},{2, 1,1, 0, 0,0,0,-1,-1},{ 0,-1, 1,-2,-1,1,2,-1,1}};

    //각 방향별 모래위치의 퍼센트
    static int[] sandLocPercent = {5,10,10,2,7,7,2,1,1};

    static Location currentLoc;
    static int currentDir;

    //밖으로 나간 모래의 양.
    static int outSandCount;

    //모래 이동
    static void sandMove(){
        //현재위치에서 모래날리기.

//        System.out.println(maps[currentLoc.x][currentLoc.y]);

        //남은 모래 (알파)
        int totalTempSand = 0;

        //총 10군데에 날려야됨.
        for(int i = 0; i < 9; i++){
            int nextX = currentLoc.x + sandLocDx[currentDir][i];
            int nextY = currentLoc.y + sandLocDy[currentDir][i];

            //해당 칸으로 이동할 모래의 양
            int tempSand = (maps[currentLoc.x][currentLoc.y] * sandLocPercent[i]) / 100;

            //해당칸이 모래밭을 벗어났다면 최종으로 출력할 변수에 추가함
            if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N){
//                System.out.println("test");
                outSandCount += tempSand;
            }
            //벗어나지 않았다면, 기존모래에 더해줌.
            else{
                maps[nextX][nextY] += tempSand;
            }

            totalTempSand += tempSand;
        }
        //알파 이동시키기
        totalTempSand = maps[currentLoc.x][currentLoc.y] - totalTempSand; //알파로 이동시킬 모래저장.
        int alphaX = currentLoc.x + dx[currentDir];
        int alphaY = currentLoc.y + dy[currentDir];

        //밖으로 벗어났다면 최종값에 누적
        if(alphaX < 0 || alphaX >= N || alphaY < 0 || alphaY >= N){
            outSandCount += totalTempSand;
        }
        //안벗어났다면 해당위치에 누적
        else{
            maps[alphaX][alphaY] += totalTempSand;
        }

        //모래 이동이 다 끝나면 0으로 만듦
        maps[currentLoc.x][currentLoc.y] = 0;
    }

    //토네이도 움직이기.
    static void tornadoMove(){

        //각각 두번씩 같은 크기만큼 이동한다. - 11,22,33,44,55,66,77....
        int count = 1;

        loop:
        while(true){

            //두번씩 반복함.
            for(int tmp = 0; tmp < 2; tmp++){
                for(int i = 0; i < count; i++){
//                    System.out.println("currentLocX : " + currentLoc.x+ " currentLocY : "+ currentLoc.y);
                    int nextX = currentLoc.x + dx[currentDir];
                    int nextY = currentLoc.y + dy[currentDir];

                    //만약 밖을 벗어나면 토네이도가 소멸함. 그대로 종료
                    if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) break loop;

                    //토네이도 위치 업데이트
                    currentLoc.x = nextX;
                    currentLoc.y = nextY;

                    //모래이동하는 메서드 호출
                    sandMove();

                }
                currentDir = (currentDir+1)%4;
            }
            count++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        maps = new int[N][N];

        for(int i = 0; i< N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        //토네이도의 현재위치 가운데 값으로 초기화
        currentLoc = new Location(N/2,N/2);
        //토네이도의 방향 초기화 - 좌측
        currentDir = 0;

        //출력할 모래 양 초기화
        outSandCount = 0;

        //토네이도 이동 시작 - 이 메서드안에서 모레도 매번 옮김.
        tornadoMove();

        System.out.println(outSandCount);
    }

    static class Location{
        int x;
        int y;

        Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
