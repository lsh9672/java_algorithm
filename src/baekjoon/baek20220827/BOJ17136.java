package baekjoon.baek20220827;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * A형 대비 복습 - 소요시간 : 약 1시간10분
 */

public class BOJ17136 {

    static int result = Integer.MAX_VALUE;

    //각 사이즈별 갯수
    static int[] colorPaperCount = {0,5,5,5,5,5};
    static int[][] maps;

    //색종이의 범위를 0 또는 1로 만듦
    static void updateMaps(int x, int y, int size, int type){

        for(int i = x; i < x+size ; i++){
            for(int j = y; j < y+size; j++){
                maps[i][j] = type;
            }
        }
    }

    //해당 크기의 색종이를 놓을 수 있는 지 확인하는 메서드
    static boolean sizeCheck(int x, int y, int size){

        for(int i = x; i < x+size; i++){
            for(int j = y; j < y+size; j++){
                if(i >= 10 || j>= 10) return false;
                if(maps[i][j] == 0) return false;

            }
        }

        return true;
    }

    //현재 색종이가 남았는지 확인하는 메서드
    static boolean paperCountCheck(){

        //남아있으면 false, 다 썼으면 true
        for(int i = 1; i <= 5; i++){
            if(colorPaperCount[i] != 0) return false;
        }

        return true;
    }

    //현재 모든 칸을 0로 덮었는지 확인
    static boolean allZeroCheck(){

        //다 덮었다면 true, 1이 남아있다면 false;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(maps[i][j] == 1) return false;
            }
        }
        return true;
    }

    static void recursive(int x, int y, int value){

        if(x >= 10) return;

        //현재까지 사용한 색종이의 수가 최소 색종이보다 크다면 그대로 종료
        if(value > result){
            return;
        }

        //전체를 돌면서 1이 있는지 확인하는 로직 필요
        if(allZeroCheck()){
            result = Integer.min(value,result);
        }

        //현재 색종이를 다 썼는지 확인하는 분기 필요
        if(paperCountCheck()){
            //다 썼다면 리턴
            return;
        }

        if(y >= 10){
            recursive(x+1,0,value);
            return ;
        }

//        System.out.println("x : " + x + " y : " + y);
        if(maps[x][y] == 0){
            recursive(x,y+1,value);
            return;
        }

        for(int i = 5; i >=1; i--) {
            //놓을 수 있다면 해당 범위 0으로 만듦
            if (colorPaperCount[i] > 0 && sizeCheck(x, y, i)) {
                updateMaps(x, y, i, 0);
                colorPaperCount[i]--;

                recursive(x, y+i, value + 1);

                colorPaperCount[i]++;
                updateMaps(x, y, i, 1); //다음 탐색을 위해 원래대로 돌려놓기;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        maps = new int[10][10];

        //초기 색종이 입력받기.
        for(int i = 0; i < 10; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 10; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recursive(0,0,0);

        if(result == Integer.MAX_VALUE){
            if(allZeroCheck()){
                System.out.println(0);
            }
            else{
                System.out.println(-1);
            }
        }
        else{
            System.out.println(result);
        }

    }
}
