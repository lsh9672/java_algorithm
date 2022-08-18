package baekjoon.baek20220818;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//1. 2차원 배열을 상어번호와 냄새 값을 가지는 객체로 만듦
//2. 모든 상어를 우선순위에 따라 움직임
//3. 상어 움직였을때 겹쳤다면 숫자가 큰 상어를 삭제
//4. 이동이 끝나면 맵을 돌면서 냄새를 감소시킴.

public class BOJ19237 {

    static int N;
    static int M;
    static int k;
    static MapInfo[][] maps;

    //상하좌우 - 인덱스를 1부터 쓰기위해서 0번째는 0으로 둠
    static int[] dx = {0,-1,1, 0,0};
    static int[] dy = {0, 0,0,-1,1};

    //상어의 위치배열, 해당 객체에 상어위치, 방향, 방향의 우선순위들을 넣어둠.
    static SharkInfo[] sharkLoc;

    //1번만 남았는지 확인.
    static boolean check(){

        int count = 0;
        for(int i = 1; i <= M; i++){
            if(sharkLoc[i] != null){
                count++;
            }
        }

        //1번만 있다면,
        if(count == 1) return true;
        return false;
    }

    //번호가 큰것 부터 맵에 넣음 - 만약 겹치면 ,sharkLoc도 업데이트 해야됨.
    static void removeShark(Location[] tempSave){

        for(int i = tempSave.length-1; i >= 1; i--){
            //null이 아니면 맵에 상어를 놓고, sharkLoc을 업데이트 함.
            if(tempSave[i] != null){
                //놓으려고 하는 해당 위치가 0이거나 같은 숫자이면 그대로 놓음
                if (maps[tempSave[i].x][tempSave[i].y].sharkNum == 0 || maps[tempSave[i].x][tempSave[i].y].sharkNum == i){
                    maps[tempSave[i].x][tempSave[i].y].sharkNum = i;
                    maps[tempSave[i].x][tempSave[i].y].smallCount = k;
                    sharkLoc[i].x = tempSave[i].x;
                    sharkLoc[i].y = tempSave[i].y;
                    sharkLoc[i].dir = tempSave[i].dir;
                }
                //놓으려고 하는 해당위치에 더 큰 번호의 상어가 있다면 덮어버림
                else if(maps[tempSave[i].x][tempSave[i].y].sharkNum > i){
                    //맵에 덮기 전에 밖으로 추방
                    sharkLoc[maps[tempSave[i].x][tempSave[i].y].sharkNum] = null;
                    sharkLoc[i].x = tempSave[i].x;
                    sharkLoc[i].y = tempSave[i].y;
                    sharkLoc[i].dir = tempSave[i].dir;

                    maps[tempSave[i].x][tempSave[i].y].sharkNum = i;
                    maps[tempSave[i].x][tempSave[i].y].smallCount = k;

                }
            }
        }

    }

    //냄새 업데이트
    static void smallUpdate() {
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(maps[i][j].smallCount > 0){
                    maps[i][j].smallCount--;
                    //냄새가 0이 되면 빈칸으로 만듦
                    if(maps[i][j].smallCount == 0){
                        maps[i][j].sharkNum = 0;
                    }
                }
            }
        }

    }

    //상어 이동메서드
    static void sharkMove() {

        //상어가 이동가능한 좌표들을 저장해둠.
        Location[] tempSave = new Location[M+1];

        //1. 1번~4번 상어가 각각 빈칸을 먼저 찾음
        //2. 빈칸이 없으면, 주어진 우선순위에 따라 회전하는데, 자신의 냄새라면 이동
        for(int i = 1; i <= M; i++){

            SharkInfo sharkInfo = sharkLoc[i];

            //null이면 상어퇴출당한거, null이 아닐때만 실행.
            if(sharkInfo != null){
                //먼저 4방향을 탐색하면서 빈칸을 찾음
                boolean flag = true;
                //빈칸 탐색
                for(int x = 0; x <4; x++){
                    int nextX = sharkInfo.x + dx[sharkInfo.dirPriority[sharkInfo.dir][x]];
                    int nextY = sharkInfo.y + dy[sharkInfo.dirPriority[sharkInfo.dir][x]];
//                    System.out.println("nextX : " + nextX + ", nextY : "+ nextY);

                    //맵을 벗어나지 않고, 해당 번호가 0이면 빈칸임
                    if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && maps[nextX][nextY].sharkNum == 0){
                        tempSave[i] = new Location(nextX,nextY,sharkInfo.dirPriority[sharkInfo.dir][x]);
                        flag = false;
                        break;
                    }
                }
                //빈칸을 못찾았으면 다시돌면서 현재 상어의 냄새인 곳으로 이동.
                if(flag){
                    for(int x = 0; x <4; x++){
                        int nextX = sharkInfo.x + dx[sharkInfo.dirPriority[sharkInfo.dir][x]];
                        int nextY = sharkInfo.y + dy[sharkInfo.dirPriority[sharkInfo.dir][x]];
                        if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && maps[nextX][nextY].sharkNum == i){
                            tempSave[i] = new Location(nextX,nextY,sharkInfo.dirPriority[sharkInfo.dir][x]);
                            break;
                        }

                    }
                }
            }
        }

        //상어 위치 업데이트 전에, 전부 냄새 -1함.
        smallUpdate();


