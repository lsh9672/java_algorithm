package baek20220810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ16935 {

    static int N;
    static int M;
    static int[][] maps;


    static void rotate(int num) {
        //빈 배열
        int[][] tempMaps = null;

        // 상하 반전
        if(num == 1) {
            tempMaps = new int[N][M];
            for(int i = N-1; i >= 0; i--) {
                tempMaps[(N-1)- i] = maps[i];
            }
        }
        // 좌우 반전
        else if(num == 2) {
            tempMaps = new int[N][M];
            for(int j = 0; j < M; j++) {
                for(int i = 0; i < N; i++) {
                    tempMaps[i][j] = maps[i][M-1-j];
                }
            }
        }
        //오른쪽으로 90도 회전
        else if(num == 3) {
            tempMaps = new int[M][N];
            for(int j = 0; j < M; j++){
                for(int i = N-1 ; i >= 0; i--) {
                    tempMaps[j][(N-1)- i] = maps[i][j];
                }
            }
            int tmp = N;
            N = M;
            M = tmp;
        }
        //왼쪽으로 90도 회전
        else if(num == 4) {
            tempMaps = new int[M][N];
            for(int j = M-1; j >= 0; j--) {
                for(int i = 0; i < N; i++) {
                    tempMaps[(M-1) - j][i] = maps[i][j];
                }
            }

            int tmp = N;
            N = M;
            M = tmp;

        }
        //1->2, 2 -> 3, 3 -> 4, 4 ->1
        else if(num == 5) {
            tempMaps = new int[N][M];
            //1->2
            for(int i = 0; i < N/2; i++) {
                for(int j = 0; j < M/2; j++) {
                    tempMaps[i][j+M/2] = maps[i][j];
                }
            }

            //2->3
            for(int i = 0; i < N/2; i++) {
                for(int j = M/2; j < M; j++) {
                    tempMaps[i+N/2][j] = maps[i][j];
                }
            }

            //3->4
            for(int i = N/2; i < N; i++) {
                for(int j = M/2; j<M; j++) {
                    tempMaps[i][j - M/2] = maps[i][j];
                }
            }

            //4->1
            for(int i = N/2; i < N; i++) {
                for(int j = 0; j< M/2; j++) {
                    tempMaps[i - N/2][j] = maps[i][j];
                }
            }
        }
        else if(num == 6) {
            tempMaps = new int[N][M];
            //1->4
            for(int i = 0; i < N/2; i++) {
                for(int j = 0; j < M/2; j++) {
                    tempMaps[i+N/2][j] = maps[i][j];
                }
            }

            //4->3
            for(int i = N/2; i < N; i++) {
                for(int j = 0; j < M/2; j++) {
                    tempMaps[i][j + M/2] = maps[i][j];
                }
            }

            //3->2
            for(int i = N/2; i < N; i++) {
                for(int j = M/2; j<M; j++) {
                    tempMaps[i - N/2][j] = maps[i][j];
                }
            }

            //2->1
            for(int i = 0; i < N/2; i++) {
                for(int j = M/2; j< M; j++) {
                    tempMaps[i][j- M/2] = maps[i][j];
                }
            }
        }


        maps = tempMaps;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 연산횟수
        int R = Integer.parseInt(st.nextToken());

        maps = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int rotateNum = 0;
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < R; i++) {
            rotateNum = Integer.parseInt(st.nextToken());
            rotate(rotateNum);
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                sb.append(maps[i][j]).append(" ");
            }

            sb.setLength(sb.length()-1);
            sb.append("\n");
        }

        sb.setLength(sb.length()-1);
        System.out.println(sb);
    }

}