package baekjoon.baek20220818;

/**
 * BOJ2630 색종이 만들기
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2630 {

    static int whiteCount;
    static int blueCount;
    static int[][] maps;

    //시작 좌표와 한변의 길이를 받아서 영역내의 값이 모두 같은 값인지 확인
    static int check(int x, int y, int size){

        int startValue = maps[x][y];

        for(int i = x; i < x+size; i++){
            for(int j = y; j < y+size; j++){
                //범위 내의 값이 하나라도 다르면 false
                if(startValue != maps[i][j]){
                    return 0;
                }
            }
        }

        //하얀색
        if(startValue == 0) return 1;
        else return 2;
    }

    //색종이를 재귀를 이용해서 쪼개가며 각 영역의 모든 숫자가 같은지 확인하고 같다면 최종 갯수 증가.
    static void recursive(int x, int y, int size){

        //확인 메서드를 돌렸을때 범위 안의 모든 값이 같다면 갯수 증가하고 리턴
        int temp = check(x,y,size);
        if(temp != 0){
            if(temp == 1) whiteCount++;
            else blueCount++;
            return;
        }

        //범위 안의 모든 값이 같지 않으면 4분할 함.
        recursive(x,y,size/2);//1사분면
        recursive(x,y+size/2,size/2);//2사분면
        recursive(x+size/2,y,size/2);//3사분면
        recursive(x+size/2,y+size/2,size/2);//4사분면
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= null;
        int N = Integer.parseInt(br.readLine());

        //주어진 색종이 입력
        maps = new int[N][N];

        for(int i= 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){

                maps[i][j] = Integer.parseInt(st.nextToken());

            }
        }
        whiteCount = 0;
        blueCount = 0;

        recursive(0,0,N);

        System.out.println(whiteCount);
        System.out.println(blueCount);
    }
    static class Location{
        int x;
        int y;

        Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
