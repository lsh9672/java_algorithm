package baekjoon.baek20220817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1992 {

    static String[][] maps;
    static StringBuilder sb;

    //해당 영역이 압축이 되는지 확인하는 메서드
    static boolean check(int n, int x, int y){

        //비교할 기준값 정함
        String checkValue = maps[x][y];

        //해당 영역 살피면서 압축이 가능한지 확인.
        for(int i = x; i < x+n; i++){
            for(int j = y; j < y+n; j++){
                //값이 다르다면 압축 불가
                if(!checkValue.equals(maps[i][j])) return false;
            }
        }

        return true;
    }

    static void recursive(int n, int x, int y){

        //해당영역이 압축이 가능하다면 sb에 해당 값을 이어 붙이고 종료
        if(check(n,x,y)){
            sb.append(maps[x][y]);
            return;
        }

        //4분할 값 시작지점에 괄호가 있어야됨
        //예. ( 1사분면 - 2사분면 - 3사분면 - 4사 분면)
        sb.append("(");

        // 해당 영역이 압축이 불가능하다면 4분할함
        // 분할 순서는 1 - 2 - 3 - 4사분면 순서임

        //1사분면
        recursive(n/2, x,y);
        //2사분면
        recursive(n/2, x,y+n/2);
        //3사분면
        recursive(n/2, x+n/2,y);
        //4사분면
        recursive(n/2, x+n/2,y+n/2);

        //각 영역의 분할이 끝나면 괄호를 닫아줘야됨.
        sb.append(")");


    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        maps = new String[N][N];

        for(int i = 0; i < N; i++){
            maps[i] = br.readLine().split("");
        }

        sb = new StringBuilder();

        recursive(N,0,0);

        System.out.println(sb);
    }
}
