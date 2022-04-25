package baek20220425;

/**
 * 백준 8911번(자바, 구현연습)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class First {

    static int xMin = 0;
    static int xMax = 0;

    static int yMin = 0;
    static int yMax = 0;

    static void maxAndMin(int x, int y){
        xMin = Math.min(xMin,x);
        xMax = Math.max(xMax,x);

        yMin = Math.min(yMin,y);
        yMax = Math.max(yMax,y);
    }

    static void maxAndMinInit(){
        xMax = 0;
        xMin = 0;

        yMin = 0;
        yMax = 0;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test_case = Integer.parseInt(br.readLine());

        //0: 상, 1: 좌, 2: 하, 3: 우,
        Integer[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};

        StringBuilder result = new StringBuilder();
        for(int i = 0; i < test_case; i++){
            String commands = br.readLine();

            maxAndMinInit();

            int dirCount = 0;

            int startX = 0;
            int startY = 0;

            for(int j = 0; j < commands.length(); j++){
                char command = commands.charAt(j);
                //전진
                if(command == 'F'){
                    startX += dir[dirCount][0];
                    startY += dir[dirCount][1];
                }
                //후진
                else if(command == 'B'){
                    startX -= dir[dirCount][0];
                    startY -= dir[dirCount][1];
                }
                //왼쪽으로 90도 회전
                else if(command == 'L'){
                    dirCount -= 1;

                    if(dirCount == -1){
                        dirCount = 3;
                    }
                }
                //오른쪽으로 90도 회전
                else if(command == 'R'){
                    dirCount += 1;

                    if(dirCount == 4){
                        dirCount = 0;
                    }
                }

                maxAndMin(startX,startY);

            }
//            System.out.println("xMax : " + xMax + " xMin : " + xMin + " yMax : " + yMax + " yMin : " + yMin);

            result.append((xMax - xMin) * (yMax - yMin)).append("\n");

        }
        result.setLength(result.length() -1);
        System.out.println(result);
    }
}
