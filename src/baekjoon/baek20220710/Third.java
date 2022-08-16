package baekjoon.baek20220710;

/**
 * 백준 2468번 (자바, 그래프 연습, 실버1)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Third {

    static Integer[] dx = {-1,1,0,0};
    static Integer[] dy = {0,0,-1,1};

    static Integer[][] rain(Integer[][] graph,int n, int rainValue){

        Integer[][] result = new Integer[n][n];
        for (Integer[] integers : result) {
            Arrays.fill(integers,0);
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(graph[i][j] <= rainValue){
                    result[i][j] = 1;
                }
            }
        }

        return result;
    }

    static Integer[][] bfs(Integer[][] graph,int n, Integer[] startNode){

        Queue<Integer[]> needVisited = new LinkedList<>();

        graph[startNode[0]][startNode[1]] = 1;

        needVisited.add(startNode);

        while(needVisited.size() != 0){

            Integer[] currentNode = needVisited.poll();

            for(int i = 0; i < 4; i++){
                Integer[] nextNode = {currentNode[0]+dx[i], currentNode[1]+dy[i]};

                if(nextNode[0] >= 0 && nextNode[0] < n && nextNode[1] >= 0 && nextNode[1] < n && graph[nextNode[0]][nextNode[1]] == 0){
                    graph[nextNode[0]][nextNode[1]] = 1;

                    Integer[] tempNode = {nextNode[0],nextNode[1]};

                    needVisited.add(tempNode);
                }
            }

        }
        return graph;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Integer[][] graph = new Integer[n][n];

        int minValue = 101;
        int maxValue = 0;

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < n; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
                if(minValue > graph[i][j]){
                    minValue = graph[i][j];
                }

                if(maxValue < graph[i][j]){
                    maxValue = graph[i][j];
                }
            }
        }

        int result = 0;

        for (int i = minValue-1; i <= maxValue-1; i++){

            //물에 잠긴지역을 1로 표현
            Integer[][] temp = rain(graph,n,i);

            int tempSaveNum = 0;

            for(int x= 0; x < n; x++){
                for(int y = 0; y <n ; y++){
                    if(temp[x][y] == 0){
                        Integer[] tempLoc = {x,y};
                        temp = bfs(temp,n,tempLoc);

                        tempSaveNum++;
                    }
                }
            }
            result = Math.max(result,tempSaveNum);
        }

        System.out.println(result);
    }
}