package baekjoon.baek20220808;

/**
 * BOJ 1158번
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class First {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        sb.append("<");

        List<Integer> numList = new ArrayList<>();

        for(int i = 1; i < N+1; i++){
            numList.add(i);
        }

        int currentIndex = 0;

        while(!numList.isEmpty()){

            currentIndex = (currentIndex + K - 1) % numList.size();
            sb.append(numList.get(currentIndex)).append(", ");
            numList.remove(currentIndex);
        }

        sb.setLength(sb.length()-2);

        sb.append(">");
        System.out.println(sb);

    }
}
