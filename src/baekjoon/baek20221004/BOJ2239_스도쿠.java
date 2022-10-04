package baekjoon.baek20221004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * @author : sh Lee
 * @date : 22. 10. 4.
 */
public class BOJ2239_스도쿠 {

    static int[][] resultMaps;
    static int[][] maps;

    static List<Node> zeroList;

    static int[][] arrayCopy(){
        int[][] returnArray = new int[9][9];

        for(int i = 0; i <9; i++){
            System.arraycopy(maps[i],0,returnArray[i],0,9);
        }

        return returnArray;
    }

    static boolean zeroCheck(){
        for(int i = 0 ; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(maps[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    static boolean check(int x, int y){
        int value = maps[x][y];


        //가로줄
        for(int i = 0; i < 9; i++){

            //자기 자신말고 다른 위치에 같은 숫자가 있다면 false
            if( i != y && maps[x][i] == value){
                return false;
            }

        }


        //세로줄
        for(int i = 0; i < 9; i++){
            //자기 자신말고 다른 위치에 같은 숫자가 있다면 false
            if( i != x && maps[i][y] == value) return false;
        }


        //속해있는 네모칸
        int startX = (x / 3) *3;
        int startY = (y / 3) *3;

        for(int i = startX; i < startX+3; i++){
            for(int j = startY; j < startY+3; j++){

                //자기 자신말고 다른 위치에 같은 숫자가 있다면 false
                if(i != x && j != y && maps[i][j] == value) return false;
            }
        }


        return true;
    }


    static void recursive(int idx){
        //체크 함수를 돌려서 놓을 수 없다면.
//        print();

        if(resultMaps != null && maps[8][8] < resultMaps[8][8]) return;


        //모든 곳에 숫자를 다 놨다면.
        if(zeroCheck()){
            if(resultMaps == null){
                resultMaps = arrayCopy();
            }
            //81번째 수가 작으면 업데이트
            else if(maps[8][8] > resultMaps[8][8]){
                resultMaps = arrayCopy();
            }
            return;
        }


        if(idx >= zeroList.size()) return;

        int x = zeroList.get(idx).x;
        int y = zeroList.get(idx).y;

        if(maps[x][y] == 0){

            for(int j = 1; j <= 9; j++){

                maps[x][y] = j;
                if(check(x,y)){
                    recursive(idx+1);
                }
            }
            maps[x][y] = 0;
        }
        else{
            recursive(idx+1);
        }



    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        maps = new int[9][9];

        zeroList = new ArrayList<>();

        for(int i = 0; i < 9; i++){
            char[] temp = br.readLine().toCharArray();
            for(int j = 0; j < 9; j++){
                maps[i][j] = Integer.parseInt(temp[j]+"");

                if(maps[i][j] == 0){
                    zeroList.add(new Node(i,j));
                }
            }
        }

        recursive(0);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                sb.append(resultMaps[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    static class Node{
        int x, y;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
