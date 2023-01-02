package baekjoon.baek20230102;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 23. 1. 2.
 */
/*
아이디어
1. 1024*1024*1024 => 대략 1000000000(10억) 따라서 랜선길이를 순차적으로 증가시키는 방법으로는 문제를 해결 할 수 없다.
2. 그렇기 때문에 이문제는 이분탐색을 이용하여 랜선의 길이를 선택해야한다.
3. 문제에서 조건에 맞는 랜선의 길이중 최대 길이를 선택하라고 했기 때문에, 파라메트릭 서치를 이용해서 상한선을 구해야 한다.

유사문제: BOJ2805 - 나무자르기.
 */

public class BOJ1654_랜선자르기 {

    static int K;
    static int N;
    static int[] lanCable;

    //주어진 랜선길이로, 몇개의 랜선을 만들 수 있는지
    static long makeLan(long lanSize){

        long count = 0;

        //반복문을 돌면서 해당 랜 사이즈로 각각의 랜 케이블을 자르면 몇개가 나오는지 나누기를 이용해서 누적
        for(int i = 0; i < K; i++){
            count += lanCable[i] / lanSize;
        }

        return count;
    }

    //이분탐색을 이용해서 조건에 맞는 랜선 길이를 찾기
    static long binarySearch(long start, long end){

        if(start >= end) return end;

        long mid = (start + end) / 2; // 길이 선택

        long cableCount = makeLan(mid);//해당 길이로 케이블을 만들면 몇개가 나오는지 확인

        //해당길이로 만든 케이블 수가 목표치와 같더나 목표치보다 더 많다면, 더 길게 잘라야 됨.
        if(cableCount >= N) return binarySearch(mid+1, end);
        //목표치보다 작으면 좀더 작은 길이로 잘라야 케이블이 많이 나옴
        else return binarySearch(start, mid);
    }


   public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken()); //가지고 있는 랜선의 개수
        N = Integer.parseInt(st.nextToken()); // 필요한 랜선의 개수


        //가지고 있는 랜선의 각각의 길이.
        lanCable = new int[K];
        for(int i = 0; i < K; i++){
            lanCable[i] = Integer.parseInt(br.readLine());
        }

       System.out.println(binarySearch(0,(long)Integer.MAX_VALUE + 1)-1);

    }
}
