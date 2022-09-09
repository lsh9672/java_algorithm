package baekjoon.baek20220909;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
1. 상어의 번호(인덱스 번호)를 지도에 표시해둔다.
2. 낚시왕의 열에서 한 행씩 내려가면서 처음으로 마주치는 상어를 선택함.
 */

public class BOJ17143_낚시왕 {

    static int R;
    static int C;
    static int M;

    static int result = 0; // 잡은 상어의 크기의 총합
    static int fisherLocY;//낚시꾼의 열 위치

    //상 좌 하 우 정의
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};

    static SharkInfo[] sharkList;
    static int[][] maps;

    //낚시꾼이 상어 잡기
    static void fisherCatchShark(){

        //현재 열을 고정하고 한 행씩 내려가봄.
        for(int i = 1; i <= R; i++){
            //상어가 있다면 먹고 종료
            if(maps[i][fisherLocY] != 0){
                result += sharkList[maps[i][fisherLocY]].size;//상어 크기만큼 총합에 넣음.
                sharkList[maps[i][fisherLocY]] = null;//해당 상어정보 null 만듦
                maps[i][fisherLocY] = 0; //상어를 잡음

                break;
            }
        }

    }

    //상어 이동
    static void sharkMove(){

        int[][] tempMaps = new int[R+1][C+1];

        //반복문 돌면서 각 상어를 이동시키고 이동 위치를 업데이트 리스트에 넣음.
        for(int num = 1; num <= M; num++){
            if(sharkList[num] == null) continue;

            SharkInfo tempInfo = sharkList[num];

            int tempSpeed = -1;
            int nextX = tempInfo.x;
            int nextY = tempInfo.y;


            //상 하 - x 값
            if(tempInfo.dir == 0 || tempInfo.dir == 2){
                tempSpeed = tempInfo.speed%((R-1)*2);
            }
            else{
                tempSpeed = tempInfo.speed%((C-1)*2);
            }

            for(int count = 0; count < tempSpeed; count++){

                nextX = tempInfo.x + dx[tempInfo.dir];
                nextY = tempInfo.y + dy[tempInfo.dir];

                if(nextX < 1 || nextX > R || nextY < 1 || nextY > C){
                    tempInfo.dir = (tempInfo.dir + 2) % 4;
                    nextX = tempInfo.x + dx[tempInfo.dir];
                    nextY = tempInfo.y + dy[tempInfo.dir];
                }

                tempInfo.x = nextX;
                tempInfo.y = nextY;
            }


           /*상어 셋팅*/
            //해당 위치에 상어가 없다면 그냥 놓기
            if(tempMaps[nextX][nextY] == 0){
                tempMaps[nextX][nextY] = num;
                sharkList[num] = new SharkInfo(nextX, nextY, tempInfo.speed,tempInfo.dir, tempInfo.size);
            }

            //해당 위치에 상어가 있다면,
            else{
                //새로운 상어가 기존의 상어의 크기보다 크다면 업데이트
                if(sharkList[num].size >  sharkList[tempMaps[nextX][nextY]].size){
                    sharkList[tempMaps[nextX][nextY]] = null;//기존에 있던 상어 정보 null
                    tempMaps[nextX][nextY] = num;
                    sharkList[num] = new SharkInfo(nextX, nextY, tempInfo.speed,tempInfo.dir, tempInfo.size);
                }

                //더 작다면
                else{
                    sharkList[num] = null;
                }
            }
        }


        maps = tempMaps;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(st.nextToken());
        sharkList = new SharkInfo[M+1];

        fisherLocY = 0; //낚시꾼의 초기 위치.

        maps = new int[R+1][C+1];

        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            //방향 전환 편하게 하기 위해서.
            if(d == 1) d = 0;
            else if(d == 4) d = 1;



            //각 상어의 정보를 저장 - 인덱스가 상어의 번호가 됨
            sharkList[i] = new SharkInfo(x,y,s,d,z);

            //지도에 상어를 표시하는데 지도에는 상어정보가 담긴 배열의 인덱스만 저장.
            maps[x][y] = i;
        }

        /*로직*/
        for(int i = 1; i <= C; i++){

            fisherLocY = i; // 낚시꾼위치.
            fisherCatchShark();//상어잡기
            sharkMove();

        }

        System.out.println(result);
    }

    static class SharkInfo{
        int x,y, speed,dir,size;

        SharkInfo(int x, int y, int speed, int dir, int size){
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }

        @Override
        public String toString() {
            return "speed : " + speed + " dir : " + dir + " size : " + size;
        }
    }

}
