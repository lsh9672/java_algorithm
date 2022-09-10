package baekjoon.baek20220910;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1039_교환 {

    static int K;
    static int result = Integer.MIN_VALUE;

    static String getNextNode(String currentNode, int i, int j){
        char[] temp = currentNode.toCharArray();

        //i,j를 스왑
        char swapTemp = temp[i];

        temp[i] = temp[j];
        temp[j] = swapTemp;

        if(temp[0] == '0') return null;

        return new String(temp);
    }

    static void bfs(String startNode){
        Map<Integer,Set<Integer>> visited = new HashMap<>();

        visited.put(Integer.parseInt(startNode), new HashSet<>());
        visited.get(Integer.parseInt(startNode)).add(0);

        Queue<Node> needVisited = new LinkedList<>();
        needVisited.add(new Node(startNode,0));

        while(!needVisited.isEmpty()){

            Node currentNode = needVisited.poll();

            if(currentNode.weight == K) {
                result = Math.max(result, Integer.parseInt(currentNode.num));
                continue;
            }

            for(int i = 0; i < currentNode.num.length()-1; i++){
                for(int j = i+1; j < currentNode.num.length(); j++){

                    String nextNode = getNextNode(currentNode.num, i, j);

                    if(nextNode != null){
                        if(visited.containsKey(Integer.parseInt(nextNode))){
                            if(!visited.get(Integer.parseInt(nextNode)).contains(currentNode.weight+1)){
                                needVisited.add(new Node(nextNode,currentNode.weight+1));
                                visited.get(Integer.parseInt(nextNode)).add(currentNode.weight+1);
                            }
                        }
                        else{
                            visited.put(Integer.parseInt(nextNode),new HashSet<>());
                            visited.get(Integer.parseInt(nextNode)).add(currentNode.weight+1);
                            needVisited.add(new Node(nextNode,currentNode.weight+1));
                        }
                    }
                }
            }

        }


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String startNode = st.nextToken();
        K = Integer.parseInt(st.nextToken());


        bfs(startNode);

        if(result == Integer.MIN_VALUE){
            System.out.println(-1);
        }
        else{
            System.out.println(result);
        }
    }


    static class Node{
        String num;
        int weight;

        Node(String num, int weight){
            this.num = num;
            this.weight = weight;
        }

    }
}
