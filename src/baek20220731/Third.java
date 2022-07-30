package baek20220731;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Third {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/baek20220731/intput2.txt"));
        Scanner sc = new Scanner(System.in);
        //방향 정의 - 상하좌우;
        int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
        String[] dirShape = {"^","v","<",">"};

        int T = sc.nextInt();
        for(int testCase = 1; testCase <= T; testCase++){


            //높이
            int H = sc.nextInt();
            //넓이
            int W = sc.nextInt();

            String[][] map = new String[H][W];

            for(int i = 0 ; i < H; i++){
                map[i] = sc.next().split("");
            }

            int N = sc.nextInt();
            String commands = sc.next();

            Location currentTank = null;
            //전차위치 찾기
            loop:
            for(int i = 0; i < H; i++){
                for(int j = 0; j < W; j++){
                    if(map[i][j].equals("^")){
                        currentTank = new Location(i,j,0);
                        break loop;
                    }
                    else if(map[i][j].equals("v")){
                        currentTank = new Location(i,j,1);
                        break loop;
                    }
                    else if(map[i][j].equals("<")){
                        currentTank = new Location(i,j,2);
                        break loop;
                    }
                    else{
                        currentTank = new Location(i,j,3);
                        break loop;
                    }
                }
            }

            //명령어 하나씩 실행
            for(int i = 0 ; i < N; i++){
                char command = commands.charAt(i);
                int nextX = currentTank.x;
                int nextY = currentTank.y;

                //방향을 위로 바꾸고 평지면 이동
                if(command == 'U'){
                    currentTank.currentDir = 0;

                    nextX = currentTank.x -1;

                    if(nextX >= 0 && nextX < H && nextY >= 0 && nextY < W && map[nextX][nextY].equals(".")){
                        map[nextX][nextY] = dirShape[currentTank.currentDir];
                        map[currentTank.x][currentTank.y] = ".";
                        currentTank.x = nextX;
                    }
                    else{

                    }


                }
                //방향을 아래로 바꾸고 평지면 이동
                else if(command == 'D'){
                    currentTank.currentDir = 1;
                    nextX = currentTank.x + 1;

                    if(nextX >= 0 && nextX < H && nextY >= 0 && nextY < W && map[nextX][nextY].equals(".")){
                        map[nextX][nextY] = dirShape[currentTank.currentDir];
                        map[currentTank.x][currentTank.y] = ".";
                        currentTank.x = nextX;
                    }
                }
                //왼쪽으로 방향을 바꾸고 평지면 이동
                else if(command == 'L'){
                    currentTank.currentDir = 2;
                    nextY = currentTank.y - 1;

                    if(nextX >= 0 && nextX < H && nextY >= 0 && nextY < W && map[nextX][nextY].equals(".")){
                        map[nextX][nextY] = dirShape[currentTank.currentDir];
                        map[currentTank.x][currentTank.y] = ".";
                        currentTank.y = nextY;
                    }
                }
                //오른쪽으로 방향을 바꾸고 평지면 이동
                else if(command == 'R'){
                    currentTank.currentDir = 3;
                    nextY = currentTank.y + 1;

                    if(nextX >= 0 && nextX < H && nextY >= 0 && nextY < W && map[nextX][nextY].equals(".")){
                        map[nextX][nextY] = dirShape[currentTank.currentDir];
                        map[currentTank.x][currentTank.y] = ".";
                        currentTank.y = nextY;
                    }
                }
                //바라보는 방향으로 포탄 발사
                else{
                    int count = 1;

                    while(true){
                        int tempX = currentTank.x + dir[currentTank.currentDir][0] * count;
                        int tempY = currentTank.y + dir[currentTank.currentDir][1] * count;

                        if(tempX >= 0 && tempX < H && tempY >= 0 && tempY < W){
                            //일반 벽이면 부수고 끝끝
                            if(map[tempX][tempY].equals("*")){
                                map[tempX][tempY] = ".";
                                break;
                            }
                            //강철벽이면 그냥 끝
                            else{
                                break;
                            }
                        }
                        //밖으로 나가면 끝.
                        else{
                            break;
                        }

                    }
                }


            }

            StringBuilder result = new StringBuilder();
            result.append("#").append(testCase).append(" ");

            for(int i = 0; i < H; i++){
                for(int j = 0; j < W; j++){
                    result.append(map[i][j]);
                }
                result.append("\n");
            }

            result.setLength(result.length()-1);

            System.out.println(result);
        }

    }
    static class Location{
        int x;
        int y;
        int currentDir;

        Location(int x, int y, int currentDir){
            this.x = x;
            this.y = y;
            this.currentDir = currentDir;
        }
    }
}
