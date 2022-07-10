package baek20220710;

/**
 * 백준 11286번 (자료구조연습,실버1)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class First {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Queue<Integer[]> num_queue = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] t1, Integer[] t2) {
                if(t1[0] < t2[0]){
                    return -1;
                }
                else if(t1[0] > t2[0]){
                    return 1;
                }
                else{
                    if (t1[1] < t2[1]){
                        return -1;
                    }
                    else{
                        return 1;
                    }
                }
            }
        });

        StringBuilder result = new StringBuilder();

        for(int i = 0; i < n; i++){
            int temp = Integer.parseInt(br.readLine());

            if(temp == 0){
                if (num_queue.size() != 0){
                    result.append(num_queue.poll()[1]);
                }
                else{
                    result.append(0);
                }
                result.append("\n");

            }
            else{
                Integer[] node = new Integer[2];
                node[0] = Math.abs(temp);
                node[1] = temp;

                num_queue.add(node);
            }
        }

        result.setLength(result.length()-1);
        System.out.println(result);
    }
}
