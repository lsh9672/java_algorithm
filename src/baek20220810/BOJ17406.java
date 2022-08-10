package baek20220810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17406 {

    static int N;
    static int M;
    static int[][] maps;
    static boolean[] check;
    static RotateInfo[] info;
    // 회전 연산수
    static int K;

    //최종출력할 최소값.
    static int result;

    //하, 우, 상, 좌
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};

    // 배열 회전 정보를 받아 회전시킴.
    static void rotate(int[][] tempMaps,RotateInfo temp) {
        int startX = temp.r - temp.s -1;
        int startY = temp.c - temp.s -1;
        int endX = temp.r + temp.s -1;
        int endY = temp.c + temp.s -1;

        for(int i = 0; i < Math.min(endX - startX, endY-startY)/2 ; i++) {
            int currentDir =0;

            int currentX = startX + i;
            int currentY = startY + i;

            int tempValue = tempMaps[currentX][currentY];

            while(currentDir < 4) {
                int nextX = currentX + dx[currentDir];
                int nextY = currentY + dy[currentDir];

                //다음좌표가 공간을 벗어났다면 방향전환
                if(nextX < startX + i || nextX > endX - i || nextY < startY+i || nextY > endY-i){
                    currentDir++;
                }
                //벗어나지 않았다면 배열 돌리기
                else{
                    tempMaps[currentX][currentY] = tempMaps[nextX][nextY];
                    currentX = nextX;
                    currentY = nextY;
                }
            }

            //종료되면 처음에 저장한 값 배열에 놓기
            tempMaps[startX+i][startY+i+1] = tempValue;

        }

    }

    // 각 배열의 값을 구하는 메서드.
    static int arrayValue(int[][] tempMaps) {
        int tempCount = Integer.MAX_VALUE;

        for(int i = 0; i < N; i++) {
            // 각 행의 값 저장.
            int temp = 0;
            for(int j = 0; j < M; j++) {
                temp += tempMaps[i][j];
            }

            // 각 행의 값중 최소값구하기.
            tempCount = Math.min(tempCount, temp);
        }

        return tempCount;
    }

    // 재귀시에 영향을 안미치도록 배열을 복사하는 메서드.
    static int[][] copyArrays(int[][] tempMaps) {

        int[][] returnMaps = new int[N][M];

        for(int i = 0 ; i< N; i++) {
            System.arraycopy(tempMaps[i], 0, returnMaps[i], 0, M);
        }

        return returnMaps;
    }

    //재귀 호출 - 순열로 경우의 수를 뽑아내면서 배열을 회전.
    static void recursive(int[][] tempMaps, int idx) {

        //K번 연산 했으면
        if(idx >= K) {
            // 목표에 도달했으면 최소값 구하기.
            result = Math.min(result, arrayValue(tempMaps));

            return;
        }

        //경우의 수 구하기.
        for(int i = 0; i < K; i++) {
            if(!check[i]) {
                // 배열 복사하고,
                int[][] tMaps = copyArrays(tempMaps);
                // 방문 체크하고,
                check[i] = true;
                //배열 회전
                rotate(tMaps, info[i]);
//                for(int[] temp: tMaps){
//                    System.out.println(Arrays.toString(temp));
//                }
//                System.out.println("-----------");
                recursive(tMaps,idx+1);
                check[i] = false;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //회전 연산의 개수
        K = Integer.parseInt(st.nextToken());

        maps = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        info = new RotateInfo[K];

        // 회전 연산의 정보
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int tempR = Integer.parseInt(st.nextToken());
            int tempC = Integer.parseInt(st.nextToken());
            int tempS = Integer.parseInt(st.nextToken());
            info[i] = new RotateInfo(tempR,tempC,tempS);
        }

        check = new boolean[K];
        result = Integer.MAX_VALUE;

//        for(int[] temp: maps){
//            System.out.println(Arrays.toString(temp));
//        }
//        System.out.println("-----------");

        recursive(maps,0);

        System.out.println(result);

    }

    static class RotateInfo{
        int r;
        int c;
        int s;

        RotateInfo(int r, int c, int s){
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

}
