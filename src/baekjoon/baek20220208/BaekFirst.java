package baekjoon.baek20220208;
/*
* 백준알고리즘 2914번 - 저작권(구현문제- 브론즈5)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BaekFirst {

    public static void main(String[] args) throws Exception {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        //앨범에 수록된 곡의 개수 * (평균-1)
        //평균을 반올림 했으니 - 1해서 계산한다.
        //결과 값에 +1을 하면 올림했을때 평균값이 나온다.
        int [] temp = new int[2];
        for (int i = 0 ; i < 2; i++) {
            temp[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println((temp[0]*(temp[1]-1))+1);
    }
}
