package programmers.prog20220915;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class 카카오공채2022_파괴되지_않은_건물_최적화 {



    public int solution(int[][] board, int[][] skill) {
        int answer = 0;

        int N = board.length;
        int M = board[0].length;

        //내구도 정보를 누적시켜둠
        int[][] sum = new int[N+1][M+1];

        //누적합 저장
        for(int[] val : skill){
            int type = val[0];
            int r1 = val[1];
            int c1 = val[2];
            int r2 = val[3];
            int c2 = val[4];
            int degree = val[5] * (type == 1 ? -1 : 1);

            sum[r1][c1] += degree;
            sum[r1][c2+1] += degree * (-1);
            sum[r2+1][c2+1] += degree;
            sum[r2+1][c1] += degree * (-1);

        }

        //저장한 누적합들 전부 계산함
        //상하로 이동하면서 누적합 계산.
        for(int i = 1; i < N; i++){
            for(int j = 0; j < M; j++){
                sum[i][j] += sum[i-1][j];
            }
        }

        //좌우 누적합 계산.
        for(int i = 1; i < M; i++){
            for(int j = 0; j < N; j++){
                sum[j][i] += sum[j][i-1];
            }
        }

        //반복문 돌면서 살아남은 건물 누적
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                //원본 값과, 누적값과 더하면 내구도 나옴
                if(board[i][j] + sum[i][j] > 0) answer++;
            }
        }


        return answer;
    }


    public static void main(String[] args) {
        카카오공채2022_파괴되지_않은_건물_최적화 tt = new 카카오공채2022_파괴되지_않은_건물_최적화();
        int[][] board = {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}};
        int[][] skill = {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}};

        System.out.println(tt.solution(board,skill));

        int[][] board2 = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] skill2 = {{1,1,1,2,2,4},{1,0,0,1,1,2},{2,2,0,2,0,100}};
        System.out.println(tt.solution(board2,skill2));

    }
}