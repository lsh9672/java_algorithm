package baekjoon.baek20220818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3109 {

    static int R;
    static int C;
    static char[][] maps;
    //방문처리 배열
    static boolean[][] visited;

    static int count;

    //오른쪽, 오른쪽위 대각선, 오른쪽 아래 대각선 정의
    static int[] dx = {-1,0,1};
    static int[] dy = {1,1,1};

    //해당 시작노드로 방문을 했을떄 목적지에 도달할수 있는지
    static boolean dfs(Location startNode){

        //해당 노드 방문 처리.
        visited[startNode.x][startNode.y] = true;
        //노드가 마지막 열에 도착했다면 종료

        if(startNode.y == C-1){
            return true;
        }

        //인접노드
        for(int i = 0 ; i < 3; i++){
            int nextX = startNode.x + dx[i];
            int nextY = startNode.y + dy[i];

            //(문제 제출이후에 수정) nextY의 경우, 게임판을 벗어날 수가 없기 때문에 굳이 확인 필요 없음.
//            if(nextX >= 0 && nextX < R && nextY >= 0 && nextY < C && maps[nextX][nextY].equals(".")&& !visited[nextX][nextY]){
//            if(nextX >= 0 && nextX < R && maps[nextX][nextY].equals(".") && !visited[nextX][nextY]){
            if(nextX >= 0 && nextX < R && maps[nextX][nextY] == '.' && !visited[nextX][nextY]){
                //해당 위치에서 시작한 값이 목적지 까지 도달이 가능하다면 종료
                if(dfs(new Location(nextX,nextY))) return true;
            }
        }

        //모든 경우를 다 실행했는데 안된거면 도달 불가
        return false;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        maps = new char[R][C];

        //(문제 제출 이후 수정) string보다 char를 사용해서 하는 것이 메모리 절약측면에서도 좋음.
//        for(int i = 0; i < R; i++){
//            maps[i] = br.readLine().split("");
//        }

        //가급적이면 String보다는 char쓰는 것이 좋음 - 대략 5배가까이 차이남
        for(int i = 0; i < R; i++) {
            maps[i] = br.readLine().toCharArray();
        }

        //방문처리
        visited = new boolean[R][C];

        //총 파이프수
        count = 0;

        //각 행마다 bfs함수를 돌려봄
        for(int i = 0; i < R; i++){

            //모든 행의 0번열에서 시작해봄 - 이전 위치에서 방문처리 한것은 되돌리지 않음(어차피 이전에 갔던곳은 도착가능한 경로라 안되거나, 애초에 도달할수 없는 경로임)
            //탐색을 했는데 true이면 목적지 도달이 가능한것이므로 갯수 증가.
            if(dfs(new Location(i,0))){
                count++;
            }

        }

        System.out.println(count);


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