//        System.out.println("----------------");
//        for(int i = 1; i <= 4; i++){
//            System.out.println("x : "+tempSave[i].x + ", y : " + tempSave[i].y+ ", dir : "+  tempSave[i].dir);
//        }
        //업데이트 리스트로 상어 위치 업데이트
        removeShark(tempSave);


    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());


        //게임판을 입력받음
        maps = new MapInfo[N][N];

        // 상어의 위치배열의 객체 초기화.
        sharkLoc = new SharkInfo[M+1];
        for(int i = 1; i <= M; i++){
            sharkLoc[i] = new SharkInfo(0,0);
        }


        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {

                int temp = Integer.parseInt(st.nextToken());
                if(temp == 0) {
                    maps[i][j] = new MapInfo(0,0);
                }
                else {
                    //상어의 위치를 찾아서 맵에 냄새를 셋팅하고, 상어의 위치좌표를 저장함.
                    maps[i][j] = new MapInfo(temp,k);
                    sharkLoc[temp].x = i;
                    sharkLoc[temp].y = j;

                }
            }
        }

        //각 상어의 방향을 넣어줌.
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= M; i++) {
            sharkLoc[i].dir = Integer.parseInt(st.nextToken());
        }

        // 우선순위 입력받기.
        for(int i = 1; i <= M; i++) {

            int[][] tempArray = new int[5][4];
            //각 방향별 우선순위.
            for(int j = 1; j <= 4; j++) {
                st = new StringTokenizer(br.readLine());
                for(int z = 0; z < 4; z++) {
                    tempArray[j][z] = Integer.parseInt(st.nextToken());
                }
            }

            sharkLoc[i].dirPriority = tempArray;
        }


//        for(int i = 1; i <= M; i++){
//            System.out.println(sharkLoc[i].toString());
//        }


        /*----로직------*/
        //시간
        int time = 1;
        while(time <= 1000) {


            // 각 상어의 이동
            sharkMove();



            //1번만 남았는지 확인
            //1번만 남으면 반복문 종료
            if(check()) break;

            /*출력*/
//            System.out.println("----------");
//
//            for(int i = 1; i <= M; i++){
//                if(sharkLoc[i] != null)
//                    System.out.println(sharkLoc[i].toString());
//            }
//
//            System.out.println("---------");
//            for(int i = 0 ; i < N; i++){
//                for(int j = 0; j < N; j++){
//                    System.out.print(maps[i][j].toString()+"  ");
//                }
//                System.out.println();
//            }


            time++;

        }




        //반복문을 빠져나왔을때 1000초가 넘어갔다면 -1
        if(time > 1000) {
            System.out.println(-1);
        }
        else {
            System.out.println(time);
        }

    }

    static class MapInfo{
        int sharkNum;
        int smallCount;

        MapInfo(int sharkNum, int smallCount){
            this.sharkNum = sharkNum;
            this.smallCount = smallCount;

        }

//        @Override
//        public String toString() {
//            return "sharkNum : " + sharkNum + ", smallCount : " + smallCount +" | ";
//        }
    }

    static class SharkInfo{
        int x;
        int y;
        int dir;

        // 방향에 따른 우선순위
        int[][] dirPriority;

        SharkInfo(int x, int y){
            this.x = x;
            this.y = y;
        }

//        @Override
//        public String toString() {
//            return "x : " + x + ", y : " + y + ", dir : " + dir;
//        }
    }

    static class Location{
        int x;
        int y;
        int dir;

        Location(int x, int y,int dir){
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

}