package baekjoon.baek20220929;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
완탐문제이다
K크기로 윈도우를 잡고, 옆으로 한칸씩 이동하면서 연속으로 먹는 초밥을 구한다.
이동할떄 마다 윈도우의 모든 값을 업데이트 하지 않고, 변화되는 양끝값만 변경해준다.
윈도우를 매번 이동할때마다,  개수를 체크하는 리스트를 이용해서 유니크한 원소의 개수와 쿠폰유무를 확인해서 가지수를 체크해준다.
 */

public class BOJ15961_회전초밥 {

    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());//접시수
        int D = Integer.parseInt(st.nextToken());//초밥의 가짓수
        int K = Integer.parseInt(st.nextToken());//연속해서 먹는 접시의 수
        int C = Integer.parseInt(st.nextToken());//쿠폰번호


        int[] countArray = new int[D+2]; //해당하는 가지수를 먹었는지 안먹었는지 체크.


        //초밥 벨트
        int[] sushiBelt = new int[N];

        for(int i = 0; i < N; i++){
            sushiBelt[i] = Integer.parseInt(br.readLine());
        }

        int start = 0;
        int end = start + K;

        //슬라이딩할 윈도우의 첫 개수 구하기.
        int count = 0; //서로다른 초밥 수 구하기.
        for(int i = start; i < end; i++){
            int tempSushi = sushiBelt[i];

            //해당하는 초밥이 전에 나온적이 없다면 갯수 증가하고 방문처리
            if(countArray[tempSushi] == 0){
                count++;
            }
            countArray[tempSushi] += 1;

        }
        if(countArray[C] == 0){
            result = count+1;
        }
        else{
            result = count; //최대값구하기 위해 저장해둠
        }

        //두번째구간부터 탐색 시작.
        while(true){

            //다음구간으로 이동.
            start++;
            end = (start + K-1)%N;

            //배열을 벗어나면 종료
            if(start == N) break;

            //새로운 초밥을 추가해주고 이전의 초밥을 제거
            countArray[sushiBelt[start-1]] -= 1;

            //제거된 값이 0이 되었다면 -1함
            if(countArray[sushiBelt[start-1]] == 0) count--;

            if(countArray[sushiBelt[end]] == 0){
                count++;
            }
            countArray[sushiBelt[end]] += 1;

            //최대값 업데이트
            if(countArray[C] == 0){
                result = Math.max(result,count+1);
            }
            else{
                result = Math.max(result, count); //최대값구하기 위해 저장해둠
            }

        }

        System.out.println(result);


    }
}
