package baek20220705;

import java.util.Scanner;
import java.io.FileInputStream;

/**
 * SWEA 1979번 - D2
 */

public class Second {
    public static void main(String[] args) throws Exception{

//        System.setIn(new FileInputStream("/home/leesh/lsh/my_study/java_study/algorism_java/src/baek20220705/input (4).txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int result = 0;

            int n = sc.nextInt();
            //글자 길이
            int k = sc.nextInt();

            //퍼즐 입력 - 검은색 부분이 0
            Integer[][] puzzle = new Integer[n][n];
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    puzzle[i][j] = sc.nextInt();
                }

            }

            //행 탐색
            for (int i = 0; i < n; i++){
                //흰색의 갯수를 측정
                int tempCount = 0;

                for (int j = 0; j < n; j++){
                    if (puzzle[i][j] == 0){
                        tempCount = 0;
                    }
                    else{
                        tempCount++;
                    }

                    //길이가 3이면 +1
                    if (tempCount == k){
                        result++;
                    }
                    //4개가 되면 안되는거니까 빼버림
                    if (tempCount == k+1){
                        result--;
                    }

                }
            }

            //열 탐색
            for (int i = 0;i < n ; i++){
                //흰색의 갯수를 측정
                int tempCount = 0;
                for (int j = 0; j < n; j++){

                    if (puzzle[j][i] == 0){
                        tempCount = 0;
                    }
                    else{
                        tempCount++;
                    }

                    //길이가 3이면 +1
                    if (tempCount == k){
                        result++;
                    }
                    //4개가 되면 안되는거니까 빼버림
                    if (tempCount == k+1){
                        result--;
                    }
                }
            }


            System.out.printf("#%d %d\n",test_case,result);

        }
    }
}
