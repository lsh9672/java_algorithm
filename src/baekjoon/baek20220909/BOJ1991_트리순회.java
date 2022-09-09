package baekjoon.baek20220909;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1991_트리순회 {

    static int N;

    static Node[] graph;

    static StringBuilder result;

    //알파벳 -> 숫자
    static int alphaToNum(char alpha){
        return alpha - 'A' + 1;
    }

    //숫자 -> 알파벳
    static char NumToAlpha(int num){
        return (char)(num + 'A');
    }


    //전위순회 - 루트,좌,우
    static void preOrder(char currentNode){

        if(currentNode == '.') return;

        result.append(currentNode);

        preOrder(graph[alphaToNum(currentNode)].leftChild); //좌
        preOrder(graph[alphaToNum(currentNode)].rightChild); //우
    }

    //중위 순회 - 좌,루트,우
    static void inOrder(char currentNode){
        if(currentNode == '.') return;

        inOrder(graph[alphaToNum(currentNode)].leftChild); //좌

        result.append(currentNode);

        inOrder(graph[alphaToNum(currentNode)].rightChild); //우
    }

    //후위 순회 - 좌,우,루트
    static void postOrder(char currentNode){
        if(currentNode == '.') return;

        postOrder(graph[alphaToNum(currentNode)].leftChild); //좌

        postOrder(graph[alphaToNum(currentNode)].rightChild); //우

        result.append(currentNode);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        graph = new Node[N+1]; //이진트리이므로 1차원 배열에 저장.

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            char parentNode = st.nextToken().charAt(0);
            char leftChild = st.nextToken().charAt(0);
            char rightChild = st.nextToken().charAt(0);

            graph[alphaToNum(parentNode)] = new Node(leftChild,rightChild);
        }

        //전위 순회
        result = new StringBuilder();
        preOrder('A');
        System.out.println(result);

        //중위 순회
        result = new StringBuilder();
        inOrder('A');
        System.out.println(result);

        //후위 순회
        result = new StringBuilder();
        postOrder('A');
        System.out.println(result);


    }

    static class Node{
        char leftChild, rightChild;

        Node(char leftChild, char rightChild){
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }
}
