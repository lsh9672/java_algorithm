package swea.sw20221113;

import java.io.*;
import java.util.StringTokenizer;

public class SWEA2115_벌꿀채취 {

    static int N,M,C;
    static int result;

    static int[][] maps;

    //뽑은 벌통의 숫자에서 최대값 구하기 - selctValue는 뽑은 꿀벌 양 누적, selectTotalValue는 얻을 수 있는 최대 수익.
    static int getMaxValue(int idx, int[] honeyArray, int selectValue,int selectTotalValue, int maxValue){

        if(idx >= M){
            maxValue = Math.max(maxValue,selectTotalValue);
            return maxValue;
        }

        //채취 가능한 수를 넘지 않아야 뽑을 수 있음
        if(selectValue + honeyArray[idx] <= C){
            //채취 가능한 수를 넘지 않았을때 뽑는 경우. - 뽑고나면 최대값 업데이트 해줌.
            maxValue = Math.max(maxValue,getMaxValue(idx+1, honeyArray, selectValue + honeyArray[idx], selectTotalValue +(honeyArray[idx] * honeyArray[idx]),maxValue));

            //채취 가능하지만, 뽑지 않는 경우,
            maxValue = Math.max(maxValue,getMaxValue(idx+1, honeyArray, selectValue, selectTotalValue, maxValue));
        }
        //채취 불가능하면 뽑지 않고 넘어감.
        else{
            maxValue = Math.max(maxValue, getMaxValue(idx+1, honeyArray, selectValue, selectTotalValue, maxValue));
        }

        return maxValue;
    }


    static void recursive(int x,int y, int selectCount, int value){

        //선택한 벌통의 수가 목적개수에 도달하면 최대값 업데이트 하고 종료.
        if(selectCount == 2){
            result = Math.max(result,value);
            return;
        }

        for(int i = x; i < N; i++){
            for(int j = y; j < N - M + 1; j++){

                //뽑은 꿀벌저장.
                int[] tempHoney = new int[M];
                for(int k = 0; k < M; k++){
                    tempHoney[k] = maps[i][j+k];
                }

                int honeyMax = getMaxValue(0,tempHoney,0,0, Integer.MIN_VALUE);//해당 꿀벌통에서 얻을 수 있는 최대 값 구하기.
                recursive(i, j+M,selectCount + 1, value + honeyMax);
            }
            y = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/home/leesh/lsh/my_study/java_study/algorism_java/src/swea/sw20221113/sample_input (24).txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        for(int testCase = 1; testCase <= T; testCase++){
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken()); //벌통 크기
            M = Integer.parseInt(st.nextToken()); //벌통의 수
            C = Integer.parseInt(st.nextToken()); //채취 할수 있는 꿀의 최대 양

            maps = new int[N][N]; //벌통
            result = Integer.MIN_VALUE;

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    maps[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            recursive(0,0, 0, 0);
            System.out.println("#"+ testCase + " " + result);

        }
    }
}
