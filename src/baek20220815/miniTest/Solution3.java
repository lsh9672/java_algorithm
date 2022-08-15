package baek20220815.miniTest;


import java.util.*;

public class Solution3 {



    static int bfs(List<Integer>[] maps,Location startNode, int n, int endNode){

        boolean[] visited = new boolean[n+1];
        visited[startNode.node] = true;

        Queue<Location> needVisited = new LinkedList<>();
        needVisited.add(startNode);

        if(startNode.node == endNode) return 0;

        while(!needVisited.isEmpty()){

            Location currentNode = needVisited.poll();

            for(int nextNode : maps[currentNode.node]){
                if(!visited[nextNode]){
                    if(nextNode == endNode) return currentNode.count+1;

                    visited[nextNode] = true;
                    needVisited.add(new Location(nextNode,currentNode.count+1));
                }
            }


        }
        return -1;

    }

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {

        //결과를 담아서 리턴
        int[] answer = new int[sources.length];

        //bfs로 탐색하기 편하게 2차원 배열로 만듦
        ArrayList<Integer>[] maps = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            maps[i] = new ArrayList<Integer>();
        }


        for(int[] temp : roads){
            maps[temp[0]].add(temp[1]);
            maps[temp[1]].add(temp[0]);
        }

        for(int i = 0; i < sources.length; i++){
            answer[i] = bfs(maps,new Location(sources[i], 0), n, destination);
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution3 sol3 = new Solution3();

        int n = 3;
        int[][] roads = {{1, 2}, {2, 3}};
        int[] sources = {2,3};
        int destination = 1;
        System.out.println(Arrays.toString(sol3.solution(n, roads, sources, destination)));

        n = 5;
        int[][] roads1 = {{1,2},{1,4},{2,4},{2,5},{4,5}};
        int[] sources1 = {1,3,5};
        destination = 5;
        System.out.println(Arrays.toString(sol3.solution(n, roads1, sources1, destination)));

    }

    static class Location{
        int node;
        int count;

        Location(int node, int count){
            this.node = node;
            this.count = count;
        }
    }
}
