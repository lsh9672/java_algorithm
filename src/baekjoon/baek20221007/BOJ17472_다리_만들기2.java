package baekjoon.baek20221007;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : sh Lee
 * @date : 22. 10. 7.
 */
public class BOJ17472_다리_만들기2 {

    static int N,M;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int[][] maps;

    static List<Node> edgeNodeList; // 가장자리 노드 리스트
    static List<Edge> edgeList; // 간선 리스트 - 크루스칼 사용을 위헤

    //섬 번호를 2번부터 설정했기 때문에 배열을 2번 부터 사용.
    static int[] parent;

    //대표 부모노트 찾기 + 경로 압축
    static int find(int node){

        if(parent[node] == node){
            return node;
        }

        return parent[node] = find(parent[node]);
    }

    //두 영역 합치기
    static boolean union(int node1, int node2){

        node1 = find(node1);
        node2 = find(node2);

        if(node1 == node2) return false;

        else if(node1 < node2){
            parent[node2] = node1;
        }

        else{
            parent[node1] = node2;
        }

        return true;
    }

    //각 섬을 연결하는 모든 간선을 만들고 저장함. - 크루스칼을 사용하기 위해서
    static void makeEdgeList(){
        //가장자리 노드를 하나씩 꺼내서 상하좌우로 이동시켜봄
        for(Node node : edgeNodeList){

            for(int i = 0; i < 4; i++){
                int count = 1;
                while(true){

                    int nextX = node.x + dx[i] * count;
                    int nextY = node.y + dy[i] * count;

                    if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M){
                        //다음위치가 시작섬과 같다면 종료
                        if(maps[nextX][nextY] == node.num) break;

                        //다른 섬에 도달했다면 저장하고 종료
                        else if(maps[nextX][nextY] != 0){

                            if(count - 1 >= 2){
                                edgeList.add(new Edge(node.num,maps[nextX][nextY],count-1));
                            }
                            break;
                        }
                        else{
                            count++;
                        }
                    }
                    else{
                        break;
                    }

                }
            }
        }
    }


    //각 섬에 번호 붙이기
    static void bfs(Node startNode, boolean[][] visited, int num){

        visited[startNode.x][startNode.y] = true;

        Queue<Node> needVisited = new LinkedList<>();
        needVisited.add(startNode);

        while(!needVisited.isEmpty()){

            Node currentNode = needVisited.poll();

            maps[currentNode.x][currentNode.y] = num;

            boolean flag = false;
            for(int i = 0; i < 4; i++){
                int nextX = currentNode.x + dx[i];
                int nextY = currentNode.y + dy[i];

                if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && !visited[nextX][nextY]){

                    if(maps[nextX][nextY] == 1){
                        visited[nextX][nextY] = true;
                        needVisited.add(new Node(nextX,nextY));
                    }

                    else if(maps[nextX][nextY] == 0) flag = true;

                }
            }

            if(flag){
                edgeNodeList.add(new Node(currentNode.x, currentNode.y, num));
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

        boolean[][] visited = new boolean[N][M];
        int num = 2;

        edgeNodeList = new ArrayList<>();
        edgeList = new ArrayList<>();

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                //방문하지 않았거나 1이라면 해당 위치부터 bfs탐색을 해서 번호를 붙임
                if(!visited[i][j] && maps[i][j] == 1){
                    bfs(new Node(i,j),visited, num);
                    num++;
                }
            }
        }


        //간선 리스트 만들기
        makeEdgeList();

        //크루스칼 사용을 위해 리스트 정렬
        edgeList.sort((o1,o2)->{
            return o1.weight - o2.weight;
        });

        parent = new int[num];
        for(int i = 2; i < num; i++){
            parent[i] = i;
        }


        int edgeCount = 0;
        int result = 0;
        for(Edge edge : edgeList){

            //두 노드를 유니온 해보기
            if(union(edge.x,edge.y)){
                edgeCount++;
                result += edge.weight;
            }

            if(edgeCount == num-3){
                break;
            }

        }

        if(edgeCount != num-3) result = -1;

        System.out.println(result);

    }

    static class Node{
        int x, y;
        int num;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }

        Node(int x, int y, int num){
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    static class Edge{
        int x, y, weight;

        Edge(int x, int y, int weight){
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

    }
}
