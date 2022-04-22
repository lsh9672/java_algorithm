package baek20220422;

/**
 * 백준 4358번(자료구조 연습)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Second {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<String,Integer> tree_map = new HashMap<>();

        int total_length = 0;

        while(true){
            String tree_name = br.readLine();

            if(tree_name == null || tree_name.length() == 0) break;

            total_length++;
            //포함되어있지 않으면 추가
            if (!tree_map.containsKey(tree_name)){
                tree_map.put(tree_name,1);
            }
            else{
                tree_map.put(tree_name,tree_map.get(tree_name)+1);
            }

        }

        List<String> names = new ArrayList<>(tree_map.keySet());

        Collections.sort(names);

        for (String name : names) {
            System.out.println(name+ " "+ String.format("%.4f",((double)tree_map.get(name)/(double)total_length)*100));
        }
    }
}
