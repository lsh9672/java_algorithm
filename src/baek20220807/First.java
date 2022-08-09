package baek20220807;

/**
 * BOJ 17825번
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class First {


    static int result;

    static int maxN = 10;
    //주사위 눈금
    static int[] diceArray;
    //기물의 순서
    static int[] orderArray;
    //현재위치를 저장할 배열 1부터 시작
    static Location[] currentLoc;

    //이렇게 나눠두면 중복되는 위치의 경우 체크해야됨, 도착지점 부분의 25(9,13,19), 30(10,14,20), 35(11,15,21) ,40(20,12,16,22)
    //분기지역(파란색 화살표로 바뀌는 지점) - 10(5- 0,1),20(10 - 0,2),30(15 - 0,3)
    static int[][] playMap = {
            {0, 2, 4, 6, 8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,-1,-1,-1,-1,-1,-1,-1},
            {0, 0, 0, 0, 0,10,13,16,19,25,30,35,40,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,20,22,24,25,30,35,40,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,30,28,27,26,25,30,35,40,-1,-1,-1,-1,-1}
    };

    static boolean[][] check;

    //4개의 말로 10번 움직이는 경우의 수
    static void playOrder(int idx){
        if(idx >= maxN){

            /*순서대로 게임 말 놓는 로직 작성*/
            move();
            return;
        }

        for(int i = 1; i <= 4; i++){
            orderArray[idx] = i;
            playOrder(idx+1);
        }
    }

    static void move(){

        //방문처리 배열 초기화.
        check = new boolean[28][28];

        //1~4번 말의 위치를 초기화.
        for(int i = 1; i <= 4; i++){
            currentLoc[i] = new Location(0,0);
        }

        //점수 누적
        int totalScore = 0;

        for(int i = 0; i < 10; i++){
            //눈금
            int tempDice = diceArray[i];
            //기물 번호
            int tempHorse = orderArray[i];

            //선택한 기물이 이미 끝난 기물이라면 다음 탐색
            if(currentLoc[tempHorse].x == -1 && currentLoc[tempHorse].y == -1){
                return;
            }

            /*로직*/
            //숫자로 분기지점이지 확인하면 곂치는 부분이 존재하기 때문에 인덱스로 확인해야됨.

            //1. 눈금 많큼 기물을 이동시킬 좌표
            int nextX = currentLoc[tempHorse].x;
            int nextY = currentLoc[tempHorse].y + tempDice;

//            //이동시킨 위치의 값이 -1이거나, 게임판을 벗어나는 좌표 또는 이미 해당위치에 기물이 있다면,
//            if(nextY >= 28 || playMap[nextX][nextY] == -1 || check[nextX][nextY]) {
//                ;
//            }

            //위의 조건에 걸리지 않으면 말을 놓을 수 있다는 뜻.
            //말을 놓을때는 위치를 확인해야됨.
            boolean startFlag = false; // 기물을 이동했을때 밖으로 나갔는지 확인 - 이 경우에는 현재 위치만 false로 만들고 이동위치는 방문처리를 할 필요가 없어짐.
            //도착점 밖으로 나가지 않는다면
            if(nextY < 28 && playMap[nextX][nextY] != -1){

                //기물이동이 가능하면,
                if(!check[nextX][nextY]){
                    /*분기점 체크 - 행을 바꿔줘야 됨.*/
                    //10
                    if(nextX == 0 && nextY == 5){
                        check[0][nextY] = true;
                        check[1][nextY] = true;
                        //이전위치 체크 해제
//                check[currentLoc[tempHorse].x][currentLoc[tempHorse].y] = false;
                        nextX = 1;
                    }

                    //20
                    else if(nextX== 0 && nextY == 10){
                        check[0][nextY] = true;
                        check[2][nextY] = true;
//                check[currentLoc[tempHorse].x][currentLoc[tempHorse].y] = false;
                        nextX = 2;
                    }
                    //30
                    else if(nextX == 0 && nextY == 15){
                        check[0][nextY] = true;
                        check[3][nextY] = true;
//                check[currentLoc[tempHorse].x][currentLoc[tempHorse].y] = false;
                        nextX = 3;
                    }
                    //여기서도 생각해야하는게 25,30,35,40 => 이것들의 경우에도 여러 행에 퍼져있기 때문에 전부 처리해야 한다.
                    //이 분기문 아래에 있는 조건들은 현재 위치를 false해줄때 여러 경우를 따져줘야 한다.
                    //이 경우들은 위치를 바꿔줄 필요는 없다.

                    //25(9,13,19), 30(10,14,20), 35(11,15,21) ,40(20,12,16,22)
                    else if(playMap[nextX][nextY] == 25){
                        check[1][9] = true;
                        check[2][13] = true;
                        check[3][19] = true;
                    }
                    else if(playMap[nextX][nextY] == 30){
                        check[1][10] = true;
                        check[2][14] = true;
                        check[3][20] = true;

                    }
                    else if(playMap[nextX][nextY] == 35){
                        check[1][11] = true;
                        check[2][15] = true;
                        check[3][21] = true;

                    }
                    else if(playMap[nextX][nextY] == 40){
                        check[0][20] = true;
                        check[1][12] = true;
                        check[2][16] = true;
                        check[3][22] = true;

                    }
                    //이를 제외한 나머지 경우는 한군데만 존재하기 때문에 그냥 처리
                    else{
                        check[nextX][nextY] = true;
                    }
                }
                //기물이동이 불가능
                else{
                    return;
                }

            }
            //도착점 밖으로 나갔다면.
            else{
                startFlag = true;
            }
            //이제 현재 위치를 전부 false처리해야됨.
            //이동을 했다면 처리.

            if((currentLoc[tempHorse].x == 0 || currentLoc[tempHorse].x == 1) && currentLoc[tempHorse].y == 5){
                check[0][currentLoc[tempHorse].y] = false;
                check[1][currentLoc[tempHorse].y] = false;
            }
            else if((currentLoc[tempHorse].x == 0 || currentLoc[tempHorse].x == 2) && currentLoc[tempHorse].y == 10){
                check[0][currentLoc[tempHorse].y] = false;
                check[2][currentLoc[tempHorse].y] = false;
            }
            else if((currentLoc[tempHorse].x == 0 || currentLoc[tempHorse].x == 3) && currentLoc[tempHorse].y == 15){
                check[0][currentLoc[tempHorse].y] = false;
                check[3][currentLoc[tempHorse].y] = false;
            }
            else if(playMap[currentLoc[tempHorse].x][currentLoc[tempHorse].y] == 25){
                check[1][9] = false;
                check[2][13] = false;
                check[3][19] = false;
            }
            else if(playMap[currentLoc[tempHorse].x][currentLoc[tempHorse].y] == 30){
                check[1][10] = false;
                check[2][14] = false;
                check[3][20] = false;
            }
            else if(playMap[currentLoc[tempHorse].x][currentLoc[tempHorse].y] == 35){
                check[1][11] = false;
                check[2][15] = false;
                check[3][21] = false;
            }
            else if(playMap[currentLoc[tempHorse].x][currentLoc[tempHorse].y] == 40){
                check[0][20] = false;
                check[1][12] = false;
                check[2][16] = false;
                check[3][22] = false;
            }
            else{
                check[currentLoc[tempHorse].x][currentLoc[tempHorse].y] = false;
            }

            if(!startFlag){
                currentLoc[tempHorse].x = nextX;
                currentLoc[tempHorse].y = nextY;
                totalScore += playMap[currentLoc[tempHorse].x][currentLoc[tempHorse].y];
            }
            else{
                currentLoc[tempHorse].x = -1;
                currentLoc[tempHorse].y = -1;
            }
        }
        result = Math.max(result,totalScore);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        diceArray = new int[10];

        //주사위 눈금 10개 입력받기
        for(int i = 0; i < 10; i++){
            diceArray[i] = Integer.parseInt(st.nextToken());
        }

        //기물 순서 저장.
        orderArray = new int[10];

        //최대 값을 구하기 위해 최소 값 저장
        result = Integer.MIN_VALUE;

        //순서 초기화 - 인덱스 1부터 쓰기위해 크기는 5로 생성
        currentLoc = new Location[5];

        //모든 말의 시작위치는 0,0;

        playOrder(0);

        System.out.println(result);

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
