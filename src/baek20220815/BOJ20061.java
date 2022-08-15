package baek20220815;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 중요 : 연한칸과 꽉찬 블록이 있으면, 우선 블럭이 가득찬게 없을떄까지 먼저 처리후에, 연한칸을 처리해야됨.
 */

public class BOJ20061 {

    static int score;
    static int[][] maps;

    //아래 , 오른쪽 두가지 방향만 정의
    static int[] dx = {1,0};
    static int[] dy = {0,1};


    //파란색과 초록색에 남아있는 블록 수 세기
    static int countBlock(){

        int total = 0;

        //파란색 영역의 블록수 세기
        for(int i = 0; i < 4; i++){
            for(int j = 6; j < 10; j++){
                if(maps[i][j] == 1) total++;
            }
        }

        //초록색 영역의 블록수 세기
        for(int i = 6; i < 10; i++){
            for(int j = 0; j < 4; j++){
                if(maps[i][j] == 1) total++;
            }
        }

        return total;
    }

    //현재 상태에서 점수를 얻을수 있는지 확인하고, 점수를 얻을 수 있으면 얻고 블록 삭제
    static void scoreCheckBlue(){
        //1. 진한색깔 영역해서 한줄이 전부 블럭이라면 삭제

        //1-1 파란색 확인
        //지울 수 있는게 없을때 까지 반복
        for(int i = 9; i >= 6; i--){
            //한줄을 삭제 할 수 있는 지 체크
            boolean flag = true;
            for(int j = 0; j < 4; j++){
                //각 열을 살펴봤을때 0이 하나라도 있으면 그 줄은 삭제 안됨.
                if(maps[j][i] == 0){
                    flag = false;
                    break;
                }
            }
            //한줄 살펴보았는데, true이면 그 줄은 삭제.
            if(flag){
                //삭제시마다 점수획득
                score++;
                //한줄씩 옮김
                for(int x = i - 1; x >= 4; x--){
                    for(int y = 0; y < 4; y++){
                        maps[y][x+1] = maps[y][x];
                    }
                }

                //마지막 줄 0으로 만들기
                for(int fin = 0; fin < 4; fin++){
                    maps[fin][4] = 0;
                }

                //삭제가 이루어졌던 열 부터 다시 탐색하도록 i값을 증가시킴
                i++;
            }
        }

        //2. 연한 색깔 확인
        //한줄씩 확인함
        for(int i = 5; i >=4; i--){
            boolean checkFlag = true;
            for(int j = 0; j < 4; j++){
                if(maps[j][i] != 0){
                    checkFlag = false;
                    break;
                }

            }

            //한 열에 하나라도 값이 있었다면 한줄 삭제하고 땡기기
            if(!checkFlag){
//                score++;
                //한줄씩 옮김
                for(int x = 8; x >= 4; x--){
                    for(int y = 0; y < 4; y++){
                        maps[y][x+1] = maps[y][x];
                    }
                }

                //마지막 줄 0으로 만들기
                for(int fin = 0; fin < 4; fin++){
                    maps[fin][4] = 0;
                }

                i++;
            }
        }

    }

    static void scoreCheckGreen(){
        //1-2 초록색 확인.
        for(int i = 9; i >= 6; i--){
            //한줄을 삭제 할 수 있는 지 체크
            boolean flag = true;
            for(int j = 0; j < 4; j++){
                //각 행을 살펴봤을때 0이 하나라도 있으면 그 줄은 삭제 안됨.
                if(maps[i][j] == 0){
                    flag = false;
                    break;
                }
            }
            //한줄 살펴보았는데, true이면 그 줄은 삭제.
            if(flag){
                //삭제시마다 점수획득
                score++;
                //한줄씩 옮김
                for(int x = i - 1; x >= 4; x--){
                    for(int y = 0; y < 4; y++){
                        maps[x+1][y] = maps[x][y];
                    }
                }

                //마지막 줄 0으로 만들기
                for(int fin = 0; fin < 4; fin++){
                    maps[4][fin] = 0;
                }

                //삭제가 이루어졌던 행 부터 다시 탐색하도록 i값을 증가시킴
                i++;
            }
        }

        //2. 연한 색깔 확인
        //한줄씩 확인함
        for(int i = 5; i >=4; i--){
            boolean checkFlag = true;
            for(int j = 0; j < 4; j++){
                if(maps[i][j] != 0){
                    checkFlag = false;
                    break;
                }

            }
            //한 행에 하나라도 값이 있었다면 한줄 삭제하고 땡기기
            if(!checkFlag){
                //한줄씩 옮김
//                score++;
                for(int x = 8; x >= 4; x--){
                    for(int y = 0; y < 4; y++){
                        maps[x+1][y] = maps[x][y];
                    }
                }

                //마지막 줄 0으로 만들기
                for(int fin = 0; fin < 4; fin++){
                    maps[4][fin] = 0;
                }

                i++;
            }
        }

    }

