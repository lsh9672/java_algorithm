package baekjoon.baek20220214;

/*
* 백준 - 소가 길을 건너간 이유1(14467번, 자바, 구현연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;


public class First {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception{

        int inputCase = Integer.parseInt(br.readLine());

        //길을 건너간 횟수 세기
        int count = 0;

        //소의 현재 위치정보 - 인덱스는 소 번호 -1, 소는 10마리이다.
        int[] cowLocation = new int[10];

        //처음 위치는 전부 -1로 채우기
        Arrays.fill(cowLocation,-1);

        //입력을 받기 위한 객체
        StringTokenizer st = null;

        for(int i = 0; i <inputCase;i++){
            //소의 번호와 위치 입력받기
            st = new StringTokenizer(br.readLine());

            int cowTempNum = Integer.parseInt(st.nextToken());
            int cowTempLoc = Integer.parseInt(st.nextToken());

            //초기값이면 그냥 넣기
            if(cowLocation[cowTempNum-1] == -1){
                cowLocation[cowTempNum-1] = cowTempLoc;

            }
            //초기값이 아니라면
            else if(cowLocation[cowTempNum-1] != -1){
                //저장되어있는 위치와 주어진 위치가 다르면 count값을 증가시키고 업데이트
                if(cowLocation[cowTempNum-1] != cowTempLoc){
                    cowLocation[cowTempNum-1] = cowTempLoc;
                    count +=1;
                }
            }

        }

        System.out.println(count);


    }
}
