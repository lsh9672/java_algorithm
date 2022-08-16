package baekjoon.baek20220214;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 백준- 빙고(2578번, 자바 구현연습)*/
public class Second {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //빙고판
    static int[][] bingo = new int[5][5];

    //빙고 수 카운트
    static int count = 0;

    //빙고탐색 - 행 탐색
    static void rowCheck(){

        for(int i= 0 ; i< 5 ; i++){
            int checkCount = 0; //5가되면 빙고
            for(int j = 0;j<5; j++){
                //부른 숫자는 0으로 변경해둠, 따라서 0인것을 탐색, 0이면 checkCount값을 증가시킴
                if(bingo[i][j] == 0){
                    checkCount +=1;
                }
            }
            //5가되면 한줄빙고
            if(checkCount == 5){
                //빙고 카운트 증가
                count +=1;
            }
        }

    }
    //빙고 탐색 - 열 탐색
    static void colCheck(){

        for(int i = 0 ; i< 5 ; i++){
            int checkCount = 0; //5가되면 빙고
            for(int j = 0;j<5; j++){
                //부른 숫자는 0으로 변경해둠, 따라서 0인것을 탐색, 0이면 checkCount값을 증가시킴
                if(bingo[j][i] == 0){
                    checkCount +=1;
                }
            }
            //5가되면 한줄빙고
            if(checkCount == 5){
                //빙고 카운트 증가
                count +=1;
            }
        }

    }

    //빙고 탐색 - 대각선 탐색
    static void slashCheck(){
        //오른쪽 아래로 내려가는 대각선
        int rightSlashCheck= 0;

        for(int i = 0 ;i<5;i++){
            if(bingo[i][i] == 0){
                rightSlashCheck+=1;
            }
        }

        if(rightSlashCheck == 5){
            count+=1;
        }

        //왼쪽 아래로 내려가는 대각선
        int leftSlashCheck = 0;

        for(int j = 0 ;j<5;j++){
            if(bingo[j][4-j] == 0){
                leftSlashCheck+=1;
            }
        }

        if(leftSlashCheck == 5){
            count+=1;
        }

    }

    public static void main(String[] args) throws Exception{
        StringTokenizer st = null;

        //사회자가 부른 숫자 개수 세기
        int bingoNumCount = 0;


        //빙고 입력
        for(int i =0 ; i < 5; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<5; j++){
                bingo[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //사회자가 부르는 수 저장
        int[] moderator = new int[25];
        int moderatorIndex = 0;
        for(int x = 0; x < 5; x++) {
            st = new StringTokenizer(br.readLine());

            //사회자가 부른단어 임시로 저장할 변수
            for (int y = 0; y < 5; y++) {
                moderator[moderatorIndex] =Integer.parseInt(st.nextToken());
                moderatorIndex+=1;
            }
        }

        loop:
        for(int x = 0; x < 25; x++){

            //빙고판 돌면서 사회자가 부른 단어를 0으로 바꾸기
            for(int n = 0; n<5; n++){
                for(int m = 0; m<5; m++){
                    if(bingo[n][m] == moderator[x]){
                        bingo[n][m] =0;
                    }
                }
            }


            //빙고인지 아닌지 확인수
            rowCheck();
            colCheck();
            slashCheck();

            //확인후에 빙고count가 3이상이면 사회자가 부른 숫자 갯수 세는 변수 출력
            if(count >= 3){

                System.out.println(x+1);
                break loop;
            }
            //다음 숫자를 불렀을떄 빙고수를 세기 위해서 초기
            count = 0;

        }

    }
}
