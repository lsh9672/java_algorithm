package baekjoon.baek20220303;
/*
* 백준 1236번 (자바 구현연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class First {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //가로 세로
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        //성의 좌표
        String[] castle = new String[n];

        //놓아야 되는 경비병숫자 - 입력시에 가로줄만우선 보고 판단
        int nCount = 0;

        //성 만들기
        for(int i = 0; i < n; i++){
            castle[i] = br.readLine();
            //가로줄에 경비가 있는지 판단 - 없으면 count 증가
            if(castle[i].contains("X") == false) nCount++;
        }

        /*처리로직*/
        //입력시 가로줄을 확인해서 카운트를 했고, 여기서는 비어있는 세로줄을 센다.
        //성에 빈틈없이 채우려면, 두 값중에 큰값을 선택하면 된다.

        int mCount = 0;
        for(int x = 0; x < m; x++){
            if(castle[0].charAt(x) == 'X') continue;

            boolean check = true;
            //빈공간이면 해당위치에서 아래로 내려감(세로탐색)
            for(int y = 0; y < n; y++){
                if(castle[y].charAt(x) == 'X'){
                    check = false;
                    break;
                }
            }
            //중간에 경비병이 없으면 count 증가
            if(check == true) mCount++;
        }

        //둘중 큰쪽 출력
        System.out.println(Math.max(nCount,mCount));

    }
}