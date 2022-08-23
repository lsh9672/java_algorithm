package programmers.mockExam.ex20220822;

import java.util.ArrayList;
import java.util.List;

public class Solution4 {

    static List<Integer>[] graph;

    public int solution(int n, int[][] lighthouse) {
        int answer = 0;

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] temp : lighthouse){
            graph[temp[0]].add(temp[1]);
        }

        return answer;


    }

    static class Location{
        int node;
        int depth;

        Location(int node, int depth){
            this.node = node;
            this.depth = depth;
        }
    }
}
