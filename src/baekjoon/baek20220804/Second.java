package baekjoon.baek20220804;

/**
 * BOJ 13459번
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Second {

    static int N;
    static int M;
    static String[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};



    //방향과 좌표를 입력받아서
   static int[] BeadMove(int x, int y, int dir){
//       System.out.println("x : " + x + ", y : " + y + ", dir : " + dir);
       //0번째가 x, 1번째가 y
        int[] temp = new int[2];

        while(true){
            int nextX = x + dx[dir];
            int nextY = y + dy[dir];

            //벽을 만나면 값을 업데이트 하지 말고 바로 종료 - 벽위치에는 구슬이 있을 수 없음
//            System.out.println("next x : " + nextX + ", next y : " + nextY);
            if(map[nextX][nextY].equals("#")) break;

            //구슬위치 업데이트
            x = nextX;
            y = nextY;

            //구멍의 경우에는 이동이 가능함.
            if(map[nextX][nextY].equals("O")) break;
        }

        temp[0] = x;
        temp[1] = y;

        return temp;
    }

    static boolean bfs(Location startRedBead){
        boolean[][][][] visited = new boolean[N][M][N][M];

        //초기값 방문처리
        visited[startRedBead.redX][startRedBead.redY][startRedBead.blueX][startRedBead.blueY] = true;

        Queue<Location> needVisited = new ArrayDeque<>();

        needVisited.add(startRedBead);

//        int tempCount = 0;

        while(!needVisited.isEmpty()){

            Location currentLoc = needVisited.poll();

            if(map[currentLoc.redX][currentLoc.redY].equals("O") && currentLoc.count <= 10){
                return true;
            }

            for(int i = 0; i <4; i++){

                int[] nextRed  = BeadMove(currentLoc.redX,currentLoc.redY,i);
                int[] nextBlue = BeadMove(currentLoc.blueX,currentLoc.blueY,i);

                //구슬을 움직였는데, 두 구슬이 겹쳤다면, 해당위치가 구멍인지 확인하고 아니라면 둘중 어떤 구슬이 진행방향에 앞에 있었는지 확인하고 뒤에 있던 구슬을 -1함.
                if(nextRed[0] == nextBlue[0] && nextRed[1] == nextBlue[1]){
                    //해당 위치가 구멍이라면 - 둘다 구멍에 빠지므로 잘못된 경우임
                    if(map[nextRed[0]][nextRed[1]].equals("O")){
                         continue;
                    }
                    //구멍이 아니라면 어떤것이 앞에오는지 확인.
                    else{
                        //방향에 따라 나뉨
                        //상 - x좌표 비교
                        if(i == 0){
                            if(currentLoc.redX < currentLoc.blueX){
                                nextBlue[0]++;
                            }
                            else{
                                nextRed[0]++;
                            }
                        }
                        //하 - x좌표 비교
                        else if(i == 1){
                            if(currentLoc.redX < currentLoc.blueX){
                                nextRed[0]--;
                            }
                            else{
                                nextBlue[0]--;
                            }

                        }
                        //좌 - y좌표 비교
                        else if(i == 2){
                            if(currentLoc.redY < currentLoc.blueY){
                                nextBlue[1]++;
                            }
                            else{
                                nextRed[1]++;
                            }
                        }
                        //우 - y좌표 비교
                        else if(i == 3){
                            if(currentLoc.redY < currentLoc.blueY){
                                nextRed[1]--;
                            }
                            else{
                                nextBlue[1]--;
                            }
                        }
                    }
                }

                /**파란색만 구멍에 빠지는 경우 - 이거 뺴먹어서 42퍼에서 계속 틀림*/
                else if(map[nextBlue[0]][nextBlue[1]].equals("O")) continue;

                //다음 탐색을 위해서 방문한 위치들인지 확인하고 다음 탐색을 위해 노드에 추가.
                if(!visited[nextRed[0]][nextRed[1]][nextBlue[0]][nextBlue[1]]){
                    visited[nextRed[0]][nextRed[1]][nextBlue[0]][nextBlue[1]] = true;
                    needVisited.add(new Location(nextRed[0],nextRed[1], currentLoc.count+1, nextBlue[0],nextBlue[1]));
                }
            }

        }

        return false;

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new String[N][M];
        for(int i = 0; i< N; i++){
            map[i] = br.readLine().split("");
        }

        //빨간 구슬과 파란 구슬 위치 구하기
        //빨간 구슬을 기준으로 bfs 탐색을 할건데, 이동시마다 파란 구슬의 위치도 계속 이동 시킴
        Location redBead = new Location(0,0,0,0,0);
        for(int i = 0; i < N ; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j].equals("R")){
                    redBead.redX = i;
                    redBead.redY = j;
                }
                else if(map[i][j].equals("B")){
                    redBead.blueX = i;
                    redBead.blueY = j;
                }
            }
        }

//        System.out.println(redBead.redX + " " + redBead.redY + " " + redBead.blueX + " " + redBead.blueY);

        //10번 이하로 구멍에 넣을 수 있다면
        if(bfs(redBead)){
            System.out.println(1);
        }
        else{
            System.out.println(0);
        }

    }

    static class Location{
        int redX;
        int redY;
        int count;
        int blueX;
        int blueY;

        Location(int redX,int redY, int count,int blueX, int blueY){
            this.redX = redX;
            this.redY = redY;
            this.count = count;
            this.blueX = blueX;
            this.blueY = blueY;
        }
    }
}
