package baekjoon.baek20221010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 11.
 */

public class BOJ15686_치킨배달 {

    static int N,M;
    static int chickCount; //치킨집 수
    static int result; // 결과(최소값)
    static int[][] maps;

    static List<Node> houseList; //모든 집 정보

    static List<Node> chickenList; //모든 치킨 집 정보.

    static int[] chickenRoadArray; //각 집별 치킨거리 정보 저장.

    static void print(){
        for(int i= 0; i < N; i++){
            System.out.println(Arrays.toString(maps[i]));
        }
        System.out.println("-----------");
    }

    //도시의 치킨거리 구하기
    static int cityChickenRoad(){

        int total = 0;

        for(Node house : houseList){
            total += chickenRoad(house);
        }

        return total;
    }

    //집과 모든 치킨집과의 거리중 가장 작은 값(치킨거리)
   static int chickenRoad(Node house){
        int total = Integer.MAX_VALUE;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(maps[i][j] == 2){
                    total = Math.min(total,distance(house,new Node(i,j)));
                }
            }
        }

        return total;
    }

    static int distance(Node house, Node chicken){

        return Math.abs(house.x - chicken.x) + Math.abs(house.y - chicken.y);
    }

    static void recursive(int idx, int count){

        //M개만 남겼다면 도시의 치킨거리 구하기.
        if(count == chickCount - M){

            result = Math.min(result, cityChickenRoad());
            return;
        }

        if(idx >= chickCount){
            return;
        }



        //폐업시킬 치킨집 구하기.
        for(int i = idx; i < chickCount; i++){

            //해당 치킨집 폐업
            Node currentNode = chickenList.get(i);
            maps[currentNode.x][currentNode.y] = 0;

            recursive(i+1,count+1);
            maps[currentNode.x][currentNode.y] = 2;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][N];

        houseList = new ArrayList<>(); //집정보 담아두기.
        chickenList = new ArrayList<>();// 치킨집 정보 담아두기.
        
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());

                if(maps[i][j] == 1){
                    houseList.add(new Node(i,j));
                }
                else if(maps[i][j] == 2){
                    chickenList.add(new Node(i,j));
                }
            }
        }

        result = Integer.MAX_VALUE;
        chickCount = chickenList.size();
        recursive(0,0);

        System.out.println(result);
    }

    static class Node{
        int x,y;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
