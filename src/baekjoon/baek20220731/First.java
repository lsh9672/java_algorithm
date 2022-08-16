package baekjoon.baek20220731;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class First {

    static int H;
    static int W;
    static String[][] map;
    //방향 정의 - 상하좌우;
    static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
    static String[] dirShape = {"^","v","<",">"};
    static Map<Character,Integer> dirMapping = new HashMap<>(){{
        put('U',0);
        put('D',1);
        put('L',2);
        put('R',3);
    }};

    static void moveMap(char command, Location currentTank){
        int nextX = 0;
        int nextY = 0;

        if(command == 'U' || command == 'D' || command == 'L' ||command == 'R'){
            currentTank.currentDir = dirMapping.get(command);
            map[currentTank.x][currentTank.y] = dirShape[currentTank.currentDir];

            nextX = currentTank.x + dir[currentTank.currentDir][0];
            nextY = currentTank.y + dir[currentTank.currentDir][1];

            if(nextX >= 0 && nextX < H && nextY >= 0 && nextY < W && map[nextX][nextY].equals(".")){
                map[nextX][nextY] = dirShape[currentTank.currentDir];
                map[currentTank.x][currentTank.y] = ".";
                currentTank.x = nextX;
                currentTank.y = nextY;
            }

        }
        else{
            int count = 1;

            while(true){
                int tempX = currentTank.x + dir[currentTank.currentDir][0] * count;
                int tempY = currentTank.y + dir[currentTank.currentDir][1] * count;

                if(tempX >= 0 && tempX < H && tempY >= 0 && tempY < W){
                    //일반 벽이면 부수고 끝
                    if(map[tempX][tempY].equals("*")){
                        map[tempX][tempY] = ".";
                        break;
                    }
                    //강철벽이면 그냥 끝
                    else if(map[tempX][tempY].equals("#")){
                        break;
                    }
                }
                //밖으로 나가면 끝.
                else{
                    break;
                }
                count++;
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/baekjoon.baek20220731/intput2.txt"));
        Scanner sc = new Scanner(System.in);


        int T = sc.nextInt();
        for(int testCase = 1; testCase <= T; testCase++){


            //높이
            H = sc.nextInt();
            //넓이
            W = sc.nextInt();

            map = new String[H][W];

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
                    else if(map[i][j].equals(">")){
                        currentTank = new Location(i,j,3);
                        break loop;
                    }
                }
            }

            //명령어 하나씩 실행
            for(int i = 0 ; i < N; i++){
                moveMap(commands.charAt(i),currentTank);
            }

            //출력
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
