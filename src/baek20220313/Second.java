package baek20220313;
/*
* 백준 11913번 달팽이(구현연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Second {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //방향을 좌표로 만들어줌
        //0: 위, 1:오른쪽, 2:아래,3: 왼쪽
        Integer[][] direction = {{-1,0},{0,1},{1,0},{0,-1}};

        int n = Integer.parseInt(br.readLine());

        int resultValue = Integer.parseInt(br.readLine());

        //0으로 채운 2차원 배열
        Integer[][] snail = new Integer[n][n];

        //시작 좌표
        int currentX = (n-1)/2;
        int currentY = (n-1)/2;

        snail[currentX][currentY] = 1;

        int direction_count = 0;

        int moveCount = 1;

        int resultX = 0;
        int resultY = 0;

        //1,1,2,2,3,3,4,4가 반복됨.
        loop:
        while(true){


            for(int i = 0 ; i< moveCount ; i++){
                int prevX = currentX;
                int prevY = currentY;
                currentX += direction[direction_count][0];
                currentY += direction[direction_count][1];

                snail[currentX][currentY] = snail[prevX][prevY] + 1;
                if(snail[prevX][prevY] == resultValue){
                    resultX = prevX;
                    resultY = prevY;
                }
                if(currentX == 0 && currentY == 0){
                    break loop;
                }
            }

            direction_count++;
            if(direction_count == 4) direction_count = 0;


            for(int i = 0 ; i< moveCount ; i++){
                int prevX = currentX;
                int prevY = currentY;
                currentX += direction[direction_count][0];
                currentY += direction[direction_count][1];

                snail[currentX][currentY] = snail[prevX][prevY] + 1;
                if(snail[prevX][prevY] == resultValue){
                    resultX = prevX;
                    resultY = prevY;
                }
                if(currentX == 0 && currentY == 0){
                    break loop;
                }
            }
            direction_count++;
            if(direction_count == 4) direction_count = 0;

            moveCount++;
        }

        for (Integer[] integers : snail) {
            StringBuilder sb = new StringBuilder();
            for (int k = 0; k< n; k++){
                sb.append(integers[k]).append(" ");
            }
            System.out.println(sb);
        }

        System.out.println((resultX+1) +" "+(resultY+1));

    }
}
