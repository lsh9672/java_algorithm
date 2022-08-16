package baekjoon.baek20220804;

/**
 * 백준 1941번
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;


//25C7  = 48만정도라 경우의 수 다 뽑아서 7개가 되면 조건에 맞는지 확인하는 식으로 구
public class First {

    static String[][] map;
    static int[] princess;
    static int count;

    //상하좌우
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    //나온 좌표로 빈 5*5에 올려두고 계산해보기
    static void checkPrincess(){

        boolean[][] visited = new boolean[5][5];

        String[][] tempMap = new String[5][5];
        //저장된 좌표를 빈 좌표에
        for(int temp: princess){
            tempMap[temp/5][temp%5] = map[temp/5][temp%5];
        }

        Queue<Location> needVisited = new ArrayDeque<>();

        needVisited.add(new Location(princess[0]/5,princess[0]%5));

        visited[princess[0]/5][princess[0]%5] = true;

        int tempCount = 1;
        int tempCountS = 0;

        while(!needVisited.isEmpty()){

            Location currentNode = needVisited.poll();
            if(map[currentNode.x][currentNode.y].equals("S")){
                tempCountS++;
            }

            for(int i = 0; i < 4; i++){

                int nextX = currentNode.x + dx[i];
                int nextY = currentNode.y + dy[i];

                if(nextX >= 0 && nextX < 5 && nextY >= 0 && nextY < 5 && visited[nextX][nextY] == false && tempMap[nextX][nextY] != null){

                    visited[nextX][nextY] = true;
                    needVisited.add(new Location(nextX,nextY));
                    tempCount++;

                }
            }


        }
//        System.out.println("test111111111 : " + tempCount);
        if(tempCount == 7&& tempCountS >=4){
            count++;
        }

    }

    //재귀로 좌표 7개 뽑아내기
    static void recursive(int loc ,int idx){

        //basis part
        if(idx >= 7){
            checkPrincess();

            return;
        }

        if(loc == 25) return;

        //해당 좌표를 선택하는 경우
        princess[idx] = loc;
        recursive(loc+1,idx+1);

        //선택하지 않는 경우
        recursive(loc+1,idx);

    }

    //좌표를 0~24로 변환한다. => 24이면 24/5 = 4 , 24%5 = 4 => 4,4
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //자리 배치도 입력받기
        map = new String[5][5];
        for(int i = 0; i< 5; i++){
            map[i] = br.readLine().split("");
        }

        //배열 7개 초기화
        princess = new int[7];

        //경우의 수 세기
        count = 0;

        recursive(0,0);
        System.out.println(count);

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
