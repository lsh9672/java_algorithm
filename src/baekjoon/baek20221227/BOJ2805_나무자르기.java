package baekjoon.baek20221227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 12. 27.
 */

/**
 * 아이디어
 * 파라메트릭 서치의 대표적인 유형
 * 나무의 최대 길이는 20억이므로, 일일이 모든 높이에 대해서 계산해볼 수 없음(또한 long으로 계산해야됨.)
 * 따라서 이를 빠르게 찾기 위해 이분탐색을 사용해야 한다.
 * 단순 이분탐색으로 하게 되면, 15라는 높이를 찾았을때  M미터의 나무를 절단해서 가져갈수 있다는 것을 찾았다고 가정했을떄,
 * 16,17도 M미터의 나무를 절단해서 가져갈 수 있는 절단기의 높이가 될수 있다.
 * 따라서 단순 이분탐색처럼 하나의 수를 찾는것에서 그치는 것이 아닌, 최대 절단기의 높이를 구하는 것이므로, 구할 수 있는 절단기의 높이중, 적어도 M만큼 자를 수 있는 최대 길이의 상한선을 구해야한다
 * 그렇기 때문에 이문제는 결정문제인 파라메트릭 서치가 된다.
 */

public class BOJ2805_나무자르기 {

    static int N;
    static int M;
    static int[] treeHeight;

    //이분탐색으로 절단기의 높이를 정하면, 주어진 나무를 다 잘라보고 얼마의 나무가 남는지 계산해서 리턴
    static long treeCount(long height){
        long returnCount = 0;

        for(int i = 0; i < N; i++){

            //절단기의 높이가 나무 높이보다 작을때만.
            if(treeHeight[i] > height) returnCount += treeHeight[i] - height;
        }

        return returnCount;
    }

    //높이를 이분탐색으로 선택해야됨.
    static long binarySearch(long start, long end){

        //start값이 end와 같아지거나 end보다 커지면 end값을 리턴(상한선을 구하는 것이므로.)
        if(start >= end) return end;

        //이분탐색 구하듯, 중간값 구해봄
        long mid = (start + end) / 2;

        //구한 절단기 높이로 가져갈 수 있는 나무의 높이를 계산해봄
        long getTree = treeCount(mid);

        //가져갈수 있는 높이가 목표 높이인 m보다 작다면 절단기 높이를 좀 더 낮게 해서 나무를 더 많이 남겨야됨
        //mid-1이 아닌 이유 :
        if(getTree < M) return binarySearch(start, mid);

        //목표 높이이거나 그것보다 크다면, 절단기 크기를 늘려봐야됨. - 설정 가능한 최대 높이를 구해야 하기 때문.
        else return binarySearch(mid+1, end);
    }

   public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());//나무 높이 받기
        treeHeight = new int[N];
       for(int i = 0; i < N; i++){
           treeHeight[i] = Integer.parseInt(st.nextToken());
       }

       //상한선이므로, 원래 구해야되는 값보다 하나 더 큰값을 리턴하기 때문에 -1을 함.
       System.out.println(binarySearch(0,(long)Integer.MAX_VALUE)-1);
    }
}
