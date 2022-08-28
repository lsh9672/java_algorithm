package swea.sw20220828;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution3 {

    static int N;
    static int result;
//    static int[] cardArrays;

    //카드의 현상태 복사
    static int[] arrayCopy(int[] cardArrays){
        int[] returnArrays = new int[N];

        System.arraycopy(cardArrays,0,returnArrays,0, N);

        return returnArrays;
    }

    //카드가 순서대로 정렬되었는지 확인.
    static boolean check(int[] cardArray){
        //오름차순인지 내림차순인지 확인
        int checkValue = 1;

        //오름차순
        if(cardArray[0] == checkValue){
            for(int i = 0 ; i < N; i++){
                if(cardArray[i] != checkValue) return false;

                checkValue++;
            }

        }
        //내림차순
        else if(cardArray[N-1] == checkValue){
            for(int i = N-1; i >=0 ; i--){
                if(cardArray[i] != checkValue) return false;

                checkValue++;
            }

        }
        //둘다 아님
        else{
            return false;
        }

        return true;
    }

    //카드 셔플하는 메서드 - 오른쪽, 왼쪽 순서대로 섞임.
    static int[] shuffleCard(int[] cardArray, int startNum){

        int[] returnCard = new int[N];

//        int leftIdx = N/2-1;
        int rightIdx = N/2;


        //초록색을 놓고

        for(int i = N/2-1; i >= 0; i--){
            int moveIndex = startNum;
            if(moveIndex > N/2) moveIndex = N/2;
            if(moveIndex < 0) moveIndex = 0;
            returnCard[i+moveIndex] = cardArray[i];

            startNum--;
        }

        //빈칸에 빨간색을 채워 넣음
        for(int i = 0; i < N; i++){
            if(returnCard[i] == 0){
                returnCard[i] = cardArray[rightIdx];
                rightIdx++;
            }
        }

        return returnCard;
   }

    static void recursive(int count,int[] CardArrays){

        if(count >= result){
            return;
        }

        if(count > 5){
            return;
        }


        if(check(CardArrays)){
            result = Math.min(result,count);
            return;
        }

        for(int i = 0; i <= N-1;i++){
            int[] tempCardArrays = arrayCopy(CardArrays); //미리 복사해두고.
            tempCardArrays = shuffleCard(tempCardArrays,i); //셔플하고
            recursive(count+1, tempCardArrays); //섞인걸로 다시 재귀호출
        }

    }



    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/swea/sw20220828/sample_input3.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        for(int testCase = 1; testCase <= T; testCase++){

            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] cardArrays = new int[N];

            for(int i = 0; i < N; i++){
                cardArrays[i] = Integer.parseInt(st.nextToken());
            }

            result = Integer.MAX_VALUE;
            recursive(0,cardArrays);

            if(result == Integer.MAX_VALUE) result = -1;

            System.out.println("#"+ testCase + " " + result);


        }
//        N = 6;
//        int[] testArrays = {1,2,3,4,5,6};
//        for(int i = 0; i <= 5; i++){
//            System.out.println(Arrays.toString(shuffleCard(testArrays,i)));
//        }

    }
}
