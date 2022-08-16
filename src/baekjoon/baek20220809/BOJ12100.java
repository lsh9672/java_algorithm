package baekjoon.baek20220809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ12100 {
    static int N;
    static int result;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static void blockMove(int[][] tempMaps, int dir, int j){

        //스택에 쌓아둠 - 0이 아닌 값들을 맨아래부터(올리는 경우는 맨 아래부터)
        Stack<Integer> tempStack = new Stack<>();

        //상
        if(dir == 0){
            for(int i = N-1; i >= 0 ; i--){
                if(tempMaps[i][j] != 0){
                    tempStack.add(tempMaps[i][j]);
                    tempMaps[i][j] = 0;

                }
            }
            int index = 0;
            int tempValue = 0;
            while(!tempStack.isEmpty()){
                //맨위의 값이 기존의 값과 동일하다면,
                if(tempValue == tempStack.peek()){
                    tempMaps[index][j] = tempValue*2;
                    tempStack.pop();
                    index++;
                    tempValue = 0;
                }
                //맨위의 값이 기존의 값과 다르다면,
                else{
                    //기존의 값이 0이라면
                    if(tempValue == 0) tempValue = tempStack.pop();
                    //0이 아니라면
                    else{
                        //0이 아닌데 새로 꺼낸 값과 기존의 값이 다르다면 저장하고, 인덱스 하나 증가하고, 값 업데이트
                        tempMaps[index][j] = tempValue;
                        tempValue = tempStack.pop();
                        index++;
                    }

                }
            }
            //마지막 남은 값 넣어주기
            if(tempValue != 0) tempMaps[index][j] = tempValue;
        }
        //하
        else if(dir == 1){
            for(int i = 0; i < N ; i++){
                if(tempMaps[i][j] != 0){
                    tempStack.add(tempMaps[i][j]);
                    tempMaps[i][j] = 0;

                }
            }
            int index = N-1;
            int tempValue = 0;
            while(!tempStack.isEmpty()){
                //맨위의 값이 기존의 값과 동일하다면,
                if(tempValue == tempStack.peek()){
                    tempMaps[index][j] = tempValue*2;
                    tempStack.pop();
                    index--;
                    tempValue = 0;
                }
                //맨위의 값이 기존의 값과 다르다면,
                else{
                    //기존의 값이 0이라면
                    if(tempValue == 0) tempValue = tempStack.pop();
                        //0이 아니라면
                    else{
                        //0이 아닌데 새로 꺼낸 값과 기존의 값이 다르다면 저장하고, 인덱스 하나 증가하고, 값 업데이트
                        tempMaps[index][j] = tempValue;
                        tempValue = tempStack.pop();
                        index--;
                    }

                }
            }
            //마지막 남은 값 넣어주기
            if(tempValue != 0) tempMaps[index][j] = tempValue;

        }
        //좌
        else if(dir == 2){
            for(int i = N-1; i >= 0 ; i--){
                if(tempMaps[j][i] != 0){
                    tempStack.add(tempMaps[j][i]);
                    tempMaps[j][i] = 0;

                }
            }
            int index = 0;
            int tempValue = 0;
            while(!tempStack.isEmpty()){
                //맨위의 값이 기존의 값과 동일하다면,
                if(tempValue == tempStack.peek()){
                    tempMaps[j][index] = tempValue*2;
                    tempStack.pop();
                    index++;
                    tempValue = 0;
                }
                //맨위의 값이 기존의 값과 다르다면,
                else{
                    //기존의 값이 0이라면
                    if(tempValue == 0) tempValue = tempStack.pop();
                        //0이 아니라면
                    else{
                        //0이 아닌데 새로 꺼낸 값과 기존의 값이 다르다면 저장하고, 인덱스 하나 증가하고, 값 업데이트
                        tempMaps[j][index] = tempValue;
                        tempValue = tempStack.pop();
                        index++;
                    }

                }
            }
            //마지막 남은 값 넣어주기
            if(tempValue != 0) tempMaps[j][index] = tempValue;

        }
        //우
        else if(dir == 3){
            for(int i = 0; i < N ; i++){
                if(tempMaps[j][i] != 0){
                    tempStack.add(tempMaps[j][i]);
                    tempMaps[j][i] = 0;

                }
            }
            int index = N-1;
            int tempValue = 0;
            while(!tempStack.isEmpty()){
                //맨위의 값이 기존의 값과 동일하다면,
                if(tempValue == tempStack.peek()){
                    tempMaps[j][index] = tempValue*2;
                    tempStack.pop();
                    index--;
                    tempValue = 0;
                }
                //맨위의 값이 기존의 값과 다르다면,
                else{
                    //기존의 값이 0이라면
                    if(tempValue == 0) tempValue = tempStack.pop();
                        //0이 아니라면
                    else{
                        //0이 아닌데 새로 꺼낸 값과 기존의 값이 다르다면 저장하고, 인덱스 하나 증가하고, 값 업데이트
                        tempMaps[j][index] = tempValue;
                        tempValue = tempStack.pop();
                        index--;
                    }

                }
            }
            //마지막 남은 값 넣어주기
            if(tempValue != 0) tempMaps[j][index] = tempValue;
        }
    }

    static int[][] arrayCopy(int[][] tempMaps){
        int[][] returnMaps = new int[N][N];

        for(int i = 0; i < N; i++){
            System.arraycopy(tempMaps[i], 0, returnMaps[i], 0, N);
        }
        return returnMaps;
    }

    //dir의 경우 0 - 상, 1 - 하, 2 - 좌, 3 - 우
    static void updateBlock(int[][] tempMaps, int dir){
        for(int j = 0; j < N; j++){
            blockMove(tempMaps, dir, j);
        }
    }

    // 현상태에서 최대 크기 블록 구하기
    static void maxBlock(int[][] tempMaps) {
        int tempCount = 2;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(tempMaps[i][j] != 0) {
                    tempCount = Math.max(tempCount, tempMaps[i][j]);
                }
            }
        }

        result = Math.max(result,tempCount);
    }

    //dir 0,1,2,3 => 상하좌우
    static void recursive(int[][] tempMaps,int count) {
        if(count >= 5) {
            maxBlock(tempMaps);
            return;
        }

        //0~4 => 각각 상,하,좌,우를 의미함.
        for(int i = 0; i < 4; i++){
            //각 반복때마다 현재배열을 복사하고, 게임판을 업데이트 함.
            int[][] currentMaps = arrayCopy(tempMaps);

            updateBlock(currentMaps,i);
            recursive(currentMaps,count+1);//업데이트가 완료되었으면 재귀호출을 함.

        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        int[][] maps = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //최소블럭은 2임.
        result = 2;

        recursive(maps,0);
        System.out.println(result);
    }
}
