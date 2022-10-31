package baekjoon.baek20221026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 26.
 */

/**
 * 문제에서 동전들은 전부 배수 관계라고 했기때문에 이 문제는 디피가 아닌 그리디이다.
 * 동전의 최소값을 구하는 문제이므로 가장 큰 가치를 가진 동전부터 작은 가치로 내려가면서 돈을 준다.
 */

public class BOJ11047_동전 {

    static int N,K;
    static int[] coin;


    static int recursive(int remainMoney, int count, int idx){

        if(remainMoney == 0){
            return count;
        }

        return recursive(remainMoney%coin[idx],count+remainMoney/coin[idx], idx-1);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coin = new int[N];

        for(int i = 0; i < N; i++){
            coin[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(recursive(K,0,N-1));

    }
}
