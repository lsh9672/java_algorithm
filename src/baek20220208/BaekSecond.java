package baek20220208;
/*
* 백준 알고리즘 3003번 - 킹,퀸,룩,비숍,나이트,폰(브론즈5)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BaekSecond {

    public static void main(String[] args) throws Exception{
        //입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //원래 있어야되는 기물의 개수
        //0번인덱스 부터 각각 킹 퀸 룩 비숍 나이트 폰의 개수
        int [] origin_chess = {1,1,2,2,2,8};

        for (int i=0; i < origin_chess.length; i++){

            int temp = origin_chess[i] - Integer.parseInt(st.nextToken());
            System.out.print(temp + " ");
        }

    }
}
