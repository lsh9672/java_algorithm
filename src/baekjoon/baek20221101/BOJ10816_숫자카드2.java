package baekjoon.baek20221101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 11. 1.
 */

/**
 * 1.숫자카드1과 유사하지만, 중복카드가 존재하고 몇개 존재하는지 세어야됨.
 * 2. 이런 유형은 계수정렬을 이용해서 빠르게 셀수도 있지만 이진탐색을 이용해보도록 함.
 * 3. lower bound - 하한(찾고자 하는 수 이상의 값이 처음으로 나타나는 위치), upper bound(찾고자 하는 값을 초과한 값을 처음만나는 위치.)
 */

public class BOJ10816_숫자카드2 {

    static int lowerBoundBinarySearch(int[] cardArray, int target, int start, int end){
        if(start >= end) return start;

        int mid = (start + end)/2;

        if(target <= cardArray[mid]) return lowerBoundBinarySearch(cardArray, target, start, mid);

        else return lowerBoundBinarySearch(cardArray,target,mid+1, end);
    }

    static int upperBoundBinarySearch(int[] cardArray, int target, int start, int end){
        if(start >= end) return end;

        int mid = (start + end) / 2;

        if(target < cardArray[mid]) return upperBoundBinarySearch(cardArray,target,start,mid);

        else return upperBoundBinarySearch(cardArray,target,mid+1,end);
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

        Arrays.sort(cardArray);

        //각 수가 나타나는 개수는 (상한 - 하한)
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        StringBuilder result = new StringBuilder();

        for(int i = 0; i < M; i++){
            int target = Integer.parseInt(st.nextToken());
            result.append(upperBoundBinarySearch(cardArray,target,0,N) - lowerBoundBinarySearch(cardArray,target,0,N-1)).append(" ");
        }

        System.out.println(result);

    }
}
