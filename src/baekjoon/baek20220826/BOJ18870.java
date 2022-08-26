package baekjoon.baek20220826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ18870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        Map<Integer,Integer> numCount = new HashMap<>();

        int[] numArray = new int[N];

        int[] sortedNum = new int[N];

        for(int i = 0; i < N; i++){
            numArray[i] = Integer.parseInt(st.nextToken());
        }

        System.arraycopy(numArray,0,sortedNum,0,N);

        Arrays.sort(sortedNum);

        int count = 0;
        for(int temp : sortedNum){
            //숫자를 포함하고 있지 않으면 새로 생성하고 count값 저장 - 포함하고 있으면 중복되기 때문에 패스
            if(!numCount.containsKey(temp)){
                numCount.put(temp,count);
                count++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int result : numArray){
            sb.append(numCount.get(result)).append(" ");
        }
        sb.setLength(sb.length()-1);
        System.out.println(sb);


    }

}
