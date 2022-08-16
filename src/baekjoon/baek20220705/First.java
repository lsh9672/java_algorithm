package baekjoon.baek20220705;



/**
 * SWEA 싸피 사전과제 D2-1959
 */

import java.util.Scanner;

public class First {
    public static void main(String[] args) throws Exception {

//        System.setIn(new FileInputStream("/home/leesh/lsh/my_study/java_study/algorism_java/src/baekjoon.baek20220705/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T=Integer.parseInt(sc.nextLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            String[] temp = sc.nextLine().split(" ");

            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);
            String[] aList;
            String[] bList;

            if (b>a){
                aList = sc.nextLine().split(" ");
                bList = sc.nextLine().split(" ");
            }
            else{
                int swap = 0;
                swap = a;
                a = b;
                b = swap;
                bList = sc.nextLine().split(" ");
                aList = sc.nextLine().split(" ");
            }


            int total = -9999999;
            int tempValue = 0;
            for(int i = 0; i <= b-a; i++){
                tempValue = 0;
                for (int j = 0; j<a; j++){
                    tempValue += Integer.parseInt(aList[j]) * Integer.parseInt(bList[j+i]);
                }
                total = Math.max(total,tempValue);
            }

            System.out.println("#"+test_case + " " + total);
        }
    }
}
