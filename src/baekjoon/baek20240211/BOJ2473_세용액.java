package baekjoon.baek20240211;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 투포인터 응용
 * 두용액과 달리 세용액을 뽑아야 한다.
 * 투포인터를 활용하기 위해 먼저 정렬을 한다.
 * 두 용액은 양쪽 끝점으로 인덱스를 잡고 하나의 용액은 나머지 점을 잡는다.
 * 양쪽끝에서 인덱스를 증가시키거나 감소시키면서 답을 찾는건 동일하고, 나머지 한점만, 매번 바뀐다.
 * 첫 용액 0, 세번째 용액 lastindex인 상태로 시작하고 두번째용액의 인덱스는 1,2,3...lastIndex - 1까지
 * 바꿔가서면 모든 경우를 따진다.
 * 대략 n^2의 시간을 가지게 된다.
 *
 * (주의 사항)
 * 주어지는 수가 10억 또는 -10억까지 주어진다.
 * 이전의 두용액의 경우, 두 수의 합이라서 int로도 충분히 가능한 범위였지만,
 * 이 문제는 세개의 수를 더하기 때문에 21억을 넘게된다.
 * 따라서 long으로 해결해야 한다.
 */
public class BOJ2473_세용액 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] numArray = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            numArray[i] = Integer.parseInt(st.nextToken());
        }


        Arrays.sort(numArray);//투 포인터 구현을 위해 정렬
        long compareAbs = 3_000_000_000L; //3개의 값을 더하기 때문에 30억을 넘지 않음.

        //인덱스 설정
        int start = 0;
        int end = n - 1;


        int[] result = new int[3];//출력할 배열.

        // 중간 인덱스는 n - 2까지
        loop:
        for(int mid = 1; mid <= n - 2; mid++){

            start = 0;
            end = n - 1;

            //mid를 넘어가기 전까지 반복,
            while(start < mid && end > mid){

                //0에 가깝다 -> 절대값으로 계산해서 0으로 부터 떨어진 거리를 계산.
                long sum = (long)numArray[start] + (long)numArray[mid] + (long)numArray[end];
                long distance = Math.abs(sum);

                //구한 절대 값이 0이면 답 출력하면 됨.
                if(distance == 0){
                    result[0] = numArray[start];
                    result[1] = numArray[mid];
                    result[2] = numArray[end];
                    break loop;
                }

                //이전에 저장한 값보다 작다면 저장.
                if(compareAbs > distance){
                    compareAbs = distance;
                    result[0] = numArray[start];
                    result[1] = numArray[mid];
                    result[2] = numArray[end];

                }

                if(sum > 0){
                    end--;
                }
                else{
                    start++;
                }
            }
        }


        Arrays.sort(result);
        System.out.println(result[0] + " " + result[1] + " " + result[2]);

    }
}
