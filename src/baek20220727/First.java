package baek20220727;

/**
 * 백준 1347번 (역테 대비)
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class First {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 방향 - 왼 : 1 , 위 : 2 , 오 : 3, 아래 : 4
        int[][] dir = {{0,-1},{-1,0},{0,1},{1,0}};

        // 이동횟수
        int N = Integer.parseInt(br.readLine());
        String inputLoc = br.readLine();

        int xMax = 50;
        int xMin = 50;

        int yMax = 50;
        int yMin = 50;

        String[][] map = new String[101][101];

        int currentDir = 3;

        int currentX = 50;
        int currentY = 50;

        map[currentX][currentY] = ".";

        for(int i = 0; i < N; i++) {
            char temp = inputLoc.charAt(i);

            //R 오른쪽으로 90도
            if(temp == 'R') {
                currentDir = (currentDir+1) % 4;
            }
            //L
            else if(temp == 'L') {

                currentDir = (currentDir-1);

                if(currentDir == -1) currentDir = 3;
            }
            //F - 전진
            else{
                currentX += dir[currentDir][0];
                currentY += dir[currentDir][1];

                xMax = Math.max(xMax, currentX);
                xMin = Math.min(xMin, currentX);

                yMax = Math.max(yMax, currentY);
                yMin = Math.min(yMin, currentY);

                map[currentX][currentY] = ".";


            }
        }




        StringBuilder result = new StringBuilder();
        for(int i = xMin; i <= xMax; i++) {
            for(int j = yMin; j <= yMax; j++) {

                if(map[i][j] == null) {
                    result.append("#");
                }
                else {
                    result.append(map[i][j]);
                }


            }
            result.append("\n");
        }

        result.setLength(result.length()-1);
        System.out.println(result);
    }

}
