package swea.sw20220828;

import java.io.*;
import java.util.*;

/**
 * 맨해튼 거리를 이용해서 구함.
 */

public class Solution2 {

    static List<Location> monsterArray;
    static List<Location> clientArray;

    static int N;
    static int result;//최단거리.
    static int[][] maps;

    static boolean[] monsterVisited;
    static boolean[] clientVisited;

    //문제에 경로상에 벽을 주지 않았기 때문에 맨해튼 거리를 이용해서 쉽게 거리르 구함.
    static int distance(Location startNode, Location endNode) {
        return Math.abs(startNode.x - endNode.x) + Math.abs(startNode.y - endNode.y);
    }

    //value에 거리를 저장.
    static void recursive(int value, int count, Location startLoc){

        //구한 거리가 기존의 최단거리보다 크다면 종료
        if(value >= result){
            return;
        }

        // 몬스터+ 클라이언트 이므로 두배.
        if(count >= monsterArray.size()*2){
            //목적지에 도달하면 최단거리 업데이트
            result = Math.min(result, value);
            return;
        }

        //몬스터먼저 선택하고
        for(Location tempMonster : monsterArray){
            //해당 몬스터를 방문하지 않았다면,
            if(!monsterVisited[tempMonster.num]){
                monsterVisited[tempMonster.num] = true;
                int tempDis = distance(startLoc, tempMonster); //거리를 구하고
                recursive(value + tempDis, count+1,new Location(tempMonster.x, tempMonster.y)); //재귀호출
                monsterVisited[tempMonster.num] = false;
            }
        }

        //클라이언트 선택함.
        for(Location tempClient: clientArray){
            //클라이언트의 경우 해당 번호의 몬스터가 방문되었는지 확인해야됨
            if(monsterVisited[tempClient.num] && !clientVisited[tempClient.num]){
                clientVisited[tempClient.num] = true;
                int tempDis = distance(startLoc, tempClient); //거리를 구하고
                recursive(value + tempDis, count+1,new Location(tempClient.x, tempClient.y)); //재귀호출
                clientVisited[tempClient.num] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/swea/sw20220828/sample_input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        for(int testCase = 1; testCase <= T; testCase++){
            N = Integer.parseInt(br.readLine());

            result = Integer.MAX_VALUE;

            maps = new int[N][N];
            //지도 입력받기
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    maps[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            monsterArray = new ArrayList<>();
            clientArray = new ArrayList<>();



            for(int i= 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(maps[i][j] != 0){
                        //몬스터
                        if(maps[i][j] > 0){
                            monsterArray.add(new Location(i,j,maps[i][j]));
                        }
                        //고객
                        else{
                            clientArray.add(new Location(i,j, (-1)*maps[i][j]));
                        }
                    }
                }
            }
            monsterVisited = new boolean[monsterArray.size()+1];
            clientVisited = new boolean[clientArray.size()+1];


            recursive(0,0,new Location(0,0));
            System.out.println("#"+testCase + " " + result);
        }
    }

    static class Location{
        int x, y, num;

        Location(int x, int y, int num){
            this.x = x;
            this.y = y;
            this.num = num;
        }

        Location(int x, int y){
            this.x = x;
            this.y = y;
            this.num = 0;
        }
    }
}
