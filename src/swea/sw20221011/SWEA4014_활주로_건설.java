package swea.sw20221011;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 11.
 */
public class SWEA4014_활주로_건설 {

    //활주로 탐색
    static int rampCheck(int[][] maps, int num, int M){

        int[] road = maps[num]; //한 행 꺼내기

        int idx = 0;//인덱스
        int size = 1;//높이가 같은 길이
        while(idx < maps.length-1){
//            System.out.println("idx : " + idx);
            //높이가 같으면 패스
            if(road[idx] == road[idx+1]){
                idx++;
                size++;
                continue;
            }

            //오르막길이면
            else if(road[idx] - road[idx+1] == -1){
                //지금까지 누적된 동일 길이가 M이상이면 가능
                if(size >= M){
                    size = 1;
                }
                else{
                    return 0;
                }
            }

            //내리막 길이면
            else if(road[idx] - road[idx+1] == 1){
//                System.out.println("idx : " + idx);

                //다음 위치부터 M칸이 전부 같은 높이인지 확인.
                for(int i = idx+1; i < idx+1 + M; i++){

                    if(i >= road.length) {
//                        System.out.println("test1");
                        return 0;
                    }

                    //높이가 하나라도 같지 않으면 종료
                    if(road[idx+1] != road[i]) {
//                        System.out.println("test2");
                        return 0;
                    }
                }

                if(idx+M+1 >= road.length) return 1;

                else if(road[idx+M] - road[idx+M+1] == 1){
                    idx = idx+M;
                    size = 1;
                    continue;
                }
                else if(road[idx+M] == road[idx+M+1]){
                    idx = idx+M+1;
                    size = 1;
                    continue;
                }
                else return 0;

//                System.out.println("test3");

            }

            // 경사로를 놓을 수 없다면 -> 높이가 2 이상이면 종료
            else return 0;

            idx++;
        }

        return 1;
    }


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/home/leesh/lsh/my_study/java_study/algorism_java/src/swea/sw20221011/sample_input (19).txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());

        for(int testCase = 1 ; testCase <= T; testCase++){
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken()); //맵크기
            int M = Integer.parseInt(st.nextToken()); //경사로 길이

            int[][] mapsCol = new int[N][N];
            int[][] mapsRow = new int[N][N];
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    int value = Integer.parseInt(st.nextToken());
                    mapsRow[i][j] = value;
                    mapsCol[j][i] = value;
                }
            }

            int result = 0;



            for(int i = 0 ; i < N; i++){

//                int temp = rampCheck(mapsCol,i,M);
//                if(temp == 1){
//                    System.out.println(i);
//                }



                result += rampCheck(mapsCol,i,M);
                result += rampCheck(mapsRow,i,M);
            }

            System.out.println("#"+testCase + " " + result);
        }


    }
}
