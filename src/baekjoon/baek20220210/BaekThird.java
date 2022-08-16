package baekjoon.baek20220210;

/*
* 백준 2455번 지능형 기차 - 구현연습*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class BaekThird {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //최대 사람수 저장
        int peopleNum = 0;

        //현재 기차사람수
        int currentNum = 0;

        for(int i=0; i<4; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            //내린수
            int outNum = Integer.parseInt(st.nextToken());

            //탄수
            int inNum = Integer.parseInt(st.nextToken());

            currentNum += inNum - outNum;

            if (peopleNum < currentNum) {
                peopleNum = currentNum;
            }
        }

        System.out.println(peopleNum);

    }
}
