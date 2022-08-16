package baekjoon.baek20220705;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * SWEA 2001번 파리퇴치 - D2
 */


public class Third {
    public static void main(String[] args) throws FileNotFoundException {

//        System.setIn(new FileInputStream("/home/leesh/lsh/my_study/java_study/algorism_java/src/baekjoon.baek20220705/input (5).txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n = sc.nextInt();
            int m = sc.nextInt();

            Integer[][] field = new Integer[n][n];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    field[i][j] = sc.nextInt();
                }
            }

            int result = -1;

            for (int i = 0; i <= n-m; i++){
                for (int j = 0; j <= n-m; j++){

                    int temp = 0;
                    for(int x = 0; x < m; x++){
                        for(int y = 0; y < m; y++){
                            temp += field[x+i][y+j];
                        }
                    }

                    result = Math.max(temp,result);
                }
            }

            System.out.printf("#%d %d\n", test_case,result);
        }
    }
}
