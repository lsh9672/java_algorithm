package baekjoon.baek20220811;

/*
 * BOJ 20058
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ20058 {

    static int tempN;
    static int iceNum;
    static int[][] maps;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    //얼음의 합
    static int totalIce(){

        int total = 0;
        for(int i = 0; i < tempN; i++){
            for(int j = 0; j < tempN; j++){
                total += maps[i][j];
            }
        }

        return total;
    }

    //얼음 덩이 구하기
    static void bfs(Location startNode){

        Queue<Location> needVisited = new LinkedList<>();
        needVisited.add(startNode);

        int count = 1;

        while(!needVisited.isEmpty()){
            Location currentNode = needVisited.poll();

            for(int i = 0; i < 4; i++){
                int nextX = currentNode.x + dx[i];
                int nextY = currentNode.y + dy[i];

                if(nextX >= 0 && nextX < tempN && nextY >= 0 && nextY < tempN && maps[nextX][nextY] != 0){
                    maps[nextX][nextY] = 0;
                    needVisited.add(new Location(nextX,nextY));
                    count++;
                }
            }
        }
        iceNum = Math.max(iceNum,count);
    }

    static void rotateMap(int L) {
        //격자 크기
        int miniN = (int)Math.pow(2,L);

        //배열 회전시킨 값을 넣을 새로운 배열
        int[][] tempMaps = new int[tempN][tempN];

        for(int i = 0; i < tempN; i += miniN){
            for(int j = 0; j < tempN; j += miniN){
                //i,j는 시작 좌표.
                for(int x = 0; x < miniN; x++){
                    for(int y = 0; y < miniN; y++){
                        tempMaps[i+y][j+miniN - 1 - x] = maps[i+x][j+y];
                    }
                }
            }

        }
        //배열 회전이 끝나면 업데이트
        maps = tempMaps;
    }
    //얼음 녹이기
    static void updateMap() {
        //얼음은 한번에 녹아야 됨.
        boolean[][] updateCheck = new boolean[tempN][tempN];

        //반복문을 돌면서 얼음 체크
        for(int i = 0; i < tempN; i++){
            for(int j = 0; j < tempN; j++){
                if (maps[i][j] == 0) continue;

                int count = 0;
                for(int d = 0; d < 4; d++){
                    int nextX = i + dx[d];
                    int nextY = j + dy[d];

                    if(nextX >= 0 && nextX < tempN && nextY >=0 && nextY < tempN && maps[nextX][nextY] > 0){
                        count++;
                    }
                }

                //count가 3보다 작으면 체크 true
                if(count < 3) updateCheck[i][j] = true;
            }
        }
        //맵의 얼음 녹이기
        for(int i = 0; i < tempN; i++){
            for(int j = 0; j < tempN; j++){
                if(updateCheck[i][j]) maps[i][j]--;
            }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 격자판 크기
        int N = Integer.parseInt(st.nextToken());

        // 마법 시전 횟수
        int Q = Integer.parseInt(st.nextToken());

        tempN = (int)Math.pow(2, N);
        maps = new int[tempN][tempN];

        //맵 입력받기
        for(int i = 0; i < tempN; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < tempN; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //마법 단계
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < Q; i++) {
            int L = Integer.parseInt(st.nextToken());
            rotateMap(L);
            updateMap();
        }


        System.out.println(totalIce());
        //얼음 덩어리
        iceNum = Integer.MIN_VALUE;

        //얼음 덩이 구하기.
        for(int i = 0; i < tempN; i++){
            for(int j = 0; j < tempN; j++){
                if(maps[i][j] != 0){
                    maps[i][j] = 0;
                    bfs(new Location(i,j));
                }
            }
        }

        if(iceNum == Integer.MIN_VALUE){
            System.out.println(0);
        }
        else{
            System.out.println(iceNum);
        }
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