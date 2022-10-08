package baekjoon.baek20221008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 8.
 */

/*
1. 가로는 왼쪽에서 오른쪽으로 세로는 위에서 아래로 내려가는 것으로 생각한다.
2. 한 행 또는 한 열씩 확인한다.
3. 한칸씩 선택하고 다음칸을 확인한다.
4. 경사차이가 1 또는 -1 이면 경사로를 고려한다
5. 경사로 고려시에는 오르막길, 내리막길에 따라서 앞순서로 L칸 뒤로 L 칸만큼 이동하면서 체크한다.
6. 체크시에는 한칸씩 이동해보면서 주어진 map크기를 벗어나지는 않았는지, 경사로가 놓아져있는지 여부, 모든 높이가 동일한지를 체크해야 한다.
 */

public class BOJ14890_경사로 {

    static int N;
    static int L;
    static int[][] maps;


    //경사로 구하기 - num은 행또는 열 번호, type이 0이면 행, 1이면 열
    static int rampCheck(int num, int type){

        boolean[] visited = new boolean[N];


        //칸 하나 선택
        for(int i = 0; i < N-1; i++){


            //행 고정
            if(type == 0){


                //현재 노드와 다음 노드를 비교.
                int gap = maps[num][i] - maps[num][i+1];

                //높이차가 2이상 이면
                if(gap <= -2 || gap >= 2){
                    return 0;
                }
                // 높이차가 -1이면 오르막길
                else if(gap == -1){
                    //현재 위치에서부터 뒤로 L+1만큼의 칸이 전부 동일한 높이이고 경사로를 놓지 않았는지 확인.
                    for(int idx = i; idx > i - L; idx--){

                        //해당위치가 범위를 벗어나거나, 이미 경사로가 놓여있거나, 높이가 다르다면 종료
                        if(idx < 0 || visited[idx] || maps[num][idx] != maps[num][i]){
                            return 0;
                        }

                        //위의 조건에 안걸렸다면 경사로 체크
                        visited[idx] = true;
                    }
                }
                //1이면 내리막길
                else if(gap == 1){
                    //앞으로 L칸 확인
                    for(int idx = i+1; idx <= i+L; idx++){

                        //오르막길과 마찬가지로 조건을 벗어나면 종료
                        if(idx >= N || visited[idx] || maps[num][idx] != maps[num][i+1]){
                            return 0;
                        }

                        visited[idx] = true;
                    }

                }

            }


            //열 고정
            else{
                //현재 노드와 다음 노드를 비교.
                int gap = maps[i][num] - maps[i+1][num];

                //높이차가 2이상 이면
                if(gap <= -2 || gap >= 2){
                    return 0;
                }
                // 높이차가 -1이면 오르막길
                else if(gap == -1){
                    //현재 위치에서부터 뒤로 L+1만큼의 칸이 전부 동일한 높이이고 경사로를 놓지 않았는지 확인.
                    for(int idx = i; idx > i - L; idx--){

                        //해당위치가 범위를 벗어나거나, 이미 경사로가 놓여있거나, 높이가 다르다면 종료
                        if(idx < 0 || visited[idx] || maps[idx][num] != maps[i][num]){
                            return 0;
                        }

                        //위의 조건에 안걸렸다면 경사로 체크
                        visited[idx] = true;
                    }
                }
                //1이면 내리막길
                else if(gap == 1){
                    //앞으로 L칸 확인
                    for(int idx = i+1; idx <= i+L; idx++){

                        //오르막길과 마찬가지로 조건을 벗어나면 종료
                        if(idx >= N || visited[idx] || maps[idx][num] != maps[i+1][num]){
                            return 0;
                        }

                        visited[idx] = true;
                    }

                }

            }

        }

        return 1;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //지도 크기
        L = Integer.parseInt(st.nextToken()); //경사로의 길이

        maps = new int[N][N];


        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int total = 0;

        //행과 열에 대해서 각각 처리
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < N; j++){

                total += rampCheck(j,i);
            }
        }

        System.out.println(total);
    }
}
