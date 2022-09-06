package baekjoon.baek20220906;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3980_선발명단 {

    static int result;

    static int[] position;
    static boolean[] peopleCheck;
    static int[][] maps;

    //포지션 능령치 총합
    static int totalValue(){
        int total = 0;
        for(int i = 0; i < 11; i++){
            total += position[i];
        }
        return total;
    }

    static void recursive(int positionNum){

        //11명 배치 끝나면 최대값 업데이트하고 리턴
        if(positionNum >= 11){
            result = Math.max(result,totalValue());
            return;
        }

        //사람 선택
        for(int peopleNum = 0; peopleNum < 11; peopleNum++){
            //사람배치 - 이미 배치 하지 않았어야되고, 포지션값이 0이 아니여야됨.
            if(!peopleCheck[peopleNum] && maps[peopleNum][positionNum] != 0){
                position[positionNum] = maps[peopleNum][positionNum]; //한명 해당 포지션에 배치.
                peopleCheck[peopleNum] = true;//방문 처리.
                recursive(positionNum+1);
                peopleCheck[peopleNum] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine()); //테스트 케이스 개수
        for(int testCase = 0; testCase < N; testCase++){
            result = Integer.MIN_VALUE; //능력치 합의 최대값을 구하기 위해 최소값 셋팅

            maps = new int[11][11]; //능력치 표
            position = new int[11]; // 각 포지션별 능력치
            peopleCheck = new boolean[11]; //각 사람들 배정했는지 체크

            //능력치 표 입력받기
            for(int i = 0; i < 11; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 11; j++){
                    maps[i][j] = Integer.parseInt(st.nextToken());
                }
            }


            recursive(0);
            System.out.println(result);
        }


    }
}
