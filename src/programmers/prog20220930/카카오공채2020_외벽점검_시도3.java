package programmers.prog20220930;

import programmers.prog20220922.카카오공채2020_외벽점검_시간초과2;

import java.util.Arrays;

public class 카카오공채2020_외벽점검_시도3 {

    //시계, 반시계
    static int[] dirArray = {1,-1};
    static int[] weakArray;
    static int[] distArray;
    static int result; //최소 친구 수 카운트

    static final int INF = Integer.MAX_VALUE;

    static boolean[] peopleCheck; //사람이 배치되었는지 확인.


    //최대 이동거리만큼 이동시킴 - 1인것이 있으면 2로 만들어버림.
    static int move(int[] visited,int peopleNum, int dir, int start){

        int count = 0;


        //두가지 방향이 존재
        for(int j = 0; j <= distArray[peopleNum]; j++ ){
            if(visited[start] == 1){
                visited[start] = 2;
                count++;
            }
            start += dirArray[dir];
            if(start < 0) start = visited.length - 1;
            else if(start >= visited.length) start = 0;

        }

        //몇개를 바꿨는지 리턴
        return count;

    }

    static int[] arrayCopy(int[] visited){

        int[] returnArray = new int[visited.length];

        System.arraycopy(visited,0,returnArray,0,visited.length);

        return returnArray;
    }

    //idx는 사람.,
    static void recursive(int idx,int[] visited, int friendCount, int weakCount){

        //이미 구해놓은 것보다 더 크다면 필요없음
        if(result <= friendCount){
            return;
        }

        //모든 취약점을 전부 커버했는지 확인.
        if(weakCount >= weakArray.length){

            result = Math.min(result,friendCount);
            return;
        }

        //모든 친구를 전부 배치 했다면 리턴
        if(friendCount == distArray.length) return;

        if(idx < 0) return;

        /*logic*/
        // 사람을 취약지점에 배치
        //사람을 하나 뽑고 배치할 취약지점을 뽑음.
        int[] tempVisited = arrayCopy(visited);
        //해당 사람을 선택하지 않는  경우
        recursive(idx-1, tempVisited, friendCount,weakCount);

        //해당 사람을 선택하는 경우 - 시계 방향과 반시계중에 좀 더 많이 방문처리가 가능한 쪽으로


        for(int i = 0; i < weakArray.length; i++){
            //아직 점검하지 않은 곳이라면 그곳 부터 시작함.
            if(visited[weakArray[i]] == 1){
                int start = weakArray[i];

                int[] tempVisited1 = arrayCopy(visited);
                int[] tempVisited2 = arrayCopy(visited);

                int count1 = move(tempVisited1,idx, 0, weakArray[i]); //시계방향일떄
                int count2 = move(tempVisited2,idx, 1, weakArray[i]); //반시계방향일때.

                if(count1 >= count2){
                    recursive(idx-1, tempVisited1, friendCount+1,weakCount+count1);
                }
                else{
                    recursive(idx-1, tempVisited2, friendCount+1,weakCount+count2);
                }
            }
        }


    }

    public int solution(int n, int[] weak, int[] dist) {
        int answer = 0;


        Arrays.sort(dist);

        weakArray = weak;
        distArray = dist;
        peopleCheck = new boolean[dist.length];
        result = INF;

        int[] visited = new int[weak.length];

        for(int i = 0; i < weak.length; i++){
            visited[i] = 0;
        }

        recursive(distArray.length-1,visited,0,0);

        if(result == INF) result = -1;

        return result;
    }

    public static void main(String[] args) {
        카카오공채2020_외벽점검_시도3 tt = new 카카오공채2020_외벽점검_시도3();

        int n1 = 12;
        int[] weak1 = {1, 5, 6, 10};
        int [] dis1 = {1, 2, 3, 4};
        System.out.println(tt.solution(n1,weak1,dis1));

        int n2 = 12;
        int[] weak2 = {1, 3, 4, 9, 10};
        int [] dis2 = {3, 5, 7};
        System.out.println(tt.solution(n2,weak2,dis2));


    }
}
