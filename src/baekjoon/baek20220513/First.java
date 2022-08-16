package baekjoon.baek20220513;

/**
 * 백준 13414번 (자바, 자료구조 연습)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class First {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        //순서를 유지하면서 중복을 없애줌
        Set<String> waitSet = new LinkedHashSet<>();

        for(int i = 0; i < l; i++){
            String temp = br.readLine();

            if (waitSet.contains(temp)){
                waitSet.remove(temp);
            }

            waitSet.add(temp);
        }

        Queue<String> resultList = new LinkedList<>(waitSet);
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < k; i++){
            if (!resultList.isEmpty()){
                result.append(resultList.poll()).append("\n");
            }

        }

        result.deleteCharAt(result.lastIndexOf("\n"));
        System.out.println(result);
    }
}
