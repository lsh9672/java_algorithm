package programmers.prog20220904;

import java.util.Arrays;

public class 카카오공채2022_양궁대회 {

    static int[] apeachInfo;

    static int gapScore; //최대 점수차 저장.
    static int[] result; //최종 결과 배열.


    //어파치와 라이언을 비교하고, 라이언이 이겼으면점수차와 해당 점수 상태를 저장
    static void compareArray(int[] lionArray){

        int apeachCount = 0;
        int lionCount = 0;

        for(int i = 0; i < 11; i++){

            //둘다0점이면 점수 안가져감.
            if(lionArray[i] == 0 && apeachInfo[i] == 0){
                continue;
            }
            //어피치가 점수를 가져가는 경우.
            if(lionArray[i] <= apeachInfo[i]){
                apeachCount += 10 - i;
            }
            //라이언이 점수 가져가는 경우
            else{
                lionCount += 10 - i;
            }
        }

        //둘중 누가 이겼는지 확인
        //어피치가 이겼거나 비겼다면 그냥 리턴
        //라이언이 이겼다면 이전에 저장되어있는 값과 비교해서 업데이트 또는 패스
        if(lionCount > apeachCount){
            infoUpdate(lionArray, lionCount - apeachCount);
        }
    }

    static void infoUpdate(int[] newLionInfo,int newGapCount){

        //새로 받은 점수차가 기존에 저장된 점수보다 크다면 업데이트
        if(newGapCount > gapScore){
            gapScore = newGapCount;
            result = arrayCopy(newLionInfo);
        }

        //두 점수차가 같다면 작은 점수를 더 많이 쏜걸로 업데이트
        else if(newGapCount == gapScore){
            if(minArrowCheck(newLionInfo)) result = arrayCopy(newLionInfo);
        }

    }

    //작은 쪽에 더 많이 쏜 배열확인 - true이면 새로운 배열, false이면 기존배열
    static boolean minArrowCheck(int[] newLionInfo){

        for(int i = 10; i >= 0; i--){
            if(newLionInfo[i] > result[i]){
                return true;
            }
            else if(newLionInfo[i] < result[i]){
                return false;
            }
        }
        return false;
    }

    //배열복사 - 과녁정보 배열
    static int[] arrayCopy(int[] lionInfo){

        int[] returnArray = new int[11];

        System.arraycopy(lionInfo,0,returnArray,0,11);

        return returnArray;
    }


    static void recursive(int n, int idx, int[] lionInfo, int count){

        //화살을 n발 다 쐈으면 점수차 비교해서 업데이트
        if(count >= n){
            compareArray(lionInfo);
            return;
        }

        int[] tempLionInfo = null;
        int getPoint = 0;
        //현재 점수를 선택하는 경우
        for(int i = idx; i <= 10; i++){
//            tempLionInfo = arrayCopy(lionInfo);//배열을 복사함.
            lionInfo[i]++;//현재 점수에 화살 추가
            //얻은 점수 구하기
            recursive(n,i,lionInfo,count+1);// 재귀 호출
            lionInfo[i]--;
        }

    }

    public int[] solution(int n, int[] info) {

        apeachInfo = info;
        gapScore = Integer.MIN_VALUE;

        recursive(n,0,new int[11],0);

        if(gapScore == Integer.MIN_VALUE) result = new int[]{-1};

        return result;
    }

    public static void main(String[] args) {
        카카오공채2022_양궁대회 t = new 카카오공채2022_양궁대회();

        int n1 = 5;
        int[] info1 = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(Arrays.toString(t.solution(n1,info1)));

        int n2 = 1;
        int[] info2 = {1,0,0,0,0,0,0,0,0,0,0};
        System.out.println(Arrays.toString(t.solution(n2,info2)));

        int n3 = 9;
        int[] info3 = {0,0,1,2,0,1,1,1,1,1,1};
        System.out.println(Arrays.toString(t.solution(n3,info3)));

        int n4 = 10;
        int[] info4 = {0,0,0,0,0,0,0,0,3,4,3};
        System.out.println(Arrays.toString(t.solution(n4,info4)));
    }
}
