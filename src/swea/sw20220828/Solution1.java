package swea.sw20220828;

/**
 * A형 대비 기출문제
 */

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution1 {

    static int N;
    static int resultDistance;

    static int[][] dx = {{-1,1},{1,-1}};
    static boolean[] check;
    static GateInfo[] order;

    static boolean[] arrayCopy(boolean[] visited){
        boolean[] returnArray = new boolean[N+1];

        System.arraycopy(visited,0,returnArray,0,N+1);

        return returnArray;

    }

    //방문처리 배열과, 시작 위치, 사람수를 받아서 사람 배치후, 이동거리 리턴
    static int setLoc(boolean[] visited, int start, int peopleNum, int dir){

        int totalDistance = 0; //총 이동거리.

        int idxCount = 0;

        loop:
        while(true){
            for(int i = 0; i < 2; i++){
                int nextLoc = start + dx[dir][i]*idxCount;

                if(nextLoc >= 1 && nextLoc <= N && !visited[nextLoc]){
                    visited[nextLoc] = true;
                    totalDistance += idxCount+1;
                    peopleNum--;
                }

                if(peopleNum == 0){
                    break loop;
                }
            }

            idxCount++;
        }
        return totalDistance;
    }

    static void recursive(int count, boolean[] visited, int value){

        //현재까지 누적된 값이 현재 최소값을 넘어섰다면,
        if(value > resultDistance){
            return;
        }

        if(count >= 3){
            resultDistance = Integer.min(value,resultDistance);
            return;
        }

        for(int i = 0; i < 3; i++){
//            System.out.println("test4");
            //방문하지 않은 배열이라면,
            if(!check[i]){
                //왼쪽 오른쪽 순서로 놓을때랑, 오른쪽 왼쪽 순서로 놓을때 두가지 경우를 따져야됨
                for(int dir = 0; dir < 2; dir++){
                    check[i] = true;
                    boolean[] temp = arrayCopy(visited);

                    int tempDistance = setLoc(temp, order[i].startLoc, order[i].peopleCount,dir);

                    recursive(count+1,temp,value + tempDistance);
                    check[i] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/swea/sw20220828/sample_input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;



        int T = Integer.parseInt(br.readLine());
//        T = 1;
        for(int testCase = 1; testCase <= T; testCase++){

            resultDistance = Integer.MAX_VALUE;

            N = Integer.parseInt(br.readLine());


            order = new GateInfo[3];
            for(int i = 0; i < 3; i++){
                st = new StringTokenizer(br.readLine());
                order[i] = new GateInfo(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            }
            check = new boolean[3];


            //재귀로 입장순서에 따라서 사람 배치.
            recursive(0,new boolean[N+1], 0);

            System.out.println("#"+testCase+ " "+ resultDistance);
        }
    }

    static class GateInfo{
        int startLoc, peopleCount;
        GateInfo(int startLoc, int peopleCount){
            this.startLoc = startLoc;
            this.peopleCount = peopleCount;
        }
    }
}
