package baekjoon.baek20221102;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 31.
 */
public class BOJ2805_나무_자르기 {

    //자른나무의 길이 확인.
    static long cutTreeLength(int[] treeHeight, long target){
        long count = 0;

        for(int i = 0; i < treeHeight.length; i++){

            if(treeHeight[i] > target){
                count += treeHeight[i] - target;
            }
        }

        return count;
    }


    static long upperBoundBinarySearch(int[] treeHeight, long target, long start, long end){

        if(start >= end) return end;

        long mid = (start + end) / 2;

        //목표 길이가 안되면 절단기의 높이를 낮게 해서 잘린 나무 조각의 길이를 길게 해야됨.
        if(cutTreeLength(treeHeight, mid) < target) return upperBoundBinarySearch(treeHeight,target,start, mid);

        else return upperBoundBinarySearch(treeHeight,target,mid+1, end);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        int[] treeHeight = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            treeHeight[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(upperBoundBinarySearch(treeHeight, M, 0, (long)Integer.MAX_VALUE + 1) - 1);



    }
}
