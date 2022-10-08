package baekjoon.baek20221008;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 8.
 */

/*
1.번호가 xi의 배수인 원판을 d방향으로 k칸 회전시킨다. d가 0인 경우는 시계 방향, 1인 경우는 반시계 방향이다.
2.원판에 수가 남아 있으면, 인접하면서 수가 같은 것을 모두 찾는다.
3.그러한 수가 있는 경우에는 원판에서 인접하면서 같은 수를 모두 지운다.
4.없는 경우에는 원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다.
 */

public class BOJ17822_원판_돌리기 {

    static int N,M,T;
    static int[][] disk;
    static int[] startIdx;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static void print(){

        for(int i = 1; i <= N; i++){
            System.out.println(Arrays.toString(disk[i]));
        }

        System.out.println("---------------");
    }

    //원판 돌리기 - 원판을 선택해서 해당 원판을 돌림.
    static void rotateDisk(int diskNum,int d, int k){

        //시계방향
        if(d == 0){
            startIdx[diskNum] = 1+k;
        }
        //반시계방향
       else{
            startIdx[diskNum] = M + (1-k);
        }
    }

    static void rotateDiskUpdate(){

        for(int i = 1; i <= N; i++){

            //1이 아닌것 즉, 회전이 일어난 것을 선택.
            if(startIdx[i] != 1){
                int[] temp = new int[M+1];
                int count = startIdx[i];
                for(int j = 1; j <= M; j++){
                    temp[count] = disk[i][j];
                    count++;
                    if(count > M) count = 1;
                }

                //회전된 상태 구해서 업데이트.
                disk[i] = temp;
            }
        }
    }

    //원판에 수가 남아있는지 찾기 - 지운 수는 -2로 표시
    static boolean check(){

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= M; j++){
                if(disk[i][j] != -2) return true;
            }
        }

        return false;

    }

    //인접한 수 지우기 - 삭제할 위치 확인해서 배열에 저장하고 한번에 삭제해야됨.
    static boolean remove(){

        boolean flag = false;
        boolean[][] visited = new boolean[N+1][M+1];

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= M; j++){

                int stdNum = disk[i][j];

                //삭제된 숫자가 아니면 사방탐색
                if(disk[i][j] != -2){
                    boolean check = false; //인접한 숫자가 있었는지 확인.
                    for(int dir = 0; dir < 4; dir++){
                        int nextX = i + dx[dir];
                        int nextY = j + dy[dir];

                        if(nextX >= 1 && nextX <= N){
                            if(nextY < 1) nextY = M;
                            else if(nextY > M) nextY = 1;

                            if(stdNum == disk[nextX][nextY]){
                                visited[nextX][nextY] = true;
                                flag = true;
                                check = true;
                            }

                        }
                    }
                    //인접한 것이 있었다면 본인삭제
                    if(check) visited[i][j] = true;
                }
            }
        }

        //삭제
        if(flag){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= M; j++){
                    if(visited[i][j]){
                        disk[i][j] = -2;
                    }
                }
            }
        }

        return flag;
    }

    //원판의 평균 구하기.
    static double avgNum(){

        int totalValue = 0;
        int totalCount = 0;

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= M; j++){
                if(disk[i][j] != -2){
                    totalCount++;
                    totalValue += disk[i][j];
                }
            }
        }

        return (double) totalValue / (double) totalCount;
    }

    //평균을 기준으로 값 업데이트 하기.
    static void update(){

        double avg = avgNum();

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= M; j++){
                if(disk[i][j] != -2){

                    //평균보다 작으면 +1
                    if(avg > disk[i][j]){
                        disk[i][j]++;
                    }
                    //평균보다 크면 -1;
                    else if(avg < disk[i][j]){
                        disk[i][j]--;
                    }
                }
            }
        }

    }

    //원판에 적힌 수 구하기.
    static int totalValue(){

        int total = 0;
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= M; j++){
                if(disk[i][j] != -2) total += disk[i][j];
            }
        }
        return total;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //원판수
        M = Integer.parseInt(st.nextToken()); //원판에 적힌 수
        T = Integer.parseInt(st.nextToken()); // 회전 수

        disk = new int[N+1][M+1]; //원판을 나타내는 배열
        startIdx = new int[N+1]; // 각 원판의 숫자의 시작 인덱스

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j= 1; j <= M; j++){
                disk[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //T번 회전
        for(int i = 0; i < T; i++){

            print();

            Arrays.fill(startIdx, 1);
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); //x의 배수인 원판 선택
            int d = Integer.parseInt(st.nextToken()); //회전 방향
            int k = Integer.parseInt(st.nextToken()); // 회전수

            //x의 배수에 해당하는 원판을 돌림.
            for(int idx = 1; idx*x <= N; idx++){
                rotateDisk(idx*x,d,k);
            }
            //배열회전이 끝나면 원판 업데이트.
            rotateDiskUpdate();

            print();

            //숫자가 남아있는지 확인.
            if(check()){
                //남아있다면 인접한수 찾아서 지우기
                //인접하면서 수가 같은 경우가 하나도 존재하지 않음.
                if(!remove()){
                    update(); //숫자 업데이트
                }
            }
            print();
        }

        System.out.println(totalValue());

    }

    static class Node{
        int x, y;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }

    }
}
