package baekjoon.baek20220422;

/**
 * 백준 1269번(자료구조 연습)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class First {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        Set<Integer> a_set = new HashSet<>();
        Set<Integer> b_set = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i< a; i++){
            a_set.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i< b; i++){
            b_set.add(Integer.parseInt(st.nextToken()));
        }

        //b-a
        int count_b = b_set.size();
        for (Integer temp : a_set) {
            if (b_set.contains(temp)){
                count_b--;
            }
        }

        int count_a = a_set.size();
        for (Integer temp : b_set) {
            if (a_set.contains(temp)){
                count_a--;
            }
        }

        System.out.println(count_a+count_b);

    }
}
