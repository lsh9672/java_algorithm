package baekjoon.baek20220815;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14503 {

    static int N;
    static int M;
    //상,우,하,좌
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int[][] maps;


    //로봇의 방향
    static int currentDir;

    //로봇의 위치
    static Location currentLoc;

    //청소칸
    static int count;


    //청소 했으면 -1
    static void robotClean(){


        boolean backCheck = true;
        while(true){
            //1. 현재위치를 청소한다.


            if(backCheck){
                maps[currentLoc.x][currentLoc.y] = -1;
                count++;
            }

            //2. 왼쪽방향 탐색
            //2-1. 왼쪽방향에 청소하는 공간이 존재한다면 한칸 이동하면
            //회전
            boolean flag = true;
            backCheck = true;
            for(int i = 0; i < 4; i++){
                currentDir--;
                if(currentDir < 0) currentDir = 3;

                //만약 해당 방향에 벽이 아니고 쓰레기가 있다면 이동
                int nextX = currentLoc.x + dx[currentDir];
                int nextY = currentLoc.y + dy[currentDir];

                //칸을 벗어나지 않았고 청소할곳이면 이동
                if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && maps[nextX][nextY] == 0){
                    currentLoc.x = nextX;
                    currentLoc.y = nextY;

                    flag = false;
                    break;
                }
            }

            //4방향 다 돌았는데 갈곳이 없다면
            if(flag){
                //후진 여부 확인해서 후진하기
                int backX = currentLoc.x + dx[currentDir] * (-1);
                int backY = currentLoc.y + dy[currentDir] * (-1);

                //후진 가능한 곳인지 확인
                if(backX >= 0 && backX < N && backY >= 0 && backY < M && maps[backX][backY] != 1){
                    //가능한 곳이면 현재 위치 업데이트(방향은 그대로)
                    currentLoc.x = backX;
                    currentLoc.y = backY;
                    backCheck = false;
                }
                //갈 수 없는 곳이라면,
                else{
                    //종료
                    break;
                }
            }

        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        st = new StringTokenizer(br.readLine());
        currentLoc = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        currentDir = Integer.parseInt(st.nextToken());

        maps = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        count = 0;

        robotClean();

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
