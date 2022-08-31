package baekjoon.baek20220831;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 각 섬에 번호를 붙여서 구분한다.
 * 2. 각 섬의 가장자리에서 한쪽방향으로 섬을 연결해서 간선을 만들고 가중치를 구한다.
 * 3. 구한 간선들의 가중치를 리스트에 넣고 mst를 구한다.
 */

public class BOJ17472_다리만들기2 {

    static int N;
    static int M;

    static int nodeNum = 0; //섬 개수 세기.

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static List<Edge> edges; //간선과 가중치를 모아둠 - mst할때 사용
    static int[][] maps;

    static int[] parents;


    //초기 배열 만들기
    static void makeParents(){

        for(int i = 1; i < nodeNum; i++){
            parents[i] = i;
        }
    }

    static int findParent(int node){

        if(parents[node] == node){
            return node;
        }

        return parents[node] = findParent(parents[node]);
    }

    static boolean union(int firstNode, int secondNode){

        firstNode = findParent(firstNode);
        secondNode = findParent(secondNode);

        if(firstNode == secondNode){
            return false;
        }
        else if(firstNode < secondNode){
            parents[secondNode] = firstNode;
        }
        else{
            parents[firstNode] = secondNode;
        }

        return true;
    }


    //크루스칼
    static int kruskal(){

        //간선리스트 정렬
        edges.sort(Comparator.naturalOrder());


        //배열 채우기.
        parents = new int[nodeNum+1];
        makeParents();

        int edgeCount = 0;

        int edgeWeight = 0;

        for(Edge edge : edges){

            //두 노드를 합쳐봄
            if(union(edge.startNode, edge.endNode)){

                edgeWeight += edge.weight;
                edgeCount++;
            }

            if(edgeCount == nodeNum-1) break;
        }

        if(edgeCount != nodeNum-1) return -1;

        return edgeWeight;

    }


    //다리만들기 위해 사방 탐색
    static void bridgeSearch(Location currentNode){

        int startNode = maps[currentNode.x][currentNode.y];

        for(int dir = 0; dir < 4; dir++){
            int count = 1;

            while(true){
                int nextX = currentNode.x + dx[dir] * count;
                int nextY = currentNode.y + dy[dir] * count;

                if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M){
                    if(maps[nextX][nextY] == 0){
                        count++;
                    }
                    //해당방향이 같은 땅이라면
                    else if(maps[nextX][nextY] == startNode){
                        break;
                    }
                    //다른 땅에 도달하면 간선 추가
                    else{
                        if(count-1 >= 2){
                            edges.add(new Edge(startNode,maps[nextX][nextY],count-1));
                            break;
                        }
                        break;
                    }
                }
                else{
                    break;
                }

            }
        }

    }

    //간선 만들기
    static void edgeMake(){

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(maps[i][j] != 0){
                    bridgeSearch(new Location(i,j));
                }
            }
        }
    }

    static void bfs(Location startNode,boolean[][] visited){

        Queue<Location> needVisited = new LinkedList<>();

        needVisited.add(startNode);

        while(!needVisited.isEmpty()){

            Location currentNode = needVisited.poll();

            for(int i = 0; i < 4; i++){
                int nextX = currentNode.x + dx[i];
                int nextY = currentNode.y + dy[i];

                if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M){
                    if(!visited[nextX][nextY] && maps[nextX][nextY] == 1){
                        visited[nextX][nextY] = true;
                        maps[nextX][nextY] = nodeNum;
                        needVisited.add(new Location(nextX,nextY));
                    }

                }
            }
        }
    }

    //각 섬에 이름 붙이기.
    static void nodeCount(){

        boolean[][] visited = new boolean[N][M];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(!visited[i][j] && maps[i][j] == 1){
                    nodeNum++;
                    visited[i][j] = true;
                    maps[i][j] = nodeNum;
                    bfs(new Location(i,j),visited);
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

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        nodeCount(); //각 섬에 번호를 붙여서 구분함.

        edges = new ArrayList<>();

        edgeMake();//각 노드 별로 가장자리에서 한방향으로 다리 만들어봄.

        int result = kruskal();

        System.out.println(result);

    }

    static class Location{
        int x, y;

        Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge>{
       int startNode, endNode, weight;

       Edge(int startNode, int endNode, int weight){
           this.startNode = startNode;
           this.endNode = endNode;
           this.weight = weight;
       }

        @Override
        public int compareTo(Edge edge) {
            return this.weight - edge.weight;
        }
    }
}
