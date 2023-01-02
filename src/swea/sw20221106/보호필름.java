package swea.sw20221106;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 11. 6.
 */
public class 보호필름 {

    static int D,W,K;
    static int[][] maps;

    static int result;

    //해당위치에 약품 셋팅 - 반환값은 셋팅전 배열. - A: 0 , B: 1
    static int[] setDrug(int row, int type){
        int[] originState = new int[W];

        //기존 값 저장하고 약품 타입으로 변경.
        for(int i = 0; i < W; i++){
            originState[i] = maps[row][i];

            maps[row][i] = type;
        }

        return originState;
    }
    //셋팅한 약품 원래대로 돌려두기. - 이러한 방식이 매번 2차원 배열을 복사하는 비용보다 적게 들것이라고 생각함.
    static void delDrug(int[] originState, int row){
        maps[row] = originState; //약품 넣었던 위치 기존값으로 돌려놓기.
    }

    //성능 검사 - 성공하면 true, 실패하면 false;
    static boolean performanceCheck(){

        for(int col = 0; col < W; col++){

            int start = 0;
            int end = start + K;;

            boolean check = false;
            while(end <= D){

                int stdValue = maps[start][col];

                check = false;
                for(int i = start; i < end; i++){
                    //탐색중에 다른 값이 하나라도 있으면.
                    if(stdValue != maps[i][col]){
                        check = true;
                        start = i; //그 다른 값 위치부터 다시 시작.
                        break;
                    }
                }

                //해당 col이 성능검사를 통과하면 다음 col로 넘어감.
                if(!check) break;
                end = start + K;
            }

            if(check){
                return false;
            }

        }
        return true;
    }

    static boolean recursive(int idx, int drugCount, int selectCount){

        //선택한 약물수가 확인하려고 하는 투입 약물수에 도달하면 성능검사함.
        if(selectCount == drugCount) return performanceCheck();

        //약품을 투여할 행 선택
        for(int i = idx; i < D; i++){

            //A 약품인 경우.
            for(int type = 0; type < 2; type++){
                int[] originState = setDrug(i, type);

                if(recursive(i+1, drugCount, selectCount+1)) return true;

                delDrug(originState, i);
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/home/leesh/lsh/my_study/java_study/algorism_java/src/swea/sw20221106/sample_input (23).txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        for(int testCase = 1; testCase <= T; testCase++){
            result = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());

            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            maps = new int[D][W];
            for(int i = 0; i < D; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < W; j++){
                    maps[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            //약물을 투입한 후의 최소값 확인.
            int drugCount = 0;
            while(true){

                //약물을 2번,3번... 각각 선택했을때 재귀호출하고, true라면 해당값이 성능검사 통과 최소값이라는 뜻, 바로 반복문 종료
                if(recursive(0, drugCount, 0)){
                    result = drugCount;
                    break;
                }

                drugCount++;
            }



            System.out.println("#"+ testCase + " " + result);
        }


    }

}
