package baekjoon.baek20220818;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphTest {
    static int N = 8;
    public static void main(String[] args) {

        // 인접행렬
        int[][] adjMat = new int[N][N];
        adjMat[1][2] = 1;
        adjMat[2][1] = 1;
        adjMat[1][3] = 1;
        adjMat[3][1] = 1;
        adjMat[2][4] = 1;
        adjMat[4][2] = 1;
        adjMat[2][5] = 1;
        adjMat[5][2] = 1;
        adjMat[3][6] = 1;
        adjMat[6][3] = 1;
        adjMat[3][7] = 1;
        adjMat[7][3] = 1;
        adjMat[1][7] = 1;
        adjMat[7][1] = 1;

//        dfs(adjMat, 1, new boolean[N]);
//        bfs(adjMat,1);

        // 인접리스트
        ArrayList<Integer>[] adjList = new ArrayList[N];
        for (int i = 1; i < N; i++) {
            adjList[i]=new ArrayList();
        }
        adjList[1].add(2);
        adjList[1].add(3);
        adjList[1].add(7);
        adjList[2].add(4);
        adjList[2].add(5);
        adjList[3].add(6);
        adjList[3].add(7);

        dfs(adjList,1,new boolean[N]);
        System.out.println();
        bfs(adjList,1);
    }

    private static void bfs(ArrayList<Integer>[] adjList, int start) {
        boolean[]v =new boolean[N];
        Queue<Integer> Q = new LinkedList();
        Q.add(start);
        v[start]=true;
        while(!Q.isEmpty()) {
            Integer p = Q.poll();
            System.out.printf(p+" ");
            for (int i : adjList[p]) {
                if(!v[i]) {
                    v[i] = true;
                    Q.add(i);
                }
            }
        }

    }

    private static void dfs(ArrayList<Integer>[] adjList, int idx, boolean[] v) {
        v[idx]=true;
        System.out.print(idx+" ");
        // idx 번지에서 갈수 있는 정점을 찾아서 간다
        for (int i : adjList[idx]) {
            if(!v[i]) {
                dfs(adjList,i,v);
            }
        }
    }

    private static void bfs(int[][] adjMat, int start) {
        boolean[]v =new boolean[N];
        Queue<Integer> Q = new LinkedList();
        Q.add(start);
        v[start]=true;
        while(!Q.isEmpty()) {
            Integer p = Q.poll();
            System.out.printf(p+" ");
            for (int i = 1; i < N; i++) {
                if(!v[i]&&adjMat[p][i]==1) {
                    v[i]=true;
                    Q.add(i);
                }
            }
        }
    }

    private static void dfs(int[][] adjMat, int idx, boolean[] v) {
        v[idx]=true;
        System.out.print(idx+" ");
        // idx 번지에서 갈수 있는 정점을 찾아서 간다
        for (int i = 1; i < N; i++) {
            if(!v[i]&&adjMat[idx][i]==1) {
                dfs(adjMat,i,v);
            }
        }
    }

}
