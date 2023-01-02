package baekjoon.baek20230102;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 23. 1. 2.
 */

/**
 * 아이디어
 * 1. 문제를 보면, 트리의 중회순회를 설명하고 있다. (왼쪽 - 루트 - 오른쪽)
 * 2. 루트가 가운데 오기 때문에 루트는 노드갯수/2한 인덱스가 루트 노드이다.
 * 3. 루트를 구했으면, 나머지 서브트리에서 루트를 구해서 출력형식에 맞춰서 누적해준다.
 * 4. 출력은 각 레벨마다 노드를 출력해야 하기 때문에 StringBuilder 배열을 이용한다.
 */
public class BOJ9934_완전이진트리 {

    static int K;
    static int[] nodeArrays;
    static StringBuilder[] treeResult;


    //재귀를 돌면서 해당 서브트리에서 루트 노드 찾기
    static void makeTree(int start,int end, int level){
        //start인덱스가 end인덱스와 같거나 스타트 보다 커지면 더 진행 불가
        if(start >= end){
            treeResult[level].append(nodeArrays[end]).append(" ");
            return;
        }

        //루트인덱스 구하기(중간 값)
        int mid = (start + end) / 2;
        int root = nodeArrays[mid];

        //루트 구했으면 추가.
        treeResult[level].append(root).append(" ");

        //왼쪽
        makeTree(start,mid-1, level+1);

        //오른쪽
        makeTree(mid+1,end,level+1);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        K =Integer.parseInt(br.readLine()); //트리의 깊이
        nodeArrays = new int[(int)Math.pow(2,K) - 1]; //중위 순회시에 노드의 순서


        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < nodeArrays.length; i++){
            nodeArrays[i] = Integer.parseInt(st.nextToken());
        }

        treeResult = new StringBuilder[K]; //결과를 출력할 스트링 빌더.
        for(int i = 0; i < K; i++){
            treeResult[i] = new StringBuilder();
        }
        makeTree(0, nodeArrays.length - 1, 0);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < treeResult.length; i++){
            sb.append(treeResult[i].toString()).append("\n");
        }

        System.out.println(sb);
    }
}
