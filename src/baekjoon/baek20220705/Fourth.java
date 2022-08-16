package baekjoon.baek20220705;

import java.util.Scanner;

/**
 * SWEA 1961번 D2
 */

public class Fourth {

    static Integer[][] rotateFunc(Integer[][] temp, int n){
        Integer[][] result = new Integer[n][n];

        for (int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                result[i][j] = temp[n-1-j][i];
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception{

//        System.setIn(new FileInputStream("/home/leesh/lsh/my_study/java_study/algorism_java/src/baekjoon.baek20220705/input (6).txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

        for(int test_case = 1; test_case <= T; test_case++)
        {

            int n = sc.nextInt();

            Integer[][] inputArray = new Integer[n][n];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    inputArray[i][j] = sc.nextInt();
                }
            }

            StringBuilder result = new StringBuilder();
            result.append("#").append(test_case).append("\n");

            //90도, 180도, 270도

            Integer[][] inputArray90 = rotateFunc(inputArray,n);
            Integer[][] inputArray180 = rotateFunc(inputArray90,n);
            Integer[][] inputArray270 = rotateFunc(inputArray180,n);

            for (int i = 0; i < n; i++){
                String temp1 = "";
                String temp2 = "";
                String temp3 = "";
                for(int j = 0; j < n; j++){
                    temp1 += String.valueOf(inputArray90[i][j]);
                    temp2 += String.valueOf(inputArray180[i][j]);
                    temp3 += String.valueOf(inputArray270[i][j]);

                }

                result.append(temp1).append(" ").append(temp2).append(" ").append(temp3).append("\n");
            }
            result.setLength(result.length()-1);
            System.out.println(result);

        }

    }
}
