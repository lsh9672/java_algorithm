package baek20220425;

/**
 * 백준 2002번 (자바 자료구조 연습)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class Second {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int carNum = Integer.parseInt(br.readLine());

        Map<String,Integer> inCars = new HashMap<>();

        Integer[] carOrder = new Integer[carNum];

        int result = 0;
        for(int i = 1; i <= carNum; i++){
            inCars.put(br.readLine(),i);
        }
        for(int i = 0; i < carNum; i++){
           String car = br.readLine();

           carOrder[i] = inCars.get(car);
        }

        for(int i = 0; i < carNum; i++){
            for (int j = i+1; j < carNum; j++){
                if(carOrder[i] > carOrder[j]){
                    result++;
                    break;
                }
            }

        }

        System.out.println(result);


    }
}
