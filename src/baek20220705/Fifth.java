package baek20220705;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * SWEA 1974번 - D2
 */

public class Fifth {
    public static void main(String[] args) throws FileNotFoundException {

//        System.setIn(new FileInputStream("/home/leesh/lsh/my_study/java_study/algorism_java/src/baek20220705/input (7).txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int result = 0;
            Integer[][] sudoku = new Integer[9][9];

            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    sudoku[i][j] = sc.nextInt();
                }
            }

            int tempTotal = 0;

            boolean check = true;

            //각 9개의 숫자를 더했을때 45가 나와야 됨.
            //행체크
            for(int i = 0; i < 9; i++){
                tempTotal = 0;
                for (int j = 0; j < 9; j++){
                    tempTotal += sudoku[i][j];
                }

                if(tempTotal != 45){
                    check = false;
                    break;
                }
            }
            if(check == true) {
                //열 체크
                for (int i = 0; i < 9; i++) {
                    tempTotal = 0;
                    for (int j = 0; j < 9; j++) {
                        tempTotal += sudoku[j][i];
                    }

                    if(tempTotal != 45){
                        check = false;
                        break;
                    }
                }
            }

            if (check == true){
                //3*3칸 체크
                loop:
                for (int i = 0; i<=6; i+=3){
                    for(int j = 0; j<=6; j+=3){
                        tempTotal = 0;
                        for (int x = 0; x < 3; x++){
                            for(int y = 0; y < 3; y++){
                                tempTotal += sudoku[x][y];
                            }
                        }

                        if(tempTotal != 45){
                            check = false;
                            break loop;
                        }
                    }
                }
            }

            if(check == true){
                result = 1;
            }

            System.out.printf("#%d %d\n",test_case,result);

        }

    }
}
