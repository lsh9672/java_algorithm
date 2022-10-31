package baekjoon.baek20221031;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 31.
 */

/**
 * 상근이의 카드 최대수가 50만개이므로 순차 탐색하면 50만*50만으로 터져버린다
 * 따라서 정렬후에 이진탐색을 통해서 logn으로 시간 복잡도를 줄여야 한다.
 */

public class BOJ10815_숫자카드 {

    //cardArray에서 target이 있는지 탐색.
    static boolean binarySearch(int[] cardArray, int target, int start, int end){

        if(start > end) return false;

        int mid = (start + end)/ 2;

        if(target == cardArray[mid]) return true;

        else if(target < cardArray[mid]) return binarySearch(cardArray,target,start,mid-1);

        else return binarySearch(cardArray,target,mid+1,end);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int[] cardArray = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            cardArray[i] = Integer.parseInt(st.nextToken());
        }
        /*
        * 1.이분 탐색을 위해 정렬
        * 2.이분 탐색 로직 메서드로 작성
        * 3. 입력으로 주어지는 숫자로 이분탐색
        * 4. 이분탐색 결과, 해당 숫자를 찾았다면 true, 못찾았다면 false를 리턴하도록 함.
        * */

        Arrays.sort(cardArray); //이분 탐색을 위한 정렬

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        StringBuilder result = new StringBuilder();

        for(int i = 0; i < M; i++){
            int temp = Integer.parseInt(st.nextToken());

            if(binarySearch(cardArray,temp,0,N-1)){
                result.append(1).append(" ");
            }
            else{
                result.append(0).append(" ");
            }
        }

        System.out.println(result);


    }
}
