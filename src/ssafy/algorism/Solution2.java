package ssafy.algorism;

import java.io.*;
import java.util.*;

public class Solution2 {

    static Map<Character,String> numberMapping = new HashMap<>(){{
        put('0',"zero");
        put('1',"one");
        put('2',"two");
        put('3',"three");
        put('4',"four");
        put('5',"five");
        put('6',"six");
        put('7',"seven");
        put('8',"eight");
        put('9',"nine");
    }};

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/ssafy/algorism/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;


        int T = Integer.parseInt(br.readLine());
        for(int testCase = 1; testCase <= T; testCase++){
            List<Value> numList = new ArrayList<>();

            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            for(int i = N; i <= M; i++){

                //입력받은 숫자를 쪼개서
                char[] temp = String.valueOf(i).toCharArray();
                StringBuilder sb = new StringBuilder();

                for(char ttt : temp){
                    sb.append(numberMapping.get(ttt)).append(" ");
                }
                sb.setLength(sb.length()-1);

                numList.add(new Value(sb.toString(), i));
            }

            numList.sort(Comparator.naturalOrder());

            System.out.println("#"+testCase);
            StringBuilder result = new StringBuilder();
            for(int i = 0; i < numList.size(); i++){
                if(i != 0 && i % 10 == 0){
                    result.setLength(result.length()-1);
                    result.append("\n");
                }
                result.append(numList.get(i).intValue).append(" ");
            }

            System.out.println(result);
        }

    }

    static class Value implements Comparable<Value>{
        String strValue;
        int intValue;

        Value(String strValue, int intValue){
            this.strValue = strValue;
            this.intValue = intValue;
        }

        @Override
        public int compareTo(Value value) {
            return this.strValue.compareTo(value.strValue);
        }
    }
}
