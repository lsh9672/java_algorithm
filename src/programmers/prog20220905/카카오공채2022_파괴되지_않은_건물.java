package programmers.prog20220905;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class 카카오공채2022_파괴되지_않은_건물 {

    static int[][] maps;


    static int checkBuilding(){

        int total = 0;
        for(int i = 0; i < maps.length; i++){
            for(int j = 0; j < maps[0].length; j++){
                if(maps[i][j] > 0) total++;
            }
        }

        return total;
    }

    //type :1 이면 공격, 2이면 힐
    static void mapUpdate(int[] skill){
        int type = skill[0];
        int r1 = skill[1];
        int c1 = skill[2];
        int r2 = skill[3];
        int c2 = skill[4];
        int degree = skill[5];


        for(int i = r1; i <= r2; i++){
            for(int j = c1; j <= c2; j++){
                if(type == 1){
                    maps[i][j] -= degree;
                }
                else maps[i][j] += degree;
            }
        }

    }

    public int solution(int[][] board, int[][] skill) {
        int answer = 0;

        maps = board;


        //공격 또는 스킬
        for(int[] skillOne : skill){
            mapUpdate(skillOne);
        }

        //힐
        answer = checkBuilding();

        return answer;
    }


    public static void main(String[] args) {
        카카오공채2022_파괴되지_않은_건물 tt = new 카카오공채2022_파괴되지_않은_건물();
        int[][] board = {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}};
        int[][] skill = {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}};

        System.out.println(tt.solution(board,skill));

        int[][] board2 = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] skill2 = {{1,1,1,2,2,4},{1,0,0,1,1,2},{2,2,0,2,0,100}};
        System.out.println(tt.solution(board2,skill2));

    }
}
