package programmers.mockExam.ex20220822;

import java.util.Arrays;

public class Solution3 {

    public int solution(int distance, int[][] scope, int[][] times) {
        int answer = -1;

        WatchManInfo[] infoArray = new WatchManInfo[scope.length];

        for(int i = 0; i < scope.length; i++){

            infoArray[i] = new WatchManInfo(Integer.min(scope[i][0],scope[i][1]),Integer.max(scope[i][0],scope[i][1]),times[i][0],times[i][1]);
        }
        //감시 영역이 앞선것 부터 확인하기 위해서.
        Arrays.sort(infoArray);

        for(WatchManInfo temp : infoArray){
            //감시 영역을 감시 사이클 첫 구간으로 땡겨옴.
            int watchCycle = (temp.workTime + temp.restTime);
            int count = (temp.scopeStart) / watchCycle;

            int tempStart = temp.scopeStart - watchCycle * count;
            int tempEnd = temp.scopeEnd - watchCycle * count;

            //그냥 무조건 걸리는 경우 - 사이클이 감시범위+1 한것보다 크거나 같다면.
            if(watchCycle <= (tempEnd - tempStart + 1)){
                if(tempStart <= temp.workTime){
                    answer = watchCycle * count + tempStart;
                    break;
                }
                //감시 범위가 한 싸이클 보다 크다면 사이클이 다시 돌아서 근무시간이 되기 때문에 걸림
                else{
                    answer = watchCycle * count + watchCycle + 1;
                    break;
                }

            }

            if(tempStart <= temp.workTime){
                answer = watchCycle * count + tempStart;
                break;
            }

            //감시 범위가 한 싸이클 보다 크다면 사이클이 다시 돌아서 근무시간이 되기 때문에 걸림
            if(tempEnd > watchCycle){
                answer = watchCycle * count + watchCycle + 1;
                break;
            }

        }

        if(answer == -1) return distance;

        return answer;
    }

    //감시병 정보
    static class WatchManInfo implements Comparable<WatchManInfo>{
        int scopeStart, scopeEnd, workTime, restTime;

        WatchManInfo(int scopeStart,int scopeEnd, int workTime, int restTime){
            this.scopeStart = scopeStart;
            this.scopeEnd = scopeEnd;
            this.workTime = workTime;
            this.restTime = restTime;
        }

        @Override
        public int compareTo(WatchManInfo o) {
            return this.scopeStart - o.scopeStart;
        }
    }

    public static void main(String[] args) {
        Solution3 s = new Solution3();

        int distance = 10;
        int[][] scope = {{3,4},{5,8}};
        int[][] times = {{2,5},{4,3}};

        System.out.println(s.solution(distance,scope,times));

        int distance1 = 12;
        int[][] scope1 = {{7, 8}, {4, 6}, {11, 10}};
        int[][] times1 = {{2, 2}, {2, 4}, {3, 3}};

        System.out.println(s.solution(distance1,scope1,times1));

    }

}
