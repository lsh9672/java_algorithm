package baekjoon.baek20230723;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 1. 각 s즉, 연산하고 나온 숫자들을 노드로 생각하고, 인접노드는 *+-/ 네가지 연산을 거쳐야 만들어진다.
 * 2. 그래프의 사방 탐색이 사칙연산이 되는 것이다.
 * 사전순으로 앞서는 것을 먼저 출력한다고 했으니, 사칙연산을 *+-/순서대로 탐색하게 하고, 먼저 도착하는 것이 사전순으로 앞서는 것이다.
 * 각 노드에는 숫자 뿐만 아니라, 해당 연산자를 이어 붙이고 저장해둘 스트링 빌더 객체도 필요하다
 * 또한 방문했던 숫자를 방문하지 않기 위해서 방문처리를 해야되는데, 숫자가 1~N까지 고르게 나오는 것이 아닌, 무작위로 나오기 때문에 set을 이용한다.
 * 주의사항으로는 숫자가 크기 때문에 그래프 탐색시에 제한을 두지 않으면 메모리가 터질수도 있다.
 * 따라서 t를 넘지 않도록 해야되며, 노드의 수는 s의 최대값인 10^9를 제곱하는 경우에 터질수도 있기 떄문에,long으로 해야된다.
 */
public class BOJ14395_4연산 {

    //노드를 표현할 객체
    private static class Node{

        long num;
        StringBuilder str;

        Node(long num){
            this.num = num;
            this.str = new StringBuilder();
        }
        Node(long num, String str1, String str2){

            this.num = num;
            this.str = new StringBuilder();
            this.str.append(str1).append(str2);
        }
    }
    //4방 탐색을 할 사칙연산 배열
    private static String[] dOp = new String[]{"*","+","-","/"};

    //숫자와 연산자를 받아서 처리하고 반환값을 주는 메서드
    private static long calNum(long num, String op){

        long result = 0;

        switch (op){
            case "*":
                result = num * num;
                break;
            case "+":
                result = num + num;
                break;
            case "-":
                result = num - num;
                break;
            default:
                result = num / num;
                break;
        }

        return result;
    }

    //bfs탐색을 할 메서드 - 가장 먼저 t에 도달한 노드를 반환한다.
    private static Node bfs(long startNum, long finishNum){

        Set<Long> visited = new HashSet<>();
        visited.add(startNum);

        Queue<Node> needVisited = new ArrayDeque<>();
        needVisited.add(new Node(startNum));

        while(!needVisited.isEmpty()){

            Node currentNode = needVisited.poll();

            //목표 숫자이면 반환
            if(currentNode.num == finishNum){
                return currentNode;
            }

            //다음 탐색을 위한 노드 추가.
            for(int i = 0; i < 4; i++){

                long nextNum = calNum(currentNode.num, dOp[i]);

                if(nextNum == 0) continue;

                if(!visited.contains(nextNum)){
                    visited.add(nextNum);
                    needVisited.add(new Node(nextNum,currentNode.str.toString(), dOp[i]));
                }
            }
        }

        return null;
    }

    //두 수가 같은지 체크하는 배열 - 같으면 true
    private static boolean numberSameCheck(long s, long t){

        if(s == t) return true;

        return false;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long s = Long.parseLong(st.nextToken());
        long t = Long.parseLong(st.nextToken());

        if(numberSameCheck(s,t)){
            System.out.println(0);
            return;
        }

        Node resultNode = bfs(s,t);

        if(resultNode == null){
            System.out.println(-1);
        }
        else{

            System.out.println(resultNode.str);
        }

    }
}
