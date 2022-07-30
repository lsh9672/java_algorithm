package baek20220730;

/**
 * 백준 9019번 DSLR
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Second {


    //D - 값을 두배로 바꿈, 10000 이상이라면 10000으로 나눈 나머지를 사용
    //S - 값 -1을 저장함, 값이 0이라면 9999를 저장함.
    //L - 각 자리수를 왼쪽으로 회전시킴, 1234라면 2341이 됨
    //R - 각 자리수를 오른쪽으로 회전시킴, 1234라면 4123이 됨
    static String[] operatorDSLR = {"D","S","L","R"};

    //R과L 연산을 처리
    static int rotate(int value, String dir){
        if (dir == "L") {
            return ((value * 10) % 10000) + (value / 1000);
        }
        //오른쪽으로 회전일때
        else {
            return (value / 10) + ((value % 10) * 1000);
        }

    }

    static String bfs(int startNode, int endNode){

        boolean[] visited = new boolean[10000];


        Queue<Node> needVisited = new ArrayDeque<>();
//        Queue<Node> needVisited = new LinkedList<>(); - 무의미하지만 약 100ms정도 느림

        visited[startNode] = true;
        needVisited.add(new Node(startNode,""));

        while(!needVisited.isEmpty()){

            Node currentNode = needVisited.poll();

            //다음에 탐색할 노드 검색
            for(int i = 0; i < 4; i++){

                String nextOperator = operatorDSLR[i];

                int nextValue;
                String nextStatus;

                if(nextOperator.equals("D")){
                    nextValue = (currentNode.value*2) % 10000;
                    nextStatus = currentNode.status + "D";
                }
                else if(nextOperator.equals("S")) {
                    nextValue = currentNode.value - 1;
                    if(nextValue == -1){
                        nextValue = 9999;
                    }
                    nextStatus = currentNode.status + "S";
                }
                else if(nextOperator.equals("L")){
                    nextValue = rotate(currentNode.value,"L");
                    nextStatus = currentNode.status + "L";

                }
                else{
                    nextValue = rotate(currentNode.value,"R");
                    nextStatus = currentNode.status + "R";

                }
                //목표값에 도달했다면 바로 리턴 - bfs탐색이므로 최단 경로는 가장 먼저 해당 값이 나오면 그것이 최단경로가 됨.
                if(nextValue == endNode){
                    return nextStatus;
                }

                //다음 탐색을 위해 노드에 추가 - 방문했던 값이 아니라면.
                if(!visited[nextValue]){
                    needVisited.add(new Node(nextValue,nextStatus));
                    visited[nextValue] = true;
                }

            }
        }

        //만약 해당 값에 도달할 수 없다면 빈값 리턴
        return "";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int testCase = 0; testCase < T ; testCase++){
            st = new StringTokenizer(br.readLine());

            int startNode = Integer.parseInt(st.nextToken());
            int endNode = Integer.parseInt(st.nextToken());

            String output = bfs(startNode,endNode);
            if(output.equals("")){
                System.out.println(-1);
            }
            else{
                System.out.println(output);
            }

        }
    }

    //어떤 연산이 이루어졌는지 확인하기 위해 노드를 클래스로 만들어서 값을 가지고 있도록 함.
    static class Node{
        int value;
        String status;

        Node(int value, String status){
            this.value = value;
            this.status = status;
        }
    }
}
