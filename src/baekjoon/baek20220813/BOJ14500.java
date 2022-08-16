package baekjoon.baek20220813;

/**
 * BOJ 14500번
 * 방식 - dfs를 이용해서 깊이가 4일떄까지 탐색하면 ㅗ,ㅜ,ㅓ,ㅏ 모양을 제외한 나머지는 모두 탐색이 가능하다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14500 {

    static int N;
    static int M;
    static int result;
    static int[][] maps;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static boolean[][] check;


    //dfs로 만들수 없는  ㅗ,ㅜ,ㅓ,ㅏ의 경우를 처리해줌.
    static void exceptionCheck(Location startNode){
        //ㅗ,ㅜ,ㅓ,ㅏ 순으로
        int[] tempDx = {0,-1,0,0,1,0,1, 1,2,1,1,2};
        int[] tempDy = {1, 1,2,1,1,2,0,-1,0,0,1,0};
        int tempCount= maps[startNode.x][startNode.y];

        int count = 0;
        boolean flag = true;
        for(int i = 0; i < 12; i++){
            count++;
            int nextX = startNode.x + tempDx[i];
            int nextY = startNode.y + tempDy[i];

            if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M){
                tempCount += maps[nextX][nextY];
            }
            else{
                flag = false;
            }

            //3의 배수일때마다 최대값 저장
            if(count % 3== 0){
                if(flag){
                    result = Math.max(result, tempCount);
                }
                flag = true;
                tempCount= maps[startNode.x][startNode.y];
            }
        }
    }

    static void dfs(Location startNode, int depth, int value){
        if(depth >= 3){
            if(result < value){
                result = value;
            }
            return;
        }

        for(int i = 0; i < 4; i++){
            int nextX = startNode.x + dx[i];
            int nextY = startNode.y + dy[i];

            if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && !check[nextX][nextY]){
                check[nextX][nextY] = true;
                dfs(new Location(nextX,nextY), depth+1, value + maps[nextX][nextY]);
                check[nextX][nextY] = false;
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

        //방문노드 체크
        check = new boolean[N][M];

        //각 칸별로 dfs탐색을 통해서 ㅗ,ㅏ,ㅜ,ㅓ의 경우를 제외한 최대값을 구해줌.
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                check[i][j] = true;
                dfs(new Location(i,j),0,maps[i][j]);
                check[i][j] = false;

                //예외 경우를 구해줌
                exceptionCheck(new Location(i,j));
            }
        }

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
