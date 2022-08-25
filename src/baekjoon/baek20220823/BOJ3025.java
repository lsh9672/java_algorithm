package baekjoon.baek20220823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 알게된 메모이제이션으로 시간 줄이는 법
 * 매번 처음부터 놓을 공간을 탐색하는 것이 아니라, 스택에 미리 이동이 가능한 공간들을 누적시켜둠.
 */

public class BOJ3025 {

    static int R;
    static int C;
    //하좌우 왼쪽아래대각선, 오른쪽 아래대각선
    static int[] dx = {1,0,0,1,1};
    static int[] dy = {0,-1,1,-1,1};
    static char[][] maps;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        maps = new char[R][C];

        for(int i = 0; i < R; i++) {
            maps[i] = br.readLine().toCharArray();
        }

        //각 열마다 스택을 만들어서 다음에 놓을 위치를 저장 시킴
        Stack<Location>[] nextLocStack = new Stack[C];

        for(int i = 0 ; i < C; i++){
            nextLocStack[i] = new Stack<>();
        }

        // 날아올 화산탄의 수
        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {

            // 인덱스를 0부터 쓰기 위해 -1을 해줌.
            int col = Integer.parseInt(br.readLine()) - 1;

            /**큐를 확인해서 다음 놓을 위치가 있다면 놓음**/

            // 스택이 비어있지 않다면
            if(!nextLocStack[col].isEmpty()){

            }
            // 스택이 비어있다면
            else{
                // 화산탄이 떨어진 위치를 초기위치로 잡음.
                int currentX = 0;
                int currentY = col;

                while(true) {
                    //현재위치가 놓을수 있는 위치라면 스택에 저장.
                    if(maps[currentX][currentY] == '.')
                        nextLocStack[col].add(new Location(currentX,currentY));

                    // 다음위치 확인을 위해 잠시 변수에 저장.
                    int nextX = currentX + dx[0];
                    int nextY = currentY;

                    // 장애물로 막혀있거나
                    if(maps[nextX][nextY] == 'X') {
                        maps[currentX][currentY] = 'O';
                        break;
                    }
                    // 맨 아래칸이고 비어있다면,
                    else if(nextX == R-1 && maps[nextX][nextY] == '.') {
                        maps[nextX][nextY] = 'O';
                        break;
                    }

                    //화산탄이면
                    else if(maps[nextX][nextY] == 'O') {

                        //왼쪽과, 왼쪽아래칸 확인
                        if(currentY+dy[1]>= 0 && maps[currentX + dx[1]][currentY + dy[1]] == '.' && maps[currentX + dx[3]][currentY + dy[3]] == '.') {

                            //화산탄의 위치 업데이트
                            currentX = currentX + dx[3];
                            currentY = currentY + dy[3];

                            // 이동한 화산탄의 위치가 마지막줄이라면 그대로 굳어버림.
                            if(currentX == R-1) {
                                maps[currentX][currentY] = 'O';
                                break;
                            }

                        }

                        // 왼쪽아래칸으로 갈수 없다면 오른쪽과 오른쪽 아래칸 확인.
                        else if(currentY+dy[2] < C && maps[currentX + dx[2]][currentY + dy[2]] == '.' && maps[currentX + dx[4]][currentY + dy[4]] == '.'){

                            //화산탄의 위치 업데이트
                            currentX = currentX + dx[4];
                            currentY = currentY + dy[4];

                            // 이동한 화산탄의 위치가 마지막줄이라면 그대로 굳어버림.
                            if(currentX == R-1) {
                                maps[currentX][currentY] = 'O';
                                break;
                            }

                        }
                        // 둘다 아니면 현재위치에 멈춤
                        else {
                            maps[currentX][currentY] = 'O';
                            break;
                        }
                    }

                    // 빈공간이면 이동
                    else if(maps[nextX][nextY] == '.'){
                        currentX = nextX;
                        currentY = nextY;
                    }
                }

            }



        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                sb.append(maps[i][j]);
            }
            sb.append("\n");
        }
        sb.setLength(sb.length()-1);
        System.out.println(sb);


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