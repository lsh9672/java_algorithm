package baek20220226;
/*
* 백준 7785번 (실버5, 구현연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Second {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        //people info
        Map<String,Integer> access = new HashMap<>();

        StringTokenizer st = null;
        for(int i = 0; i < testCase; i++){

            st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            String accessRecord = st.nextToken();

            if(accessRecord.equals("enter")){
                access.put(name,1);

            }
            else{
                access.remove(name);
            }
        }

        List<String> results = new ArrayList<>(access.keySet());

        Collections.sort(results,Collections.reverseOrder());

        for (String result : results) {
            System.out.println(result);
        }

    }
}
