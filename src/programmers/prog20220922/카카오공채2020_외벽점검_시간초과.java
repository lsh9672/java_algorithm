package programmers.prog20220922;

/*아이디어*/
//1. 백트래킹으로 생각.
//2. 외벽의 길이 n만큼 배열을 만들고 취약지점을 설정함.
//3. 최소한의 친구로 취약지점을 탐색하기 위해서는 서로 경로가 겹치지 않아야 되고 또한 최대의 효율을 내기 위해서는 한쪽방향으로만 이동해야됨.
//4. 매번 취약지점에 배치가 될 때 마다 카운트를 함.
//5. 카운트가 취약지점의 수가 되면 모든 취약지점을 전부 커버한것이므로 투입된 친구의 수를 저장하고 바로 종료
//6. 친구의 수도 체크를 해서, 모든 친구가 다 투입이 되었는데도 모든 취약지점을 커버할 수 없다면 -1 저장하도록 함.

public class 카카오공채2020_외벽점검_시간초과 {

    //시계, 반시계
    static int[] dirArray = {1,-1};
    static int[] weakArray;
    static int[] distArray;
    static int result; //최소 친구 수 카운트

    static final int INF = Integer.MAX_VALUE;

    static boolean[] peopleCheck; //사람이 배치되었는지 확인.

    //최대 이동거리만큼 이동시킴 - 1인것이 있으면 2로 만들어버림.
    static int move(int[] visited,int peopleNum, int start, int dir){

        int count = 0;

        dir = dirArray[dir];

        for(int i = 0; i <= distArray[peopleNum]; i++){
            if(visited[start] == 1){
                visited[start] = 2;
                count++;
            }
            start += dir;
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

    //idx는 사람., visited는 외벽
    static void recursive(int[] visited, int friendCount, int weakCount){

        //이미 구해놓은 것보다 더 크다면 필요없음
        if(result <= friendCount){
            return;
        }

        //모든 취약점을 전부 커버했는지 확인.
        if(weakCount == weakArray.length){
            result = Math.min(result,friendCount);
            return;
        }

        //모든 친구를 전부 배치 했다면 리턴
        if(friendCount == distArray.length) return;

        /*logic*/
        // 사람을 취약지점에 배치
        //사람을 하나 뽑고 배치할 취약지점을 뽑음.
        for(int i= distArray.length - 1; i >= 0 ; i--){
            //해당 사람을 선택하지 않았다면 선택
            if(!peopleCheck[i]){
                peopleCheck[i] = true;

                for(int j = 0; j < weakArray.length; j++){
                    //현재 뽑은 취약지점.
                    if(visited[j] != 2){

                        /*시계, 반시계방향으로 최대 이동거리만큼 이동하는 경우.*/
                        //배치하고 나서도 시계, 반시계방향으로 움직이는 두가지 경우가 있음.
                        for(int dir = 0; dir < 2; dir++){

                            //배열 복사해서 취약지점 체크
                            int[] tempVisited = arrayCopy(visited);

                            // 이동 가능한 위치까지 이동.
                            int tempCount = move(tempVisited, i, weakArray[j], dir);

                            //재귀 호출
                            recursive(tempVisited,friendCount+1,weakCount+tempCount);
                        }
                    }
                }

                peopleCheck[i] = false;
            }
        }



    }

    public int solution(int n, int[] weak, int[] dist) {
        int answer = 0;

        weakArray = weak;
        distArray = dist;
        peopleCheck = new boolean[dist.length];
        result = INF;

        int[] visited = new int[n];

        for(int index : weak){
            visited[index] = 1; //취약지점을 1로 표시하고 이미 방문한 곳이라면 2로 표시.
        }

        recursive(visited,0,0);

        if(result == INF) result = -1;

        return result;
    }

    public static void main(String[] args) {
        카카오공채2020_외벽점검_시간초과 tt = new 카카오공채2020_외벽점검_시간초과();

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
