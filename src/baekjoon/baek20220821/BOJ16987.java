package baekjoon.baek20220821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BOJ16987 {
    static int N;
    static int maxValue;

    // 배열 복사.
    static EggInfo[] copyArrays(EggInfo[] tempEgg) {
        EggInfo[] newArrays = new EggInfo[N];

        for(int i = 0; i < N; i++){
            newArrays[i] = new EggInfo(tempEgg[i].hp,tempEgg[i].weight);
        }

        return newArrays;
    }

    //깨진 계란 확인
    static int brokenEggCheck(EggInfo[] tempEgg) {
        int total = 0;
        for(int i = 0; i < N; i++) {
            if(tempEgg[i].hp <= 0) total++;
        }

        return total;
    }


    //1.start위치의 계란을 든다.
    //2.start 계란이 아니고 깨지지 않은 계란을 친다.
    //3. 만약 손에 든 계란이 깨졌다면 치지 않는다.
    static void recursive(EggInfo[] tempEgg,int start) {



        //계란이 몇개 깨졌는지 업데이트
        maxValue = Integer.max(maxValue,brokenEggCheck(tempEgg));


        // 최근에 집어든 계란이 가장 오른쪽 계란이라면
        if(start == N) {
            return;
        }

        // 들고있는 계란이 깨졌다면 패스
        if(tempEgg[start].hp <= 0) {
            recursive(tempEgg, start+1);
            return;
        }

        //반복문을 돌면서 계란을 하나씩 침
        for(int i = 0 ; i < N; i++) {

            // 치려고 하는 계란이 시작계란이 아니고, 계란이 깨지지 않았다면
            if(i != start && tempEgg[i].hp > 0) {

                //배열을 복사하고 복사한 배열에 값 업데이트 하고 넘김
                EggInfo[] tmp = copyArrays(tempEgg);

//                System.out.println(Arrays.toString(tempEgg));

                tmp[i].hp -= tmp[start].weight;
                tmp[start].hp -= tmp[i].weight;

                recursive(tmp,start+1);

            }
        }

    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= null;
        N = Integer.parseInt(br.readLine());

        EggInfo[] eggArrays = new EggInfo[N];

        for(int i = 0 ; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            eggArrays[i] = new EggInfo(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        maxValue = Integer.MIN_VALUE;

        recursive(eggArrays, 0);
        System.out.println(maxValue);

    }

    static class EggInfo{
        int hp;
        int weight;

        EggInfo(int hp, int weight){
            this.hp = hp;
            this.weight = weight;
        }

//        @Override
//        public String toString() {
//            return "hp : " + hp + ", weight : " + weight;
//        }
    }

}