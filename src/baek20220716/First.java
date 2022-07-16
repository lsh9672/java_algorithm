package baek20220716;

/**
 * 백준 16948번 (자료구조, 그래프)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class First {

    static int[] dx = {-2,-2,0,0,2,2};
    static int[] dy = {-1,1,-2,2,-1,1};

    static int bfs(int[] startNode,int[] endNode, int[][] graph,int n){
        Queue<int[]> needVisited = new LinkedList<>();

        needVisited.add(startNode);

        graph[startNode[0]][startNode[1]] = 1;

        while(!needVisited.isEmpty()){

            int[] currentNode = needVisited.poll();

            for(int i = 0; i < 6; i++){
                int[] nextNode = new int[2];

                nextNode[0] = currentNode[0] + dx[i];
                nextNode[1] = currentNode[1] + dy[i];

                if (nextNode[0] >= 0 && nextNode[0] < n && nextNode[1] >= 0 && nextNode[1] < n && graph[nextNode[0]][nextNode[1]] == 0){
                    graph[nextNode[0]][nextNode[1]] = graph[currentNode[0]][currentNode[1]] + 1;
                    needVisited.add(nextNode);

                    if (nextNode[0] == endNode[0] && nextNode[1] == endNode[1]){
                        return graph[nextNode[0]][nextNode[1]]-1;
                    }

                }

            }

        }

        return -1;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] graph = new int[n][n];

        int[] startNode = new int[2];
        int[] endNode = new int[2];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 2; i++){
            startNode[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 2; i++){
            endNode[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(bfs(startNode,endNode,graph,n));

    }
}
