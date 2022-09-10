package baekjoon.baek20220910;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ16724_피리부는_사나이 {

    static int N;
    static int M;

    //상하좌우
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static char[][] maps;
    static int[] parents;
    static Map<Character,Integer> dirMapping;

    static int find(int node){

        if(parents[node] == node) return node;

        return parents[node] = find(parents[node]);
    }

    static void union(int firstNode, int secondNode){
        firstNode = find(firstNode);
        secondNode = find(secondNode);

        if(firstNode == secondNode) return;

        else if(firstNode > secondNode){
            parents[firstNode] = secondNode;
        }
        else{
            parents[secondNode] = firstNode;
        }
    }

    //다음 노드를 찾아서 해당되는 인덱스 리턴(인덱스는 1차원배열 인덱스로 변경)
    static int findNextNode(int currentNode) {
        int currentX = currentNode/M;
        int currentY = currentNode%M;

//        System.out.println("currentNode : " + currentNode + " currentX : " + currentX + " currentY : " + currentY);

        int nextDir = dirMapping.get(maps[currentX][currentY]);

        int nextX = currentX + dx[nextDir];
        int nextY = currentY + dy[nextDir];

        return nextX * M + nextY;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dirMapping = new HashMap<>();
        dirMapping.put('U',0);
        dirMapping.put('D',1);
        dirMapping.put('L',2);
        dirMapping.put('R',3);

        maps = new char[N][M];

        for(int i = 0; i < N; i++){
            maps[i] = br.readLine().toCharArray();
        }

        parents = new int[N*M];
        for(int i = 0; i < N*M; i++){
            parents[i] = i;
        }

        //현재 노드와 다음 노드를 찾아서 유니온함.
        for(int node = 0; node < N*M; node++){

            int currentNode = node;
            int nextNode = findNextNode(currentNode);

            union(currentNode,nextNode);
        }

        Set<Integer> result = new HashSet<>();

        //하나씩 find함수 돌려서 경로 압축
        for(int parent : parents){
            result.add(find(parent));
        }

        System.out.println(result.size());

    }
}