    //빨간줄에 블럭넣고 초록, 파란으로 이동
    static void setAndUpdateBlock(int t, int x, int y){
        //t의 값에 따라 블럭이 달라짐.

        //1개짜리라면 x,y좌표 하나만 가지고 보면 됨.
        if(t == 1){
            //하- 초록색, 우 - 파란색
            for(int dir = 0; dir < 2; dir++){

                //최대 9칸까지 이동가능 - 각 칸을 확인하면서 놓을수 있는지 확인
                for(int i = 1; i <= 10; i++){
                    int nextX = x + dx[dir]*i;
                    int nextY = y + dy[dir]*i;

                    //해당좌표가 게임판을 벗어났거나 이미 있는 위치(1이 들어있음)라면 그 전위치에 블럭을 놓음.
                    if(nextX < 0 || nextX >= 10 || nextY < 0 || nextY >= 10 || maps[nextX][nextY] == 1){
                        //블럭 놓고 종료
                        maps[x + dx[dir] * (i-1)][y + dy[dir] * (i-1)] = 1;
                        break;
                    }

                }
            }
        }
        else if(t == 2){
            for(int dir = 0; dir < 2; dir++){

                //최대 9칸까지 이동가능 - 각 칸을 확인하면서 놓을수 있는지 확인
                for(int i = 1; i <= 10; i++){
                    int nextX = x + dx[dir]*i;
                    int nextY = y + dy[dir]*i;

                    //해당좌표가 게임판을 벗어났거나 이미 있는 위치(1이 들어있음)라면 그 전위치에 블럭을 놓음.
                    //t=2인 경우에는 y+1값도 확인해야 됨.
//                    System.out.println("dir : "+ dir + ", nextX : " + nextX + ", nextY : " + nextY);
                    if(dir == 0){
                        if(nextX < 0 || nextX >= 10 || nextY < 0 || nextY >= 10 || maps[nextX][nextY] == 1 || maps[nextX][nextY+1] == 1){
                            //블럭 놓고 종료
                            maps[x + dx[dir] * (i-1)][y + dy[dir] * (i-1)] = 1;
                            maps[x + dx[dir] * (i-1)][y + dy[dir] * (i-1) + 1] = 1;
                            break;
                        }
                    }

                    else if(dir == 1){
                        if(nextX < 0 || nextX >= 10 || nextY < 0 || nextY >= 9 || maps[nextX][nextY] == 1 || maps[nextX][nextY+1] == 1){
                            //블럭 놓고 종료
                            maps[x + dx[dir] * (i-1)][y + dy[dir] * (i-1)] = 1;
                            maps[x + dx[dir] * (i-1)][y + dy[dir] * (i-1) + 1] = 1;
                            break;
                        }
                    }


                }
            }
        }
        else{
            for(int dir = 0; dir < 2; dir++){

                //최대 9칸까지 이동가능 - 각 칸을 확인하면서 놓을수 있는지 확인
                for(int i = 1; i <= 10; i++){
                    int nextX = x + dx[dir]*i;
                    int nextY = y + dy[dir]*i;

                    //해당좌표가 게임판을 벗어났거나 이미 있는 위치(1이 들어있음)라면 그 전위치에 블럭을 놓음.
                    //t=2인 경우에는 y+1값도 확인해야 됨.
                    if(dir == 0){
                        if(nextX < 0 || nextX >= 9 || nextY < 0 || nextY >= 10 || maps[nextX][nextY] == 1 || maps[nextX+1][nextY] == 1){
                            //블럭 놓고 종료
                            maps[x + dx[dir] * (i-1)][y + dy[dir] * (i-1)] = 1;
                            maps[x + dx[dir] * (i-1) + 1][y + dy[dir] * (i-1)] = 1;
                            break;
                        }
                    }
                    else if(dir == 1){
//                        System.out.println("dir : "+ dir + ", nextX : " + nextX + ", nextY : " + nextY);

                        if(nextX < 0 || nextX >= 10 || nextY < 0 || nextY >= 10 || maps[nextX][nextY] == 1 || maps[nextX+1][nextY] == 1){
                            //블럭 놓고 종료
                            maps[x + dx[dir] * (i-1)][y + dy[dir] * (i-1)] = 1;
                            maps[x + dx[dir] * (i-1) + 1][y + dy[dir] * (i-1)] = 1;
                            break;
                        }
                    }


                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        //놓은 불럭 수
        int N = Integer.parseInt(br.readLine());

        int t = 0;
        int x = 0;
        int y = 0;

        maps = new int[10][10];
        score = 0;

        //놓아야 되는 블럭
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            t = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());


            //블록 놓고 이동시키기
            setAndUpdateBlock(t,x,y);

            //파란색 업데이트
            scoreCheckBlue();

            //초록색 업데이트
            scoreCheckGreen();


        }

        //점수 출력
        System.out.println(score);

        //타일이 들어있는 칸의 수
        System.out.println(countBlock());

    }
}
