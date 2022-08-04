package baek20220803;

/**
 * 백준 17471번
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;


public class Second {

    static int N;
    static int[][] map;
    static int[] peopleNum;

    static int[] teams;

    //모든 선거구 인원수의 총 합을 구해둠.
    static int totalSum;

    static int minGapValue = Integer.MAX_VALUE;

    //각 선거구가 전부 연결되어 있는지 확인.(bfs탐색으로 찾음)
    static boolean bfs(int teamNum){
        int[] visited = new int[N+1];
        //만약 팀 번호가 0이면 전부 1로 채우고 시작. - 구분을 위함.(초기값이 0이라 탐색하지 않아도 0인것을 방지)
        if(teamNum == 0) Arrays.fill(visited,1);

        Queue<Integer> needVisited = new ArrayDeque<>();

        //해당팀의 시작노드 하나 찾기
        for(int i = 1; i <= N; i++) {
            if (teams[i] == teamNum) {
                visited[i] = teamNum;
                needVisited.add(i);
                break;
            }
        }

        while(!needVisited.isEmpty()){

            Integer currentNode = needVisited.poll();

            for(int i = 1; i <= N; i++){
                if(map[currentNode][i] == 1 && teams[i] == teamNum && visited[i] != teamNum){
                    visited[i] = teamNum;
                    needVisited.add(i);
                }
            }
        }

        //해당 팀번호에 소속된 팀으로 방문배열을 탐색하면서 모든 팀이 연결되었는지 확인
        for(int i = 1; i <= N; i++){
            if(teams[i] == teamNum){
                if(visited[i] != teamNum){
                    return false;
                }
            }
        }

        return true;
    }

    //두 팀의 최소 차이를 구함. - 차이를 구해서 minGapValue에 저장함. => bfs() 함수를 돌려서 확인통과 되면 함.
    static void minGap(){
        //팀1의 선거구 인원을 뽑아냄
        int team1Total = 0;
        for(int i = 1; i <= N; i++){
            if(teams[i] == 1){
                team1Total += peopleNum[i];
            }
        }

        int team2Total = totalSum - team1Total;

        minGapValue = Math.min(minGapValue,Math.abs(team1Total- team2Total));

    }

    //선거구 구하기
    static void recursive(int teamNum){

        //basis part - teamNum이 팀 수인 N을 넘어가면 리턴
        //두팀을 다 구했다면 계산 - 팀이 연결되어 있는지 확인하고 연결되어 있으면 두 팀의 차이값을 구함.
        if(teamNum > N){
            //두팀이 모두 연결되어 있는지 확인

            if(bfs(0) && bfs(1)){
                minGap();
            }

            return;
        }

        /*각각 해당 번호를 선택하는 경우와 선택하지 않는 경우*/
        //해당 teamNum을 선택 - 1번팀
        teams[teamNum] = 1;
        recursive(teamNum+1);

        //해당 teamNum을 선택하지 않는 경우 - 0으로 표시 (0번 팀)
        teams[teamNum] = 0;
        recursive(teamNum+1);

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        //구역의 인구
        peopleNum  = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            peopleNum[i] = Integer.parseInt(st.nextToken());
        }

        //지역
        map = new int[N+1][N+1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            int tempNum = Integer.parseInt(st.nextToken());

            for(int j = 0; j < tempNum; j++){
                int nextNode = Integer.parseInt(st.nextToken());
                map[i][nextNode] = 1;
                map[nextNode][i] = 1;
            }

        }

        //지역구를 나눠서 넣을 배열 초기화.
        //1번과 0번으로 나눔
        teams = new int[N+1];

        //두 팀의 인원을 각각 구할때 구하기 편하게 총합을 미리 구해둠.
        for(int i = 1; i <=N; i++){
            totalSum += peopleNum[i];
        }

        recursive(1);

        if(minGapValue == Integer.MAX_VALUE){
            System.out.println(-1);
        }
        else{
            System.out.println(minGapValue);
        }

    }
}
