package baek20220805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Third {
    static Map<Character,Integer> mapping;
    static Character[] temp = {'A','C','G','T'};

    //인덱스에 문자 매핑
    static int[] charNum;
    // 각 부분 문자열 마다 문자의 개수 셀 배열
    static int[] countArrays;

    //입력으로 받은 문자열
    static String inputStr;

    static boolean passwdCheck(int start, int end) {
//		int[] countArrays = new int[4];

        //이전에 탐색한 값중 마지막 값 뺴기
        countArrays[mapping.get(inputStr.charAt(start-1))]--;
        //새로 들어온 값 더해주기
        countArrays[mapping.get(inputStr.charAt(end-1))]++;



        //가능한 문자의 개수와 비교
        for(int i = 0; i < 4; i++){
            if(countArrays[i] < charNum[i]) {
                return false;
            }
        }
        return true;

    }

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        // 문자열 총길이
        int S = Integer.parseInt(st.nextToken());
        //부분 문자열의 길이
        int P = Integer.parseInt(st.nextToken());

        inputStr = br.readLine();

        mapping = new HashMap<>();
        charNum = new int[4];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++) {
            mapping.put(temp[i], i);
            charNum[i] = Integer.parseInt(st.nextToken());
        }


        int startIdx = 0;
        int endIdx = startIdx+P;

        int result = 0;

        //첫번째 문자열의 갯수 구해두기
        countArrays = new int[4];

        String startStr = inputStr.substring(startIdx, endIdx);
        for(int i = startIdx; i< endIdx; i++) {
            countArrays[mapping.get(startStr.charAt(i))]++;
        }

        //첫번째는 미리 비교
        //가능한 문자의 개수와 비교
        boolean flag = false;
        for(int i = 0; i < 4; i++){
            if(countArrays[i] < charNum[i]) {
                flag = true;
                break;
            }
        }
        if(!flag) result++;

        startIdx++;
        endIdx++;

        // 슬라이딩 윈도우를 이동하면서 문자의 개수는 startIdx의 문자를 빼고 endIdx를 더하면 됨.
        while(true) {

            if(endIdx > S) break;

            if(passwdCheck(startIdx, endIdx)) result++;

            startIdx++;
            endIdx++;
        }

        System.out.println(result);

    }

}