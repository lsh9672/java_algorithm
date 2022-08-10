package baek20220810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ16926 {

    static int N;
    static int M;

    static int[][] maps;

    // 우,하,좌,상
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    // 배열을 회전시키는 메서드 - 한칸씩 밀기
    static void rotate() {

        // 돌려야 되는 배열 그룹의 수 => MIN(N,M)/2
        // 각 그룹의 시작지점은 (0,0) ->(1,1) -> (2,2)

        for(int i = 0; i < Math.min(N,M)/2; i++) {

            int currentDir = 0;

            int currentX = i;
            int currentY = i;

            //시작값을 저장해뒀다가 마지막에 추가해줌.
            int tempValue = maps[currentX][currentY];


            while(true) {
                int nextX = currentX + dx[currentDir];
                int nextY = currentY + dy[currentDir];

                //범위를 벗어났으면 방향전환.
                if(nextX < i || nextX >= N-i || nextY < i || nextY >= M-i) {
                    currentDir = currentDir+1;
                }
                else {
//					System.out.println("currentX : " + currentX + " currentY : " + currentY + " nextX : " + nextX + " nextY : " + nextY);
                    maps[currentX][currentY] = maps[nextX][nextY];
                    currentX = nextX;
                    currentY = nextY;
                }

                //방향전환이 4번 일어났으면 종료
                if(currentDir == 4) break;
            }
            // 다 돌았으면 마지막꺼 업데이트
            maps[i+1][i] = tempValue;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        maps = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //R번 회전
        for(int i = 0; i < R; i++) {

            rotate();
        }

        //회전이 끝나면 출력
        StringBuilder result = new StringBuilder();
        for(int i = 0 ; i < N; i++) {
            for(int j = 0; j < M; j++) {
                result.append(maps[i][j]).append(" ");
            }
            result.setLength(result.length()-1);
            result.append("\n");
        }

        result.setLength(result.length()-1);
        System.out.println(result);

    }
}
