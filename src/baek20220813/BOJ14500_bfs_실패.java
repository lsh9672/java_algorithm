package baek20220813;
/**
 * bfs로 풀어보기
 * 뎁스가 4일때 업데이트 해야되는데, ㅗ,ㅓ,ㅏ,ㅜ 이 모양들은 뎁스가 3임
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14500_bfs_실패 {

    static int N;
    static int M;
    static int result;
    static int[][] maps;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};


    static void bfs(Location startNode){
        boolean[][] visited = new boolean[N][M];

        visited[startNode.x][startNode.y] = true;

        Queue<Location> needVisited = new LinkedList<>();

        needVisited.add(startNode);

        while(!needVisited.isEmpty()){

            Location currentNode = needVisited.poll();

            if(currentNode.depth == 4){
                result = Math.max(result, currentNode.count);
            }
            if(currentNode.depth < 4){
                for(int i = 0; i < 4; i++){
                    int nextX = currentNode.x + dx[i];
                    int nextY = currentNode.y + dy[i];

                    if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && !visited[nextX][nextY]){
                        visited[nextX][nextY] = true;
                        needVisited.add(new Location(nextX,nextY, currentNode.count + maps[nextX][nextY], currentNode.depth+1));
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][M];

        //지도 입력받기
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //최대값을 구하기 위한 변수, 초기값을 최소로 잡아서 최대값으로 업데이트 하도록 함
        result = Integer.MIN_VALUE;

        //각 칸별로 dfs탐색을 통해서 ㅗ,ㅏ,ㅜ,ㅓ의 경우를 제외한 최대값을 구해줌.
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
               bfs(new Location(i,j,maps[i][j],1));
            }
        }

        System.out.println(result);

    }

    static class Location{
        int x;
        int y;
        int count;
        int depth;

        Location(int x, int y,int count, int depth){
            this.x = x;
            this.y = y;
            this.count = count;
            this.depth = depth;
        }
    }


}

