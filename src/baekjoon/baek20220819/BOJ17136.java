package baekjoon.baek20220819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17136 {

    static int[][] maps;
    // 색종이 타입 - 인덱스 1번부터 사용(1*1, 2*2,3*3,4*4,5*5)
    static int[] paperType = {0,5,5,5,5,5};
    static int count;

    //색종이가 남아있는지 없는지 확인.
    static boolean paperNum() {
        for(int i = 1; i <=5; i++) {
            if(paperType[i] >= 1) {
                return true;
            }
        }
        return false;
    }

    // 전체에 1이 남아있는지 확인
    static boolean checkOne() {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j< 10; j++) {
                if(maps[i][j] == 1){
                    return false;
                }
            }
        }
        return true;
    }


    // 주어진 시작위치로부터 주어진 크기의 색종이를 붙임.
    static void setPaper(int x, int y, int size) {
        for(int i = x; i < x+size; i++) {
            for(int j = y; j< y+size; j++) {
                maps[i][j] = 0;
            }
        }
    }
    //붙였던 종이를 떼는 메서드.
    static void removePaper(int x, int y, int size) {
        for(int i = x; i < x+size; i++) {
            for(int j = y; j< y+size; j++) {
                maps[i][j] = 1;
            }
        }
    }

    //해당 사이즈의 색종이를 붙일수 있는지 확인하는 메서드
    static boolean checkPaper(int x, int y, int size) {
        for(int i = x; i < x+size; i++) {
            for(int j = y; j < y+size; j++) {
                if(i >= 10 || j >= 10) {
                    return false;
                }
                if(maps[i][j] != 1) {
                    return false;
                }

            }
        }
        return true;
    }

    static void recursive(int x, int y,int value) {

        if(x >= 10 && y >= 10) {
            return;
        }

        //최소값과 비교해서 value가 최소값보다 크다면 종료
        if(count < value) {
            return;
        }

        //전체를 확인해서 모든 1을 덮었는지 확인.
        if(checkOne()) {
            //덮었다면, 갯수 업데이트
            count = Integer.min(count,value);
            return;
        }

        //색종이가 남아있는지 확인.
        //남아있지 않다면.
        if(!paperNum()) {

            return;
        }

        if(y >= 10) {
            recursive(x+1,0,value);
            return;
        }


        // 1이라면 색종이를 놓아봄
        if(maps[x][y] == 1) {
            // 색종이를 놓을때는 작은것 부터 순차적으로 해서 전부 놓아봄.
            for(int type = 5; type >= 1; type--) {
                // 해당 색종이를 붙일수 있는 지 확인.
                if(paperType[type] >= 1 && checkPaper(x,y,type)) {
                    paperType[type]--;
                    //종이를 붙임.
                    setPaper(x,y,type);
                    recursive(x,y+type,value+1);
                    removePaper(x, y, type);
                    paperType[type]++;
                }
            }
        }

        else {
            recursive(x,y+1,value);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        maps = new int[10][10];

        for(int i= 0 ; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 10; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        count = Integer.MAX_VALUE;

        recursive(0,0,0);

        if(count == Integer.MAX_VALUE) {
            //전체 맵에 1이 없는 경우였다면,
            if(checkOne()) {
                System.out.println(0);
            }
            else {
                System.out.println(-1);
            }

        }
        else {
            System.out.println(count);
        }

    }

}