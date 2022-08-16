package baekjoon.baek20220213;

/*
* 백준 - 5597(과제안내주신분 , 브론즈2, 자바 구현연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class First {
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] numList = new int[30];
        for(int j =0 ; j < 30 ; j++) numList[j] = j+1;


        for(int i =0 ; i < 28 ; i++){

            //출석번호 받기
            int num = Integer.parseInt(br.readLine());

            //입력받은 값을 배열에서 -1로 만듦
            numList[num-1] = -1;
        }


        for(int k = 0 ; k <30 ; k++){
            if(numList[k] != -1){
                System.out.println(numList[k]);
            }
        }


    }
}
