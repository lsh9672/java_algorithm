package baekjoon.baek20221005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 5.
 */
public class BOJ19951_태상이의_훈련소_생활 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //연병장 크기
        int M = Integer.parseInt(st.nextToken()); //조교의 수

        int[] numArray = new int[N+1]; //연병장 정보 -
        st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++){
            numArray[i] = Integer.parseInt(st.nextToken());
        }

        int[] tempArray = new int[N+2]; //누적합을 할 배열. - 누적합을 이용하기 위해 한칸 더 크게 잡음.
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); //시작칸
            int b = Integer.parseInt(st.nextToken()); //끝칸
            int k = Integer.parseInt(st.nextToken()); // 늘거나 줄어들어야 하는 양

            tempArray[a] += k;
            tempArray[b+1] += (-1) * k;
        }

        int temp = 0;
        for(int i= 1; i <= N; i++){
            temp += tempArray[i]; //temp에 계속 누적시킴
            numArray[i] += temp; //실제 배열에 계속 누적되고 있는 값을 더해줌.
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++){
            sb.append(numArray[i]).append(" ");
        }
        System.out.println(sb);

    }
}
