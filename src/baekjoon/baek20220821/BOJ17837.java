package baekjoon.baek20220821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17837 {

    static int N;
    static int K;

    //인덱스 1부터 사용 - 우,좌,상,하
    static int[] dx = {0,0,0,-1,1};
    static int[] dy = {0,1,-1,0,0};

    static int[][] maps;

    static Location[] horseLoc;

    static List<Integer>[][] mapsHorse; //말을 여러개 겹치기

    //방향 바꾸기
    static int changeDir(int currentDir){
        int returnDir;

        if(currentDir == 1){
            returnDir = currentDir+1;

        }
        else if(currentDir == 2){
            returnDir = currentDir - 1;
        }
        else if(currentDir == 3){
            returnDir = currentDir+1;
        }
        else{

            returnDir = currentDir - 1;
        }
        return returnDir;
    }

    //각 색깔별로 이동
    static void colorHorseUpdate(int currentNum, int nextX, int nextY, int color){
        //3가지 색깔별로 나눔

        int currentX = horseLoc[currentNum].x;
        int currentY = horseLoc[currentNum].y;

        //흰색
        if(color == 0){
            int idx = mapsHorse[currentX][currentY].indexOf(currentNum);//이동하려고 하는 위치의 인덱스 찾기
            for(int i = idx; i < mapsHorse[currentX][currentY].size(); i++){
                int removeValue = mapsHorse[currentX][currentY].remove(i--);
                //뺀 값을 다음 위치에 넣음
                mapsHorse[nextX][nextY].add(removeValue);
                //각 말들의 위치를 업데이트;
                horseLoc[removeValue].x = nextX;
                horseLoc[removeValue].y = nextY;
            }
        }
        //빨간색 - 역순으로 넣음
        else if(color == 1){
            int idx = mapsHorse[currentX][currentY].indexOf(currentNum);//이동하려고 하는 위치의 인덱스 찾기
            for(int i = mapsHorse[currentX][currentY].size()-1; i >= idx ; i--){
                int removeValue = mapsHorse[currentX][currentY].remove(i);
                //뺀 값을 다음 위치에 넣음
                mapsHorse[nextX][nextY].add(removeValue);
                //각 말들의 위치를 업데이트;
                horseLoc[removeValue].x = nextX;
                horseLoc[removeValue].y = nextY;
            }
        }
    }

    //이동한 위치좌표를 받아서 4개이상 쌓였는지 확인.
    static boolean horseNumCheck(int x, int y){

        if(mapsHorse[x][y].size() >= 4){
            return true;
        }
        return false;
    }

    //말이 이동.
    static boolean horseMove(){

        //말을 하나씩 꺼내서 이동시킴.
        for(int horseNum = 1; horseNum <= K; horseNum++){

            //해당 말의 위치확인
            int nextX = horseLoc[horseNum].x + dx[horseLoc[horseNum].dir];
            int nextY = horseLoc[horseNum].y + dy[horseLoc[horseNum].dir];

            //게임 판을 벗어나지 않는다면
            if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N){
                //만약 다음 위치가 파란색이라면 방향바꾸기.
                if(maps[nextX][nextY] == 2){
                    horseLoc[horseNum].dir = changeDir(horseLoc[horseNum].dir);
                    nextX = horseLoc[horseNum].x + dx[horseLoc[horseNum].dir];
                    nextY = horseLoc[horseNum].y + dy[horseLoc[horseNum].dir];
                    //방향을 바꾸고 이동하려는 위치가 게임판을 벗어나는지 한번더 확인
                    //벗어난다면 그대로 두고 다음 말 이동
                    if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N){
                        continue;
                    }
                }

                colorHorseUpdate(horseNum, nextX,nextY, maps[nextX][nextY]);
            }
            //칸을 벗어나면 방향 바꾸고 이동
            else{
                horseLoc[horseNum].dir = changeDir(horseLoc[horseNum].dir);
                nextX = horseLoc[horseNum].x + dx[horseLoc[horseNum].dir];
                nextY = horseLoc[horseNum].y + dy[horseLoc[horseNum].dir];
                colorHorseUpdate(horseNum, nextX,nextY, maps[nextX][nextY]);
            }

            //4개 쌓였는지 매번 확인
            if(horseNumCheck(nextX, nextY)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        maps = new int[N][N];

        horseLoc = new Location[K+1];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        mapsHorse = new ArrayList[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                mapsHorse[i][j] = new ArrayList<>();
            }
        }

        for(int i = 1; i <= K; i++){
            st = new StringTokenizer(br.readLine());
            int tempX = Integer.parseInt(st.nextToken())-1;
            int tempY = Integer.parseInt(st.nextToken())-1;
            int tempDir = Integer.parseInt(st.nextToken());

            mapsHorse[tempX][tempY].add(i);
            horseLoc[i] = new Location(tempX,tempY,tempDir);
        }

        int turn = 0;

        while(turn <= 1000){
            turn++;
            //true면 말이 4개 쌓임
            if(horseMove()) break;

        }

        if(turn > 1000) System.out.println(-1);
        else System.out.println(turn);
    }

    static class Location{
        int x;
        int y;
        int dir;

        Location(int x, int y, int dir){
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}
