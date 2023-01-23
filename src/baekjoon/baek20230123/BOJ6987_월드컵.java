package baekjoon.baek20230123;

/**
 * @author : sh Lee
 * @date : 23. 1. 23.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 모든 경우를 확인해보는 문제이다
 * 백트래킹으로 분류되어있는데, 아마 앞선 조건이 거짓이면 나머지는 볼 필요 없기 때문에 일종의 가지치기로 본것이 아닌가 싶다.
 * 가능하지 않은 결과는 다음과 같이 나타낼 수 있다.
 * 1. 승의 합과, 패의 합이 다를 떄 => 한쪽에서 n번 승리했으면 n번의 패배가 생겨야 한다.
 * 2. 각 팀의 승,무,패를 합쳤을때 6이상일 경우 => 경기는 최대 5번 가능하다
 * 3. 무의 합이 홀수 이고, 나온 수를 셌을때, 짝수개가 아닐 경우(1,1,2 이런식으로 나왔을때) => 한팀에서 무승부가 2번 나오면 다른쪽에서도 2번 나와야 된다.
 *
 * 다음 조건들을 만족하는 지 하나씩 확인하면 된다.
 *
 * 현재 입력은 1차원배열로 주어졌기 때문에, 이를 문제와 같이 2차원 배열로 알아보기 쉽게 만들어서 풀이하면 된다.
 *
 *
 * 아이디어2
 * 이전의 방식은 너무 단순하게 생각했다.
 * 각 팀은 다른팀과 한번씩만 경기를 할 수 있기 때문에 단순하게 생각 할 수 없다.
 * 하나의 팀을 잡고, 승리가 5회라면, 다른 모든 팀의 패배 횟수에서 1씩 빼보고, 다음에는 B팀을 잡고 승리 횟수에서 1씩 빼보는 식으로 했어야 된다.
 */
public class BOJ6987_월드컵 {


    static final int maxMatch = 15; //최대 경기 횟수 - A->B를 하면 B->A는 할 필요 없음.

    //백트래킹 - 주어진 경기표를 가지고 모든 경기를 해봄
    static boolean recursive(Match[] matches, int[][] scoreArray , int matchCount){

        //모든 경기를 다했으면 가능
        if(matchCount == maxMatch) return true;


        int firstTeam = matches[matchCount].firstTeam;
        int secondTeam = matches[matchCount].secondTeam;

        //승 - 패
        if(scoreArray[firstTeam][0] > 0 && scoreArray[secondTeam][2] > 0){
            scoreArray[firstTeam][0]--;
            scoreArray[secondTeam][2]--;
            if(recursive(matches, scoreArray, matchCount+1)) return true;
            scoreArray[firstTeam][0]++;
            scoreArray[secondTeam][2]++;
        }

        //패 - 승
        if(scoreArray[firstTeam][2] > 0 && scoreArray[secondTeam][0] > 0){
            scoreArray[firstTeam][2]--;
            scoreArray[secondTeam][0]--;
            if(recursive(matches, scoreArray, matchCount+1)) return true;
            scoreArray[firstTeam][2]++;
            scoreArray[secondTeam][0]++;
        }

        //무 - 무
        if(scoreArray[firstTeam][1] > 0 && scoreArray[secondTeam][1] > 0){
            scoreArray[firstTeam][1]--;
            scoreArray[secondTeam][1]--;
            if(recursive(matches, scoreArray, matchCount+1)) return true;
            scoreArray[firstTeam][1]++;
            scoreArray[secondTeam][1]++;
        }

        return false;
    }

   public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int[][] scoreArray = new int[6][3];

        Match[] matches = new Match[maxMatch]; //매치를 치룰 팀 저장.

        StringBuilder sb = new StringBuilder();

        //총 15번의 매치 만들기.
       int index = 0;
       for(int i = 0; i < 5; i++){
           for(int j = i+1; j < 6; j++){
               matches[index++] = new Match(i,j); //i와 j 가 붙는다.
           }
       }

        for(int i = 0; i < 4; i++){
            st = new StringTokenizer(br.readLine());

            boolean check = true;

            //3개씩(승,무,패) 배열에 넣기
            for(int x = 0; x < 6; x++){
                for(int y = 0; y < 3; y++){
                    scoreArray[x][y] = Integer.parseInt(st.nextToken());
                }

                int tempCount = 0;

                tempCount += scoreArray[x][0];
                tempCount += scoreArray[x][1];
                tempCount += scoreArray[x][2];

                //승무패의 합이 6이상이면 잘못된 경우.
                if(tempCount != 5){

                    sb.append(0).append(" ");
                    check = false;
                    break;

                }
            }

            if(check){
                if(recursive(matches, scoreArray , 0)){
                    sb.append(1).append(" ");
                }
                else{
                    sb.append(0).append(" ");
                }
            }
        }

//        System.out.println(Arrays.deepToString(scoreArray));
        System.out.println(sb);

    }

    static class Match{
        int firstTeam, secondTeam;

        Match(int firstTeam, int secondTeam){
            this.firstTeam = firstTeam;
            this.secondTeam = secondTeam;
        }
    }
}
