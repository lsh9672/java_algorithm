package programmers.prog20221018;

import java.util.Arrays;

//소요시간 약 30분, 참조 X, int-> binary로 바꿔주는 함수가 핵심
//다른사람은 둘중하나 벽 -> 벽, 둘다 공백 -> 공백 => 이부분을 or연산으로 처리해버림.

/**
 * @author : sh Lee
 * @date : 22. 10. 18.
 */
public class 카카오2018공채_비밀지도 {


    static String intToBinary(int n, int num){

        //n은 자리수
        //num은 변경할 숫자.

        String strNum = Integer.toBinaryString(num);

        //붙여야 되는 길이
        int appendLen = n - strNum.length();

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < appendLen; i++){
            sb.append("0");
        }

        return sb.toString() + strNum;
    }


    // n : 한변의 길이,
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        String[] newArray1 = new String[n];
        String[] newArray2 = new String[n];

        for(int i = 0; i < n; i++){
            newArray1[i] = intToBinary(n,arr1[i]);
            newArray2[i] = intToBinary(n,arr2[i]);
        }


        for(int i = 0; i < n; i++){
            StringBuilder sb = new StringBuilder();

            for(int j = 0; j < n; j++){

                //둘중에 하나라도 벽이면 벽
                if(newArray1[i].charAt(j) == '1' || newArray2[i].charAt(j) == '1'){
                    sb.append('#');
                }
                //둘다 공백이면 공백
                else if(newArray1[i].charAt(j) == '0' && newArray2[i].charAt(j) == '0'){
                    sb.append(' ');
                }
            }

            answer[i] = sb.toString();
        }


        return answer;
    }

    public static void main(String[] args) {

        카카오2018공채_비밀지도 s = new 카카오2018공채_비밀지도();

        int n1 = 5;
        int[] arr11 = {9, 20, 28, 18, 11};
        int[] arr12 = {30, 1, 21, 17, 28};
        System.out.println(Arrays.toString(s.solution(n1,arr11,arr12)));

        int n2 = 6;
        int[] arr21 = {46, 33, 33, 22, 31, 50};
        int[] arr22 = {27, 56, 19, 14, 14, 10};
        System.out.println(Arrays.toString(s.solution(n2,arr21,arr22)));

    }
}
