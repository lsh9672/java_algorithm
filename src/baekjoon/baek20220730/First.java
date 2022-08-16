package baekjoon.baek20220730;

/**
 * 백준 7562
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class First {

    static int N;
    static int[][] graph;

    static int[] dx = {-1,-2,-2,-1,1,2,2,1};
    static int[] dy = {-2,-1,1,2,-2,-1,1,2};

    static int bfs(Location startNode, Location endNode){

        int[][] visited = new int[N][N];

        Queue<Location> needVisited = new ArrayDeque<>();

        needVisited.add(startNode);

        visited[startNode.x][startNode.y] = 1;

        if(startNode.x == endNode.x && startNode.y == endNode.y){
            return 0;
        }

        while(!needVisited.isEmpty()){

            Location currentNode = needVisited.poll();


            for(int i = 0; i < 8; i++){
                int nextX = currentNode.x + dx[i];
                int nextY = currentNode.y + dy[i];

                if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && graph[nextX][nextY] == 0){
                    graph[nextX][nextY] = graph[currentNode.x][currentNode.y] + 1;
                    needVisited.add(new Location(nextX,nextY));

                    if(nextX == endNode.x && nextY == endNode.y){
                        return graph[endNode.x][endNode.y];
                    }
                }
            }

        }

        return -1;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int testCase = 0; testCase < T; testCase++){
            N = Integer.parseInt(br.readLine());

            graph = new int[N][N];
            StringTokenizer st;

            Location startNode;
            Location endNode;

            st = new StringTokenizer(br.readLine());
            startNode = new Location(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));

            st = new StringTokenizer(br.readLine());
            endNode = new Location(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));

            System.out.println(bfs(startNode,endNode));

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
