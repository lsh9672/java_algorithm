package baek20220511;

/**
 * 백준 1822번 (자바, 자료구조 연습)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class First {
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());

       int aN = Integer.parseInt(st.nextToken());
       int bN = Integer.parseInt(st.nextToken());

        Set<Integer> aSet = new HashSet<>();
        Set<Integer> bSet = new HashSet<>();

        StringTokenizer aToken = new StringTokenizer(br.readLine());
        StringTokenizer bToken = new StringTokenizer(br.readLine());

        for(int i = 0; i < aN ; i++){

            aSet.add(Integer.parseInt(aToken.nextToken()));

        }

        for(int j = 0; j < bN; j++){

            bSet.add(Integer.parseInt(bToken.nextToken()));
        }

        aSet.removeAll(bSet);

        if(aSet.size() == 0){
            System.out.println(0);
        }
        else{
            System.out.println(aSet.size());
            StringBuilder sb = new StringBuilder();
            List<Integer> temp = new ArrayList(aSet);
            Collections.sort(temp);

            for (Integer integer : temp) {
                sb.append(integer).append(" ");
            }
            System.out.println(sb);
        }
    }
}
