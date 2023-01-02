package swea.sw20221108;

/**
 * @author : sh Lee
 * @date : 22. 11. 8.
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * 1. dfs로 탐색을 한다.
 * 2. 회전하는 방향은 시계방향으로 한다.
 * 3. 시계방향으로 사각형을 만들기 위해서는 왼쪽 아래 - 오른쪽 아래 - 오른쪽 위 - 왼쪽 위 순서로 탐색한다.
 * 4. 방향을 바꾸면 이전 방향은 사용 할 수 없음.
 */
public class SWEA2105_디저트_카페 {

    static int N;
    static int[][] maps;

    static boolean[][] visited;

    static int[] dessertCheck;

    static int result;

    //왼쪽 아래(0) - 오른쪽 아래(1) - 오른쪽 위(2) - 왼쪽 위(3);

    static int[] dx = { 1, 1,-1,-1};
    static int[] dy = {-1, 1, 1,-1};

    static void dfs(Node currentNode, int count, Node startNode, int dir){

        //방문을 했고,시작노드라면 종료
        if(visited[currentNode.x][currentNode.y] && currentNode.x == startNode.x && currentNode.y == startNode.y){
            result = Math.max(result, count);
            return;
        }




    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/D:/SSAFY/intellijAlgo/algorithm/src/algo20221108/sample_input (5).txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        for(int testCase = 1; testCase <= T ; testCase++){

            N = Integer.parseInt(br.readLine());
            maps = new int[N][N];
            result = Integer.MIN_VALUE;

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    maps[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            visited = new boolean[N][N];
            dessertCheck = new int[101];

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
//                    dfs(new Node(i,j,0),);
                }
            }



            System.out.println("#"+ testCase + " " + result);

        }

    }

    static class Node{
        int x, y, count;

        Node(int x, int y, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}