package baekjoon.baek20220803;



/**
 * 정올 1681 해밀턴 순환회로
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class First {

    static int[][] map;
    static int N;
    static int minValue = Integer.MAX_VALUE;
    //방문했던 곳인지 체크
    static boolean[] check;

    //출발지,누적된 비용, 인덱스를 인자로 받음
    //인덱스는 모든 노드를 방문했는지 확인하기 위함 - N-1개를 방문하면 회사를 제외하고 모두 방문한 것.
    static void recursive(int startNode,int value, int index){


        /**기저 조건**/
        //만약 비용이 현재 저장된 최소비용보다 더 크면 볼필요 없음 - 가장 작은 경로를 구하는 것이므로.(백트래킹의 가지치기)
        if(minValue < value){
            return;
        }
        //다 방문했으면 누적된 값+ 해당 startNode파라미터에서 회사까지의 경로를 더해서 최소비용을 업데이트
        if(index == N-1){

            if(map[startNode][1] != 0){
                minValue = Math.min(minValue,value + map[startNode][1]);
            }

            return;
        }

        /**로직**/
        //1은 회사이므로 2부터 돌림
        for(int i = 2; i <=N; i++){
            //방문안했으면 방문처리하고 재귀호출

            if(map[startNode][i] != 0 && !check[i]){
                check[i] = true;
                recursive(i,map[startNode][i] + value,index+1);
                //다음 탐색을 위해 원복
                check[i] = false;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //배달해야되는 장소의 수
        N = Integer.parseInt(br.readLine().trim());

        //방문처리배열 초기화
        check = new boolean[N+1];

        //인덱스를 1번부터 쓰기 위함
        map = new int[N+1][N+1];

        //장소까지 가는데 걸리는 비용
        StringTokenizer st = null;
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        recursive(1,0,0);

        System.out.println(minValue);

    }
}
