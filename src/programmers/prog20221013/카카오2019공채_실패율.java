package programmers.prog20221013;


import java.util.Arrays;

/**
 * @author : sh Lee
 * @date : 22. 10. 13.
 */
public class 카카오2019공채_실패율 {
    public int[] solution(int N, int[] stages) {


        int[] clearNum = new int[N+2]; //해당 스테이지에 도달한 사람수
        int[] notClearNum = new int[N+1]; //스테이지에 도달했으나 아직 클리어하지 못한사람.

        for(int stage : stages){

            if(stage == N+1){
                clearNum[stage-1]++;
            }
            else{
                clearNum[stage]++;
                notClearNum[stage]++;
            }
        }

        //해당 스테이지에 도달한 사람 수 구하기
        for(int i = N-1; i > 0; i--){
            clearNum[i] += clearNum[i+1];
        }

        //실패율 구해서 저장.
        Info[] result = new Info[N];
        for(int i = 1; i <= N; i++){

            if(clearNum[i] == 0){
                result[i-1] = new Info(i,0.0);
            }
            else{
                result[i-1] = new Info(i,(double)notClearNum[i]/(double)clearNum[i]);
            }

        }

        Arrays.sort(result);

        int[] answer = new int[N];
        for(int i=  0; i < N; i++){
            answer[i] = result[i].index;
        }

        return answer;
    }

    public static void main(String[] args) {
        카카오2019공채_실패율 s = new 카카오2019공채_실패율();

        int N1 = 5;
        int[] stages1 = {2, 1, 2, 6, 2, 4, 3, 3};
        System.out.println(Arrays.toString(s.solution(N1,stages1)));

        int N2 = 4;
        int[] stages2 = {4,4,4,4,4};
        System.out.println(Arrays.toString(s.solution(N2,stages2)));
    }

    static class Info implements Comparable<Info>{
        int index;
        double fail;

        Info(int index,double fail){
            this.index = index;
            this.fail = fail;
        }

        @Override
        public int compareTo(Info o) {
            if(Double.compare(o.fail, this.fail) == 0) return this.index - o.index;
            return Double.compare(o.fail, this.fail);
        }
    }
}
