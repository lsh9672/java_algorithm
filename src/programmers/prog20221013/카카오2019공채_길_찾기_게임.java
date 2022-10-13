package programmers.prog20221013;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
1. y위치로 정렬을 해서 루트부터 리프노드 순으로 트리를 구성할수 있도록 배치함(y크기로 내림차순) => 인덱스가 노드 번호라 이 방식 말고 클래스로 만들
2. 트리 구성이 끝나면 재귀를 이용해서 전위, 후위순회를 함
(의문점) - 과연 이렇게 단순하게 냈을까, 노드의 수가 10000개라서 정렬에도 시간이 좀 걸리고, 트리 구성후, 순회과정에 서도시간이 걸리게 됨.
 */

/**
 * @author : sh Lee
 * @date : 22. 10. 13.
 */
public class 카카오2019공채_길_찾기_게임 {

    static List<Integer[]> graph;

    //이진 트리 구성
    static void makeBinaryTree(int[][] nodeinfo){

//        for(int i = 0;)

    }

    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = {};

        graph = new ArrayList<>();

        //x값을 기준으로 내림차순 정렬
        Arrays.sort(nodeinfo,(o1,o2) -> {
            return Integer.compare(o2[0],o1[0]);
        });




        return answer;
    }

    public static void main(String[] args) {
        카카오2019공채_길_찾기_게임 s = new 카카오2019공채_길_찾기_게임();

        int[][] nodeinfo = {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}};
        System.out.println(Arrays.deepToString(s.solution(nodeinfo)));
    }

    static class Node{
        int x,y,nodeNum;
    }
}
