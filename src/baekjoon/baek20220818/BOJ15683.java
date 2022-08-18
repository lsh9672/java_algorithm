package baekjoon.baek20220818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ15683 {

    static int N;
    static int M;
    static String[][] maps;
    static int result;

    //방향 - 상부터 90도 회전 -> 상 우 하 좌
    static int[] dx = {-1,0,1, 0};
    static int[] dy = { 0,1, 0,-1};

    //감시카메라의 위치 담기.
    static List<Location> cameraLoc;

    //각 감시카메라마다의 초기 상태
    static Map<Integer,Integer[]> initDir;

    //각 감시카메라마다 90도 회전 회수
    static Map<Integer,Integer> rotateNum;

    //2차원 배열을 복사하는 함수
    static String[][] arrayCopy(String[][] tempMaps){
        //맵을 복사해서 새로운 리스트 반환
        String[][] returnArray = new String[N][M];

        for(int i = 0; i < N; i++){
            System.arraycopy(tempMaps[i],0,returnArray[i],0,M);
        }

        return returnArray;
    }

    //현재의 사각지대 영역을 구함.
    static int BlindSpotCount(String[][] tempMaps){

        int count = 0;

        for(int i = 0; i< N; i++){
            for(int j = 0; j < M; j++){
                if(tempMaps[i][j].equals("0")){
                    count++;
                }
            }
        }

        return count;
    }

    //해당 위치와, 방향과 지도를 받아서 감시받는 영역을 전부 #으로 바꿔줌
    static void changeMap(String[][] tempMaps, Location loc, int rotateCount){


        //방향을 하나씩 꺼냄
        for(int dir : initDir.get(Integer.parseInt(tempMaps[loc.x][loc.y]))){
            dir = (dir + rotateCount) % 4;
            int count = 1;
            while(true){
                int nextX = loc.x + dx[dir] * count;
                int nextY = loc.y + dy[dir] * count;

                if(nextX >= 0 && nextX < N && nextY >=0 && nextY < M){
                    if(tempMaps[nextX][nextY].equals("0")){
                        tempMaps[nextX][nextY] = "#";
                    }
                    //벽이면 종료
                    else if(tempMaps[nextX][nextY].equals("6")){
                        break;
                    }
                }
                else{
                    break;
                }

                count++;
            }
        }

    }

    //각 씨씨티비의 방향 정하기. - 재귀 이용
    //하나의 씨씨티비의 방향을 정하고, 해당방향이 빈공간을 전부 #으로 만들고 재귀 호출
    static void dirSet(String[][] tempMaps,int count){
        //모든 카메라의 방향을 정했다면, 전체 사각 지대의 영역을 구함.
        if(count >= cameraLoc.size()){
            int tempCount = BlindSpotCount(tempMaps);
            if(result > tempCount){
                result = tempCount;

            }
            return;
        }

        Location location = cameraLoc.get(count);
        for(int i = 0; i < rotateNum.get(location.cameraNum); i++){
            //맵을 복사한 후에, 메서드를 호출해서 영역을 #으로 바꿈
            String[][] temp2Maps = arrayCopy(tempMaps);
            changeMap(temp2Maps,location,i);

            dirSet(temp2Maps,count+1);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new String[N][M];

        //맵 입력받기
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j< M; j++){
                maps[i][j] = st.nextToken();
            }
        }

        //카메라의 위치를 담을 리스트
        cameraLoc = new ArrayList<>();

        //각 카메라의 위치와 번호 저장.
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                //카메라는 1~5
                if(maps[i][j].equals("1")){
                    cameraLoc.add(new Location(i,j,1));
                }
                else if(maps[i][j].equals("2")){
                    cameraLoc.add(new Location(i,j,2));
                }
                else if(maps[i][j].equals("3")){
                    cameraLoc.add(new Location(i,j,3));
                }
                else if(maps[i][j].equals("4")){
                    cameraLoc.add(new Location(i,j,4));
                }
                else if(maps[i][j].equals("5")){
                    cameraLoc.add(new Location(i,j,5));
                }
            }
        }

        result = Integer.MAX_VALUE;

        //각 카메라마다 나올수 있는 초기 상태 저장.
        initDir = new HashMap<>();

        //각 카메라의 회전 횟수
        rotateNum = new HashMap<>();

        //각 카메라의 초기 방향들과, 가능한 회전 횟수 저장.
        for(int i = 1; i <= 5; i++){
            if(i == 1){
                initDir.put(i,new Integer[]{0});
                rotateNum.put(i,4);
            }
            else if(i==2){
                initDir.put(i,new Integer[]{1,3});
                rotateNum.put(i,2);
            }
            else if(i==3){
                initDir.put(i,new Integer[]{0,1});
                rotateNum.put(i,4);
            }
            else if(i==4){
                initDir.put(i,new Integer[]{0,1,3});
                rotateNum.put(i,4);
            }
            else{
                initDir.put(i,new Integer[]{0,1,2,3});
                rotateNum.put(i,1);
            }
        }

        dirSet(maps,0);

        System.out.println(result);

    }

    static class Location{
        int x;
        int y;
        int cameraNum;

        Location(int x, int y, int cameraNum){
            this.x = x;
            this.y = y;
            this.cameraNum = cameraNum;
        }
    }
}
