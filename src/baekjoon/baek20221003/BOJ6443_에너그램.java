package baekjoon.baek20221003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


public class BOJ6443_에너그램 {

    static char[] check;

    static void recursive(char[] originStr, boolean[] visited, char[] value, int idx){

        if(idx == originStr.length){
            StringBuilder sb = new StringBuilder();
            for(char t : value){
                sb.append(t);
            }
            System.out.println(sb);
            return;
        }

        check[idx] = 0;
        for(int i = 0; i < originStr.length; i++){
            if(!visited[i] && check[idx] < originStr[i]){

                check[idx] = originStr[i];

                visited[i] = true;
                value[idx] = originStr[i];
                recursive(originStr, visited, value,idx+1);
                visited[i] = false;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++){
            char[] temp = br.readLine().toCharArray();
            check = new char[temp.length];
            Arrays.sort(temp);
            recursive(temp,new boolean[temp.length],new char[temp.length],0);

        }


    }
}
