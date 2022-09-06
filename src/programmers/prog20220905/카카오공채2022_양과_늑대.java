package programmers.prog20220905;

import java.util.ArrayList;
import java.util.List;

public class 카카오공채2022_양과_늑대 {

    static int result;
    static int[] nodeInfo;
    static List<Integer>[] graph;
    static boolean[][][] visited; //노드번호,


    static void dfs(int currentNode, int sheepValue, int wolfValue){


        //양 업데이트
        result = Math.max(result,sheepValue);

        for(int nextNode : graph[currentNode]){

            int tempSheepValue = sheepValue;
            int tempWolfValue = wolfValue;
            int tempNodeInfo = nodeInfo[nextNode]; //원래 무슨 값이었는지 저장.
            //양이라면
            if(nodeInfo[nextNode] == 0) tempSheepValue++;
            //늑대라면
            else if(nodeInfo[nextNode] == 1) tempWolfValue++;

            //방문하지 않았고 다음 노드를 방문했을때 늑대수 < 양이라면
            if(!visited[nextNode][tempSheepValue][tempWolfValue] && tempWolfValue < tempSheepValue){
                visited[nextNode][tempSheepValue][tempWolfValue] = true;
                nodeInfo[nextNode] = -1;//늑대 또는 양 배열의 값을 -1로 만들어서 이미 양또는 늑대가 따라왔음을 나타냄.
                dfs(nextNode,tempSheepValue,tempWolfValue);//재귀호출
                visited[nextNode][tempSheepValue][tempWolfValue] = false;
                nodeInfo[nextNode] = tempNodeInfo;
            }
        }

    }

    public int solution(int[] info, int[][] edges) {

        //변수 초기화
        nodeInfo = info;
        result = 0;
        graph = new ArrayList[info.length];
        visited = new boolean[info.length][18][18]; // 노드번호, 양 수, 늑대 수

        //그래프 리스트 초기화
        for(int i = 0; i < info.length; i++){
            graph[i] = new ArrayList<>();
        }

        //그래프 입력
        for(int[] edge : edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        //dfs호출
        visited[0][1][0] = true;
        nodeInfo[0] = -1;

        dfs(0,1,0);


        return result;
    }

    public static void main(String[] args) {
        카카오공채2022_양과_늑대 tt = new 카카오공채2022_양과_늑대();
        int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};
        int[][] edges = {{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}};
        System.out.println(tt.solution(info,edges));

        int[] info2 = {0,1,0,1,1,0,1,0,0,1,0};
        int[][] edges2 = {{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{3,7},{4,8},{6,9},{9,10}};
        System.out.println(tt.solution(info2,edges2));
    }
}
